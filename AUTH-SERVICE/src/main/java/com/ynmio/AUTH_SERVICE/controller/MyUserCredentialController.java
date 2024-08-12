package com.ynmio.AUTH_SERVICE.controller;

import com.ynmio.AUTH_SERVICE.dto.AuthenticationRequest;
import com.ynmio.AUTH_SERVICE.model.MyUserCredential;
import com.ynmio.AUTH_SERVICE.service.MyUserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class MyUserCredentialController {

    @Autowired
    private MyUserCredentialService myUserCredentialService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public MyUserCredential createUser(@RequestBody MyUserCredential credential){
        credential.setRole(Collections.singletonList("user"));
        return myUserCredentialService.saveUser(credential);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );

            return myUserCredentialService.authenticate(authenticationRequest);
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

    }

    @PostMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestBody String token) {
        Boolean isValid = myUserCredentialService.validateToken(token);
        return ResponseEntity.ok(isValid);
    }
    @GetMapping("/get-users")
    public List<MyUserCredential> getUsers(){
        // this method is not secure this one written for testing only
        return myUserCredentialService.getAllUsers();
    }
}
