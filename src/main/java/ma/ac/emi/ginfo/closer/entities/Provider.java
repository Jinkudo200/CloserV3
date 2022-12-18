
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

//    @OneToOne(cascade=CascadeType.ALL)
//    private Position positionP;

//    @OneToOne(cascade=CascadeType.ALL)
//    private Compte compteP;



    public Provider(String name , Services services) {
        super(name);
        this.setProvider(true);
        this.services = services;
        this.ratings = new ArrayList<>();
    }

    public Provider(Adherent a , Services services) {
        Position position = new Position(a.getPosition().getLatitude(),
                a.getPosition().getLongitude(),
                this);
        Compte compte = new Compte(a.getCompte().getEmail(),
                a.getCompte().getPassword(),
                this);
        this.setName(a.getName());
        this.setPosition(position);
        this.setCompte(compte);
//        for (Provider p: a.getFavoris()) {
//            this.getFavoris().add(p);
//        }
        this.setProvider(true);
        this.services = services;
        this.ratings = new ArrayList<>();
    }


}

