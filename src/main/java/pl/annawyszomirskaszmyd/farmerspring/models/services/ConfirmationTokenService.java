package pl.annawyszomirskaszmyd.farmerspring.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.annawyszomirskaszmyd.farmerspring.models.entities.ConfirmationTokenEntity;
import pl.annawyszomirskaszmyd.farmerspring.models.repositories.ConfirmationTokenRepository;

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
}
