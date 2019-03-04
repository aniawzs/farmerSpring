package pl.annawyszomirskaszmyd.farmerspring.farmer.models.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class FarmerHash {

    @Bean
    public BCryptPasswordEncoder getBCrypt(){
        return new BCryptPasswordEncoder();
    }

    public String hashPassword(String password){
        return getBCrypt().encode(password);
    }

    public boolean isPasswordCorrect(String typed, String hashed){
        return getBCrypt().matches(typed, hashed);
    }

}
