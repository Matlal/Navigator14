
package se.ltu.student.navigator.navigator14.json.container.googleMapResponse;

import com.google.android.gms.maps.model.LatLng;

public class End_location {
	private Number lat;
	private Number lng;

	public Number getLat() {
		return this.lat;
	}

	public void setLat(Number lat) {
		this.lat = lat;
	}

	public Number getLng() {
		return this.lng;
	}

	public void setLng(Number lng) {
		this.lng = lng;
	}

    public LatLng toLatLng() {
        return new LatLng(lat.doubleValue(), lng.doubleValue());
    }
}
