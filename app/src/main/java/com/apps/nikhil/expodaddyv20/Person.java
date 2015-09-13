package com.apps.nikhil.expodaddyv20;

/**
 * Created by Nikhil on 29-08-2015.
 */
public class Person {

        private String name=null;
        private String surname;
        private String address=null;
        private String phoneNumber=null;
        private String email=null, gender=null, password=null, age=null, zipCode=null;
        private double locationLat=0.0, locationLong=0.0;


//Getter

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }


    public String getNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getZip() {
        return zipCode;
    }

    public double getLat() {
        return locationLat;
    }

    public double getLong() {
        return locationLong;
    }

    //Setter


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phoneNumber = phone;
    }

    public void setZip(String zip) {
        this.zipCode = zip;
    }

    public void setLocation(String location) {
        if (location.length() ==0){
            this.locationLat=0.0;
            this.locationLong=0.0;
        }
        else {
            String[] value = location.split(",");
            this.locationLat = Double.parseDouble(value[0]);
            this.locationLong = Double.parseDouble(value[1]);
        }
    }

    public void setGender(String gender) {
        this.gender = gender;
    }







}
