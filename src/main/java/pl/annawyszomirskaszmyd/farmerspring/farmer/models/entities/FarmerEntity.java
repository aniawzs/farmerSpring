package pl.annawyszomirskaszmyd.farmerspring.farmer.models.entities;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.entities.BarnEntity;

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
    private String email;
    private String username;
    private String password;
    private @Column(name="create_time") LocalDateTime createTime;

    @OneToMany(mappedBy = "farmerEntity", fetch = FetchType.LAZY, cascade ={})
    Set<BarnEntity> barns;

    private @Column(name="is_enabled") boolean isEnabled;

    @OneToOne(mappedBy = "farmerEntity")
    ConfirmationTokenEntity confirmationTokenEntity;
}
