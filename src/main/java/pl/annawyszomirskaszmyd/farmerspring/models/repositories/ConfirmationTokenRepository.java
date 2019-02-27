package pl.annawyszomirskaszmyd.farmerspring.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.annawyszomirskaszmyd.farmerspring.models.entities.ConfirmationTokenEntity;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationTokenEntity, Integer> {

    ConfirmationTokenEntity findByConfirmationToken(String confirmtionToken);
}
