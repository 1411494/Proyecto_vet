package pe.edu.utp.vet.models;

import java.util.Date;

/**
 * Created by Marco on 17/07/2016.
 */
public class Pet {
    private String pet_id;
    private String dni;
    private String pet_name;
    private String breed;
    private String hair_color;
    private Date birth_date;
    private boolean status;


    private String age_year;
    private String age_month;
    private String age_day;


    public String getPet_id() {
        return pet_id;
    }

    public void setPet_id(String pet_id) {
        this.pet_id = pet_id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPet_name() {
        return pet_name;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAge_year() {
        return age_year;
    }

    public void setAge_year(String age_year) {
        this.age_year = age_year;
    }

    public String getAge_month() {
        return age_month;
    }

    public void setAge_month(String age_month) {
        this.age_month = age_month;
    }

    public String getAge_day() {
        return age_day;
    }

    public void setAge_day(String age_day) {
        this.age_day = age_day;
    }
}
