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
    public static final String ACCOUNTSID = "AC7e5705f51fb6c9dd3b8b0a6c66390f51";
    public static final String AUTHTOKEN = "b0d8b73ee2d31d2dd6d7dd7baf939f4d";
    public static final String twilioNumber = "408-512-3078";

    public static void sendSMSOnReservation(String toPhoneNumber,String Receiver){

        String toNumber = toPhoneNumber;

        //build map of post parameters
        Map<String,String> params = new HashMap<String,String>();
        params.put("From", twilioNumber);
        params.put("To", toNumber);
        params.put("Body", "Hi "+Receiver+"! This is Spartan Bike Share. Your bike reservation details are as follows.." +
                "Have a safe ride!");


        sendSMSUsingTwilio(params);
    }

    public static void sendSMSOnSignUp(String toPhoneNumber,String Receiver){

        String toNumber = toPhoneNumber;

        //build map of post parameters
        Map<String,String> params = new HashMap<String,String>();
        params.put("From", twilioNumber);
        params.put("To", toNumber);
        params.put("Body", "Hi "+Receiver+"! Welcome to Spartan Bike Share.");

        sendSMSUsingTwilio(params);
    }

    public static void sendSMSUsingTwilio(Map<String,String> params){
        try {
            TwilioRestClient client = new TwilioRestClient(ACCOUNTSID, AUTHTOKEN);
            Account acct = client.getAccount();
            SmsFactory smsFactory = acct.getSmsFactory();

            Sms sms = smsFactory.create(params);
            //System.out.println("Success sending SMS: " + sms.getSid());
        }
        catch (TwilioRestException e) {
            e.printStackTrace();
        }
    }


}
