package github.kanwalnain.creditcard.rest;

import github.kanwalnain.creditcard.model.CreditCardRequest;
import github.kanwalnain.creditcard.service.impl.CreditCardServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;


/**
 * Credit card REST controller with api operation on credit card.
 * @author Kanwal Nain Singh
 */
@RestController
@CrossOrigin
@Tag(name = "Credit Cards", description = "Endpoints performing credit card api operations.")
public class CreditCardController {

    private final Logger logger = LoggerFactory.getLogger(CreditCardController.class);

    @Autowired
    private CreditCardServiceImpl creditCardService;

    /**
     * Api to add credit card to database.
     * @param creditCardRequest
     * @return confirmation of credit card addition.
     */
    @PostMapping(path = "/creditCards", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Add a credit card.",
            description = "API to register a new credit card.",
            tags = { "Credit Cards" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreditCardRequest.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Bad Data Input", responseCode = "400", content = @Content)
            }
    )
    public ResponseEntity addCreditCard(@Valid @RequestBody CreditCardRequest creditCardRequest){
        logger.info("Add Credit Request Received: {}", creditCardRequest);
        Boolean result = creditCardService.addCreditCard(creditCardRequest);
        logger.info("Credit Card Details Added");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**
     * Retrieve list of all the credit cards saved in system.
     * @return list of credit cards.
     */

    @Operation(
            summary = "Retrieve all credit cards.",
            description = "API to register a new credit card.",
            tags = { "Credit Cards" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreditCardRequest  .class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content)
            }
    )
    @GetMapping(path = "/creditCards", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody  ResponseEntity getCreditCards(){
        logger.info("Get All Credits Request Received");
        List<CreditCardRequest> creditCardRequests = creditCardService.getAllCreditCards();
        if (null == creditCardRequests || creditCardRequests.isEmpty()){
            logger.info("Not credit card found.");
            new ResponseEntity<List<CreditCardRequest>>(creditCardRequests, HttpStatus.NOT_FOUND);
        }
        logger.info("Get All Credits Response: {}", creditCardRequests);
        return new ResponseEntity<List<CreditCardRequest>>(creditCardService.getAllCreditCards(), HttpStatus.OK);
    }
}
