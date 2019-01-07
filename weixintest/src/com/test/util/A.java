package com.test.util;

public class A {
	private String qrcode;
	 private String longitude;
	 private String latitude;
	 private String address;
	 
	 public A(String qrcode, String longitude, String latitude,
	   String address) {
	  super();
	  this.qrcode = qrcode;
	  this.longitude = longitude;
	  this.latitude = latitude;
	  this.address = address;
	 }
	 public String getQrcode() {
	  return qrcode;
	 }
	 public void setQrcode(String qrcode) {
	  this.qrcode = qrcode;
	 }
	 public String getLongitude() {
	  return longitude;
	 }
	 public void setLongitude(String longitude) {
	  this.longitude = longitude;
	 }
	 public String getLatitude() {
	  return latitude;
	 }
	 public void setLatitude(String latitude) {
	  this.latitude = latitude;
	 }
	 public String getAddress() {
	  return address;
	 }
	 public void setAddress(String address) {
	  this.address = address;
	 }
}
