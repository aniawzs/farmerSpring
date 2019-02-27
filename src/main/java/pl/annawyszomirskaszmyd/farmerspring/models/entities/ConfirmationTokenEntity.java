package pl.annawyszomirskaszmyd.farmerspring.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "confirmation_token")
@Data
public class ConfirmationTokenEntity {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private @Column(name="confirmation_token") String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private @Column(name = "create_date") Date createDate;

    @OneToOne(targetEntity = FarmerEntity.class, fetch = FetchType.LAZY, cascade ={})
    @JoinColumn(name = "farmer_id", referencedColumnName = "id", insertable=false, updatable = false)
    private FarmerEntity farmerEntity;

    public ConfirmationTokenEntity(FarmerEntity farmerEntity) {
        this.farmerEntity = farmerEntity;
        createDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
}
