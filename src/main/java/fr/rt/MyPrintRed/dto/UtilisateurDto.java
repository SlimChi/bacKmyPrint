package fr.rt.MyPrintRed.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.rt.MyPrintRed.entities.Adresse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value="password")
@JsonPropertyOrder({"idUtilisateur","nom","prenom","email","telephone"})
public class UtilisateurDto extends HateOas{

    private Integer idUtilisateur;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String password;
    private boolean active = false;
    private List<Adresse> adresse;

    public UtilisateurDto(int idUtilisateur, String nom, String prenom, String email, String telephone, List<Adresse> adresse, String password, boolean active) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.telephone = telephone;
        this.password = password;
        this.active = active;
    }


}
