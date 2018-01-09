package com.target.cabservice.dto;

/**
 * Request paylaod for Drop Points
 * @author sudhansu
 *
 */
public class DropPointDTO {

	private String target_headquarter;

	private String pointA;
	private String pointB;
	private String pointC;
	private String pointD;
	private String pointE;

	public String getTarget_headquarter() {
		return target_headquarter;
	}

	public void setTarget_headquarter(String target_headquarter) {
		this.target_headquarter = target_headquarter;
	}

	public String getPointA() {
		return pointA;
	}

	public void setPointA(String pointA) {
		this.pointA = pointA;
	}

	public String getPointB() {
		return pointB;
	}

	public void setPointB(String pointB) {
		this.pointB = pointB;
	}

	public String getPointC() {
		return pointC;
	}

	public void setPointC(String pointC) {
		this.pointC = pointC;
	}

	public String getPointD() {
		return pointD;
	}

	public void setPointD(String pointD) {
		this.pointD = pointD;
	}

	public String getPointE() {
		return pointE;
	}

	public void setPointE(String pointE) {
		this.pointE = pointE;
	}

}
