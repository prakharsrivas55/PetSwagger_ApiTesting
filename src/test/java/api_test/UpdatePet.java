package api_test;

import static org.hamcrest.Matchers.equalTo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import api_endpoints.PetEndPoints;
import api_payloads.Pets;
import io.restassured.response.Response;

public class UpdatePet {

    private static final Logger log = LogManager.getLogger(UpdatePet.class);
    private int id = 10;

    @BeforeClass
    public void setup(){

        log.info("************************* Petstore Swagger --[UPDATE.JAVA]-- API TEST STARTED ************************");

    }

    @BeforeMethod
    public void before_Upating_Pet(){

        CreatePet createPet = new CreatePet();
        createPet.add_pet();
    }

    @Test(priority = 1)
    public void update_Pet(){

        log.info("************************* [UPDATE] TEST CASE 1 STARTED ************************");

        // beforeUpatingPet();
        Response response= PetEndPoints.UpdateAPet(Pets.UpdatePetjson());   
        int statusCode = 200; 
        Assert.assertEquals(statusCode, 200);
        response.then().body("status", equalTo("sold"));  
        response.then().log().all();
        
        log.info("************************* [COMPLETE] TEST CASE 1 COMPLETED ************************");
        
    }

    // Update the Pet using formData
    @Test(priority = 2)
    public void update_Pet_Via_FormData(){

        log.info("************************* [FORM UPDATE] TEST CASE 1 STARTED ************************");

        Response response= PetEndPoints.UpdatePetData(id);
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200);
        response.then().body("code", equalTo(200));
        response.then().body("type", equalTo("unknown"));
        response.then().body("message", equalTo(String.valueOf(id)));

        log.info("************************* [FORM UPDATE] TEST CASE 1 COMPLETED ************************");

    }
 
}
