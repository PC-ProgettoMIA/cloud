package dataelaboration.utility.json;

import dataelaboration.model.InfoThing;
import dataelaboration.model.Tuple;
import dataelaboration.model.csvmodel.DailyClimateData;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

/**
 * Object to Json converter.
 */
public class ObjectToJsonUtility {

    /**
     * Convert DailyClimateData to JsonObject.
     *
     * @param thingId, thing's id.
     * @param data,    daily climate data.
     * @return json object.
     */
    public static JsonObject dailyClimateDataToJson(final String thingId, final DailyClimateData data) {
        return new JsonObject("{\n" +
                "    \"thingID\": \"" + thingId + "\",\n" +
                "    \"properties\": {\n" +
                "        \"avgtemp\": " + data.getAvgtemp() + ",\n" +
                "        \"mintemp\": " + data.getMintemp() + ",\n" +
                "        \"maxtemp\": " + data.getMaxtemp() + ",\n" +
                "        \"avghum\": " + data.getAvghum() + ",\n" +
                "        \"avgpress\": " + data.getAvgpress() + ",\n" +
                "        \"minpress\": " + data.getMinpress() + ",\n" +
                "        \"maxpress\": " + data.getMaxpress() + ",\n" +
                "        \"avgco2\": " + data.getAvgco2() + ",\n" +
                "        \"avgtvoc\": " + data.getAvgtvoc() + ",\n" +
                "        \"avgpm2_5\": " + data.getAvgpm2_5() + ",\n" +
                "        \"avgpm1_0\": " + data.getAvgpm1_0() + ",\n" +
                "        \"avgpm10\": " + data.getAvgpm10() + ",\n" +
                "        \"avgwind\": " + data.getAvgwind() + ",\n" +
                "        \"maxwind\": " + data.getMaxwind() + ",\n" +
                "        \"avguv\": " + data.getAvguv() + "\n" +
                "    }\n" +
                "}");
    }

    /**
     * Convert property data to json.
     *
     * @param thingId thing's id.
     * @param data    data to convert.
     * @return the json object.
     */
    public static JsonObject propertyHistoryClimateDataToJson(final String thingId, final String property, final List<Tuple<String, Float>> data) {
        JsonObject json = new JsonObject().put("thingId", thingId);
        JsonArray array = new JsonArray();
        for (var elem : data) {
            JsonObject obj = new JsonObject();
            obj.put("timestamp", elem.getString());
            obj.put("data", elem.getProperties());
            array.add(obj);
        }
        json.put(property, array);
        return json;
    }

    /**
     * Convert rain data to json.
     *
     * @param thingId thing's id.
     * @param data    rain data to convert.
     * @return the json object.
     */
    public static JsonObject rainHistoryClimateDataToJson(final String thingId, final List<Tuple<String, Boolean>> data) {
        JsonObject json = new JsonObject().put("thingId", thingId);
        JsonArray array = new JsonArray();
        if (!data.isEmpty()) {
            for (var elem : data) {
                JsonObject obj = new JsonObject();
                obj.put("timestamp", elem.getString());
                obj.put("data", elem.getProperties());
                array.add(obj);
            }
            json.put("rain", array);
        }
        return json;
    }

    /**
     * Convert id and coordinate data to json.
     *
     * @param data coordinate data to convert.
     * @return the json object.
     */
    public static JsonObject IdCoordinateDataToJson(final List<InfoThing> data) {
        JsonObject json = new JsonObject();
        JsonArray array = new JsonArray();
        if (!data.isEmpty()) {
            for (var elem : data) {
                JsonObject obj = new JsonObject();
                obj.put("thingId", elem.getThingId());
                obj.put("school", elem.getSchoolName());
                JsonObject position = new JsonObject()
                        .put("latitude", elem.getCoordinate().getLatitude())
                        .put("longitude", elem.getCoordinate().getLongitude());
                obj.put("position", position);
                array.add(obj);
            }
            json.put("items", array);
        }
        return json;
    }

//    public static void main(String[] args) {
//        List<Tuple<String, Float>> data = new LinkedList<>();
//        data.add(new Tuple<>("timestamp", 13F));
//        data.add(new Tuple<>("timestamp", 11.2F));
//        JsonObject json = new JsonObject().put("thingId", "ciao");
//
//        JsonArray array = new JsonArray();
//        for (var elem : data) {
//            JsonObject obj = new JsonObject();
//
//            obj.put("timestamp", elem.getTimestamp());
//            obj.put("data", elem.getProperties());
//            array.add(obj);
//        }
//        json.put("properties", array);
//        System.out.println(json.encodePrettily());
//    }
}

