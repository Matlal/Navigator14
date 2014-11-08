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

/**
    Mainclass, here is where the magic happens.

 * You need to be connected to the internet to access the google maps.
 * You need to create static files(json format) for new paths do display on the map.
 * You can put the jsonfiles in "File manager/Android/data/se.ltu.navigator.navigator14"
 * Methods are implemented to easy access json from internet, speak to Emil Tylen.
 *
 */
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


        // load Routehandler, this one only reads offline from the NEXUS.
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


                  //Google map camera,  hardcoded to LTU ATM, should be dynamic and follow mylocation.
                  mMap.setMyLocationEnabled(true);
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(65.617354, 22.138138), 12));




        }
    }


     //Display the list of different paths(busslinjer), Static atm, but should be dynamic later.
    public void displayList(){
        StreetItem[] streets = new StreetItem[5];
        streets[0] = new StreetItem(13, "Linje 13",    this);//new LatLng(65.595208, 22.146251), new LatLng(65.602917, 22.136378));
        streets[1] = new StreetItem(92, "Linje 4",    this);//new LatLng(65.619536, 22.053202), new LatLng(65.614294, 22.098204));
        streets[2] = new StreetItem(93, "Linje 5",   this);//new LatLng(65.613796, 22.098353), new LatLng(65.595185, 22.140745));
        streets[3] = new StreetItem(94, "Linje 6",     this);//new LatLng(65.575106, 22.259129), new LatLng(65.589793, 22.229244));
        streets[4] = new StreetItem(95, "Linje 7",      this);//new LatLng(65.551470, 22.257588), new LatLng(65.571721, 22.200582));


        //Creating Adapter
        ArrayAdapterItem adapter = new ArrayAdapterItem(this, R.layout.listview_row, streets);

        //Filling Adapter with StreetItems
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new OnItemClickListenerListViewItem());

        //Topic of the list.
        View header = (View)getLayoutInflater().inflate(R.layout.header, null);
        list.addHeaderView(header);
    }

    // Runs when a StreetItem is selected and the user presses OK on the alert.
    public void StreetItemCallback(int itemId, Object data) {
        // draw routes on the map for this ID
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

// Clicklistener, not used atm but good to use later with buttons.
        /*
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
        //listening for "buttons" and how to handle them with handler.
        findViewById(R.id.sandningButton).setOnClickListener(handler);
*/