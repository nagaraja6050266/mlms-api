package com.example.mlms.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {
    private static final Map<String, String> otpStorage = new HashMap<>();
    private final Random random = new Random();

    public String generateOtp(String mobileNumber, String msgBody) {
        String otp = String.format("%04d", random.nextInt(10000));
        otpStorage.put(mobileNumber, otp);
        String thanksMsg = "\n\nThanks for Registering!\nYour OTP is: "+otp;
        StringBuilder message;
        if(msgBody == null || msgBody.isEmpty()){
            message = new StringBuilder(thanksMsg);
        } else {
            message = new StringBuilder(msgBody);
            message.append(thanksMsg);
        }
        SMSService.sendSMS(mobileNumber, message.toString());
        System.out.println("OTP sent to " + mobileNumber + ": " + otp);
        return otp;
    }

    public boolean verifyOtp(String mobileNumber, String otp) {
        return otpStorage.remove(mobileNumber,otp);
    }

    public String append(String mobileNumber){
        if(mobileNumber.startsWith("+")){
            return mobileNumber;
        }
        return "+91"+mobileNumber;
    }
}
