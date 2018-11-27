package com.example.ludanortmun.tablefinder.model;

import java.util.Map;
import java.util.function.Function;

public class UserProperties {
    private String username;
    private String email;
    private String password;
    private String name;
    private String birthdate;
    private String gender;
    private String city;
    private String favoriteGenre;
    private String about;

    public UserProperties(String email, String name, String birthdate, String gender, String city, String favoriteGenre, String about) {
        this.email = email;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.city = city;
        this.favoriteGenre = favoriteGenre;
        this.about = about;
    }

    public UserProperties(String username, String email, String password, String name, String birthdate, String gender, String city, String favoriteGenre, String about) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthdate = birthdate;
        this.gender = gender;
        this.city = city;
        this.favoriteGenre = favoriteGenre;
        this.about = about;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    public String getAbout() {
        return about;
    }
}

