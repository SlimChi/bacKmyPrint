package fr.rt.MyPrintRed.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LigneCommandeDto {

    private Integer numeroCommande;

    private Integer numeroLigneCommande;

    private Boolean rectoVerso;

    private String format;

    private Boolean couleur;

    private Integer nombreExemplaire;

    private Integer nombreFeuille;

    private BigDecimal prixLigneCommande;

    private StatusDto statusDto;

    private FichierDto fichierDto;
}
