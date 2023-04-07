package fr.rt.MyPrintRed.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionLigneCommandeDto {

    private  Integer numeroCommande;
    private  Integer numeroLigneCommande;

    private TypeOptionDto typeOptionDto;
}
