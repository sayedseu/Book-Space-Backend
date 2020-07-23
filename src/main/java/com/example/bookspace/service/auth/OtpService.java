package com.example.bookspace.service.auth;

import com.example.bookspace.exception.ResourceNotFoundException;
import com.example.bookspace.model.auth.OTP;
import com.example.bookspace.repository.auth.OtpRepository;
import com.example.bookspace.utils.OTPManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpService {
    @Value("${otp.username}")
    private String otpUserName;
    @Value("${otp.password}")
    private String otpPassword;
    @Value("${otp.baseURL}")
    private String otpBaseUrl;

    private OtpRepository otpRepository;

    public OtpService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    public int sendOtp(String number) throws ResourceNotFoundException {
        final String otpCode = OTPManager.send(otpBaseUrl, otpUserName, otpPassword, number);
        System.out.println(otpCode);
        if (otpCode == null) {
            throw new ResourceNotFoundException(number);
        } else {
            Optional<OTP> optionalOTP = otpRepository.findById(number);
            if (optionalOTP.isPresent()) {
                OTP otp = optionalOTP.get();
                otp.setGeneratedTime(LocalDateTime.now());
                otp.setValidTime(LocalDateTime.now().plusMinutes(5));
                otp.setOtpCode(otpCode);
                otpRepository.save(otp);
                return 200;
            } else {
                OTP otp = new OTP(number, otpCode, LocalDateTime.now(), LocalDateTime.now().plusMinutes(5));
                otpRepository.save(otp);
                return 200;
            }
        }
    }

    public int verifyOtp(String number, String code) throws ResourceNotFoundException {
        Optional<OTP> optionalOTP = otpRepository.findById(number);
        if (optionalOTP.isPresent()) {
            OTP otp = optionalOTP.get();
            int timeDifference = LocalDateTime.now().compareTo(otp.getValidTime());
            if (timeDifference <= 0) {
                if (otp.getOtpCode().equals(code)) {
                    otpRepository.deleteById(number);
                    return 200;
                } else {
                    return 500;
                }
            } else {
                return 500;
            }
        } else {
            throw new ResourceNotFoundException(number);
        }
    }
}
