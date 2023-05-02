package fr.rt.MyPrintRed.dto;

import fr.rt.MyPrintRed.entities.Adresse;
import fr.rt.MyPrintRed.entities.TypeAdresse;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdresseDto {


  private Integer id;

  private String rue;

  private String complement;

  private String codePostal;

  private String ville;

  private TypeAdresseDto typeAdresseDto;
  private Integer UtilisateurId;



}
