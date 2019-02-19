package pl.annawyszomirskaszmyd.farmerspring.models.services;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.annawyszomirskaszmyd.farmerspring.models.entities.FarmerEntity;

@Service
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class FarmerSession {

    private boolean isLogin;
    private FarmerEntity userEntity;//remove that when we use hibernate with many tables uses

}
