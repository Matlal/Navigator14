package se.ltu.student.navigator.navigator14.direction;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

import se.ltu.student.navigator.navigator14.json.container.googleMapResponse.*;
import se.ltu.student.navigator.navigator14.json.Parser;

public class Decoder {
    GoogleMapResponseContainerObject o;
    String status;

    public Decoder(File in) {
        try {
            o = (GoogleMapResponseContainerObject) new Parser().parseFile(in, GoogleMapResponseContainerObject.class);
        } catch (IOException e) {
            Log.e("DirectionDecoder", "Unable to read file " + in.getName());
            e.printStackTrace();
        }
    }

    public Decoder(String in) throws InvalidObjectException {
        o = (GoogleMapResponseContainerObject) new Parser().parse(in, GoogleMapResponseContainerObject.class);
        if (o == null) {
            throw new InvalidObjectException("No data recieved from JSON Parser");
        }
    }

    public String status() {
        return o.getStatus();
    }

    private Step convertStep(se.ltu.student.navigator.navigator14.json.container.googleMapResponse.Step s) {
        Step out = new Step();
        out.setDistance(s.getDistance().getValue().longValue());
        out.setDuration(s.getDuration().getValue().longValue());
        out.setStart_location(s.getStart_location().toLatLng());
        out.setEnd_location(s.getEnd_location().toLatLng());
        out.setInstructions(s.getHtml_instructions());
        for (LatLng l : decodePoly(s.getPolyline().getPoints())) {
            out.addPoint(l);
        }
        return out;
    }

    public Container decode() {
        List<Step> steps = new ArrayList<Step>();
        for (Route r : o.getRoutes()) {
            for (Leg l : r.getLegs()) {
                for (se.ltu.student.navigator.navigator14.json.container.googleMapResponse.Step s : l.getSteps()) {
                    steps.add(convertStep(s));
                }
            }
        }
        return new Container(steps);
    }

    /**
     * Method to decode polyline points. Courtesy :
     * jeffreysambells.com/2010/05/27
     * /decoding-polylines-from-google-maps-direction-api-with-java
     */
    private List<LatLng> decodePoly(String encoded) {
        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }
}
