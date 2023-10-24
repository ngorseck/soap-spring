package com.spring.training.mapping;

import com.spring.training.entity.CountryEntity;
import com.spring.training.model.Country;
import org.mapstruct.Mapper;

@Mapper
public interface CountryMapper {

    Country toCountry(CountryEntity countryEntity);

    CountryEntity fromCountry(Country country);

}
