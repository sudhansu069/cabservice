package com.target.cabservice.dto;

public class RouteDTO {

	private String cab_id;
	private String team_member_ids;
	private String route;
	private double route_cost;

	public String getCab_id() {
		return cab_id;
	}

	public void setCab_id(String cab_id) {
		this.cab_id = cab_id;
	}

	public String getTeam_member_ids() {
		return team_member_ids;
	}

	public void setTeam_member_ids(String team_member_ids) {
		this.team_member_ids = team_member_ids;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public double getRoute_cost() {
		return route_cost;
	}

	public void setRoute_cost(double route_cost) {
		this.route_cost = route_cost;
	}

}
