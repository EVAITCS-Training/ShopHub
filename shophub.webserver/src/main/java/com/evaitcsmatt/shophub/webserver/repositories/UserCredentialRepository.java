package com.evaitcsmatt.shophub.webserver.repositories;

import com.evaitcsmatt.shophub.webserver.entities.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<UserCredential,String> {
}
