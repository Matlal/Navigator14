package se.ltu.student.navigator.navigator14.json.container.weatherDataRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Navigator on 2014-05-06.
 */
public class WeatherDataRequestContainer {
    private List<TimedCoords> coordinates;

    public WeatherDataRequestContainer() {
        coordinates = new ArrayList<TimedCoords>();
    }

    public void add(TimedCoords t) {
        coordinates.add(t);
    }

    public TimedCoords[] getArray() {
        return (TimedCoords[]) coordinates.toArray();
    }
}


