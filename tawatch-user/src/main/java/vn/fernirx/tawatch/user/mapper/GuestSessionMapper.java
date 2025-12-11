package vn.fernirx.tawatch.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.fernirx.tawatch.user.dto.request.GuestSessionRequest;
import vn.fernirx.tawatch.user.dto.response.GuestSessionResponse;
import vn.fernirx.tawatch.user.entity.GuestSession;

@Mapper(componentModel = "spring")
public interface GuestSessionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastActive", ignore = true)
    @Mapping(target = "expiresAt", ignore = true)
    GuestSession toEntity(GuestSessionRequest request);

    @Mapping(target = "isActive", expression = "java(!session.isExpired())")
    @Mapping(target = "daysRemaining", expression = "java(session.getDaysUntilExpiration())")
    GuestSessionResponse toResponse(GuestSession session);
}