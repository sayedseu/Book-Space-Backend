package com.example.bookspace.repository.auth;

import com.example.bookspace.model.auth.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<OTP, String> {
}
