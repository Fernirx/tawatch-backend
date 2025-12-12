package vn.fernirx.tawatch.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import vn.fernirx.tawatch.common.exception.ResourceAlreadyExistsException;
import vn.fernirx.tawatch.common.exception.ResourceNotFoundException;
import vn.fernirx.tawatch.infrastructure.storage.CloudinaryService;
import vn.fernirx.tawatch.user.dto.request.ProfileRequest;
import vn.fernirx.tawatch.user.dto.response.ProfileResponse;
import vn.fernirx.tawatch.user.entity.User;
import vn.fernirx.tawatch.user.entity.UserProfile;
import vn.fernirx.tawatch.user.mapper.ProfileMapper;
import vn.fernirx.tawatch.user.repository.UserProfileRepository;
import vn.fernirx.tawatch.user.repository.UserRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {
    private final UserProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;
    private final CloudinaryService cloudinaryService;

    @Transactional(readOnly = true)
    public ProfileResponse getProfileByUserId(Long userId) {
        log.info("Getting profile for user ID: {}", userId);

        UserProfile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Profile"));

        return profileMapper.toResponse(profile);
    }

    @Transactional
    public ProfileResponse createProfile(Long userId, ProfileRequest request) {
        log.info("Creating profile for user ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile"));

        if (profileRepository.findByUserId(userId).isPresent()) {
            throw new ResourceAlreadyExistsException("Profile");
        }

        if (request.getPhone() != null && profileRepository.existsByPhone(request.getPhone())) {
            throw new ResourceAlreadyExistsException("Profile");
        }

        UserProfile profile = profileMapper.toEntity(request);
        profile.setUser(user);

        UserProfile savedProfile = profileRepository.save(profile);
        log.info("Profile created for user ID: {}", userId);

        return profileMapper.toResponse(savedProfile);
    }

    @Transactional
    public ProfileResponse updateProfile(Long userId, ProfileRequest request) {
        log.info("Updating profile for user ID: {}", userId);

        UserProfile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile"));

        if (request.getPhone() != null &&
                !request.getPhone().equals(profile.getPhone()) &&
                profileRepository.existsByPhone(request.getPhone())) {
            throw new ResourceAlreadyExistsException("Profile");
        }

        profileMapper.updateEntity(request, profile);
        UserProfile updatedProfile = profileRepository.save(profile);

        log.info("Profile updated for user ID: {}", userId);
        return profileMapper.toResponse(updatedProfile);
    }

    @Transactional
    public ProfileResponse uploadAvatar(Long userId, MultipartFile avatarFile) {
        log.info("Uploading avatar for user ID: {}", userId);

        if (avatarFile == null || avatarFile.isEmpty()) {
            throw new RuntimeException("Avatar file is required");
        }

        String contentType = avatarFile.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new RuntimeException("Only image files are allowed");
        }

        if (avatarFile.getSize() > 5 * 1024 * 1024) {
            throw new RuntimeException("File size must be less than 5MB");
        }

        UserProfile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile"));

        try {
            // Delete old avatar if exists
            deleteOldAvatar(profile.getAvatarUrl());

            // Upload new avatar to Cloudinary
            String avatarUrl = cloudinaryService.uploadAvatar(avatarFile, userId);
            profile.setAvatarUrl(avatarUrl);

            UserProfile updatedProfile = profileRepository.save(profile);
            log.info("Avatar uploaded for user ID: {}", userId);

            return profileMapper.toResponse(updatedProfile);

        } catch (IOException e) {
            log.error("Failed to upload avatar for user ID: {}", userId, e);
            throw new RuntimeException("Failed to upload avatar: " + e.getMessage());
        }
    }

    // Delete avatar
    @Transactional
    public ProfileResponse deleteAvatar(Long userId) {
        log.info("Deleting avatar for user ID: {}", userId);

        UserProfile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile"));

        deleteOldAvatar(profile.getAvatarUrl());

        profile.setAvatarUrl(null);
        UserProfile updatedProfile = profileRepository.save(profile);

        log.info("Avatar deleted for user ID: {}", userId);
        return profileMapper.toResponse(updatedProfile);
    }

    private void deleteOldAvatar(String oldAvatarUrl) {
        if (oldAvatarUrl != null && !oldAvatarUrl.trim().isEmpty()) {
            try {
                cloudinaryService.deleteImageByUrl(oldAvatarUrl);
                log.info("Old avatar deleted from Cloudinary");
            } catch (IOException e) {
                log.warn("Failed to delete old avatar from Cloudinary: {}", e.getMessage());
            }
        }
    }

    // Delete profile
    @Transactional
    public void deleteProfile(Long userId) {
        log.info("Deleting profile for user ID: {}", userId);

        UserProfile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile"));

        profileRepository.delete(profile);
        log.info("Profile deleted for user ID: {}", userId);
    }

    @Transactional(readOnly = true)
    public boolean isPhoneExists(String phone) {
        return profileRepository.existsByPhone(phone);
    }

    @Transactional(readOnly = true)
    public ProfileResponse getProfileByPhone(String phone) {
        log.info("Getting profile by phone: {}", phone);

        UserProfile profile = profileRepository.findByPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("Profile"));

        return profileMapper.toResponse(profile);
    }
}
