package pl.annawyszomirskaszmyd.farmerspring.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.annawyszomirskaszmyd.farmerspring.models.forms.AddAnimalForm;
import pl.annawyszomirskaszmyd.farmerspring.models.mappers.AddAnimalFormToAnimalEntityMapper;
import pl.annawyszomirskaszmyd.farmerspring.models.repositories.AnimalRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnimalService {
    final AnimalRepository animalRepository;
    final FarmerSession farmerSession;

    @Autowired
    public AnimalService(AnimalRepository animalRepository, FarmerSession farmerSession) {
        this.animalRepository = animalRepository;
        this.farmerSession = farmerSession;
    }

    public void addAnimal(AddAnimalForm addAnimalForm){
        animalRepository.save(new AddAnimalFormToAnimalEntityMapper().map(addAnimalForm));
    }

    public void removeAnimal(String type){
        animalRepository.deleteByType(type);
    }

    public List<String> returnFiveOldestAnimals(){
        return animalRepository.returnFiveOldestAnimals(farmerSession.getUserEntity().getId());
    }
    public List<String> returnFiveYoungestAnimals(){
       return animalRepository.returnFiveYoungestAnimals(farmerSession.getUserEntity().getId());
    }

    public String returnMostNumberedAnimal() {
        Optional<String> mostNumberedAnimal =
                animalRepository.returnMostNumberedAnimal(farmerSession.getUserEntity().getId());

        return mostNumberedAnimal.orElse("Brak zwierząt na farmie!");
    }

    public List<String> returnAllVaccinatedAnimals(){
        return animalRepository.returnAllVaccinatedAnimals(farmerSession.getUserEntity().getId());
    }

    public boolean existsByType(String type){
        return animalRepository.existsByType(type);
    }


    public List<String> returnAllFarmerAnimals(){
       return animalRepository.returnAllFarmerAnimals(farmerSession.getUserEntity().getId());
    }

    public boolean isFarmerAnimalListEmpty(){
        return returnAllFarmerAnimals().isEmpty();
    }

}
