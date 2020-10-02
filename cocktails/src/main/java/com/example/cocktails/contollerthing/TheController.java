package com.example.cocktails.contollerthing;

import com.example.cocktails.Drink;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TheController {
    String Base_URL = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=";

    //transfors the response from API to a JSON format
    ObjectMapper objectMapper = new ObjectMapper();
    RestTemplate restTemplate = new RestTemplate();


    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public List<Drink> getMapping(@RequestParam String drinkName ) throws JsonProcessingException {
        HttpEntity<String> httpEntity = new HttpEntity<>(new HttpHeaders());

        ResponseEntity<String> response = restTemplate.exchange(Base_URL + drinkName, HttpMethod.GET, httpEntity, String.class);
        // JsonNode helps store jason objects
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        System.out.println(jsonNode.path("drinks").size());

        List<Drink> drinks = new ArrayList<Drink>();

        for (int i = 0; i < jsonNode.path("drinks").size(); i++) {


            String name = jsonNode.path("drinks").path(i).path("strDrink").toString();
            String ingredient = jsonNode.path("drinks").path(i).path("strIngredient1").toString();
            Drink newDrink = new Drink(name, ingredient);
            drinks.add(newDrink);

        }
        return drinks;
    }
}