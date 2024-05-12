package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.entities.Customer;
import org.example.repositories.CustomerRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer;
        try {
            customer = customerRepository.findByUsername(username).orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(customer.getUsername())
                .password(customer.getPassword())
                .build();
    }
}