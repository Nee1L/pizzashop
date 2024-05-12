package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.SigninRequest;
import org.example.dto.SignupRequest;
import org.example.security.JwtCore;
import org.example.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authorization")
public class CustomerController  {

    private final CustomerService customerService;

    private final AuthenticationManager authenticationManager;

    private final JwtCore jwtCore;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignupRequest signupRequest) throws Exception {

        customerService.signup(signupRequest);
    }

    @PostMapping("/sign-in")
    ResponseEntity<?> signIn(@RequestBody SigninRequest signinRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(signinRequest.getUsername());
        return ResponseEntity.ok(jwt);
    }

}
