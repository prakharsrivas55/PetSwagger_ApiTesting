package api_endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
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
}
