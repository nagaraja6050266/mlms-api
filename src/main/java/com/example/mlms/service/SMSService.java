package com.example.mlms.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.github.cdimascio.dotenv.Dotenv;

public class SMSService {

    private static final Dotenv dotenv = Dotenv.load();

    public static final String ACCOUNT_SID = dotenv.get("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = dotenv.get("TWILIO_AUTH_TOKEN");
    public static final String TWILIO_NUMBER = dotenv.get("TWILIO_PHONE_NUMBER");

    public static void sendSMS(String recipientPhoneNumber, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber(recipientPhoneNumber), // to
                new PhoneNumber(TWILIO_NUMBER), // from (your Twilio phone number)
                messageBody)
            .create();

        System.out.println(message.getSid()); // Optional: print message SID for tracking
    }

}