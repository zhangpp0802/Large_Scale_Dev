package document;
import java.awt.Point;

import view.*;

/**
 * Interface for defining and storing specfic relationships
 * between classComponent objects
 *
 */
public interface Relation {

    /**
    * getter for the source classComponent
    * in the relationship
    */
    public ClassComponent getSource();

    /**
    * getter for the destination classComponent
    * in the relationship
    */
    public ClassComponent getDestination();

    /**
    * getter for the component(String of type of Relation)
    * for the relationship
    */
    public String getComponent();

    public Point getSourcePosition();

    public Point getDestinationPosition();

    public void accept(Visitor v);



}
