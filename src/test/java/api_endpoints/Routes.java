package api_endpoints;

public class Routes {

    public static String base_url = "https://petstore.swagger.io/v2";

    // PET MODEL
    public static String get_pet_url(int id) {
        // Add '/' between /pet and the id to form the correct URL.
        return base_url + "/pet/" + id;
    }

    // STORE MODEL
    // USER MODEL
}
