package com.vtn.services.implement;

import com.vtn.dto.customer.CustomerDTO;
import com.vtn.pojo.Customer;
import com.vtn.pojo.Invoice;
import com.vtn.pojo.User;
import com.vtn.repository.CustomerRepository;
import com.vtn.repository.InvoiceRepository;
import com.vtn.repository.UserRepository;
import com.vtn.services.CustomerService;
import com.vtn.util.Utils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Transactional
public class CustomerServiceImplement implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Customer findById(Long id) {
        return this.customerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        this.customerRepository.update(customer);
    }

    @Override
    public void delete(Long id) {
        Customer customer = this.customerRepository.findById(id);
        User user = customer.getUser();
        List<Invoice> invoices = new ArrayList<>(user.getInvoiceSet());
        invoices.forEach(invoice -> {
            invoice.setUser(null);
            this.invoiceRepository.update(invoice);
        });

        this.customerRepository.delete(id);
    }

    @Override
    public Long count() {
        return this.customerRepository.count();
    }

    @Override
    public List<Customer> findAllWithFilter(Map<String, String> params) {
        return this.customerRepository.findAllWithFilter(params);
    }

    @Override
    public CustomerDTO getCustomerResponse(@NotNull Customer customer) {
        return CustomerDTO.builder()
                .lastName(customer.getLastName())
                .middleName(customer.getMiddleName())
                .firstName(customer.getFirstName())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .gender(customer.getGender())
                .dateOfBirth(customer.getDateOfBirth())
                .build();
    }

    @Override
    public Customer getProfileCustomer(String username) {
        User user = this.userRepository.findByUsername(username);

        return this.customerRepository.findByUser(user);
    }

    @Override
    public CustomerDTO updateProfileCustomer(String username, CustomerDTO customerDTO) {
        User user = this.userRepository.findByUsername(username);

        if (!user.getConfirm()) {
            throw new IllegalArgumentException("Tài khoản chưa được xác nhận");
        }

        Customer customer = this.customerRepository.findByUser(user);

        Field[] fields = CustomerDTO.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(customerDTO);

                if (value != null && !value.toString().isEmpty()) {
                    Field customerField = Customer.class.getDeclaredField(field.getName());
                    customerField.setAccessible(true);

                    Object convertedValue = Utils.convertValue(customerField.getType(), value.toString());
                    customerField.set(customer, convertedValue);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                Logger.getLogger(UserServiceImplement.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        this.customerRepository.update(customer);

        return this.getCustomerResponse(customer);
    }
}
