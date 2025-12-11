package vn.fernirx.tawatch.user.mapper;

import org.mapstruct.*;
import vn.fernirx.tawatch.user.dto.request.AddressRequest;
import vn.fernirx.tawatch.user.dto.response.AddressResponse;
import vn.fernirx.tawatch.user.entity.Address;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Address toEntity(AddressRequest request);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "fullAddress", expression = "java(address.getFullAddress())")
    AddressResponse toResponse(Address address);

    List<AddressResponse> toResponseList(List<Address> addresses);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(AddressRequest request, @MappingTarget Address address);
}