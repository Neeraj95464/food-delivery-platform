package com.ynmio.AUTH_SERVICE.repository;

import com.ynmio.AUTH_SERVICE.model.MyUserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserCredentialRepository extends JpaRepository<MyUserCredential,Long> {

    MyUserCredential findByEmail(String email);
}
