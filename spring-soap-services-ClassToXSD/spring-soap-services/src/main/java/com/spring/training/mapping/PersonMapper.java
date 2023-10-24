package com.spring.training.mapping;

import com.spring.training.entity.PersonEntity;
import com.spring.training.model.Person;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {

    Person toPerson(PersonEntity PersonEntity);

    PersonEntity fromPerson(Person Person);

}
