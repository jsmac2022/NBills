package com.nictbills.app.activities.tbo.flight.model;

public class FlightSeatModel {
    String seat_code;
    String avilability_type;
    String price;



    private boolean isSelected = false;

    public FlightSeatModel() {
    }

    public String getAvilability_type() {
        return avilability_type;
    }

    public void setAvilability_type(String avilability_type) {
        this.avilability_type = avilability_type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public FlightSeatModel(String seat_code) {
        this.seat_code = seat_code;
    }

    public String getSeat_code() {
        return seat_code;
    }

    public void setSeat_code(String seat_code) {
        this.seat_code = seat_code;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

}
