package api_endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import java.io.File;
import java.util.Map;
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

    public static Response UpdateAPet(Object Payload){

        Response response = given()

            .contentType(ContentType.JSON)
            .body(Payload)
        
        .when()
            .put(Routes.update_pet);
        
        return response;
        
    }

    public static Response UpdatePetData(int id){

        Response response= given()

            .formParam("name", "Reaper")
            .formParam("status", "Dagger")

        .when()
            .post(Routes.update_Pet_FormData(id));
            
        return response;

    }

    public static Response GetByStatus(Map<String, String> queryParams){

        RequestSpecification request = given();
            
        for(Map.Entry<String, String> entry : queryParams.entrySet()){
            
            request.queryParams(entry.getKey(), entry.getValue());
        } 
        
        Response response = request
        
        .when()
            .get(Routes.find_by_status);

        return response;

    }

    /*---------------------------------------- STORE MODEL ----------------------------------------- */

    public static Response GetInventory(){

        Response response = given() 

            .contentType(ContentType.JSON)

        .when()
            .get(Routes.get_inventory);
        
        return response;
    }

    public static Response CreateOrder(Object Payload){

        Response response = given()

            .contentType(ContentType.JSON)
            .body(Payload)

        .when()
            .post(Routes.post_order);

        return response;

    }
    
}
