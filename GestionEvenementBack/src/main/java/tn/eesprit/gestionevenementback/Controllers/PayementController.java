package tn.eesprit.gestionevenementback.Controllers;

import com.google.gson.Gson;
import com.stripe.exception.StripeException;
import com.stripe.model.billingportal.Session;
import com.stripe.param.billingportal.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.eesprit.gestionevenementback.Entities.Payement;
import tn.eesprit.gestionevenementback.Repository.PayementRepository;
import tn.eesprit.gestionevenementback.Services.IPayementService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payement")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PayementController {
@Autowired
private PayementRepository payementRepository;

    @GetMapping("/getPayements")
    public ResponseEntity<List<Payement>> get(){


        return new ResponseEntity<>(payementRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("/updateStatus/{id}")
    public ResponseEntity<List<Payement>> get(@PathVariable (value = "id") Long id){
        Optional<Payement> payement=payementRepository.findById(id);
        payement.get().setStatus(true);
        payementRepository.save(payement.get());
        return new ResponseEntity<>(payementRepository.findAll(),HttpStatus.OK);
    }

    @GetMapping("/getStatistique")
    public ResponseEntity< List<Object[]>> getStatistique(){

        List<Object[]> list=payementRepository.getStatistique();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

        // create a Gson object
        private static Gson gson = new Gson();

       /* @PostMapping("/payment")
        public String paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException {
            // We initilize stripe object with the api key
            init();
            // We create a  stripe session parameters
            SessionCreateParams params = SessionCreateParams.builder()
                    // We will use the credit card payment method
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl())
                    .setCancelUrl(
                            payment.getCancelUrl())
                    .addLineItem(
                            SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
                                                    .setProductData(SessionCreateParams.LineItem.PriceData.ProductData
                                                            .builder().setName(payment.getName()).build())
                                                    .build())
                                    .build())
                    .build();
            // create a stripe session
            Session session = Session.create(params);
            Map<String, String> responseData = new HashMap<>();
            // We get the sessionId and we putted inside the response data you can get more info from the session object
            responseData.put("id", session.getId());
            // We can return only the sessionId as a String
            return gson.toJson(responseData);
        }

        private static void init() {
            Stripe.apiKey = "Your_secret_key";
        }*/
    }