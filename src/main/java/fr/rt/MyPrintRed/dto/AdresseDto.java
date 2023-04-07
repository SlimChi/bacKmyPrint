package fr.rt.MyPrintRed.dto;

import fr.rt.MyPrintRed.entities.Adresse;
import fr.rt.MyPrintRed.entities.TypeAdresse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AdresseDto {


  private Integer id;

  private String rue;

  private String complement;

  private String codePostal;

  private String ville;

  private TypeAdresse typeAdresse;
  private Integer UtilisateurId;

    public AdresseDto() {
    }

  public AdresseDto(Integer id, String rue, String complement, String codePostal, String ville) {
    this.id = id;
    this.rue = rue;
    this.complement = complement;
    this.codePostal = codePostal;
  }

  public static AdresseDto fromEntity(Adresse adresse) {
    return AdresseDto.builder()
            .id(adresse.getId())
            .rue(adresse.getRue())
            .complement(adresse.getComplement())
            .codePostal(adresse.getCodePostal())
            .ville(adresse.getVille())
            .UtilisateurId(adresse.getIdUtilisateur())
            .typeAdresse(adresse.getTypeAdresse())
            .build();
  }


  public static Adresse toEntity(AdresseDto adresse) {
    return Adresse.builder()
            .id(adresse.getId())
            .rue(adresse.getRue())
            .complement(adresse.getComplement())
            .codePostal(adresse.getCodePostal())
           .idUtilisateur(adresse.getUtilisateurId())
            .typeAdresse(adresse.getTypeAdresse())
            .ville(adresse.getVille())
        .build();
  }

}
