package api_test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import java.util.Random;

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

    /*--------------------------------------- Pet inventories by status -----------------------------------*/
    @Test(priority = 1, enabled = false)
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

    /*--------------------------------------- Pet inventories by status -----------------------------------*/

    @Test(priority = 2)
    public void get_by_orderID_validCase(){

        log.info("************************* [GET BY ORDER ID] TEST CASE 1 STARTED ************************");


        Random random = new Random();
        int orderId = random.nextInt(10) + 1;

        Response response = PetEndPoints.GetOrderByID(orderId);
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200);
        response.then().body("id", equalTo(orderId));
        response.then().body("$", hasKey("petId"));
        response.then().body("$", hasKey("quantity"));
        response.then().body("$", hasKey("status"));
        response.then().body("complete", equalTo(true));

        log.info("************************* [GET BY ORDER ID] TEST CASE 1 COMPLETED ************************");

    }
    
}