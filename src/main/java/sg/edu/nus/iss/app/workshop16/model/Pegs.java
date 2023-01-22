package sg.edu.nus.iss.app.workshop16.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Pegs implements Serializable{
    private int total_count;
    private List<Type> types;

    public List<Type> getTypes() {
        return types;
    }
    public void setTypes(List<Type> types) {
        this.types = types;
    }

    //Converting from Java Object: List to JSON object: Array
    public JsonObjectBuilder toJSON(){
        JsonArrayBuilder arrbld = Json.createArrayBuilder();
        List<JsonObjectBuilder> listOfTypes = this.getTypes()
                            .stream()
                            .map(t -> t.toJSON())
                            .toList();
        for (JsonObjectBuilder x : listOfTypes)
            arrbld.add(x);

        return Json.createObjectBuilder()
                .add("total_count", this.getTotal_count())
                .add("types", arrbld);
                
    }
    public int getTotal_count() {
        return total_count;
    }
    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }  

    public static Pegs createJson(JsonObject o) {
        Pegs pp = new Pegs();
        List<Type> result = new LinkedList<Type>();
        JsonArray types = o.getJsonArray("types");
        for (int i = 0; i < types.size(); i++) {
            JsonObject x = types.getJsonObject(i);
            Type t = Type.createJson(x);
            result.add(t);
        }
        pp.setTypes(result);
        pp.setTotal_count(o.getInt("total_count"));
        return pp;
    }
    
}
