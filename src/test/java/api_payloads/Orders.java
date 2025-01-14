package api_payloads;

import java.util.HashMap;
import java.util.Map;

public class Orders {

    private static String apiCurrentTime;

    public static Map<String, Object> add_a_ValidOrder_Data(){

        Map<String, Object> orders =  new HashMap<>();

        orders.put("id", 100);
        orders.put("petId", 200);
        orders.put("quantity", 5);
        orders.put("shipDate", "2025-01-14T07:53:27.604+0000");
        orders.put("status", "placed");
        orders.put("complete", true);
        
        return orders;
    }
    public static String getApiCurrentTime() {
        
        return apiCurrentTime;
    }
    
}
