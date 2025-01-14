package api_test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api_endpoints.PetEndPoints;
import io.restassured.response.Response;  
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import static org.hamcrest.Matchers.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;


public class GetPetData {

    private static final Logger log = LogManager.getLogger(GetPetData.class);
    private int id=10;

    public void before_getting_pet(){

        CreatePet createPet = new CreatePet();
        createPet.add_pet();
    }

    @BeforeClass
    public void setup(){

        log.info("************************* Petstore Swagger --[GetPetData.JAVA] -- API TEST STARTED ************************");

    }
    
    @Test(priority = 1)
    @Feature("Pet store API")
    @Story("Get a pet by ID")
    public void find_pet_by_id_also_validate_statusCode(){

        log.info("************************* [GET A PET] TESTCASE 1 STARTED ************************");

        // int id=10;
        int statusCode;

        Response response= PetEndPoints.readpetuser(id);
        response.then().log().all();
        statusCode= response.getStatusCode();

        switch (statusCode) {
            case 200:
                Assert.assertEquals(statusCode, 200);
                log.info("Status Code={} - Success", statusCode);
                break;

            case 404:
                log.info("Status Code={} - Invalid ID supplied", statusCode);
                break;

        default:
                log.info("Status Code={} - Pet not found", statusCode);
                break;

        }

        log.info("************************* [GET A PET] TESTCASE 1 COMPLETED ************************");

    }

    @Test(priority = 2)
    public void find_pet_by_id_and_validate_jsonResponse(){

        log.info("************************* [GET A PET] TESTCASE 2 STARTED ************************");

        // int id=10;

        Response response= PetEndPoints.readpetuser(id);
        // response.then().log().all();
        response.then().body("id", equalTo(id));
        response.then().body("category.id", equalTo(id));
        response.then().body("name", equalTo("doggie"));
        response.then().body("tags[0].id", equalTo(id));
        response.then().body("status", equalTo("available"));

        log.info("************************* [GET A PET] TESTCASE 2 COMPLETED ************************");
 
    }

    @Test(priority = 3)
    public void find_pet_by_id_and_validate_jsonSchema(){

        log.info("************************* [GET A PET] TESTCASE 3 STARTED ************************");

        // int id=10;
        File PetjsonSchemaFile= new File("src/test/resources/JsonSchemas/PetSchema.json");
        // int statusCode;

        Response response= PetEndPoints.readpetuser(id);
        response.then().body(JsonSchemaValidator.matchesJsonSchema(PetjsonSchemaFile));

        log.info("************************* [GET A PET] TESTCASE 3 COMPLETED ************************");
        
    }

    /*---------------------------------------------Get Pet by Status---------------------------------------------*/
    @Test(priority = 4)
    public void find_pet_by_status(){

        log.info("************************* [GET A PET BY STATUS] TESTCASE 1 STARTED ************************");

        before_getting_pet();

        Map<String, String> params = new HashMap<>();
        params.put("status", "available");

        Response response= PetEndPoints.GetByStatus(params);
        int statusCode = response.getStatusCode();
        int numberOfPets = response.jsonPath().getList("").size();
        Assert.assertEquals(statusCode, 200);

        for(int i=0;i< numberOfPets;i++){

            String status = response.jsonPath().getString("["+ i + "].status");
            Assert.assertEquals(status, "available");

        }

        log.info("************************* [GET A PET BY STATUS] TESTCASE 1 COMPLETED ************************");

    }

    @Test(priority = 5)
    public void find_pet_by_all_status(){

        log.info("************************* [GET A PET BY STATUS] TESTCASE 2 STARTED ************************");

        Map<String, String> params = new HashMap<>();

        params.put("status", "sold,available,pending");
        // params.put("status", "available");
        // params.put("status", "pending");

        Response response= PetEndPoints.GetByStatus(params);

        int statusCode = response.getStatusCode();
        int numberOfPets = response.jsonPath().getList("").size();
        Assert.assertEquals(statusCode, 200);

        for(int i=0;i< numberOfPets;i++){

            String status = response.jsonPath().getString("["+ i + "].status");
            Assert.assertTrue(status.equals("sold") || status.equals("available") || status.equals("pending"));

        }

        log.info("************************* [GET A PET BY STATUS] TESTCASE 2 COMPLETED ************************");

    }

    @Test(priority = 6)
    public void find_pet_by_empty_status(){

        log.info("************************* [GET A PET BY STATUS] TESTCASE 3 STARTED ************************");

        Map<String, String> params = new HashMap<>();

        params.put("status", "qwerty");
        
        Response response= PetEndPoints.GetByStatus(params);

        int statusCode = response.getStatusCode();
        int numberOfPets = response.jsonPath().getList("").size();
        Assert.assertEquals(statusCode, 200);

        for(int i=0;i< numberOfPets;i++){

            List<?> pets = response.jsonPath().getList("null");
            Assert.assertTrue(pets.isEmpty());
        }

        log.info("************************* [GET A PET BY STATUS] TESTCASE 3 COMPLETED ************************");

    }

}