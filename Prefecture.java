package jp.co.val.sample;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Prefecture")
public class Prefecture {
	@XStreamAsAttribute
	private int code;
	
	@XStreamAlias("Name")
	private String name;
}