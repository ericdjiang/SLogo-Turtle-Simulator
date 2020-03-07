package model;

import java.util.ArrayList;
import java.util.List;

public class TurtleModel {
  private int myId;
  private double myX;
  private double myY;
  private double myAngle;
  private double penSize;
  private boolean isShowing;
  private boolean penDown;
  private double myZeroX;
  private double myZeroY;
  private boolean isCleared;
  private List<Double> myPoints;
  private List<Double> backgroundColor;
  private List<Double> penColor;


  public TurtleModel (int id,double myX, double myY, double myAngle) {
    this.myId = id;
    this.myX = myX;
    this.myY = myY;
    this.myZeroX = myX;
    this.myZeroY = myY;
    this.myAngle = myAngle;
    this.myPoints = new ArrayList();
    this.myPoints.add(myX);
    this.myPoints.add(myY);
    this.isShowing = true;
    this.penDown = true;
  }
  public void setBackgroundColor(List<Double> rgbVals){
    backgroundColor = rgbVals;
  }

  public void setPenColor(List<Double> rgbVals){
    penColor = rgbVals;
  }

  public void setX(double x){
    this.myX = x;
    addPoints(x);
  }

  public void setY(double y) {
    this.myY = y;
    addPoints(y);
  }
  private void addPoints(double p) {
    myPoints.add(p);
  }
  public List getPointList() {
    return this.myPoints;
  }
  public void clearList() {
    myPoints.clear();
  }
  public void reInitCenter() {
    clearList();
    myPoints.add(myZeroX);
    myPoints.add(myZeroY);
  }
  public void setXY(double x, double y) {
    setX(x);
    setY(y);
  }
  public void setAngle(double a) {
    this.myAngle = a;
  }
  public double getX() {
    return this.myX;
  }
  public double getY() {
    return this.myY;
  }
  public double getAngle() {
    return this.myAngle;
  }

  public boolean getShowing() {return this.isShowing;}
  public boolean getPenStatus() {return this.penDown;}

  public void makePenDown(){
    penDown = true;
  }
  public void makePenUp(){
    penDown = false;
  }
  public void hideTurtle(){
    isShowing = false;
  }
  public void showTurtle(){
    isShowing = true;
  }
  public void setCleared(boolean b){
    isCleared = b;
  }
  public void setPenSize(double size){
    penSize = size;
  }
  public boolean getClearedStatus() {
    return this.isCleared;
  }
  public void trackPos() {
    myPoints.clear();
    myPoints.add(myX);
    myPoints.add(myY);
  }
  public int getModelId(){
    return myId;
  }

  public List<Double> getPenColor(){
    return penColor;
  }
}