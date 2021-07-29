package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

    private String name;

    @Builder
    public Pet(Long id,String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "pettype_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    @Override
    public String toString() {
        return "Pet{" +
                "petType=" + petType +
                ", owner=" + owner +
                ", birthDate=" + birthDate +
                '}';
    }
}
