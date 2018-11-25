import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GsonTest {
    public static void main(String[] args) {
        JsonObject map = new JsonObject();
        map.addProperty("a",1);
        String s = new Gson().toJson(map);
        System.out.println(s);

        JsonArray array = new JsonArray();
        array.add(3);
        array.add("haha");
        array.add(false);
        String s2 = new Gson().toJson(array);
        System.out.println(s2);

    }
}
