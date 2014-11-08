package se.ltu.student.navigator.navigator14;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by root on 2014-09-15.
 */
public class StreetItem extends ObjectItem {

    private final MapsActivity parent;
    public StreetItem(int itemId, String itemName, MapsActivity parent) {
        super(itemId,itemName);
        this.parent = parent;
    }

    @Override
    public void callBack(Object data) {
        parent.StreetItemCallback(itemId,data);
    }
}
