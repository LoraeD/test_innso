package technique.innso.api.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import technique.innso.api.dto.CreateMessageDTO;
import technique.innso.api.dto.GetCustomerDTO;
import technique.innso.api.dto.GetMessageDTO;
import technique.innso.business.domain.Customer;
import technique.innso.business.domain.Message;
import technique.innso.business.mapper.MessageMapper;
import technique.innso.business.service.MessageService;

import java.net.URI;

@RestController()
@RequestMapping("/api")
@Api(tags = "Message", consumes = "application/json")
public class MessageController {

    @Autowired
    private MessageService service;

    @Autowired
    private MessageMapper mapper;

    @PostMapping(value = "/messages")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiOperation(value = "create a message")
    @ApiResponses({@ApiResponse(code = 201, message = "OK, the message was created"),
            @ApiResponse(code = 404, message = "BAD REQUEST, the json was not acceptable")})
    public ResponseEntity<Object> createMessage(@ApiParam(value = "the message to create", required = true) @RequestBody CreateMessageDTO messageToCreate)
    {
        Long id = service.createMessage(mapper.toDomain(messageToCreate));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(value = "customers/{idCustomer}/messages")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiOperation(value = "create a message to a customer")
    @ApiResponses({@ApiResponse(code = 201, message = "OK, the message was created"),
            @ApiResponse(code = 404, message = "BAD REQUEST, the json was not acceptable")})
    public ResponseEntity<Object> createMessage(@ApiParam(value = "the customer's technical identifier", required = true) @PathVariable(name = "idCustomer")Long idCustomer, @ApiParam(value = "the message to create", required = true) @RequestBody CreateMessageDTO messageToCreate)
    {
        Message message = mapper.toDomain(messageToCreate);
        message.setCustomer(new Customer());
        message.getCustomer().setIdCustomer(idCustomer);
        Long id = service.createMessage(message);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "messages/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "get message by technical identifier")
    public ResponseEntity<GetMessageDTO> getMessageById(@ApiParam(value = "the customer's technical identifier", required = true) @PathVariable(name = "id") Long idCustomer) {
        return ResponseEntity.ok().body(mapper.toDTO(service.getMessageById(idCustomer)));
    }

    @GetMapping(value = "/customers/{idCustomer}/messages/{idMessage}")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "get message by technical identifier for a customer")
    public ResponseEntity<GetMessageDTO> getMessageCustomerById(
            @ApiParam(value = "the customer's technical identifier", required = true) @PathVariable(name = "idCustomer") Long idCustomer,
            @ApiParam(value = "the message's technical identifier", required = true) @PathVariable(name = "idMessage") Long idMessage) {
        return ResponseEntity.ok().body(mapper.toDTO(service.getMessageCustomerById(idCustomer, idMessage)));
    }
}
