package api_test;

import static org.hamcrest.Matchers.equalTo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api_endpoints.PetEndPoints;
import io.restassured.response.Response;

public class DeletePet {
    
    private static final Logger log = LogManager.getLogger(DeletePet.class);

    @BeforeClass
    public void setup(){

        log.info("************************* Petstore Swagger --[DELETEPET.JAVA]-- API TEST STARTED ************************");

    }

    public void before_Deleting_pet(){

        CreatePet createPet = new CreatePet();
        createPet.add_pet();
    }
    
        @Test(priority = 1)
        public void delete_Pet(){

            log.info("************************* [DELETE] TEST CASE 1 STARTED ************************");

            before_Deleting_pet();
            int id =10;

            Response response= PetEndPoints.deleteAPet(id);
            response.then().body("code", equalTo(200));
            response.then().body("type", equalTo("unknown"));
            response.then().body("message", equalTo("10"));
            
            log.info("************************* [DELETE] TEST CASE 1 COMPLETED ************************");
            
        }

        @Test(priority = 2)
        public void Pet_NotFound(){

            log.info("************************* [DELETE] TEST CASE 2 STARTED ************************");

            int id =10;
            
            Response response= PetEndPoints.deleteAPet(id);
            int statusCode= response.getStatusCode();
            Assert.assertEquals(statusCode, 404);
            
            log.info("************************* [DELETE] TEST CASE 2 COMPLETED ************************");
            
        }

}
