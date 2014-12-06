package util;


import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Sms;
/**
 * Created by gaurav on 12/2/14.
 */
public class SendSMSNotifications {
    /* Twilio REST API version */
    public static final String ACCOUNTSID = "AC7e5705f51fb6c9dd3b8b0a6c66390f51";
    public static final String AUTHTOKEN = "b0d8b73ee2d31d2dd6d7dd7baf939f4d";

    public static void sendSMS(String toPhoneNumber,String Receiver){

        /* Instantiate a new Twilio Rest Client */
        TwilioRestClient client = new TwilioRestClient(ACCOUNTSID, AUTHTOKEN);

        // Get the account and call factory class
        Account acct = client.getAccount();
        SmsFactory smsFactory = acct.getSmsFactory();

        String fromNumber = "408-512-3078";
        String toNumber = toPhoneNumber;

        //build map of post parameters
        Map<String,String> params = new HashMap<String,String>();
        params.put("From", fromNumber);
        params.put("To", toNumber);
        params.put("Body", "Hi "+Receiver+"! This is Spartan Bike Share. Your bike reservation details are as follows.." +
                "Have a safe ride!");

        try {
            // send an sms a call
            // ( This makes a POST request to the SMS/Messages resource)
            Sms sms = smsFactory.create(params);
            System.out.println("Success sending SMS: " + sms.getSid());
        }
        catch (TwilioRestException e) {
            e.printStackTrace();
        }
    }
}
