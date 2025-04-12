package com.officetech.officetech.API.payment.interfaces.rest;

import com.officetech.officetech.API.payment.domain.model.aggregates.PaymentDetail;
import com.officetech.officetech.API.payment.domain.services.PaymentDetailsCommandService;
import com.officetech.officetech.API.payment.domain.services.PaymentDetailsQueryService;
import com.officetech.officetech.API.payment.interfaces.rest.resources.CreatePaymentDetailsResource;
import com.officetech.officetech.API.payment.interfaces.rest.resources.PaymentDetailsResource;
import com.officetech.officetech.API.payment.interfaces.rest.transform.CreatePaymentDetailsCommandFromResourceAssembler;
import com.officetech.officetech.API.payment.interfaces.rest.transform.PaymentDetailsResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * This controller is to manage payment details
 * It has endpoints to create payment details and check if the card is expired
 * It is annotated with @RestController to indicate that it is a controller
 * It is annotated with @RequestMapping to map the endpoints to the controller
 * It is annotated with @Tag to provide metadata about the controller
 * @author Zaid Ramirez
 * @version 1.0
 * */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/payment-details")
@Tag(name = "payment Details", description = "Endpoints to manage payment details")
public class PaymentDetailsController {

    private final PaymentDetailsCommandService paymentDetailsCommandService;

    private final PaymentDetailsQueryService paymentDetailsQueryService;

    public PaymentDetailsController(PaymentDetailsCommandService paymentDetailsCommandService, PaymentDetailsQueryService paymentDetailsQueryService) {
        this.paymentDetailsCommandService = paymentDetailsCommandService;
        this.paymentDetailsQueryService = paymentDetailsQueryService;
    }

    /**
     * POST endpoint to create payment details
     * It is annotated with @PostMapping to indicate that it is a POST endpoint
     * It is annotated with @RequestBody to indicate that the request body will be mapped to the resource
     * It is annotated with @ResponseStatus to indicate that the response status will be CREATED
     * It returns a ResponseEntity with the payment details resource
     * @author Zaid Ramirez
     * @version 1.0
     * */
    @PostMapping
    public ResponseEntity<PaymentDetailsResource> createPaymentDetails(@RequestBody CreatePaymentDetailsResource resource){
        System.out.println("PaymentDetailsController: creating payment details");
        Optional<PaymentDetail> paymentDetail = paymentDetailsCommandService.handle(CreatePaymentDetailsCommandFromResourceAssembler.toCommandFromResource(resource));
        return paymentDetail.map(source-> new ResponseEntity<>(PaymentDetailsResourceFromEntityAssembler.toResourceFromEntity(source),CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * GET endpoint to check if the card is expired
     * It is annotated with @GetMapping to indicate that it is a GET endpoint
     * It is annotated with @PathVariable to indicate that the user_id will be mapped to the path variable
     * It returns a ResponseEntity with a boolean indicating if the card is expired or not
     * @author Zaid Ramirez
     * @version 1.0
     * */
    @GetMapping("/{user_id}/isExpired")
    public ResponseEntity<Boolean> isCardExpired(@PathVariable Long user_id) {
        Optional<PaymentDetail> paymentDetail = paymentDetailsQueryService.findByUserId(user_id);
        if (paymentDetail.isPresent()) {
            int currentYear = LocalDate.now().getYear();
            int currentMonth = LocalDate.now().getMonthValue();
            int expiratoryYear = Integer.parseInt(paymentDetail.get().getExpiratoryYear());
            int expiratoryMonth = Month.valueOf(paymentDetail.get().getExpiratoryMonth().toUpperCase()).getValue();
            boolean isExpired = expiratoryYear < currentYear || (expiratoryYear == currentYear && expiratoryMonth < currentMonth);
            return ResponseEntity.ok(isExpired);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
