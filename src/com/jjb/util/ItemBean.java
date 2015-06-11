package com.jjb.util;

public class ItemBean {
	private long id;		// 自增长id
	private String userId;	// 外键，用户名
	private String name;	// item 的名字
	private double price;	// item 的价格
	private boolean isOut;	// 是否支出
	private int classify;	// item 的类型
	private String time;	// item 产生的时间
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
		this.userId = "abc"; // offline
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isOut() {
		return isOut;
	}
	public void setOut(boolean isOut) {
		this.isOut = isOut;
	}
	public int getClassify() {
		return classify;
	}
	public void setClassify(int classify) {
		this.classify = classify;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ItemBean [id=" + id + ", userId=" + userId + ", name=" + name
				+ ", price=" + price + ", isOut=" + isOut + ", classify="
				+ classify + ", time=" + time + "]";
	}
	
	public ItemBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemBean(String userId, String name, double price,
			boolean isOut, int classify, String time) {
		super();
		this.userId = userId;
		this.userId = "abc";
		this.name = name;
		this.price = price;
		this.isOut = isOut;
		this.classify = classify;
		this.time = time;
	}
	
}
