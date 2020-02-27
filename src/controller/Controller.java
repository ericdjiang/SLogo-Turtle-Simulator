package controller;

import model.ConsoleModel;
import model.TurtleModel;
import view.util.Pen;
import view.views.TurtleView;
import view.layout.TurtleWindow;

public class Controller {
    private TurtleModel turtleModel;
    private TurtleWindow turtleWindow;
    private TurtleView turtleView;
    private ConsoleModel consoleModel;
    private Pen pen;
    private int index = 1;
    private double point;

    public Controller(TurtleModel turtleBackEnd, TurtleWindow turtleFrontEnd, ConsoleModel consoleModel) {
        this.turtleModel = turtleBackEnd;
        this.turtleWindow = turtleFrontEnd;
        this.turtleView = new TurtleView();
        this.consoleModel = consoleModel;
        this.pen = new Pen();
        turtleView.setX(turtleModel.getX() + turtleWindow.getViewWidth()/2 - turtleView.getWidth()/2);
        turtleView.setY(turtleModel.getY() + turtleWindow.getViewHeight()/2 - turtleView.getHeight()/2);
        turtleWindow.getChildren().add(turtleView);
    }
    public void update() {
        if (turtleModel.getClearedStatus()) {
            pen.clear(turtleWindow);
            turtleView.setX(turtleWindow.getViewWidth() / 2 - turtleView.getWidth() / 2);
            turtleView.setY(turtleWindow.getViewHeight() / 2 - turtleView.getHeight() / 2);
            turtleView.setRotate(0);
            turtleModel.setCleared(false);
        }
        else {
            if (turtleModel.getPenStatus()) {

            for (Object o : turtleModel.getPointList()) {
                if (index % 2 == 1) {
                    point = (double) o + turtleWindow.getViewWidth() / 2;
                    index = 2;
                } else {
                    point = (double) o * -1 + turtleWindow.getViewHeight() / 2;
                    index = 1;
                }
                pen.addPoint(point);
//                if (point < turtleWindow.getViewHeight() && point < turtleWindow.getViewWidth() && point > 0 && point > 0) {
//                    pen.addPoint(point);
//                }
//                else {
//                    if (point > turtleWindow.getViewHeight()) {
//                        point = turtleWindow.getViewHeight();
//                    }
//                    else if (point > turtleWindow.getViewWidth()) {
//                        point = turtleWindow.getViewWidth();
//                    }
//                    pen.addPoint(point);
//                }

            }
            turtleModel.clearList();
            turtleWindow.getChildren().add(pen.draw(pen.getColor()));
             }
            else {
                turtleModel.trackPos();
                pen.pickUp();
            }
            turtleView.setX(turtleModel.getX() + turtleWindow.getViewWidth() / 2 - turtleView.getWidth() / 2);
            turtleView.setY(-turtleModel.getY() + turtleWindow.getViewHeight() / 2 - turtleView.getHeight() / 2);
            turtleView.setTurtleRotation(turtleModel.getAngle());
            turtleView.setVisible(turtleModel.getShowing());
        }

    }
    public TurtleModel getModel() {
        return this.turtleModel;
    }
    public TurtleView getView() {
        return this.turtleView;
    }
    public Pen getPen() {
        return this.pen;
    }
    public ConsoleModel getConsoleModel() { return this.consoleModel; }
}
