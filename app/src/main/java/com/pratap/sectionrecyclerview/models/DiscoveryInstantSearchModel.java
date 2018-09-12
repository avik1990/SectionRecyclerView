package com.pratap.sectionrecyclerview.models;

import java.io.Serializable;

public class DiscoveryInstantSearchModel implements Serializable {


    String userId;
    String userName;
    String userFirstName;
    String userLastName;
    String userProfilePic;


    String tripId;
    String tripName;
    String tripCoverPic;

    int itemType;
    String seemoreUser;


    public DiscoveryInstantSearchModel(String userId, String userName, String userFirstName, String userLastName, String userProfilePic, String tripId, String tripName, String tripCoverPic, int itemType) {
        this.userId = userId;
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userProfilePic = userProfilePic;
        this.tripId = tripId;
        this.tripName = tripName;
        this.tripCoverPic = tripCoverPic;
        this.itemType = itemType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserProfilePic() {
        return userProfilePic;
    }

    public void setUserProfilePic(String userProfilePic) {
        this.userProfilePic = userProfilePic;
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

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
