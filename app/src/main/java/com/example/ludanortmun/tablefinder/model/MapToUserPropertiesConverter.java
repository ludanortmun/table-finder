package com.example.ludanortmun.tablefinder.model;

import java.util.Map;
import java.util.function.Function;

public class MapToUserPropertiesConverter implements Function<Map<String, String>, UserProperties> {

    @Override
    public UserProperties apply(Map<String, String> userPropertiesMap) {
        String email = userPropertiesMap.get("email");
        String name = userPropertiesMap.get("name");
        String gender = userPropertiesMap.get("gender");
        String city = userPropertiesMap.get("locale");
        String birthdate = userPropertiesMap.get("birthdate");
        String favoriteGenre = userPropertiesMap.get("custom:favorite_genre");
        String about = userPropertiesMap.get("custom:about");

        UserProperties properties = new UserProperties(email, name, birthdate, gender, city, favoriteGenre, about);

        return properties;
    }
}
