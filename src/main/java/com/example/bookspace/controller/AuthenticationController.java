package com.example.bookspace.controller;

import com.example.bookspace.exception.ResourceAlreadyExistException;
import com.example.bookspace.exception.ResourceNotFoundException;
import com.example.bookspace.model.auth.User;
import com.example.bookspace.service.auth.AuthenticationService;
import com.example.bookspace.service.auth.OtpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class AuthenticationController {
    private AuthenticationService authenticationService;
    private OtpService otpService;

    public AuthenticationController(AuthenticationService authenticationService, OtpService otpService) {
        this.authenticationService = authenticationService;
        this.otpService = otpService;
    }

    @PostMapping(value = "/auth/reg", consumes = {"application/json"})
    public ResponseEntity<Long> register(@RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authenticationService.register(user));
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/auth/{username}/{password}")
    public ResponseEntity<User> authenticate(@PathVariable(value = "username") String username, @PathVariable(value = "password") String password) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authenticationService.authenticate(username, password));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/auth/update/{number}/{newPassword}")
    public ResponseEntity<User> updatePassword(@PathVariable(value = "number") String number, @PathVariable(value = "newPassword") String password) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authenticationService.updatePassword(number, password));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/auth/update", consumes = {"application/json"})
    public ResponseEntity<User> updateProfile(@RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authenticationService.updateProfile(user));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/otp/send/{number}")
    public ResponseEntity<Integer> sendOtp(@PathVariable(value = "number") String number) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(otpService.sendOtp(number));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/otp/verify/{number}/{code}")
    public ResponseEntity<Integer> verifyOtp(@PathVariable(value = "number") String number, @PathVariable(value = "code") String code) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(otpService.verifyOtp(number, code));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "auth/check/{number}/{email}")
    public ResponseEntity<Integer> checkDuplicateEntry(@PathVariable(value = "number") String number, @PathVariable(value = "email") String email) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authenticationService.checkDuplicateUser(number, email));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
