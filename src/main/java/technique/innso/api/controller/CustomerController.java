package technique.innso.api.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import technique.innso.api.dto.CreateCustomerDTO;
import technique.innso.api.dto.GetCustomerDTO;
import technique.innso.api.dto.UpdateCustomerDTO;
import technique.innso.business.mapper.CustomerMapper;
import technique.innso.business.service.CustomerService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Api(tags = "Customer", consumes = "application/json")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerMapper mapper;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiOperation(value = "create a customer")
    @ApiResponses({
            @ApiResponse(code = 201, message = "OK, the customer was created"),
            @ApiResponse(code = 404, message = "BAD REQUEST, the json was not acceptable")
    })
    public ResponseEntity<Object> createCustomer(@ApiParam(value = "the customer to create", required = true) @RequestBody CreateCustomerDTO customer) {
        Long id = service.createCustomer(mapper.toDomain(customer));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "get all customers")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK, all the customers were returned")
    })
    public ResponseEntity<List<GetCustomerDTO>> getCustomers() {
        return ResponseEntity.ok().body(mapper.toDTOs(service.getAllCustomers()));
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "update a customer")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK, the customer was updated"),
            @ApiResponse(code = 404, message = "BAD REQUEST, the json was not acceptable")
    })
    public void updateCustomer(@ApiParam(value = "the message to update", required = true) @RequestBody UpdateCustomerDTO customerToUpdate) {
        service.updateCustomer(mapper.toDomain(customerToUpdate));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @ApiOperation(value = "get customer by technical identifier")
    public ResponseEntity<GetCustomerDTO> getCustomerById(@ApiParam(value = "the customer's technical identifier", required = true) @PathVariable(name = "id") Long idCustomer) {
        return ResponseEntity.ok().body(mapper.toDTO(service.getCustomer(idCustomer)));
    }
}
