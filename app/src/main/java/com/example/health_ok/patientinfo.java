package com.example.health_ok;

public class patientinfo {
    private String patientname , pnumber;

    public void setPatientname(String patientname)
    {
        this.patientname = patientname;
    }

    public void setPnumber(String pnumber)
    {
        this.pnumber = pnumber;
    }

    public String getPnumber()
    {
        return pnumber;
    }

    public String getPatientname()
    {
        return patientname;
    }

    public patientinfo(String patientname, String pnumber) {
        this.patientname = patientname;
        this.pnumber = pnumber;
    }
}
