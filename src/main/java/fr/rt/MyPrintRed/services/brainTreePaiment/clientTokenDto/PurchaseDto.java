package fr.rt.MyPrintRed.services.brainTreePaiment.clientTokenDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author slimane
 * @Project
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PurchaseDto {
    private String nonce;
    private BigDecimal amount;

}
