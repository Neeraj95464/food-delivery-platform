package com.ynmio.AUTH_SERVICE.service;

import com.ynmio.AUTH_SERVICE.dto.AuthenticationRequest;
import com.ynmio.AUTH_SERVICE.dto.AuthenticationResponse;
import com.ynmio.AUTH_SERVICE.model.MyUserCredential;
import com.ynmio.AUTH_SERVICE.repository.MyUserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MyUserCredentialService {

    @Autowired
    private MyUserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    public MyUserCredential saveUser(MyUserCredential credential){
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        return repository.save(credential);
    }
    public ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest){
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        MyUserCredential user = repository.findByEmail(authenticationRequest.getEmail());
        List<String> role = user.getRole();

        final String jwt = jwtUtil.generateToken(userDetails, String.valueOf(role));

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    public boolean validateToken(String token){
        return jwtUtil.validateToken(token);
    }
    public List<MyUserCredential> getAllUsers(){
        return repository.findAll();
    }
}
