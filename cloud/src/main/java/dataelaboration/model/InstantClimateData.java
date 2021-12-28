package dataelaboration.model;

import com.opencsv.bean.CsvBindByPosition;

/**
 * Csv Bean for instant detection of climate.
 */
public class InstantClimateData {

    @CsvBindByPosition(position = 0)
    private String timestamp;
    @CsvBindByPosition(position = 1)
    private float temperature;
    @CsvBindByPosition(position = 2)
    private float humidity;
    @CsvBindByPosition(position = 3)
    private float pressure;
    @CsvBindByPosition(position = 4)
    private float co2;
    @CsvBindByPosition(position = 5)
    private float tvoc;
    @CsvBindByPosition(position = 6)
    private float pm2_5;
    @CsvBindByPosition(position = 7)
    private float pm1_0;
    @CsvBindByPosition(position = 8)
    private float pm10;
    @CsvBindByPosition(position = 9)
    private float wind;
    @CsvBindByPosition(position = 10)
    private boolean rain;
    @CsvBindByPosition(position = 11)
    private float uv;

    public InstantClimateData() {
        this.temperature = Float.MIN_VALUE;
        this.humidity = Float.MIN_VALUE;
        this.pressure = Float.MIN_VALUE;
        this.co2 = Float.MIN_VALUE;
        this.tvoc = Float.MIN_VALUE;
        this.pm2_5 = Float.MIN_VALUE;
        this.pm1_0 = Float.MIN_VALUE;
        this.pm10 = Float.MIN_VALUE;
        this.wind = Float.MIN_VALUE;
        this.uv = Float.MIN_VALUE;
    }


    public InstantClimateData(String timestamp, float temperature, float humidity,
                              float pressure, float co2, float tvoc,
                              float pm2_5, float pm1_0, float pm10,
                              float wind, boolean rain, float uv) {
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.co2 = co2;
        this.tvoc = tvoc;
        this.pm2_5 = pm2_5;
        this.pm1_0 = pm1_0;
        this.pm10 = pm10;
        this.wind = wind;
        this.rain = rain;
        this.uv = uv;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "DailyClimateData{" +
                "timestamp='" + timestamp + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", co2=" + co2 +
                ", tvoc=" + tvoc +
                ", pm2_5=" + pm2_5 +
                ", pm1_0=" + pm1_0 +
                ", pm10=" + pm10 +
                ", wind=" + wind +
                ", rain=" + rain +
                ", uv=" + uv +
                '}';
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getCo2() {
        return co2;
    }

    public void setCo2(float co2) {
        this.co2 = co2;
    }

    public float getPm2_5() {
        return pm2_5;
    }

    public void setPm2_5(float pm2_5) {
        this.pm2_5 = pm2_5;
    }

    public float getPm1_0() {
        return pm1_0;
    }

    public void setPm1_0(float pm1_0) {
        this.pm1_0 = pm1_0;
    }

    public float getPm10() {
        return pm10;
    }

    public void setPm10(float pm10) {
        this.pm10 = pm10;
    }

    public boolean isRain() {
        return rain;
    }

    public void setRain(boolean rain) {
        this.rain = rain;
    }

    public float getTvoc() {
        return tvoc;
    }

    public void setTvoc(float tvoc) {
        this.tvoc = tvoc;
    }

    public float getWind() {
        return wind;
    }

    public void setWind(float wind) {
        this.wind = wind;
    }

    public float getUv() {
        return uv;
    }

    public void setUv(float uv) {
        this.uv = uv;
    }
}
