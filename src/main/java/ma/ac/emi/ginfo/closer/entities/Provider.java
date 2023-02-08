
package ma.ac.emi.ginfo.closer.entities;//package ma.ac.emi.ginfo.closer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("P")
@JsonPropertyOrder({"idP"})
public class Provider extends Adherent{

    private Long idP;

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


//    public Provider(Long id, String name , Services services) {
//        super(name);
//        this.setId(id);
//        this.setProvider(true);
//        this.services = services;
//        this.ratings = new ArrayList<>();
//    }



    public Provider(Adherent a , Services services) {
        this.setName(a.getName());
        this.setPosition(a.getPosition());
        this.setCompte(a.getCompte());
//        System.out.println(a.getFavoris());
        for (Provider p: a.getFavoris()) {
            this.getFavoris().add(p);
        }
        this.setProvider(true);
        this.services = services;
        this.ratings = new ArrayList<>();
        this.idP = a.getId();
    }

//    public Provider(Long id, Adherent a , Services services) {
//        this.setId(id);
//        Position position = new Position(a.getPosition().getLatitude(),
//                a.getPosition().getLongitude(),
//                this);
//        Compte compte = new Compte(a.getCompte().getEmail(),
//                a.getCompte().getPassword(),
//                this);
//        this.setName(a.getName());
//        this.setPosition(position);
//        this.setCompte(compte);
////        System.out.println(a.getFavoris());
//        for (Provider p: a.getFavoris()) {
//            this.getFavoris().add(p);
//        }
//        this.setProvider(true);
//        this.services = services;
//        this.ratings = new ArrayList<>();
//    }


}

