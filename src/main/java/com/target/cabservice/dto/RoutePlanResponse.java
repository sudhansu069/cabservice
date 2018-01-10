package com.target.cabservice.dto;

import java.util.List;

public class RoutePlanResponse {

	double total_cost;

	List<RouteDTO> routes;

	public double getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}

	public List<RouteDTO> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteDTO> routes) {
		this.routes = routes;
	}

}
