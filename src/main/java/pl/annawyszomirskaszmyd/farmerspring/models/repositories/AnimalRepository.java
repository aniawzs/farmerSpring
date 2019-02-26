package pl.annawyszomirskaszmyd.farmerspring.models.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.annawyszomirskaszmyd.farmerspring.models.entities.AnimalEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends CrudRepository<AnimalEntity, Integer> {

    @Query(value = "SELECT `type` FROM ((`animal` JOIN `barn` ON `animal`.`barn_id` = `barn`.`id` JOIN `farmer`" +
            " ON `barn`.`farmer_id` = `farmer`.`id`)) WHERE `farmer`.`id`=?2 AND `animal`.`type`=?1", nativeQuery = true)
    List<String> returnAnimalsByTypeAndFarmerId(String type, int farmerId);

    @Modifying
    @Query(value = "DELETE `animal` FROM ((`animal` JOIN `barn` ON `animal`.`barn_id` = `barn`.`id` JOIN `farmer`" +
            " ON `barn`.`farmer_id` = `farmer`.`id`)) WHERE `farmer`.`id`=?2 AND `animal`.`type`=?1", nativeQuery = true)
    void deleteByTypeAndFarmerId(String type, int farmerId);


    @Query(value = "SELECT `type` FROM ((`animal` JOIN `barn` ON `animal`.`barn_id` = `barn`.`id` JOIN `farmer` " +
            "ON `barn`.`farmer_id` = `farmer`.`id`)) WHERE `farmer`.`id`=?1 ORDER BY `age` DESC LIMIT 5",
            nativeQuery = true)
    List<String> returnFiveOldestAnimals(int farmerId);


    @Query(value = "SELECT `type` FROM ((`animal` JOIN `barn` ON `animal`.`barn_id` = `barn`.`id` JOIN `farmer` " +
            "ON `barn`.`farmer_id` = `farmer`.`id`)) WHERE `farmer`.`id`=?1 ORDER BY `age` ASC LIMIT 5",
            nativeQuery = true)
    List<String> returnFiveYoungestAnimals(int farmerId);


    @Query(value = "SELECT `type` FROM ((`animal` JOIN `barn` ON `animal`.`barn_id` = `barn`.`id` JOIN `farmer` " +
            "ON `barn`.`farmer_id` = `farmer`.`id`)) WHERE `farmer`.`id`=?1 GROUP BY `type` ORDER BY COUNT(`type`)" +
            " DESC LIMIT 1", nativeQuery = true)
    Optional<String> returnMostNumberedAnimal(int farmerId);


    @Query(value = "SELECT `type` FROM ((`animal` JOIN `barn` ON `animal`.`barn_id` = `barn`.`id` JOIN `farmer` " +
            "ON `barn`.`farmer_id` = `farmer`.`id`)) WHERE `farmer`.`id`=?1 AND `is_vaccinated` = 1", nativeQuery = true)
    List<String> returnAllVaccinatedAnimals(int farmerId);


    @Query(value = "SELECT `type` FROM ((`animal` JOIN `barn` ON `animal`.`barn_id` = `barn`.`id` JOIN `farmer`" +
            " ON `barn`.`farmer_id` = `farmer`.`id`)) WHERE `farmer`.`id`=?1", nativeQuery = true)
    List<String> returnAllFarmerAnimals(int farmerId);
}
