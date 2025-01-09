package api_test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api_endpoints.PetEndPoints;
import io.restassured.response.Response;  
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import static org.hamcrest.Matchers.*;

import api_payloads.Pets;

public class CreatePet {

    private static final Logger log = LogManager.getLogger(CreatePet.class);

    @BeforeClass
    public void setup(){

        log.info("************************* Petstore Swagger API TEST STARTED ************************");

    }

    @Test(priority = 1)
    public void add_pet(){

        log.info("************************* [CREATE] TEST CASE 1 STARTED ************************");

        int statusCode = 200;
        Response response = PetEndPoints.postpetuser(Pets.NewPet_ValidCase());
        Assert.assertEquals(statusCode, 200);
        response.then().statusCode(200);

        log.info("Status Code={} - Success", statusCode);
        log.info("************************* [CREATE] TEST CASE 1 COMPLETED ************************");

    }

    @Test(priority = 2)
    public void add_pet_InvalidID(){

        log.info("************************* [CREATE] TEST CASE 2 STARTED ************************");

        int statusCode = 500;
        Response response = PetEndPoints.postpetuser(Pets.NewPet_InvalidID());
        Assert.assertEquals(statusCode, 500);
        response.then().body("type", equalTo("unknown"));
        response.then().body("message", equalTo("something bad happened"));
        
        log.info("Status Code={} - something bad happened", statusCode);
        log.info("************************* [CREATE] TEST CASE 2 COMPLETED ************************");

    }

    @Test(priority = 3)
    public void add_pet_InvalidPhotoURL(){

        log.info("************************* [CREATE] TEST CASE 3 STARTED ************************");

        int statusCode = 500;
        Response response = PetEndPoints.postpetuser(Pets.NewPet_InvalidID());
        Assert.assertEquals(statusCode, 500);
        response.then().body("code", equalTo(500));
        response.then().body("type", equalTo("unknown"));
        response.then().body("message", equalTo("something bad happened"));

        log.info("Status Code={} - something bad happened", statusCode);
        log.info("************************* [CREATE] TEST CASE 3 COMPLETED ************************");

    }
    
}
