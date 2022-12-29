package ma.ac.emi.ginfo.closer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String password;

    @JsonIgnore
    @OneToOne(mappedBy = "compte", cascade=CascadeType.ALL)
    private Adherent adherent;

//    @JsonIgnore
//    @OneToOne(mappedBy = "compteP", cascade=CascadeType.ALL)
//    private Provider provider;

    public Compte(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Compte() {
    }

    public Compte(String email, String password, Adherent adherent) {
        this.email = email;
        this.password = password;
        this.adherent = adherent;
    }
}
