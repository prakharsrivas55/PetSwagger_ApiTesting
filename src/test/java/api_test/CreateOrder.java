package api_test;

import static org.hamcrest.Matchers.equalTo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api_endpoints.PetEndPoints;
import api_payloads.Orders;
import io.restassured.response.Response;

public class CreateOrder {

    private static final Logger log = LogManager.getLogger(CreateOrder.class);

    @BeforeClass
    public void setup(){

        log.info("************************* Petstore Swagger --[CREATEORDER.JAVA]-- API TEST STARTED ************************");

    }

    @Test(priority = 1)
    public void add_a_pet_order(){

        log.info("************************* [POST] TEST CASE 1 STARTED ************************");

        Response response = PetEndPoints.CreateOrder(Orders.add_a_ValidOrder_Data());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        response.then().body("id", equalTo(100));
        response.then().body("petId", equalTo(200));
        response.then().body("quantity", equalTo(5));
        response.then().body("shipDate", equalTo("2025-01-14T07:53:27.604+0000"));
        response.then().body("status", equalTo("placed"));
        response.then().body("complete", equalTo(true));

        log.info("************************* [POST] TEST CASE 1 COMPLETED ************************");
    
    }
    
}
