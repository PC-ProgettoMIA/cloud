package dataelaboration.model;

public class InfoThing {
    private String thingId;
    private String schoolName;
    private Coordinate coordinate;

    public InfoThing(String thingId, String schoolName, Coordinate coordinate) {
        this.thingId = thingId;
        this.schoolName = schoolName;
        this.coordinate = coordinate;
    }

    public String getThingId() {
        return thingId;
    }

    public void setThingId(String thingId) {
        this.thingId = thingId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

}
