package com.example.mlms.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSService {

    public static final String ACCOUNT_S = "AC52089691318b8f4ad57d7afa7f3ad5f7";
    public static final String AUTH_T = "9620ecea10c049743b9ca3a5b9365f95";
    public static final String NUMBER = "+19786254569";

    public static void sendSMS(String recipientPhoneNumber, String messageBody) {
        Twilio.init(ACCOUNT_S, AUTH_T);

        Message message = Message.creator(
                new PhoneNumber(recipientPhoneNumber), // to
                new PhoneNumber(NUMBER), // from (your Twilio phone number)
                messageBody)
            .create();

        System.out.println(message.getSid()); // Optional: print message SID for tracking
    }

}