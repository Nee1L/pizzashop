package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.SignupRequest;
import org.example.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authorization")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignupRequest signupRequest) throws Exception {

        customerService.signup(signupRequest);
    }

}
