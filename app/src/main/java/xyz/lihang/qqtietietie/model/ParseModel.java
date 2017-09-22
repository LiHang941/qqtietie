package xyz.lihang.qqtietietie.model;

public class ParseModel {
	
	private Integer way;
	
	private float attr;
	
	public ParseModel(){
		this.way = Way.DIV.getFlag();
		this.attr =  0F;
	}
	public Integer getWay() {
		return way;
	}

	public ParseModel setWay(Integer way) {
		this.way = way;return this;
	}

	public float getAttr() {
		return attr;
	}

	public ParseModel setAttr(float attr) {
		this.attr = attr;
		return this;
	}
	
	@Override
	public String toString() {
		return "ParseModel [way=" + way + ", attr=" + attr + "]";
	}
}

