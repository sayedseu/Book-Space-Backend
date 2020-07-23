package com.example.bookspace.model.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OTP {
    @Id
    @NotNull
    private String phoneNumber;

    @NotNull
    @Size(max = 4)
    private String otpCode;

    @NotNull
    private LocalDateTime generatedTime;

    @NotNull
    private LocalDateTime validTime;
}
