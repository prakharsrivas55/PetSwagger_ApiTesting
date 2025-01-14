package api_test;

import static org.hamcrest.Matchers.equalTo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api_endpoints.PetEndPoints;
import io.restassured.response.Response;

public class DeleteOrder {

    private static final Logger log = LogManager.getLogger(DeleteOrder.class);

    @BeforeClass
    public void setup(){

        log.info("************************* Petstore Swagger --[DELETEORDER.JAVA]-- API TEST STARTED ************************");

    }

    public void before_Deleting_order(){

        CreateOrder createPet = new CreateOrder();
        createPet.add_a_pet_order();
    }
    
        @Test(priority = 1)
        public void delete_Pet(){

            log.info("************************* [DELETE ORDER BY ID] TEST CASE 1 STARTED ************************");

            before_Deleting_order();
            int id =100;

            Response response= PetEndPoints.DeleteOrder(id);
            int statusCode = response.getStatusCode();
            Assert.assertEquals(statusCode, 200);

            response.then().body("code", equalTo(200));
            response.then().body("type", equalTo("unknown"));
            response.then().body("message", equalTo(String.valueOf(id)));
            
            log.info("************************* [DELETE ORDER BY ID] TEST CASE 1 COMPLETED ************************");
            
        }

        @Test(priority = 2)
        public void delete_OrderNotFound(){

            log.info("************************* [DELETE ORDER NOT FOUND] TEST CASE 2 STARTED ************************");

            int id = 999;

            Response response= PetEndPoints.DeleteOrder(id);
            int statusCode = response.getStatusCode();
            Assert.assertEquals(statusCode,404);

            response.then().body("code", equalTo(404));
            response.then().body("type", equalTo("unknown"));
            response.then().body("message", equalTo("Order Not Found"));
            
            log.info("************************* [DELETE ORDER NOT FOUND] TEST CASE 2 COMPLETED ************************");
            
        }
    
}
