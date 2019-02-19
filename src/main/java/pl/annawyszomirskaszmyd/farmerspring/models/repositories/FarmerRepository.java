package pl.annawyszomirskaszmyd.farmerspring.models.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.annawyszomirskaszmyd.farmerspring.models.entities.FarmerEntity;

import java.util.Optional;

@Repository
public interface FarmerRepository extends CrudRepository<FarmerEntity, Integer> {

    boolean existsByUsername(String username);

    @Query(value = "SELECT * FROM `farmer` WHERE `username` = ?1", nativeQuery = true)
    Optional<FarmerEntity> getFarmerByUsername(String username);


    @Modifying
    @Query(value = "UPDATE `farmer` SET `password`= ?1 WHERE `id`= ?2", nativeQuery = true)
    void setFarmerPassword(String newPassword, int farmerId);

    @Query(value= "SELECT * FROM `farmer` WHERE `id`=?1", nativeQuery = true)
    FarmerEntity getFarmerById(int id);

    @Modifying
    @Query(value="UPDATE `farmer` SET `name`=?1 WHERE `id`=?2", nativeQuery = true)
    void updateFarmerName(String name, int id);

    @Modifying
    @Query(value="UPDATE `farmer` SET `surname` = ?1 WHERE `id` = ?2", nativeQuery = true)
    void updateFarmerSurname(String surname, int id);
}
