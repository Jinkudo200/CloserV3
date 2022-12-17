package ma.ac.emi.ginfo.closer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ma.ac.emi.ginfo.closer.enumeration.State;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("A")
public class Adherent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String mail;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Book> services = new ArrayList<>();

    private boolean isProvider;

    @ManyToMany
    private List<Provider> favoris = new ArrayList<>();


    public Adherent() {
    }

    public Adherent(String name , String mail) {
        this.name = name;
        this.mail = mail;
    }




    @Override
    public String toString() {
        return "Adherent{" +
                "idAdherent=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }

}
