
package document;

import java.awt.Point;
import java.util.ArrayList;

import java.io.Serializable;
import view.Visitor;

/**
 * Interface that defines generic class object
 *
 */
public interface ClassComponent{

    /**
    * Adds a class name to the classComponent object
    * @param String name to name the class
    **/
    public void addClassName(String name);

    /**
    * getter for the name of the class
    * @returns a String of the name
    **/
    public String getClassName();

    /**
    * getter for the position of the class
    * @returns a Point object of the position of the class
    **/
    public Point getPosition();

    /**
    * Sets the position of the ClassComponent
    * @param Point onject that represent the new position
    **/
    public void setPosition(Point p);

    /**
     * Adds a property to a class component.
     * @param m property
     */
    public void addProperty(String m);

    /**
     * Gets the list of properties from a class component
     * @return lis of properties
     */
    public ArrayList<String> getListProperty();

    /**
     * Gets the current property of the class component
     * @return current property
     */
    public String getProperty();

    /**
     * Repsonsible for adding other properties to class components
     * @param m [description]
     */
    public void addOtherProperty(String m);

    /**
     * Accept method (Visitor pattern)
     * @param v a visitor
     */
    public void accept(Visitor v);

    /**
     * Gets the size of a class component
     * @return the dimensions of class components as a list
     */
    public int[] getSize();

    /**
     * Sets the size of a class component
     */
    public void setSize(int[] c);

}
