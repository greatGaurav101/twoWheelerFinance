package com.rupyy.authentication_service.repository;

import com.rupyy.authentication_service.entity.SignUpUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<SignUpUser,Long> {
}
