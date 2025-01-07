package api_payloads;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pets {

    public static Map<String, Object> NewPet_ValidCase(){

        Map<String, Object> category= new HashMap<>();
        
        category.put("id", 10);
        category.put("name", "string");

        Map<String, Object> tag= new HashMap<>();

        tag.put("id", 10);
        tag.put("name", "string");

        List<Map<String, Object>> tags = Arrays.asList(tag);

        Map<String, Object> pet= new HashMap<>();

        pet.put("id", 10);
        pet.put("category", category);
        pet.put("name", "doggie");
        pet.put("photoUrls", Arrays.asList("string"));
        pet.put("tags", tags);
        pet.put("status", "available");

        return pet;

    }

    public static Map<String, Object> NewPet_InvalidID(){

        Map<String, Object> category= new HashMap<>();
        
        category.put("id", "aa");     // ID as a string
        category.put("name", "string");

        Map<String, Object> tag= new HashMap<>();

        tag.put("id", 10);
        tag.put("name", "string");

        List<Map<String, Object>> tags = Arrays.asList(tag);

        Map<String, Object> pet= new HashMap<>();

        pet.put("id", 10);
        pet.put("category", category);
        pet.put("name", "doggie");
        pet.put("photoUrls", Arrays.asList("string"));
        pet.put("tags", tags);
        pet.put("status", "available");

        return pet;

    }

    public static Map<String, Object> InvalidPhotoURL(){

        Map<String, Object> category= new HashMap<>();
        
        category.put("id", "aa");     // ID as a string
        category.put("name", "string");

        Map<String, Object> tag= new HashMap<>();

        tag.put("id", 10);
        tag.put("name", "string");

        List<Map<String, Object>> tags = Arrays.asList(tag);

        Map<String, Object> pet= new HashMap<>();

        pet.put("id", 10);
        pet.put("category", category);
        pet.put("name", "doggie");
        pet.put("photoUrls", "string");
        pet.put("tags", tags);
        pet.put("status", "available");

        return pet;

    }
    
}
