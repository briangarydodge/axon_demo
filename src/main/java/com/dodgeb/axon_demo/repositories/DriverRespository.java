package com.dodgeb.axon_demo.repositories;

import com.dodgeb.axon_demo.models.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DriverRespository extends MongoRepository<Driver, String> {




}
