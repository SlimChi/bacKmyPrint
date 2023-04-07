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
public class InsertLigneCommandeDto {

    private Integer numeroCommande;
    private Boolean rectoVerso;
    private Boolean couleur;
    private Integer nombreExemplaire;

    private Integer nombreFeuille;

    private BigDecimal prixLigneCommande;

    private Integer idFichier;
}
