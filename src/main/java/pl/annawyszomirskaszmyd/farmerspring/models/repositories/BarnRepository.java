package pl.annawyszomirskaszmyd.farmerspring.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.annawyszomirskaszmyd.farmerspring.models.entities.BarnEntity;
import java.util.List;
import java.util.Optional;

@Repository
public interface BarnRepository extends CrudRepository<BarnEntity, Integer> {

    boolean existsByNameAndFarmerId(String name, int farmerId);

    boolean existsById(Integer id);

    boolean existsByIdAndFarmerId(int barnId, int farmerId);

//    void removeByNameAndFarmerId(String barnName, int farmerId);

    void deleteByNameAndFarmerId(String name, int farmerId);

    @Query(value = "SELECT `barn`.`name` FROM ((`animal` JOIN `barn` ON `animal`.`barn_id` = `barn`.`id` JOIN `farmer`" +
            " ON `barn`.`farmer_id` = `farmer`.`id`)) WHERE `farmer`.`id`= ?1 GROUP BY `barn_id` " +
            "ORDER BY COUNT(`barn_id`) DESC LIMIT 1", nativeQuery = true)
    Optional<String> returnMostNumberedBarn(int farmerId);

    @Query(value="SELECT COUNT(`id`) FROM `barn`", nativeQuery = true)
    int countBarns();

    @Query(value="SELECT `name` FROM `barn` WHERE `farmer_id`=?1", nativeQuery = true)
    List<String> getBarnNamesByFarmerId(int id);
}
