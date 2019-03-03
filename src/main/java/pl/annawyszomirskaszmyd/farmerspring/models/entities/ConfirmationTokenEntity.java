package pl.annawyszomirskaszmyd.farmerspring.models.entities;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "confirmation_token")
@Data
public class ConfirmationTokenEntity {
    private static final int EXPIRATION_TIME_IN_MINUTES = 60 * 24;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private @Column(name="token") String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private @Column(name = "expiry_date") Date expiryDate;


    @OneToOne(targetEntity = FarmerEntity.class, fetch = FetchType.LAZY, cascade ={})
    @JoinColumn(name = "farmer_id", nullable = false)
    private FarmerEntity farmerEntity;

    public ConfirmationTokenEntity(){}

    public ConfirmationTokenEntity(FarmerEntity farmerEntity) {
        this.farmerEntity = farmerEntity;
        expiryDate = calculateExpiryDate(EXPIRATION_TIME_IN_MINUTES);
        confirmationToken = UUID.randomUUID().toString();
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }


    @Override
    public String toString() {
        return "ConfirmationTokenEntity{" +
                "id=" + id +
                ", confirmationToken='" + confirmationToken + '\'' +
                ", expiryDate=" + expiryDate +
                ", farmerEntity=" + farmerEntity +
                '}';
    }
}
