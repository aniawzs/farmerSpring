package pl.annawyszomirskaszmyd.farmerspring.farmer.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.entities.ConfirmationTokenEntity;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.repositories.ConfirmationTokenRepository;

import java.util.Optional;

@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public void addConfirmationToken(ConfirmationTokenEntity confirmationToken){
        confirmationTokenRepository.save(confirmationToken);
    }

    public ConfirmationTokenEntity findByConfirmationToken(String confirmationToken) {
        Optional<ConfirmationTokenEntity> confirmationTokenEntity =
                confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        return confirmationTokenEntity.orElse(null);
    }

    public void deleteByFarmerId(int farmerId){
        confirmationTokenRepository.deleteByFarmerId(farmerId);
    }
}
