package fr.rt.MyPrintRed.entities;

import fr.rt.MyPrintRed.entities.Utilisateur;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author slim
 * @Project
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class PasswordToken {

    @SequenceGenerator
            (name = "password_token_sequence",
                    sequenceName = "password_token_sequence",
                    allocationSize = 1)

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "password_token_sequence"
    )


    private Long id;
    @Column(nullable = false)
    private String token;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "id_utilisateur"
    )
    private Utilisateur utilisateur;

    public PasswordToken(String token, Utilisateur utilisateur) {
        this.token = token;
        this.utilisateur = utilisateur;
    }


}

