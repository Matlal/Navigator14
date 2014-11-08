package se.ltu.student.navigator.navigator14.direction;

import com.google.android.gms.maps.model.LatLng;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import se.ltu.student.navigator.navigator14.HttpCallback;
import se.ltu.student.navigator.navigator14.HttpService;

public class Fetcher {
    String fetchURL = "http://maps.googleapis.com/maps/api/directions/json?";
    List<String> URLParams;
    List<LatLng> waypoints;
    private HttpCallback parentObject;
    private String file;

    public Fetcher(HttpCallback parent) {
        URLParams = new ArrayList<String>();
        waypoints = new ArrayList<LatLng>();
        this.parentObject = parent;
    }

    public void addParam(String key, String value) {
        URLParams.add(key + "=" + value);
    }

    public void addWaypoint(LatLng point) {
        waypoints.add(point);
    }

    public void addWaypoints(List<LatLng> points) {
        for (LatLng p : points) addWaypoint(p);
    }


    private void parseWaypoints() {
        String out = "";
        if (waypoints.size() == 0) return;
        for (LatLng l : waypoints) out += l.latitude + "," + l.longitude + "|";
        try {
            addParam("waypoints", URLEncoder.encode(out.substring(0, out.length() - 2), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void allowAlternateRoutes(Boolean in) {
        addParam("alternatives", in.toString());
    }

    private String parseParams() {
        String out = "";
        for (String i : URLParams) {
            out += "&" + i;
        }
        return out;
    }

    public void fetch(LatLng start, LatLng end) {
        parseWaypoints();
        String toFetch = "origin=" + start.latitude + ","
                + start.longitude + "&destination=" + end.latitude + ","
                + end.longitude + parseParams();

        file = "dir_" + new SimpleDateFormat("yyyyMMdd-HHmmss").format(Calendar.getInstance().getTime());

        HttpService s = new HttpService(fetchURL, parentObject);
        s.GET(toFetch);
    }
}
