package github.kanwalnain.creditcard.rest;

import github.kanwalnain.creditcard.constant.ErrorMessage;
import github.kanwalnain.creditcard.model.ApiError;
import github.kanwalnain.creditcard.model.CreditCardRequest;
import github.kanwalnain.creditcard.service.CreditCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Collection;
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
    private CreditCardService creditCardService;

    /**
     * Api to add credit card to database.
     * @param creditCardRequest
     * @return confirmation of credit card addition.
     */
    @PostMapping(path = "/creditCards")
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
         return new ResponseEntity<>(creditCardService.addCreditCard(creditCardRequest), HttpStatus.CREATED);
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
    @GetMapping(path = "/creditCards")
    public @ResponseBody  ResponseEntity getCreditCards(){

        List<CreditCardRequest> creditCardRequests = creditCardService.getAllCreditCards();
        if (null == creditCardRequests || creditCardRequests.isEmpty()){
            new ResponseEntity<List<CreditCardRequest>>(creditCardRequests, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<CreditCardRequest>>(creditCardService.getAllCreditCards(), HttpStatus.OK);
    }
}
