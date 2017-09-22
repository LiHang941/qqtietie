package xyz.lihang.qqtietietie.utils.model;


/**
 *
 */
public class Face {
	
	private ParseModel xParseModel;
	
	private ParseModel yParseModel;

	public ParseModel getxParseModel() {
		return xParseModel;
	}

	public Face setxParseModel(ParseModel xParseModel) {
		this.xParseModel = xParseModel;
		return  this;
	}

	public ParseModel getyParseModel() {
		return yParseModel;
	}

	public Face setyParseModel(ParseModel yParseModel) {
		this.yParseModel = yParseModel;
		return  this;
	}

	@Override
	public String toString() {
		return "Face{" +
				"xParseModel=" + xParseModel +
				", yParseModel=" + yParseModel +
				'}';
	}
}
