package dataelaboration.utility;

/**
 * Tuple.
 *
 * @param <String>, string.
 * @param <B>,      numeric or boolean value.
 */
public class Tuple<String, B> {

    private String timestamp;
    private B property;

    public Tuple(String timestamp, B property) {
        this.timestamp = timestamp;
        this.property = property;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public B getProperties() {
        return property;
    }

    public void setProperties(B property) {
        this.property = property;
    }
}