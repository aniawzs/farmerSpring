package pl.annawyszomirskaszmyd.farmerspring.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.annawyszomirskaszmyd.farmerspring.models.entities.AnimalEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends CrudRepository<AnimalEntity, Integer> {

    boolean existsByType(String type);

    @Query(value="SELECT `type` FROM `animal`", nativeQuery = true)
    List<String> returnAllTypes();

    @Query(value="SELECT COUNT(`type`) FROM `animal` WHERE `type`=?1", nativeQuery = true)
    int returnNumberOfAnimalsHavingType(String type);


    void deleteByType(String type);

    @Query(value="SELECT `type` FROM `animal` ORDER BY `age` DESC LIMIT 5", nativeQuery = true)
    List<String> returnFiveOldestAnimals();

    @Query(value = "SELECT `type` FROM `animal` ORDER BY `age` ASC LIMIT 5", nativeQuery = true)
    List<String> returnFiveYoungestAnimals();

    @Query(value = "SELECT `type` FROM `animal` GROUP BY `type` ORDER BY COUNT(`type`) DESC LIMIT 1", nativeQuery = true)
    Optional<String> returnMostNumberedAnimal();

    @Query(value="SELECT `type` FROM `animal` WHERE `is_vaccinated` = 'true'", nativeQuery = true)
    List<String> returnAllVaccinatedAnimals();

}
