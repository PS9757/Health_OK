package com.example.health_ok;

public class doctorInfo {
    private String doctorname, location,uid,specialist;

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getSpecialist() {
        return specialist;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public String getLocation() {
        return location;
    }

    public String getUid() {
        return uid;
    }

    doctorInfo(String doctorname, String location,String uid,String specialist) {
        this.doctorname = doctorname;
        this.location = location;
        this.uid=uid;
        this.specialist=specialist;

    }
}
