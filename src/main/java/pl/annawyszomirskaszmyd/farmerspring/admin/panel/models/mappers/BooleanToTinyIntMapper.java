package pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.mappers;

public class BooleanToTinyIntMapper implements Mapper<Boolean, Integer> {

    @Override
    public Integer map(Boolean key) {
        if(key){
            return 1;
        }

        return 0;
    }
}
