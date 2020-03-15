package pl.arek.petclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "speciaities")
public class Speciality extends BaseEntity {
    @Column(name = "description")
    private String description;

}
