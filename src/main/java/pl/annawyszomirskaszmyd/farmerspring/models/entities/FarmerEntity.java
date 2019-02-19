package pl.annawyszomirskaszmyd.farmerspring.models.entities;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "farmer")
@DynamicUpdate
@Data
public class FarmerEntity {
    private @Id @GeneratedValue int id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private @Column(name="create_time") LocalDateTime createTime;

    @OneToMany(mappedBy = "farmerEntity", fetch = FetchType.LAZY, cascade ={})
    Set<BarnEntity> barns;
}
