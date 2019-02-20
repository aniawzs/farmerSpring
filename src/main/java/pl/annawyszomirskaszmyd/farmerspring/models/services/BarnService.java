package pl.annawyszomirskaszmyd.farmerspring.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.annawyszomirskaszmyd.farmerspring.models.entities.BarnEntity;
import pl.annawyszomirskaszmyd.farmerspring.models.forms.AddBarnForm;
import pl.annawyszomirskaszmyd.farmerspring.models.mappers.AddBarnFormToBarnEntityMapper;
import pl.annawyszomirskaszmyd.farmerspring.models.repositories.BarnRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BarnService {
    final BarnRepository barnRepository;
    final FarmerSession farmerSession;

    @Autowired
    public BarnService(BarnRepository barnRepository, FarmerSession farmerSession) {
        this.barnRepository = barnRepository;
        this.farmerSession = farmerSession;
    }

    public void addBarn(AddBarnForm addBarnForm){
        BarnEntity barnEntity = new AddBarnFormToBarnEntityMapper().map(addBarnForm);
        barnEntity.setFarmerId(farmerSession.getUserEntity().getId());

        barnRepository.save(barnEntity);
    }

    public void removeBarn(String barnName){
        barnRepository.removeByName(barnName);
    }

    public String returnMostNumberedBarn(){
        Optional<String> mostNumberedBarn = barnRepository.returnMostNumberedBarn();
        return mostNumberedBarn.orElse("Nie została dodana żadna stodoła!");

    }

    public boolean existsByNameAndFarmerId(String name){
        return barnRepository.existsByNameAndFarmerId(name, farmerSession.getUserEntity().getId());
    }

    public boolean existsByIdAndFarmerId(int id){
        return barnRepository.existsByIdAndFarmerId(id, farmerSession.getUserEntity().getId());
    }

    public boolean isBarnListEmpty() {
         return barnRepository.countBarns() == 0;
    }

    public List<String> getBarnNamesByFarmerId() {
        return barnRepository.getBarnNamesByFarmerId(farmerSession.getUserEntity().getId());
    }
}
