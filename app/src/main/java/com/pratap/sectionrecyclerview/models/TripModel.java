package com.pratap.sectionrecyclerview.models;

public class TripModel {

    String tripId;
    String tripName;
    String tripCoverPic;

    public TripModel(String tripId, String tripName, String tripCoverPic) {
        this.tripId = tripId;
        this.tripName = tripName;
        this.tripCoverPic = tripCoverPic;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getTripCoverPic() {
        return tripCoverPic;
    }

    public void setTripCoverPic(String tripCoverPic) {
        this.tripCoverPic = tripCoverPic;
    }
}
