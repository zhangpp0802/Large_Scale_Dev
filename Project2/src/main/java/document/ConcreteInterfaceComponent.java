package document;

import java.awt.Point;
import java.util.ArrayList;
import view.Visitor;
import java.lang.reflect.Array;
import java.io.Serializable;

/**
 *  Class that Defines a basic class object
 */
public class ConcreteInterfaceComponent implements ClassComponent, Serializable{
    private String name;
    private Point position;

    private int sizex;
    private int sizey;

    public ConcreteInterfaceComponent()
    {
      this.name = "";
      // this.position = null;
      this.position = new Point();
      sizex = 200;
      sizey = 200;

    }
    /**
    * Adds a class name to the classComponent object
    * @param String name to name the class
    **/
    public void addClassName(String n)
    {
        this.name = n;
    }

    /**
    * getter for the name of the class
    * @returns a String of the name
    **/
    public String getClassName(){
      return name;
    }

    /**
    * getter for the position of the class
    * @returns a Point object of the position of the class
    **/
    public Point getPosition(){
      return this.position;
    }

    /**
    * Sets the position of the ClassComponent
    * @param Point onject that represent the new position
    **/
    public void setPosition(Point p){
      this.position = p;
    }

    public void addProperty(String m){;}
    public ArrayList<String> getListProperty(){return new ArrayList<String>();}
    public String getProperty(){return "";}
    public void addOtherProperty(String m){;}
    public void accept(Visitor v){
      v.visit(this);
    }

    public int[] getSize(){
      return new int[]{sizex,sizey};
    }
    public void setSize(int[] s){
      sizex = (int) Array.get(s, 0);
      sizey = (int) Array.get(s, 1);
    }

}
