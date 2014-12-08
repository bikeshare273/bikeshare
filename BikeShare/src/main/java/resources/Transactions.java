package resources;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.mapping.Document;
import util.serializers.CustomDateSerializer;

import javax.validation.constraints.NotNull;

/**
 * Created by gaurav on 12/6/14.
 */

@Document(collection = "Transactions")
public class Transactions {

    @NotNull(message = "Please enter 'transaction_Id'.")
    private int transaction_id;

    private DateTime date_of_booking;

    @NotNull(message = "Please enter 'User_Id'.")
    private int user_id;

    @NotEmpty(message = "Please enter 'Booked_Bike_Id'.")
    private String booked_bike_id;

    @NotNull(message = "Please enter 'Location'.")
    private int location_id;

    @NotEmpty(message = "Please enter 'From_Hour'.")
    private String from_hour;

    @NotEmpty(message = "Please enter 'To_Hour'.")
    private String to_hour;

    private String booking_status;

    @NotEmpty(message = "Please enter 'User_Payment'.")
    private String user_payment;

    //@JsonSerialize(using = CustomDateSerializer.class)
    private DateTime created_date;

    //@JsonSerialize(using = CustomDateSerializer.class)
    private DateTime updated_date;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getFrom_hour() {
        return from_hour;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public void setFrom_hour(String from_hour) {
        this.from_hour = from_hour;

    }

    public String getTo_hour() {
        return to_hour;
    }

    public void setTo_hour(String to_hour) {
        this.to_hour = to_hour;
    }

    public String getUser_payment() {
        return user_payment;
    }

    public void setUser_payment(String user_payment) {
        this.user_payment = user_payment;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public DateTime getDate_of_booking() {
        return date_of_booking;
    }

    public void setDate_of_booking(DateTime date_of_booking) {
        this.date_of_booking = date_of_booking;
    }

    public String getBooked_bike_id() {
        return booked_bike_id;
    }

    public void setBooked_bike_id(String booked_bike_id) {
        this.booked_bike_id = booked_bike_id;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public DateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(DateTime created_date) {
        this.created_date = created_date;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public DateTime getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(DateTime updated_date) {
        this.updated_date = updated_date;
    }
}
