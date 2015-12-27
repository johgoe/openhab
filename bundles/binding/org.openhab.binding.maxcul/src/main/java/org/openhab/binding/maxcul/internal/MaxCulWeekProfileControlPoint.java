package org.openhab.binding.maxcul.internal;

public class MaxCulWeekProfileControlPoint {

	private Integer hour;
	private Integer min;
	private float temperature;
	
	public void setMin(int i) {
		min=i;
	}

	public void setHour(int i) {
		hour=i;
	}

	public Integer getMin() {
		return min;
	}

	public Integer getHour() {
		return hour;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;		
	}
	
	public float getTemperature(){
		return temperature;
	}

}
