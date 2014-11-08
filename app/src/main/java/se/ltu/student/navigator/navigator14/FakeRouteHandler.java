package se.ltu.student.navigator.navigator14;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import se.ltu.student.navigator.navigator14.direction.Container;
import se.ltu.student.navigator.navigator14.direction.Decoder;
import se.ltu.student.navigator.navigator14.direction.Step;
import se.ltu.student.navigator.navigator14.json.Parser;
import se.ltu.student.navigator.navigator14.json.container.googleMapResponse.GoogleMapResponseContainerObject;

/**
 * Created by Navigator on 2014-09-26.
 */
public class FakeRouteHandler {
    private final GoogleMap map;
    // TODO: Make this name dynamic depending on program name (which will probably change)
    private final String filepath;

    public FakeRouteHandler(Context context, GoogleMap map ){
        this.map = map;
        this.filepath = context.getExternalFilesDir(null).getAbsolutePath();

    }

    public List<Polyline> run(int id) throws IOException{
        File f = new File(filepath+ "/route" +String.valueOf(id)+ ".route");
        Log.d("route",filepath);
        Decoder d = new Decoder(f);
        Container c = d.decode();

        List<Polyline> out = new ArrayList<Polyline>();
        Step current;
            // While the list of steps isn't empty
           while((current = c.nextStep()) != null) {
               Polyline p = map.addPolyline(new PolylineOptions().color(Color.BLUE).addAll(current.getPolyline()));
               out.add(p);
           }
        return out;
    }
}
