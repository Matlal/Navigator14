package se.ltu.student.navigator.navigator14.direction;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emil on 2014-04-28.
 */
public class Step {
    private Long distance, duration;
    private LatLng start_location, end_location;
    private String instructions;
    private List<LatLng> polyline;

    public Step() {
        polyline = new ArrayList<LatLng>();
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Number distance) {
        this.distance = distance.longValue();
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Number duration) {
        this.duration = duration.longValue();
    }

    public LatLng getStart_location() {
        return start_location;
    }

    public void setStart_location(LatLng start_location) {
        this.start_location = start_location;
    }

    public LatLng getEnd_location() {
        return end_location;
    }

    public void setEnd_location(LatLng end_location) {
        this.end_location = end_location;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<LatLng> getPolyline() {
        return polyline;
    }

    public void setPolyline(List<LatLng> polyline) {
        this.polyline = polyline;
    }

    public void addPoint(LatLng point) {
        this.polyline.add(point);
    }
}