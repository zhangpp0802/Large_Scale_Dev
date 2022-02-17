package document;

import java.awt.Point;
import document.ClassComponent;
import java.util.ArrayList;
import view.Visitor;
import java.io.Serializable;
import java.lang.reflect.Array;

/**
 * A class decorator abstract class of type class compoenent
 */
public abstract class ClassDecorator implements ClassComponent, Serializable{
    protected ClassComponent aclass;
    private int sizex;
    private int sizey;

    public ClassDecorator(){}

    public ClassDecorator(ClassComponent c)
    {
      super();
      this.aclass = c;
      //this.name = new String();
      //this.position = new Point();
      sizex = 0;
      sizey = 0;

    }
    public void addClassName(String n)
    {
        //this.name = n;
        this.aclass.addClassName(n);
    }
    /**
    public String toString(String n)
    {
        return this.name;
    }
    */
    public String getClassName(){
      return this.aclass.getClassName();

    }

    /**
    * getter for the position of the class
    * @returns a Point object of the position of the class
    **/
    public Point getPosition(){
      return this.aclass.getPosition();
    }

    /**
    * Sets the position of the ClassComponent
    * @param Point onject that represent the new position
    **/
    public void setPosition(Point p){
      this.aclass.setPosition(p);
    }

    public void addProperty(String m){this.aclass.addProperty(m);}
    public ArrayList<String> getListProperty(){return this.aclass.getListProperty();}
    public String getProperty(){return this.aclass.getProperty();}
    public void addOtherProperty(String m){;}
    public void accept(Visitor v){;}
    public int[] getSize(){
      return new int[]{sizex,sizey};
    }
    public void setSize(int[] s){
      sizex = (int) Array.get(s, 0);
      sizey = (int) Array.get(s, 1);
    }


}
