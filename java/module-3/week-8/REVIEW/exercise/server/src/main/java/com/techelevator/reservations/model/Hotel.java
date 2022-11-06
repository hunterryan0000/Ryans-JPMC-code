package com.techelevator.reservations.model;

public class Hotel {

    private int id;
    private String name;
    private Address address;
    private int stars;
    private int roomsAvailable;
    private double costPerNight;
    private String coverImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getRoomsAvailable() {
        return roomsAvailable;
    }

    public void setRoomsAvailable(int roomsAvailable) {
        this.roomsAvailable = roomsAvailable;
    }

    public double getCostPerNight() {
        return costPerNight;
    }

    public void setCostPerNight(double costPerNight) {
        this.costPerNight = costPerNight;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Hotel() {
    }

    public Hotel(int id, String name, Address address, int stars, int roomsAvailable, double costPerNight) {
        this(id, name, address, stars, roomsAvailable, costPerNight, "hotel.png");
    }

    public Hotel(int id, String name, Address address, int stars, int roomsAvailable, double costPerNight, String coverImage) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.stars = stars;
        this.roomsAvailable = roomsAvailable;
        this.costPerNight = costPerNight;
        this.coverImage = coverImage;
    }

    @Override
    public String toString() {
        return "\n--------------------------------------------" +
                "\n Hotel Details" +
                "\n--------------------------------------------" +
                "\n Id: " + id +
                "\n Name:'" + name + '\'' +
                "\n Stars: " + stars +
                "\n RoomsAvailable: " + roomsAvailable +
                "\n Cost Per Night: " + costPerNight +
                "\n Image:" + coverImage;
    }
}
