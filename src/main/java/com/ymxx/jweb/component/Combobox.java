package com.ymxx.jweb.component;
 
public class Combobox {

	private String text = "";
	private String value =  "" ;
	private boolean selected = false;
	
	
	public Combobox(String value, String text) {
		super();
		this.value = value;
		this.text = text;
	}
	public Combobox(String value, String text, boolean selected) {
		this(value,text);
		this.selected = selected;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	
}
