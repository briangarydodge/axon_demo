package com.dodgeb.axon_demo.repositories;

import com.dodgeb.axon_demo.models.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MongoDriverRespository extends MongoRepository<Driver, String> {


    @Query("{identifier: ?0})")
    List<Driver> findByIdentifier(String identifier);

}
