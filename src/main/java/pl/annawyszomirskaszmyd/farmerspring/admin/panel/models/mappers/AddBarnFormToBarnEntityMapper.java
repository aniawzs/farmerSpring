package pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.mappers;

import pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.forms.AddBarnForm;
import pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.entities.BarnEntity;


public class AddBarnFormToBarnEntityMapper implements Mapper<AddBarnForm, BarnEntity> {
    @Override
    public BarnEntity map(AddBarnForm key) {
        BarnEntity barnEntity = new BarnEntity();
        barnEntity.setName(key.getName());

        return barnEntity;
    }
}
