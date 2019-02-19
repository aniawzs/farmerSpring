package pl.annawyszomirskaszmyd.farmerspring.models.mappers;

import pl.annawyszomirskaszmyd.farmerspring.models.forms.AddBarnForm;
import pl.annawyszomirskaszmyd.farmerspring.models.entities.BarnEntity;


public class AddBarnFormToBarnEntityMapper implements Mapper<AddBarnForm, BarnEntity> {
    @Override
    public BarnEntity map(AddBarnForm key) {
        BarnEntity barnEntity = new BarnEntity();
        barnEntity.setName(key.getName());

        return barnEntity;
    }
}
