package system.packages;

public class Data {

    public static void setValue(String key, Object value) {
        StaticVariables.responseData.put(key, value);
    }

    public static Object getValue(String key) {
        return StaticVariables.responseData.get(key);
    }
    
}
