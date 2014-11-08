package se.ltu.student.navigator.navigator14.direction;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
import java.util.List;

//import se.ltu.student.navigator.JSONObject.WeatherDataRequestContainer.TimedCoords;
//import se.ltu.student.navigator.JSONObject.WeatherDataRequestContainer.WeatherDataRequestContainer;

/**
 * Created by Emil on 2014-04-28.
 */
public class Container {
    private Long distance = 0L;
    private Long duration = 0L;
    private LatLng start_location, end_location;
    private List<Step> steps;
    private int currentStep;

    public Container() {
        steps = new ArrayList<Step>();
        currentStep = 0;
    }

    public Container(Long distance, Long duration, LatLng start_location, LatLng end_location, List<Step> steps) {
        this.distance = distance;
        this.duration = duration;
        this.start_location = start_location;
        this.end_location = end_location;
        this.steps = steps;
        this.currentStep = 0;
    }

    public Container(List<Step> steps) {
        this.start_location = steps.get(0).getStart_location();
        this.end_location = steps.get(steps.size() - 1).getEnd_location();
        this.steps = steps;
        for (Step s : steps) {
            this.distance += s.getDistance();
            this.duration += s.getDuration();
        }
        this.currentStep = 0;
    }

    public Step nextStep() {
        Step out;
        if (currentStep >= steps.size()) {
            out = null;
        } else {
            out = steps.get(currentStep);
            currentStep++;
        }
        return out;
    }

/*    public WeatherDataRequestContainer getTimedCoords(int granularity, Date when) {
        LatLng Start = steps.get(0).getStart_location();
        LatLng Stop = steps.get(steps.size() - 1).getEnd_location();
        WeatherDataRequestContainer out = new WeatherDataRequestContainer();
        Calendar now = Calendar.getInstance();
        now.setTime(when);

        out.add(new TimedCoords(now.getTime(), Start));
        for (Step s : steps) {
            Long d = s.getDuration() / s.getPolyline().size();
            for (int i = granularity; i < s.getPolyline().size(); i += granularity) {
                now.add(Calendar.SECOND, d.intValue());
                out.add(new TimedCoords(now.getTime(), s.getPolyline().get(i)));
            }
        }
        out.add(new TimedCoords(now.getTime(), Stop));
        return out;

    }

    public WeatherDataRequestContainer getTimedCoords(int granularity) {
        return getTimedCoords(granularity, Calendar.getInstance().getTime());
    }*/

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
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

    public void addStep(Step s) {
        this.steps.add(s);
    }

    public void addSteps(List<Step> steps) {
        this.steps.addAll(steps);
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public List<Step> getSteps() { return steps;}
}
