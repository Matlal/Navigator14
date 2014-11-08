
package se.ltu.student.navigator.navigator14.json.container.googleMapResponse;

import java.util.List;

public class GoogleMapResponseContainerObject {
	private List<Route> routes;
	private String status;

	public List<Route> getRoutes() {
		return this.routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
