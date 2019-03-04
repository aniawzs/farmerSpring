package pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.entities.BarnEntity;
import pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.forms.AddBarnForm;
import pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.mappers.AddBarnFormToBarnEntityMapper;
import pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.repositories.BarnRepository;
import pl.annawyszomirskaszmyd.farmerspring.farmer.models.services.FarmerSession;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BarnService {
    private final BarnRepository barnRepository;
    private final FarmerSession farmerSession;

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
        barnRepository.deleteByNameAndFarmerId(barnName, farmerSession.getUserEntity().getId());
    }

    public String returnMostNumberedBarn(){
        Optional<String> mostNumberedBarn = barnRepository.returnMostNumberedBarn(farmerSession.getUserEntity().getId());
        return mostNumberedBarn.orElse("Nie została dodana jeszcze żadna stodoła lub nie posiadasz zwierząt na farmie!");

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
