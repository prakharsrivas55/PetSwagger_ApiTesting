package api_test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import api_endpoints.PetEndPoints;
import io.restassured.response.Response;  
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import static org.hamcrest.Matchers.*;

public class UploadFile {

    private static final Logger log = LogManager.getLogger(UploadFile.class);

    @BeforeClass
    public void setup(){

        log.info("************************* Petstore Swagger -- [UploadFile.JAVA] -- API TEST STARTED ************************");

    }

    @Test(priority = 1)
    public void validUploadFile(){
        
        log.info("************************* [Upload File] TESTCASE 1 STARTED ************************");

        int statusCode = 200;
        int id = 10;
        Response response= PetEndPoints.uploadFile(id);
        Assert.assertEquals(statusCode, 200);

        response.then().body("code", equalTo(200));
        response.then().body("type", equalTo("unknown"));
        response.then().body("message", equalTo("additionalMetadata: null\nFile uploaded to ./Dummy.pdf, 28857 bytes"));

        log.info("************************* [Upload File] TESTCASE 1 COMPLETED ************************");

    }
    
}
