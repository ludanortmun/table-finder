package com.example.ludanortmun.tablefinder;

public class UserProperties {
    public String username;
    public String email;
    public String password;
    public String name;
    public String birthdate;
    public String gender;
    public String city;
    public String favoriteGenre;
    public String about;

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

    public UserProperties() { }

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
