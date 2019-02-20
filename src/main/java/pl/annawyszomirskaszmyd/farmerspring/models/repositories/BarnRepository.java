package pl.annawyszomirskaszmyd.farmerspring.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.annawyszomirskaszmyd.farmerspring.models.entities.BarnEntity;
import java.util.List;
import java.util.Optional;

@Repository
public interface BarnRepository extends CrudRepository<BarnEntity, Integer> {

    boolean existsByNameAndFarmerId(String name, int farnerId);

    boolean existsById(Integer id);

    @Query(value = "SELECT `id` FROM `barn`", nativeQuery = true)
    List<Integer> getAllBarnId();

    @Query(value = "SELECT COUNT(`id`) FROM `barn`", nativeQuery = true)
    int returnBarnCount();

    boolean existsByIdAndFarmerId(int barnId, int farmerId);

    void removeByName(String barnName);

    @Query(value = "SELECT `name` FROM `barn` WHERE `id` IN(SELECT `barn_id` FROM `animal`" +
            " GROUP BY `barn_id` ORDER BY COUNT(`barn_id`) DESC) LIMIT 1", nativeQuery = true)
    Optional<String> returnMostNumberedBarn();

    @Query(value="SELECT COUNT(`id`) FROM `barn`", nativeQuery = true)
    int countBarns();

    @Query(value="SELECT `name` FROM `barn` WHERE `farmer_id`=?1", nativeQuery = true)
    List<String> getBarnNamesByFarmerId(int id);
}
