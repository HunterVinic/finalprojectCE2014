package com.example.finalprojectce2014;

import java.util.Date;

public class studentData {

    private Integer ssn;
    private String firstName;
    private String mi;
    private String lastName;
    private String phone;
    private Date birthDate;
    private  String street;
    private  Integer zipcode;
    private String deptid;



    public studentData(Integer ssn, String firstName, String mi, String lastName,
                       String phone, Date birthDate, String street, Integer zipcode,
                       String deptid)
    {
        this.ssn =ssn;
        this.firstName = firstName;
        this.mi = mi;
        this.lastName = lastName;
        this.phone = phone;
        this.birthDate= birthDate;
        this.street = street;
        this.zipcode = zipcode;
        this.deptid = deptid;
    }




    public Integer getSsn(){
        return ssn;
    }
    public  String getFirstName(){
        return firstName;
    }
    public  String getMi(){
        return  mi;
    }
    public  String getLastName(){
        return  lastName;
    }
    public  String getPhone(){
        return  phone;
    }
    public Date getBirthDate(){
        return  birthDate;
    }
    public String getStreet(){
        return  street;
    }
    public Integer getZipcode(){
        return zipcode;
    }
    public String getDeptid(){
        return deptid;
    }
}
