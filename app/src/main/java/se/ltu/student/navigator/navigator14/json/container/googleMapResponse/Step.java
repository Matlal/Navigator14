package se.ltu.student.navigator.navigator14.json.container.googleMapResponse;

public class Step {
    private Distance distance;
    private Duration duration;
    private End_location end_location;
    private String html_instructions;
    private Pl polyline;
    private Start_location start_location;
    private String travel_mode;

    public Distance getDistance() {
        return this.distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public End_location getEnd_location() {
        return this.end_location;
    }

    public void setEnd_location(End_location end_location) {
        this.end_location = end_location;
    }

    public String getHtml_instructions() {
        return this.html_instructions;
    }

    public void setHtml_instructions(String html_instructions) {
        this.html_instructions = html_instructions;
    }

    public Pl getPolyline() {
        return this.polyline;
    }

    public void setPolyline(Pl polyline) {
        this.polyline = polyline;
    }

    public Start_location getStart_location() {
        return this.start_location;
    }

    public void setStart_location(Start_location start_location) {
        this.start_location = start_location;
    }

    public String getTravel_mode() {
        return this.travel_mode;
    }

    public void setTravel_mode(String travel_mode) {
        this.travel_mode = travel_mode;
    }
}
