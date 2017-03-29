package com.example.anilkothari.weatherapp;

/**
 * Created by anilkothari on 26/03/17.
 */

public class WeatherModel {

 private String summary;

 private String icon;

 private double temperature;

 private double windSpeed;

 private double visibility;

 private double humidity;

 private double apparentTemp;

 private double temperatureMin;

 private double temperatureMax;

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

 public void setApparentTemp(double apparentTemp) {
  this.apparentTemp = apparentTemp;
 }

 public double getApparentTemp() {
  return apparentTemp;
 }

 public void setHumidity(double humidity) {
  this.humidity = humidity;
 }

 public double getHumidity() {
  return humidity;
 }

 public double getVisibility() {
  return visibility;
 }

 public void setVisibility(double visibility) {
  this.visibility = visibility;
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
