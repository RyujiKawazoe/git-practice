package jp.co.val.sample;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Point")
public class Point {
	@XStreamAlias("Prefecture")
	private Prefecture prefecture;
	
	@XStreamAlias("Minute")
	private int minute;
	
	@XStreamAlias("Station")
	private Station station;

	public Prefecture getPrefecture() {
		return prefecture;
	}
	
	public void setPrefecture(Prefecture prefecture) {
		this.prefecture = prefecture;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public void setMinute(int minute) {
		this.minute = minute;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}
}