package vn.fernirx.tawatch.user.mapper;

import org.mapstruct.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import vn.fernirx.tawatch.common.enums.UserRole;
import vn.fernirx.tawatch.infrastructure.security.CustomUserDetails;
import vn.fernirx.tawatch.user.dto.request.UserRequest;
import vn.fernirx.tawatch.user.dto.request.UserUpdateRequest;
import vn.fernirx.tawatch.user.dto.response.UserResponse;
import vn.fernirx.tawatch.user.entity.User;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", uses = {ProfileMapper.class})
public interface UserMapper {

    @Mapping(target = "authorities", expression = "java(mapAuthorities(user.getRole()))")
    CustomUserDetails toCustomUserDetails(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "provider", ignore = true)
    @Mapping(target = "providerId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(UserRequest request);

    @Mapping(target = "profile", source = "profile")
    UserResponse toResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(UserUpdateRequest request, @MappingTarget User user);

    default Collection<? extends GrantedAuthority> mapAuthorities(UserRole role) {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
