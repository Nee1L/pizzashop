package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.SignupRequest;
import org.example.entities.Customer;
import org.example.repositories.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;


    public void signup(SignupRequest request) throws Exception {
        String email = request.getUsername();
        Optional<Customer> existingUser = customerRepository.findByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            throw new Exception();
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        Customer customer = new Customer();
        customer.setUsername(request.getUsername());
        customer.setEmail(email);
        customer.setPassword(hashedPassword);
        customerRepository.save(customer);
    }
}