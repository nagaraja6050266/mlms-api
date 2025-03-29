package com.example.mlms.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {
    private static final Map<String, String> otpStorage = new HashMap<>();
    private final Random random = new Random();

    public String generateOtp(String mobileNumber) {
        String otp = String.format("%04d", random.nextInt(10000));
        otpStorage.put(mobileNumber, otp);
        SMSService.sendSMS(mobileNumber, "Your OTP is: " + otp);
        System.out.println("OTP sent to " + mobileNumber + ": " + otp);
        return otp;
    }

    public boolean verifyOtp(String mobileNumber, String otp) {
        return otp.equals(otpStorage.get(mobileNumber));
    }
}
