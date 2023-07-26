
package com.nictbills.app.activities.tbo.flight.model.seatlayoutmodel;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seat implements Serializable
{

    @SerializedName("AirlineCode")
    @Expose
    private String airlineCode;
    @SerializedName("FlightNumber")
    @Expose
    private String flightNumber;
    @SerializedName("CraftType")
    @Expose
    private String craftType;
    @SerializedName("Origin")
    @Expose
    private String origin;
    @SerializedName("Destination")
    @Expose
    private String destination;
    @SerializedName("AvailablityType")
    @Expose
    private int availablityType;
    @SerializedName("Description")
    @Expose
    private int description;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("RowNo")
    @Expose
    private String rowNo;
    @SerializedName("SeatNo")
    @Expose
    private Object seatNo;
    @SerializedName("SeatType")
    @Expose
    private int seatType;
    @SerializedName("SeatWayType")
    @Expose
    private int seatWayType;
    @SerializedName("Compartment")
    @Expose
    private int compartment;
    @SerializedName("Deck")
    @Expose
    private int deck;
    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("Price")
    @Expose
    private int price;
    private final static long serialVersionUID = 8526337036953107238L;

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public Seat withAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
        return this;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Seat withFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
        return this;
    }

    public String getCraftType() {
        return craftType;
    }

    public void setCraftType(String craftType) {
        this.craftType = craftType;
    }

    public Seat withCraftType(String craftType) {
        this.craftType = craftType;
        return this;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Seat withOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Seat withDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public int getAvailablityType() {
        return availablityType;
    }

    public void setAvailablityType(int availablityType) {
        this.availablityType = availablityType;
    }

    public Seat withAvailablityType(int availablityType) {
        this.availablityType = availablityType;
        return this;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public Seat withDescription(int description) {
        this.description = description;
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Seat withCode(String code) {
        this.code = code;
        return this;
    }

    public String getRowNo() {
        return rowNo;
    }

    public void setRowNo(String rowNo) {
        this.rowNo = rowNo;
    }

    public Seat withRowNo(String rowNo) {
        this.rowNo = rowNo;
        return this;
    }

    public Object getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Object seatNo) {
        this.seatNo = seatNo;
    }

    public Seat withSeatNo(Object seatNo) {
        this.seatNo = seatNo;
        return this;
    }

    public int getSeatType() {
        return seatType;
    }

    public void setSeatType(int seatType) {
        this.seatType = seatType;
    }

    public Seat withSeatType(int seatType) {
        this.seatType = seatType;
        return this;
    }

    public int getSeatWayType() {
        return seatWayType;
    }

    public void setSeatWayType(int seatWayType) {
        this.seatWayType = seatWayType;
    }

    public Seat withSeatWayType(int seatWayType) {
        this.seatWayType = seatWayType;
        return this;
    }

    public int getCompartment() {
        return compartment;
    }

    public void setCompartment(int compartment) {
        this.compartment = compartment;
    }

    public Seat withCompartment(int compartment) {
        this.compartment = compartment;
        return this;
    }

    public int getDeck() {
        return deck;
    }

    public void setDeck(int deck) {
        this.deck = deck;
    }

    public Seat withDeck(int deck) {
        this.deck = deck;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Seat withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Seat withPrice(int price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Seat.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("airlineCode");
        sb.append('=');
        sb.append(((this.airlineCode == null)?"<null>":this.airlineCode));
        sb.append(',');
        sb.append("flightNumber");
        sb.append('=');
        sb.append(((this.flightNumber == null)?"<null>":this.flightNumber));
        sb.append(',');
        sb.append("craftType");
        sb.append('=');
        sb.append(((this.craftType == null)?"<null>":this.craftType));
        sb.append(',');
        sb.append("origin");
        sb.append('=');
        sb.append(((this.origin == null)?"<null>":this.origin));
        sb.append(',');
        sb.append("destination");
        sb.append('=');
        sb.append(((this.destination == null)?"<null>":this.destination));
        sb.append(',');
        sb.append("availablityType");
        sb.append('=');
        sb.append(this.availablityType);
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(this.description);
        sb.append(',');
        sb.append("code");
        sb.append('=');
        sb.append(((this.code == null)?"<null>":this.code));
        sb.append(',');
        sb.append("rowNo");
        sb.append('=');
        sb.append(((this.rowNo == null)?"<null>":this.rowNo));
        sb.append(',');
        sb.append("seatNo");
        sb.append('=');
        sb.append(((this.seatNo == null)?"<null>":this.seatNo));
        sb.append(',');
        sb.append("seatType");
        sb.append('=');
        sb.append(this.seatType);
        sb.append(',');
        sb.append("seatWayType");
        sb.append('=');
        sb.append(this.seatWayType);
        sb.append(',');
        sb.append("compartment");
        sb.append('=');
        sb.append(this.compartment);
        sb.append(',');
        sb.append("deck");
        sb.append('=');
        sb.append(this.deck);
        sb.append(',');
        sb.append("currency");
        sb.append('=');
        sb.append(((this.currency == null)?"<null>":this.currency));
        sb.append(',');
        sb.append("price");
        sb.append('=');
        sb.append(this.price);
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
