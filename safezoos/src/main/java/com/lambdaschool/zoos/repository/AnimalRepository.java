package com.lambdaschool.zoos.repository;

import com.lambdaschool.zoos.model.Animal;
import com.lambdaschool.zoos.view.AnimalCount;
import com.lambdaschool.zoos.view.JustTheCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface AnimalRepository extends CrudRepository<Animal, Long>
{
    Animal findByAnimaltype(String type);

    @Query(value = "SELECT a.ANIMALID, a.ANIMALTYPE, count(z.ZOOID) as zoocount " +
                   "FROM ANIMAL a JOIN ZOOANIMALS z " +
                   "WHERE a.ANIMALID = z.ANIMALID " +
                   "GROUP BY a.ANIMALID", nativeQuery = true)
    ArrayList<AnimalCount> getAnimalCount();
}
