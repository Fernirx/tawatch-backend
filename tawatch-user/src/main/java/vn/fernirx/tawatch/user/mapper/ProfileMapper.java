package vn.fernirx.tawatch.user.mapper;

import org.mapstruct.*;
import vn.fernirx.tawatch.user.dto.request.ProfileRequest;
import vn.fernirx.tawatch.user.dto.response.ProfileResponse;
import vn.fernirx.tawatch.user.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "user", ignore = true)
    UserProfile toEntity(ProfileRequest request);

    @Mapping(target = "fullName", expression = "java(profile.getFullName())")
    @Mapping(target = "age", expression = "java(profile.getAge())")
    ProfileResponse toResponse(UserProfile profile);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ProfileRequest request, @MappingTarget UserProfile profile);
}