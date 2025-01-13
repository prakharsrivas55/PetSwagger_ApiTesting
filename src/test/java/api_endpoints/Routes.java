package api_endpoints;

public class Routes {

    public static String base_url = "https://petstore.swagger.io/v2";

    // PET MODEL
    public static String get_pet_url(int id) {
        return base_url + "/pet/" + id;
    }

    public static String post_pet_url= base_url + "/pet";

    public static String post_upload_image(int id){
        return base_url + "/pet/" + id + "/uploadImage";
    } 
    
    public static String delete_pet(int id){
        return base_url + "/pet/" + id;
    }

    public static String update_pet = base_url + "/pet";

    public static String updatePetFormData(int id){
        return base_url + "/pet/" + id;
    } 

    // STORE MODEL
    // USER MODEL
}
