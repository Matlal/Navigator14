
package se.ltu.student.navigator.navigator14.json.container.googleMapResponse;

import java.util.List;

public class Route {
	private Bounds bounds;
	private String copyrights;
	private List<Leg> legs;
	private Pl overview_polyline;
	private String summary;
	private List<String> warnings;
	private List<Integer> waypoint_order;

	public Bounds getBounds() {
		return this.bounds;
	}

	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}

	public String getCopyrights() {
		return this.copyrights;
	}

	public void setCopyrights(String copyrights) {
		this.copyrights = copyrights;
	}

	public List<Leg> getLegs() {
		return this.legs;
	}

	public void setLegs(List<Leg> legs) {
		this.legs = legs;
	}

	public Pl getOverview_polyline() {
		return this.overview_polyline;
	}

	public void setOverview_polyline(Pl overview_polyline) {
		this.overview_polyline = overview_polyline;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<String> getWarnings() {
		return this.warnings;
	}

	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}

	public List<Integer> getWaypoint_order() {
		return this.waypoint_order;
	}

	public void setWaypoint_order(List<Integer> waypoint_order) {
		this.waypoint_order = waypoint_order;
	}
}
