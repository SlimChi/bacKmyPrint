package fr.rt.MyPrintRed.controllers;

import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import fr.rt.MyPrintRed.dto.ClientTokenDto;
import fr.rt.MyPrintRed.dto.PurchaseDto;
import fr.rt.MyPrintRed.services.impl.PaymentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class PaymentController {
    private final PaymentServiceImpl paymentService;
    @Inject
    public PaymentController(PaymentServiceImpl paymentService) {
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
