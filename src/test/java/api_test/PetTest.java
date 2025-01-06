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

import io.qameta.allure.Feature;
import io.qameta.allure.Story;


public class PetTest {

    private static final Logger log = LogManager.getLogger(PetTest.class);

    @BeforeClass
    public void setup(){

        log.info("************************* Petstore Swagger API TEST STARTED ************************");

    }
    
    @Test(priority = 1)
    @Feature("Pet store API")
    @Story("Get a pet by ID")
    public void find_pet_by_id_also_validate_statusCode(){

        log.info("************************* TESTCASE 1 STARTED ************************");

        int id=10;
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

        log.info("************************* TESTCASE 1 COMPLETED ************************");

    }

    @Test(priority = 2)
    public void find_pet_by_id_ando_validate_jsonResponse(){

        log.info("************************* TESTCASE 2 STARTED ************************");

        int id=10;

        Response response= PetEndPoints.readpetuser(id);
        response.then().log().all();
        response.then().body("id", equalTo(id));
        response.then().body("category.id", equalTo(id));
        response.then().body("name", equalTo("doggie"));
        response.then().body("tags[0].id", equalTo(id));
        response.then().body("status", equalTo("available"));

        log.info("************************* TESTCASE 2 COMPLETED ************************");
 
    }

    @Test(priority = 3)
    public void find_pet_by_id_and_validate_jsonSchema(){

        log.info("************************* TESTCASE 3 STARTED ************************");

        int id=10;
        File PetjsonSchemaFile= new File("src/test/resources/JsonSchemas/PetSchema.json");
        // int statusCode;

        Response response= PetEndPoints.readpetuser(id);
        response.then().body(JsonSchemaValidator.matchesJsonSchema(PetjsonSchemaFile));

        log.info("************************* TESTCASE 3 COMPLETED ************************");
        
    }
}