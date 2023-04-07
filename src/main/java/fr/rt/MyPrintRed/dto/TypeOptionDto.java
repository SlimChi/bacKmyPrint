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
public class TypeOptionDto {

    private Integer idOption;
    private Integer idTypeOption;
    private String libelle;
    private BigDecimal prix;
}
