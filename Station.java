package jp.co.val.sample;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Station")
public class Station {
	@XStreamAsAttribute
	private int code;
	
	@XStreamAlias("Name")
	private String name;
	
	@XStreamAlias("Type")
	private String type;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}