package com.lambdaschool.zoos.repository;

import com.lambdaschool.zoos.model.Zoo;
import com.lambdaschool.zoos.view.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ZooRepository extends CrudRepository<Zoo, Long>
{
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM zooanimals WHERE zooid = :zooid", nativeQuery = true)
    void deleteZooFromZooAnimals(long zooid);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM zooanimals WHERE zooid = :zooid AND animalid = :animalid", nativeQuery = true)
    void deleteZooAnimalCombo(long zooid, long animalid);

    @Query(value = "SELECT COUNT(*) as count FROM zooanimals WHERE zooid = :zooid AND animalid = :animalid", nativeQuery = true)
    JustTheCount checkZooAnimalCombo(long zooid, long animalid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO zooanimals (zooid, animalid) VALUES (:zooid, :animalid)", nativeQuery = true)
    void saveZooAnimalCombo(long zooid, long animalid);

    Zoo findByZooname(String name);
}
