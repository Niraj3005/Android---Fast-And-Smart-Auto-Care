package com.example.onroadremotevehicleassistantapp.Model;

public class RequestModel {
    int requestId,requestMechanicId,requestUserId;
    String requestVehicleno,requestVehicletype,requestServiceName,requestCharges,requestLocation,requestDescription,requestDate;
    boolean requestStatus;

    public RequestModel(int requestId, int requestMechanicId, int requestUserId, String requestVehicleno, String requestVehicletype, String requestServiceName, String requestCharges, String requestLocation, String requestDescription, String requestDate, boolean requestStatus) {
        this.requestId = requestId;
        this.requestMechanicId = requestMechanicId;
        this.requestUserId = requestUserId;
        this.requestVehicleno = requestVehicleno;
        this.requestVehicletype = requestVehicletype;
        this.requestServiceName = requestServiceName;
        this.requestCharges = requestCharges;
        this.requestLocation = requestLocation;
        this.requestDescription = requestDescription;
        this.requestDate = requestDate;
        this.requestStatus = requestStatus;
    }

    @Override
    public String toString() {
        return "RequestModel{" +
                "requestId=" + requestId +
                ", requestMechanicId=" + requestMechanicId +
                ", requestUserId=" + requestUserId +
                ", requestVehicleno='" + requestVehicleno + '\'' +
                ", requestVehicletype='" + requestVehicletype + '\'' +
                ", requestServiceName='" + requestServiceName + '\'' +
                ", requestCharges='" + requestCharges + '\'' +
                ", requestLocation='" + requestLocation + '\'' +
                ", requestDescription='" + requestDescription + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", requestStatus=" + requestStatus +
                '}';
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getRequestMechanicId() {
        return requestMechanicId;
    }

    public void setRequestMechanicId(int requestMechanicId) {
        this.requestMechanicId = requestMechanicId;
    }

    public int getRequestUserId() {
        return requestUserId;
    }

    public void setRequestUserId(int requestUserId) {
        this.requestUserId = requestUserId;
    }

    public String getRequestVehicleno() {
        return requestVehicleno;
    }

    public void setRequestVehicleno(String requestVehicleno) {
        this.requestVehicleno = requestVehicleno;
    }

    public String getRequestVehicletype() {
        return requestVehicletype;
    }

    public void setRequestVehicletype(String requestVehicletype) {
        this.requestVehicletype = requestVehicletype;
    }

    public String getRequestServiceName() {
        return requestServiceName;
    }

    public void setRequestServiceName(String requestServiceName) {
        this.requestServiceName = requestServiceName;
    }

    public String getRequestCharges() {
        return requestCharges;
    }

    public void setRequestCharges(String requestCharges) {
        this.requestCharges = requestCharges;
    }

    public String getRequestLocation() {
        return requestLocation;
    }

    public void setRequestLocation(String requestLocation) {
        this.requestLocation = requestLocation;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public boolean isRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }
}
