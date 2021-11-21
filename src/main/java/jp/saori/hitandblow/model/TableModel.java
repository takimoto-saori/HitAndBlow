package jp.saori.hitandblow.model;

import java.io.Serializable;

public class TableModel implements Serializable {
	private String number;
	private int count;
	private int hit;
	private int blow;

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getBlow() {
		return blow;
	}
	public void setBlow(int blow) {
		this.blow = blow;
	}

}
