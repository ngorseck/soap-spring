package com.spring.training.repository;

import com.spring.training.entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CountryRepository extends CrudRepository<CountryEntity, String> {

    Optional<CountryEntity> findByNameIgnoreCase(String name);

}
