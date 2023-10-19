package com.ms.mapper;

import com.ms.model.PhoneDto;
import com.ms.model.PhoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )
public interface PhoneMapper {

    @Mapping(target = "cityCode", source = "citycode")
    @Mapping(target = "countryCode", source = "countrycode")
    PhoneEntity toEntity(PhoneDto phone);

    @Mapping(target = "citycode", source = "cityCode")
    @Mapping(target = "countrycode", source = "countryCode")
    PhoneDto toDto(PhoneEntity phone);

}
