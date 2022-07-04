package com.santander.challengelocal.repository;

import com.santander.challengelocal.model.Sector;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorMongoRepository extends MongoRepository<Sector,String> {
}
