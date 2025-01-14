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

    public static String update_Pet_FormData(int id){
        return base_url + "/pet/" + id;
    }
    
    public static String find_by_status = base_url + "/pet/findByStatus";

    // STORE MODEL
    public static String get_inventory = base_url + "/store/inventory";

    public static String post_order = base_url + "/store/order";

    public static String get_order_by_id(int orderId){
        return base_url + "/store/order/" + orderId;
    }

    public static String delete_order(int id){
        return base_url + "/store/order/" + id;
    }
    // USER MODEL
}
