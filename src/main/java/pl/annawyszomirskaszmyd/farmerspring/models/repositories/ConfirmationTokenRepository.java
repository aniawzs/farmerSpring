package pl.annawyszomirskaszmyd.farmerspring.models.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.annawyszomirskaszmyd.farmerspring.models.entities.ConfirmationTokenEntity;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationTokenEntity, Integer> {

    Optional<ConfirmationTokenEntity> findByConfirmationToken(String confirmationToken);

    @Modifying
    @Query(value="DELETE FROM `confirmation_token` WHERE `farmer_id` = ?1", nativeQuery = true)
    void deleteByFarmerId(int farmerId);
}
