package com.example.mlms.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSService {

    // Find your Account SID and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC9b72739ac5e063efae5337b55965abdf"; // Replace with your Account SID
    public static final String AUTH_TOKEN = "7ee87d73e501fa293e40beebbb73021c"; // Replace with your auth token

    public static void sendSMS(String recipientPhoneNumber, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber(recipientPhoneNumber), // to
                new PhoneNumber("+19897889058"), // from (your Twilio phone number)
                messageBody)
            .create();

        System.out.println(message.getSid()); // Optional: print message SID for tracking
    }

}