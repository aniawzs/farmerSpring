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

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public void addAnimal(AddAnimalForm addAnimalForm){
        animalRepository.save(new AddAnimalFormToAnimalEntityMapper().map(addAnimalForm));
    }

    public void removeAnimal(String type){
        animalRepository.deleteByType(type);
    }

    public List<String> returnFiveOldestAnimals(){
        return animalRepository.returnFiveOldestAnimals();
    }
    public List<String> returnFiveYoungestAnimals(){
       return animalRepository.returnFiveYoungestAnimals();
    }

    public String returnMostNumberedAnimal() {
        Optional<String> mostNumberedAnimal =  animalRepository.returnMostNumberedAnimal();

        return mostNumberedAnimal.orElse("Brak zwierząt na farmie!");
    }

    public List<String> returnAllVaccinatedAnimals(){
        return animalRepository.returnAllVaccinatedAnimals();
    }

    public List<String> returnAllTypes(){
        return animalRepository.returnAllTypes();
    }


    public boolean existsByType(String type){
        return animalRepository.existsByType(type);
    }


}
