package se.ltu.student.navigator.navigator14;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity {
   public ListView list;
    private GoogleMap mMap;
    private FakeRouteHandler routeHandler;
    private List<Polyline> visibleRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        displayList();

        // Clicklistener
        View.OnClickListener handler = new View.OnClickListener(){
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.sandningButton:
                        //Alert();
                        break;

                   // case R.id.textViewItem:
                   //break;
                }
            }
        };
        //lyssnar om knappen trycks på
        findViewById(R.id.sandningButton).setOnClickListener(handler);

        // Hämtar kartan
        routeHandler = new FakeRouteHandler(this.getApplicationContext(), mMap);


    }



    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {

        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();

                // hårdkod flytta kameran, dynamisk ska läggas in senare
                  mMap.setMyLocationEnabled(true);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(65.617354, 22.138138), 12));


                /*
                Location loc;
                LatLng myLoc =null;
                mMap.setMyLocationEnabled(true);
                loc = mMap.getMyLocation();
                if(loc == null){
                    myLoc = new LatLng(loc.getLatitude(), loc.getLongitude());
                }
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLoc,12));
                */




            }
        }
    }

    private void setUpMap() {
       // mMap.addMarker(new MarkerOptions().position(new LatLng(65.617354, 22.138138)).title("Marker"));

    }

    public void displayList(){

        //Hårdkodar in gator, andras till dynamiskt senare.
        StreetItem[] streets = new StreetItem[5];

        streets[0] = new StreetItem(13, "Linje 13",    this);//new LatLng(65.595208, 22.146251), new LatLng(65.602917, 22.136378)) Midgårdsvägen;
        streets[1] = new StreetItem(92, "Linje 4",    this);//new LatLng(65.619536, 22.053202), new LatLng(65.614294, 22.098204));
        streets[2] = new StreetItem(93, "Linje 5",   this);//new LatLng(65.613796, 22.098353), new LatLng(65.595185, 22.140745));
        streets[3] = new StreetItem(94, "Linje 6",     this);//new LatLng(65.575106, 22.259129), new LatLng(65.589793, 22.229244));
        streets[4] = new StreetItem(95, "Linje 7",      this);//new LatLng(65.551470, 22.257588), new LatLng(65.571721, 22.200582));


        // Läggerin  objekten i en adapter
        ArrayAdapterItem adapter = new ArrayAdapterItem(this, R.layout.listview_row, streets);

        //Lägger in Listan med gator
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListenerListViewItem());

        //lägger in Rubriken "gator"
        View header = (View)getLayoutInflater().inflate(R.layout.header, null);
        list.addHeaderView(header);
    }

    // runs when a StreetItem is selected and the user presses OK on the alert.
    public void StreetItemCallback(int itemId, Object data) {
        // draw the map for this ID
        try {
            boolean value = (Boolean) data;
            if (!value) {
                for (Polyline p : visibleRoute) {
                    p.remove();
                }
                visibleRoute.clear();
                return;
            }
        }catch (Exception e){
            //lolololololololol
        }
        try {
            visibleRoute = routeHandler.run(itemId);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("RouteHandler","No route found.");
        }
    }
}
