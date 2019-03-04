package pl.annawyszomirskaszmyd.farmerspring.admin.panel.models.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="animal")
@Data
public class AnimalEntity {
    private @Id @GeneratedValue int id;
    private String type;
    private int age;
    private boolean isVaccinated;
    private @Column(name="barn_id") int barnId;

    @ManyToOne
    @JoinColumn(name = "barn_id", referencedColumnName = "id", insertable=false, updatable = false)
    private BarnEntity barnEntity;

}
