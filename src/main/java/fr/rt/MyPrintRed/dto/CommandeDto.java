package fr.rt.MyPrintRed.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"numeroCommande","prixCommande","date","statusDto","utilisateurDto","adresseDto"})
public class CommandeDto extends HateOas{

    private Integer numeroCommande;
    private BigDecimal prixCommande;
    private Date date;
    private StatusDto statusDto;
    private UtilisateurDto utilisateurDto;
    private AdresseDto adresseDto;


}
