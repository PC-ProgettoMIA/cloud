package dataelaboration.utility.json;

import dataelaboration.utility.DateConversion;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Json to Object converter.
 */
public class JsonToObjectUtility {

    private static JsonObject checkProperties(JsonObject result) {
        JsonObject properties = null;
        if (result.containsKey("features")) {
            JsonObject features = result.getJsonObject("features");
            if (features.containsKey("measurements")) {
                JsonObject measurements = features.getJsonObject("measurements");
                if (measurements.containsKey("properties")) {
                    properties = measurements.getJsonObject("properties");
                }
            }
        }
        return properties;
    }

    /**
     * Obtain timestamp from json object.
     *
     * @param result, integral json object.
     * @return json timestamp.
     */
    public static String getJsonTimestamp(JsonObject result) {
        List<Timestamp> list = new LinkedList<>();
        // TODO: 23/12/21 Controllare pm2_5, pm1_0, pm10, cosi non vanno bene mi sa 
        List<String> keys = Arrays.asList("temperature", "humidity",
                "pressure", "co2", "tvoc", "quality", "wind", "rain", "uv");
        JsonObject properties = checkProperties(result);
        if (properties != null) {
            for (String key : keys) {
                if (properties.containsKey(key)) {
                    JsonObject property = properties.getJsonObject(key);
                    if (property.containsKey("timestamp")) {
                        list.add(DateConversion.stringToTimestamp((property.getString("timestamp"))));
                    }
                }
            }
        }
        list.sort(Timestamp::compareTo);
        return String.valueOf(list.get(list.size() - 1).getTime());
    }

    /**
     * Obtain temperature from json object.
     *
     * @param result, integral json object.
     * @return json temperature.
     */
    public static float getJsonTemperature(JsonObject result) {
        float value = Float.MIN_VALUE;
        JsonObject properties = checkProperties(result);
        if (properties != null && properties.containsKey("temperature")) {
            JsonObject temperature = properties.getJsonObject("temperature");
            if (temperature.containsKey("data")) {
                value = temperature.getFloat("data");
            }
        }
        return value;
    }

    /**
     * Obtain humidity from json object.
     *
     * @param result, integral json object.
     * @return json humidity.
     */
    public static float getJsonHumidity(JsonObject result) {
        float value = Float.MIN_VALUE;
        JsonObject properties = checkProperties(result);
        if (properties != null && properties.containsKey("humidity")) {
            JsonObject humidity = properties.getJsonObject("humidity");
            if (humidity.containsKey("data")) {
                value = humidity.getFloat("data");
            }
        }
        return value;
    }

    /**
     * Obtain pressure from json object.
     *
     * @param result, integral json object.
     * @return json pressure.
     */
    public static float getJsonPressure(JsonObject result) {
        float value = Float.MIN_VALUE;
        JsonObject properties = checkProperties(result);
        if (properties != null && properties.containsKey("pressure")) {
            JsonObject pressure = properties.getJsonObject("pressure");
            if (pressure.containsKey("data")) {
                value = pressure.getFloat("data");
            }
        }
        return value;
    }

    /**
     * Obtain co2 from json object.
     *
     * @param result, integral json object.
     * @return json co2.
     */
    public static float getJsonCo2(JsonObject result) {
        float value = Float.MIN_VALUE;
        JsonObject properties = checkProperties(result);
        if (properties != null && properties.containsKey("co2")) {
            JsonObject co2 = properties.getJsonObject("co2");
            if (co2.containsKey("data")) {
                value = co2.getFloat("data");
            }
        }
        return value;
    }

    /**
     * Obtain tvoc from json object.
     *
     * @param result, integral json object.
     * @return json tvoc.
     */
    public static float getJsonTvoc(JsonObject result) {
        float value = Float.MIN_VALUE;
        JsonObject properties = checkProperties(result);
        if (properties != null && properties.containsKey("tvoc")) {
            JsonObject tvoc = properties.getJsonObject("tvoc");
            if (tvoc.containsKey("data")) {
                value = tvoc.getFloat("data");
            }
        }
        return value;
    }

    /**
     * Obtain pm2_5 from json object.
     *
     * @param result, integral json object.
     * @return json pm2_5.
     */
    public static float getJsonPm2_5(JsonObject result) {
        float value = Float.MIN_VALUE;
        JsonObject properties = checkProperties(result);
        if (properties != null && properties.containsKey("quality")) {
            JsonObject quality = properties.getJsonObject("quality");
            if (quality.containsKey("data")) {
                JsonArray data = quality.getJsonArray("data");
                value = data.getJsonObject(1).getFloat("pm2_5_std");
            }
        }
        return value;
    }

    /**
     * Obtain pm1_0 from json object.
     *
     * @param result, integral json object.
     * @return json pm1_0.
     */
    public static float getJsonPm1_0(JsonObject result) {
        float value = Float.MIN_VALUE;
        JsonObject properties = checkProperties(result);
        if (properties != null && properties.containsKey("quality")) {
            JsonObject quality = properties.getJsonObject("quality");
            if (quality.containsKey("data")) {
                // TODO: 21/12/21 To test
                JsonArray data = quality.getJsonArray("data");
                value = data.getJsonObject(0).getFloat("pm1_0_std");
            }
        }
        return value;
    }

    /**
     * Obtain pm10 from json object.
     *
     * @param result, integral json object.
     * @return json pm10.
     */
    public static float getJsonPm10(JsonObject result) {
        float value = Float.MIN_VALUE;
        JsonObject properties = checkProperties(result);
        if (properties != null && properties.containsKey("quality")) {
            JsonObject quality = properties.getJsonObject("quality");
            if (quality.containsKey("data")) {
                JsonArray data = quality.getJsonArray("data");
                value = data.getJsonObject(2).getFloat("pm10_std");
            }
        }
        return value;
    }

    /**
     * Obtain wind from json object.
     *
     * @param result, integral json object.
     * @return json wind.
     */
    public static float getJsonWind(JsonObject result) {
        float value = Float.MIN_VALUE;
        JsonObject properties = checkProperties(result);
        if (properties != null && properties.containsKey("wind")) {
            JsonObject wind = properties.getJsonObject("wind");
            if (wind.containsKey("data")) {
                value = wind.getFloat("data");
            }
        }
        return value;
    }

    /**
     * Obtain rain from json object.
     *
     * @param result, integral json object.
     * @return json rain.
     */
    public static boolean getJsonRain(JsonObject result) {
        boolean value = false;
        JsonObject properties = checkProperties(result);
        if (properties != null && properties.containsKey("rain")) {
            JsonObject rain = properties.getJsonObject("rain");
            if (rain.containsKey("data")) {
                value = rain.getBoolean("data");
            }
        }
        return value;
    }

    /**
     * Obtain uv from json object.
     *
     * @param result, integral json object.
     * @return json uv.
     */
    public static float getJsonUv(JsonObject result) {
        float value = Float.MIN_VALUE;
        JsonObject properties = checkProperties(result);
        if (properties != null && properties.containsKey("uv")) {
            JsonObject uv = properties.getJsonObject("uv");
            if (uv.containsKey("data")) {
                value = uv.getFloat("data");
            }
        }
        return value;
    }
}
