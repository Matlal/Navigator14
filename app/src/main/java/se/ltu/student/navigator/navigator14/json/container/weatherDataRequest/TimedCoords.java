package se.ltu.student.navigator.navigator14.json.container.weatherDataRequest;

import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Navigator on 2014-05-06.
 */
public class TimedCoords {
    final String ts;
    final Double lt, lg;

    public TimedCoords(Date timestamp, LatLng coords) {
        ts = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
        lt = coords.latitude;
        lg = coords.longitude;
    }

}
