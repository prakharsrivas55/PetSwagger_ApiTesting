package api_test;

import static org.hamcrest.Matchers.hasKey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api_endpoints.PetEndPoints;
import io.restassured.response.Response;

public class GetInventory {
    
    private static final Logger log = LogManager.getLogger(GetInventory.class);

    @BeforeClass
    public void setup(){

        log.info("************************* Petstore Swagger --[GETINVENTORY.JAVA]-- API TEST STARTED ************************");

    }

    @Test(priority = 1)
    public void get_inventory(){

        log.info("************************* [GET] TEST CASE 1 STARTED ************************");
        
        Response response = PetEndPoints.GetInventory();
        int statusCode = 200;

        Assert.assertEquals(statusCode, 200);
        response.then()
                .assertThat()
                .body("$", hasKey("\"\""))
                .body("$", hasKey("sold"))
                .body("$", hasKey("string"))
                .body("$", hasKey("pending"))
                .body("$", hasKey("available"));

        log.info("************************* [GET] TEST CASE 1 COMPLETED ************************");
        
    }

}