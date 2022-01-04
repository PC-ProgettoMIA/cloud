package dataelaboration.model;

/**
 * Tuple.
 *
 * @param <String>, string.
 * @param <B>,      numeric or boolean or other value.
 */
public class Tuple<String, B> {

    private String string;
    private B property;


    public Tuple(String timestamp, B property) {
        this.string = timestamp;
        this.property = property;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public B getProperties() {
        return property;
    }

    public void setProperties(B property) {
        this.property = property;
    }
}