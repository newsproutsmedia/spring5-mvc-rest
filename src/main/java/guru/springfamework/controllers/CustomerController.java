package guru.springfamework.controllers;


import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import guru.springfamework.setup.Variables;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@Api(description = "This is my Customer Controller")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final String BASE_URL = Variables.CUSTOMER_BASE_URL;

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value="This will get a list of customers.", notes = "These are some notes about the API.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getListofCustomers(){

        return new CustomerListDTO(customerService.getAllCustomers());

    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    // @RequestBody tells spring to look at the body of the request itself (JSON) and try to create an object out of it
    // In this case, @RequestBody will look at what is being sent and try to render a CustomerDTO from the data
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createNewCustomer(customerDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomerByDTO(id, customerDTO);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.patchCustomer(id, customerDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {

        customerService.deleteCustomerById(id);
    }
}
