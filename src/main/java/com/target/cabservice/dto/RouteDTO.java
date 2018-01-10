package com.target.cabservice.dto;

import java.util.List;
import java.util.Set;

public class RouteDTO {

	private int cab_id;
	private Set<Integer> team_member_ids;
	List<Location> route;
	private double route_cost;

	public int getCab_id() {
		return cab_id;
	}

	public void setCab_id(int cab_id) {
		this.cab_id = cab_id;
	}

	public Set<Integer> getTeam_member_ids() {
		return team_member_ids;
	}

	public void setTeam_member_ids(Set<Integer> team_member_ids) {
		this.team_member_ids = team_member_ids;
	}

	public List<Location> getRoute() {
		return route;
	}

	public void setRoute(List<Location> route) {
		this.route = route;
	}

	public double getRoute_cost() {
		return route_cost;
	}

	public void setRoute_cost(double route_cost) {
		this.route_cost = route_cost;
	}

}
