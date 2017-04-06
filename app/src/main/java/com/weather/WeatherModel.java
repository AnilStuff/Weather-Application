package com.weather;

/**
 * Created by anilkothari on 26/03/17.
 */

public class WeatherModel {

 private String summary;
 private String icon;
 private String cityName;
 private String main;
 private String description;

 private double id;

 private double windSpeed;
 private double windAngle;


 private double humidity;
 private double pressure;
 private double temperature;
 private double temperatureMin;
 private double temperatureMax;
 private String dateTime;


 public String getDateTime() {
  return dateTime;
 }

 public void setDateTime(String dateTime) {
  this.dateTime = dateTime;
 }

 public String getCityName(){return cityName;}

 public Double getId() {
  return id;
 }


 public void setId(Double id) {
  this.id = id;
 }

 public String getDescription() {
  return description;
 }

 public void setDescription(String description) {
  this.description = description;
 }

 public String getMain() {
  return main;
 }

 public void setMain(String main) {
  this.main = main;
 }

 public void setCityName(String cityName) {
  this.cityName = cityName;
 }

 public void setPressure(double pressure) {
  this.pressure = pressure;
 }

 public double getPressure() {
  return pressure;
 }

 public double getTemperatureMax() {
  return temperatureMax;
 }

 public void setTemperatureMax(double temperatureMax) {
  this.temperatureMax = temperatureMax;
 }

 public double getTemperatureMin() {
  return temperatureMin;
 }

 public void setTemperatureMin(double temperatureMin) {
  this.temperatureMin = temperatureMin;
 }

 public void setWindAngle(double apparentTemp) {
  this.windAngle = apparentTemp;
 }

 public double getWindAngle() {
  return windAngle;
 }

 public void setHumidity(double humidity) {
  this.humidity = humidity;
 }

 public double getHumidity() {
  return humidity;
 }

 public void setWindSpeed(double windSpeed) {
  this.windSpeed = windSpeed;
 }

 public double getWindSpeed() {
  return windSpeed;
 }

 public double getTemperature() {
  return temperature;
 }

 public void setTemperature(double temperature) {
  this.temperature = temperature;
 }

 public String getIcon() {
  return icon;
 }

 public void setIcon(String icon) {
  this.icon = icon;
 }

 public String getSummary() {
  return summary;
 }

 public void setSummary(String summary) {
  this.summary = summary;
 }

}
