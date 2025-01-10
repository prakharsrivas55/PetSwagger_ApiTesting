package api_endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.File;

import io.restassured.http.ContentType;

public class PetEndPoints {

    public static Response readpetuser(int id) {

        String petUrl = Routes.get_pet_url(id);

        Response response = given()

            .contentType(ContentType.JSON)

        .when()
            .get(petUrl);

        return response;
    }

    public static Response postpetuser(Object Payload){

        Response response = given()

            .contentType(ContentType.JSON)
            .body(Payload)
        
        .when()
            .post(Routes.post_pet_url);
        
        return response;
        
    }

    public static Response uploadFile(int id){

        File file1 = new File("src/test/resources/files/Dummy.pdf");
        if (!file1.exists()) {
            System.out.println("File not found at: " + file1.getAbsolutePath());
        }


        Response response= given()

            .multiPart("file", file1) 
            // .param("id", id)

        .when()
            .post(Routes.post_upload_image(id));
            
        return response;
        
    }

    public static Response deleteAPet(int id){

        Response response = given()

            .contentType(ContentType.JSON)
    
        .when()
            .delete(Routes.delete_pet(id));
        
        return response;
        
    }
}
