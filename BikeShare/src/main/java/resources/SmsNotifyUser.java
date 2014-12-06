package resources;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by gaurav on 12/2/14.
 */
public class SmsNotifyUser {

    @NotEmpty(message = "Please enter the phone number to whom the message has to be sent.")
    private String toPhoneNumber;
    private String receiver;

    @NotEmpty(message = "Please enter the name of the receiver to whom the message has to be sent.")
    public String getToPhoneNumber() {
        return toPhoneNumber;
    }

    public void setToPhoneNumber(String toPhoneNumber) {
        this.toPhoneNumber = toPhoneNumber;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
