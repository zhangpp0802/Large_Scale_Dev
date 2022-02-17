package document;

import java.util.ArrayList;
import java.awt.Point;
import java.lang.reflect.Array;

import java.io.Serializable;
import view.Visitor;


/**
 *  Class that Defines Design class object witg methods,
 *  stereotypes and Instance Variables
 */
public class Stereotype extends ClassDecorator implements Serializable{
    // private static final long serialVersionUID = 1L;

    // private String stereotype;
    private String stereotype;
    private int sizex;
    private int sizey;
    // private ArrayList<String> instanceVariables;
    // private String name;
    // private Point position;
    // private ArrayList<String> ivm;
    // private static final long serialVersionUID = 1L;
    private ClassComponent s;

    // public Stereotype(){}
    public Stereotype(){
      // super();
      this.stereotype = "";
      sizex = 0;
      sizey = 0;

    }

    public Stereotype(ClassComponent c)
    {
      super(c);
      // ivm = c.getListProperty();
      stereotype = "";
       s = c;

    }

    @Override
    public void addClassName(String name){
      super.addClassName(name);
    }

    @Override
    public String getClassName(){
      return super.getClassName();
    }

    @Override
    public Point getPosition(){
      return super.getPosition();
    }

    @Override
    public void setPosition(Point p){
      super.setPosition(p);
    }

    /**
    * getter for the methods for the class object
    **/
    public String getProperty()
    {
        return this.stereotype;
    }

    /**
    * getter for the methods for the class object
    **/
    public void addProperty(String s)
    {
        this.stereotype = s;
    }

    public void addOtherProperty(String m)
    {
        s.addOtherProperty(m);
    }

    @Override
    public ArrayList<String> getListProperty(){
      return super.getListProperty();
    }

    @Override
    public void accept(Visitor v){
      v.visit(this);
      s.accept(v);
    }
    public int[] getSize(){
      return new int[]{sizex,sizey};
    }
    public void setSize(int[] c){
      s.setSize(c);

    }

}
