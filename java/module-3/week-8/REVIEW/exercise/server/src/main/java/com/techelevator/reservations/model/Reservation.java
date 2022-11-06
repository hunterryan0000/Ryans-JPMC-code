package com.techelevator.reservations.model;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.*;

import java.time.LocalDate;

public class Reservation {

    private int id;
    @Min( value = 1, message = "The field 'hotel id' is required.")
    private int hotelId;
    @NotBlank( message = "The field 'full name' is required.")
    private String fullName;
    @NotNull( message = "The field 'checkin date' is required.")
    @FutureOrPresent( message = "Checkin date must be today or later")
    private LocalDate checkinDate;
    @NotNull( message = "The field 'checkout date' is required.")
    @Future( message = "Checkout date must be after today")
    private LocalDate checkoutDate;
    @Min( value = 1, message = "The minimum number of guests is 1")
    @Max( value = 5, message = "The maximum number of guests is 5")
    private int guests;

    public Reservation() {
    }

    public Reservation(int id, int hotelId, String fullName, LocalDate checkinDate, LocalDate checkoutDate, int guests) {
        this.id = id;
        this.hotelId = hotelId;
        this.fullName = fullName;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.guests = guests;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    @Override
    public String toString() {
        return "\n--------------------------------------------" +
                "\n Reservation Details" +
                "\n--------------------------------------------" +
                "\n Id: " + id +
                "\n Hotel Id: " + hotelId +
                "\n Full Name: " + fullName +
                "\n Checkin Date: " + checkinDate +
                "\n Checkout Date: " + checkoutDate +
                "\n Guests: " + guests;
    }
}
