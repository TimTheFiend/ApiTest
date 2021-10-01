package com.example.apitest;

import com.example.apitest.Relationship;

import java.io.Serializable;

public class Person implements Serializable {
    private int id;
    private String name;
    private String job;
    private int dob;
    private boolean driversLicense;
    private char sex;
    private String relationship;

    public Person() {}


    public Person(String name, String job, int dob, boolean driversLicense, char sex, String relationship) {
        this.name = name;
        this.job = job;
        this.dob = dob;
        this.driversLicense = driversLicense;
        this.sex = sex;
        this.relationship = relationship;
    }

    public Person(int id, String name, String job, int dob, boolean driversLicense, char sex, String relationship) {
        // super();
        this.id = id;
        this.name = name;
        this.job = job;
        this.dob = dob;
        this.driversLicense = driversLicense;
        this.sex = sex;
        this.relationship = relationship;
    }


    /* ID GET SET */
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    /* Name GET SET */
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    /* Job GET SET */
    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }
    /* DOB (Date Of Birth) GET SET */
    public int getDob() { return dob; }
    public void setDob(int dob) { this.dob = dob; }

    /* Drivers License GET SET */
    public boolean getDriversLicense() { return driversLicense; }
    public int dbGetDriversLicense() {
        if (getDriversLicense()) {
            return 1;
        }
        return 0;
    }
    public void setDriversLicense(boolean driversLicense) { this.driversLicense = driversLicense; }

    /* Sex GET SET */
    public char getSex() { return sex; }
    public void setSex(char sex) { this.sex = sex; }
    /* Relationship GET SET*/
    public String getRelationship() { return relationship; }
    public void setRelationship(String relationship) { this.relationship = relationship; }


}
