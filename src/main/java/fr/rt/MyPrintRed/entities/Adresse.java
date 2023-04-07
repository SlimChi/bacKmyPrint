package fr.rt.MyPrintRed.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@Entity
@Table(name = "adresse")
public class Adresse {

    @Id
    @GeneratedValue
    private Integer id;

    private String rue;

    private String complement;

    private String codePostal;

    private String ville;

        @ManyToOne
        @JoinColumn(name = "id_type_adresse")
        private TypeAdresse typeAdresse;


    @Basic
    @Column(name = "id_utilisateur")
    private int idUtilisateur;

    public Adresse() {}

    public Adresse(Integer id, String rue, String complement, String codePostal, String ville) {
        this.id = id;
        this.rue = rue;
        this.complement = complement;
        this.codePostal = codePostal;
        this.ville = ville;
    }


}
