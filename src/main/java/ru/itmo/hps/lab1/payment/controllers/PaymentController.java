package ru.itmo.hps.lab1.payment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hps.lab1.payment.entity.Payment;
import ru.itmo.hps.lab1.payment.repository.PaymentRepository;
import ru.itmo.hps.lab1.payment.services.PaymentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/payments")
    public ResponseEntity<String> createPayment(@RequestBody Payment payment) {
        paymentService.save(payment);
        return new ResponseEntity<>("Payment was created successfully.", HttpStatus.CREATED);
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable("id") long id) {
        Payment payment = paymentService.getById(id);

        if (payment != null) {
            return new ResponseEntity<>(payment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getAllPayment() {
        return new ResponseEntity<>(paymentService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/payments/{id}")
    public ResponseEntity<String> updatePayment(@PathVariable("id") long id, @RequestBody Payment payment) {
        Payment _payment = paymentService.getById(id);

        if (_payment != null) {
            _payment.setId(id);
            _payment.setToken(payment.getToken());
            _payment.setDescription(payment.getDescription());
            _payment.setCreateDate(payment.getCreateDate());

            paymentService.update(_payment);
            return new ResponseEntity<>("Payment was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find Payment with id=" + id, HttpStatus.NOT_FOUND);
        }
    }


//    @PostMapping("/signin")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
//        String token = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
//        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
//    }
}
