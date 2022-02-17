package document;

import java.util.ArrayList;
import java.awt.Point;
import view.Visitor;
import java.io.Serializable;
import java.lang.reflect.Array;


/**
 *  Class that Defines Design class object witg methods,
 *  stereotypes and Instance Variables
 */
public class InstanceVars extends ClassDecorator implements Serializable{

    // private String stereotype;
    private ArrayList<String> iv;
    // private ArrayList<String> instanceVariables;
    // private String name;
    // private Point position;
    private ClassComponent s;
    private int sizex;
    private int sizey;

    public InstanceVars(ClassComponent c)
    {
      super(c);
      iv = new ArrayList<String>();
      s = c;
      sizex = 0;
      sizey = 0;

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
    * adds a method to the class object
    * @param String to reperesent the method
    */
    @Override
    public void addProperty(String m)
    {
        super.addProperty(m);
    }


    public void addOtherProperty(String m){
      if (!(m.contains("("))  && !(m.contains(")"))){
        this.iv.add(m);
      }
      else{
        s.addOtherProperty(m);
      }


    }

    /**
    * getter for the methods for the class object
    **/
    @Override
    public ArrayList<String>  getListProperty()
    {
      return this.iv;
    }


    @Override
    public String getProperty(){return super.getProperty();}

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
