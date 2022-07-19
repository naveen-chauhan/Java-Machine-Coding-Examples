package inmemorykeyvaluestore.helper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author naveen.chauhan on 19/07/22
 */
public class JsonHelper {
     public static String toString(HashMap<String, String> data) {
         StringBuilder stringBuilder = new StringBuilder();
         for (Map.Entry<String, String> entry: data.entrySet()) {
            stringBuilder.append(entry.getKey()).append(": ").append(", ");
         }
         String result = stringBuilder.toString();
         return result.length() == 0 ? result: result.substring(0, result.length()-1);
     }

    public static HashMap<String, String> readTree(String value) {
         String[] keyValues = value.split(" ");
         HashMap<String, String> result = new HashMap<>();

         for (int i = 0; i < keyValues.length ; i += 2) {
             result.put(keyValues[i], keyValues[i+1]);
         }

         return result;
    }
}
