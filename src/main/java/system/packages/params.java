package system.packages;

import java.util.HashMap;
import java.util.Map;

public class params {

    public static String id;
    private static Map<String, String> parameters = new HashMap<>();

    public static void setParams(String key, String val) {
        parameters.put(key, val);
    }

    public static String getParams(String key) {
        return parameters.get(key);
    }

    public static long Long(String val) {
        if ("id".equals(val)) {
            val = id;
        }
        return Long.parseLong(val);
    }

    public static int Int(String val) {
        if ("id".equals(val)) {
            val = id;
        }
        return Integer.parseInt(val);
    }

}
