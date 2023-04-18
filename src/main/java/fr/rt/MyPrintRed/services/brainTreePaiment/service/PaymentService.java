package fr.rt.MyPrintRed.services.brainTreePaiment.service;

import com.braintreegateway.*;
import fr.rt.MyPrintRed.services.brainTreePaiment.clientTokenDto.ClientTokenDto;
import fr.rt.MyPrintRed.services.brainTreePaiment.clientTokenDto.PurchaseDto;
import fr.rt.MyPrintRed.services.brainTreePaiment.config.BrainTreeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    private final BrainTreeConfig config;

    @Autowired
    public PaymentService(BrainTreeConfig config) {
        this.config = config;
    }

    public BraintreeGateway getGateway() {
        return new BraintreeGateway(
                Environment.SANDBOX,
                config.getMerchantId(),
                config.getPublicKey(),
                config.getPrivateKey()
        );
    }

    public ClientTokenDto getToken() {
        ClientTokenRequest request = new ClientTokenRequest();
        String token = getGateway().clientToken().generate(request);
        return new ClientTokenDto(token);
    }

    public Result<Transaction> checkout(PurchaseDto dto){
        TransactionRequest request = new TransactionRequest()
                .amount(dto.getAmount())
                .paymentMethodNonce(dto.getNonce())
                .options()
                .submitForSettlement(true)
                .done();

                return getGateway().transaction().sale(request);
    }
}
