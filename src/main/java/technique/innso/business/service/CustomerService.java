package technique.innso.business.service;

import technique.innso.business.domain.Customer;

import java.util.List;

public interface CustomerService {

    Long createCustomer(Customer customer);

    void updateCustomer(Customer customerToUpdate);

    List<Customer> getAllCustomers();

    Customer getCustomer(Long idCustomer);
}
