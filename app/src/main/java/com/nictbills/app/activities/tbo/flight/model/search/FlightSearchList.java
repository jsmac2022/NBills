package com.nictbills.app.activities.tbo.flight.model.search;

public class FlightSearchList {
    String AirlineCode;
    String flightname;
    String flightno;
    String flighseatavilable;
    String CityCode_origin;
    String CityCode_destination;
    String DepartTime;
    String ArriveTime;
    String PublishFared;
    String Offerfared;
    String duration;
    String fulldepttime;
    String fullarrtime;
    String ResulrIndex;
    String isLLC;
    public String getIsLLC() {
        return isLLC;
    }

    public void setIsLLC(String isLLC) {
        this.isLLC = isLLC;
    }


    public String getResulrIndex() {
        return ResulrIndex;
    }

    public void setResulrIndex(String resulrIndex) {
        ResulrIndex = resulrIndex;
    }

    public String getFulldepttime() {
        return fulldepttime;
    }

    public void setFulldepttime(String fulldepttime) {
        this.fulldepttime = fulldepttime;
    }

    public String getFullarrtime() {
        return fullarrtime;
    }

    public void setFullarrtime(String fullarrtime) {
        this.fullarrtime = fullarrtime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


    public String getPublishFared() {
        return PublishFared;
    }

    public void setPublishFared(String publishFared) {
        PublishFared = publishFared;
    }

    public String getOfferfared() {
        return Offerfared;
    }

    public void setOfferfared(String offerfared) {
        Offerfared = offerfared;
    }


    public String getCityCode_origin() {
        return CityCode_origin;
    }

    public void setCityCode_origin(String cityCode_origin) {
        CityCode_origin = cityCode_origin;
    }

    public String getCityCode_destination() {
        return CityCode_destination;
    }

    public void setCityCode_destination(String cityCode_destination) {
        CityCode_destination = cityCode_destination;
    }

    public FlightSearchList() {

    }

    public String getFlighseatavilable() {
        return flighseatavilable;
    }

    public void setFlighseatavilable(String flighseatavilable) {
        this.flighseatavilable = flighseatavilable;
    }

    public String getFlightname() {
        return flightname;
    }

    public void setFlightname(String flightname) {
        this.flightname = flightname;
    }

    public String getFlightno() {
        return flightno;
    }

    public void setFlightno(String flightno) {
        this.flightno = flightno;
    }

    public String getAirlineCode() {
        return AirlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        AirlineCode = airlineCode;
    }
    public String getDepartTime() {
        return DepartTime;
    }

    public void setDepartTime(String departTime) {
        DepartTime = departTime;
    }

    public String getArriveTime() {
        return ArriveTime;
    }

    public void setArriveTime(String arriveTime) {
        ArriveTime = arriveTime;
    }

}
