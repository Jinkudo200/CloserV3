
package ma.ac.emi.ginfo.closer.entities;//package ma.ac.emi.ginfo.closer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("P")
public class Provider extends Adherent{

    @ManyToOne
    private Services services;

    private float rate;

    @OneToMany(mappedBy = "provider")
    private List<Rating> ratings;


    public Provider(String name, String mail , Services services) {
        super(name, mail);
        this.setProvider(true);
        this.services = services;
        this.ratings = new ArrayList<>();
    }
}

