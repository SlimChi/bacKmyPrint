package fr.rt.MyPrintRed.services.brainTreePaiment.controller;

import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import fr.rt.MyPrintRed.services.brainTreePaiment.clientTokenDto.ClientTokenDto;
import fr.rt.MyPrintRed.services.brainTreePaiment.clientTokenDto.PurchaseDto;
import fr.rt.MyPrintRed.services.brainTreePaiment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class PaymentController {
    private final PaymentService paymentService;
    @Inject
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/token")
    public ResponseEntity<ClientTokenDto> getToken() {
        return ResponseEntity.ok(paymentService.getToken());
    }

    @PostMapping("/checkout")
    public ResponseEntity<Result<Transaction>> checkOut(@RequestBody PurchaseDto dto) {
        return ResponseEntity.ok(paymentService.checkout(dto));
    }
}
