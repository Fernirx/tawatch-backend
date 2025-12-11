package vn.fernirx.tawatch.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import vn.fernirx.tawatch.common.enums.UserRole;
import vn.fernirx.tawatch.infrastructure.security.CustomUserDetails;
import vn.fernirx.tawatch.user.entity.User;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "authorities", expression = "java(mapAuthorities(user.getRole()))")
    CustomUserDetails toCustomUserDetails(User user);

    default Collection<? extends GrantedAuthority> mapAuthorities(UserRole role) {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
