package dataelaboration.model.csvmodel;

import com.opencsv.bean.CsvBindByPosition;

/**
 * Csv Bean for daily average climate data.
 */
public class DailyClimateData {

    @CsvBindByPosition(position = 0)
    private String thingID;
    @CsvBindByPosition(position = 1)
    private String timestamp;
    @CsvBindByPosition(position = 2)
    private float avgtemp;
    @CsvBindByPosition(position = 3)
    private float mintemp;
    @CsvBindByPosition(position = 4)
    private float maxtemp;
    @CsvBindByPosition(position = 5)
    private float avghum;
    @CsvBindByPosition(position = 6)
    private float avgpress;
    @CsvBindByPosition(position = 7)
    private float minpress;
    @CsvBindByPosition(position = 8)
    private float maxpress;
    @CsvBindByPosition(position = 9)
    private float avgco2;
    @CsvBindByPosition(position = 10)
    private float avgtvoc;
    @CsvBindByPosition(position = 11)
    private float avgpm2_5;
    @CsvBindByPosition(position = 12)
    private float avgpm1_0;
    @CsvBindByPosition(position = 13)
    private float avgpm10;
    @CsvBindByPosition(position = 14)
    private float avgwind;
    @CsvBindByPosition(position = 15)
    private float maxwind;
    @CsvBindByPosition(position = 16)
    private float avguv;


    public DailyClimateData() {
        this.avgtemp = Float.MIN_VALUE;
        this.mintemp = Float.MIN_VALUE;
        this.maxtemp = Float.MIN_VALUE;
        this.avghum = Float.MIN_VALUE;
        this.avgpress = Float.MIN_VALUE;
        this.minpress = Float.MIN_VALUE;
        this.maxpress = Float.MIN_VALUE;
        this.avgco2 = Float.MIN_VALUE;
        this.avgtvoc = Float.MIN_VALUE;
        this.avgpm2_5 = Float.MIN_VALUE;
        this.avgpm1_0 = Float.MIN_VALUE;
        this.avgpm10 = Float.MIN_VALUE;
        this.avgwind = Float.MIN_VALUE;
        this.maxwind = Float.MIN_VALUE;
        this.avguv = Float.MIN_VALUE;
    }

    public DailyClimateData(String thingID, String timestamp, float avgtemp, float mintemp, float maxtemp,
                            float avghum, float avgpress, float minpress,
                            float maxpress, float avgco2, float avgtvoc,
                            float avgpm2_5, float avgpm1_0, float avgpm10,
                            float avgwind, float maxwind, float avguv) {
        this.thingID = thingID;
        this.timestamp = timestamp;
        this.avgtemp = avgtemp;
        this.mintemp = mintemp;
        this.maxtemp = maxtemp;
        this.avghum = avghum;
        this.avgpress = avgpress;
        this.minpress = minpress;
        this.maxpress = maxpress;
        this.avgco2 = avgco2;
        this.avgtvoc = avgtvoc;
        this.avgpm2_5 = avgpm2_5;
        this.avgpm1_0 = avgpm1_0;
        this.avgpm10 = avgpm10;
        this.avgwind = avgwind;
        this.maxwind = maxwind;
        this.avguv = avguv;
    }


    public String getThingID() {
        return thingID;
    }

    public void setThingID(String thingID) {
        this.thingID = thingID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public float getAvgtemp() {
        return avgtemp;
    }

    public void setAvgtemp(float avgtemp) {
        this.avgtemp = avgtemp;
    }

    public float getMintemp() {
        return mintemp;
    }

    public void setMintemp(float mintemp) {
        this.mintemp = mintemp;
    }

    public float getMaxtemp() {
        return maxtemp;
    }

    public void setMaxtemp(float maxtemp) {
        this.maxtemp = maxtemp;
    }

    public float getAvghum() {
        return avghum;
    }

    public void setAvghum(float avghum) {
        this.avghum = avghum;
    }

    public float getAvgpress() {
        return avgpress;
    }

    public void setAvgpress(float avgpress) {
        this.avgpress = avgpress;
    }

    public float getMinpress() {
        return minpress;
    }

    public void setMinpress(float minpress) {
        this.minpress = minpress;
    }

    public float getMaxpress() {
        return maxpress;
    }

    public void setMaxpress(float maxpress) {
        this.maxpress = maxpress;
    }

    public float getAvgco2() {
        return avgco2;
    }

    public void setAvgco2(float avgco2) {
        this.avgco2 = avgco2;
    }

    public float getAvgtvoc() {
        return avgtvoc;
    }

    public void setAvgtvoc(float avgtvoc) {
        this.avgtvoc = avgtvoc;
    }

    public float getAvgwind() {
        return avgwind;
    }

    @Override
    public String toString() {
        return "HistoryClimateData{" +
                "thingID='" + thingID + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", avgtemp=" + avgtemp +
                ", mintemp=" + mintemp +
                ", maxtemp=" + maxtemp +
                ", avghum=" + avghum +
                ", avgpress=" + avgpress +
                ", minpress=" + minpress +
                ", maxpress=" + maxpress +
                ", avgco2=" + avgco2 +
                ", avgtvoc=" + avgtvoc +
                ", avgpm2_5=" + avgpm2_5 +
                ", avgpm1_0=" + avgpm1_0 +
                ", avgpm10=" + avgpm10 +
                ", avgwind=" + avgwind +
                ", maxwind=" + maxwind +
                ", avguv=" + avguv +
                '}';
    }

    public float getAvgpm2_5() {
        return avgpm2_5;
    }

    public void setAvgpm2_5(float avgpm2_5) {
        this.avgpm2_5 = avgpm2_5;
    }

    public float getAvgpm1_0() {
        return avgpm1_0;
    }

    public void setAvgpm1_0(float avgpm1_0) {
        this.avgpm1_0 = avgpm1_0;
    }

    public float getAvgpm10() {
        return avgpm10;
    }

    public void setAvgpm10(float avgpm10) {
        this.avgpm10 = avgpm10;
    }

    public void setAvgwind(float avgwind) {
        this.avgwind = avgwind;
    }

    public float getMaxwind() {
        return maxwind;
    }

    public void setMaxwind(float maxwind) {
        this.maxwind = maxwind;
    }

    public float getAvguv() {
        return avguv;
    }

    public void setAvguv(float avguv) {
        this.avguv = avguv;
    }

    public boolean isEmpty() {
        return this.avgtemp == Float.MIN_VALUE &&
                this.mintemp == Float.MIN_VALUE &&
                this.maxtemp == Float.MIN_VALUE &&
                this.avghum == Float.MIN_VALUE &&
                this.avgpress == Float.MIN_VALUE &&
                this.minpress == Float.MIN_VALUE &&
                this.maxpress == Float.MIN_VALUE &&
                this.avgco2 == Float.MIN_VALUE &&
                this.avgtvoc == Float.MIN_VALUE &&
                this.avgpm2_5 == Float.MIN_VALUE &&
                this.avgpm1_0 == Float.MIN_VALUE &&
                this.avgpm10 == Float.MIN_VALUE &&
                this.avgwind == Float.MIN_VALUE &&
                this.maxwind == Float.MIN_VALUE &&
                this.avguv == Float.MIN_VALUE;
    }
}
