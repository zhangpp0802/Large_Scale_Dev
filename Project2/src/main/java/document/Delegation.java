package document;

import java.awt.Point;
import java.io.Serializable;
import view.*;

public class Delegation implements Relation, Serializable{
  private String component;
  private ClassComponent src;
  private ClassComponent dest;

  /**
   * An object that defines a = line that represents a Delegation relationship
   * between two classComponents
   *
   */
  public Delegation(ClassComponent source, ClassComponent destination){

    this.component = "Delegation";
    this.src = source;
    this.dest = destination;
  }

  /**
  * getter for the source classComponent
  * in the relationship
  */
  public ClassComponent getSource(){
    return this.src;
  }

  /**
  * getter for the Destination classComponent
  * in the relationship
  */
  public ClassComponent getDestination(){
    return this.dest;
  }

  /**
   * Gets the coordinates of the source class
   * @return coordinates
   */
  public Point getSourcePosition(){
    return this.src.getPosition();
  }

  /**
   * Gets the coordinates of the destination class
   * @return coordinates
   */
  public Point getDestinationPosition(){
    return this.dest.getPosition();
  }


  /**
  * getter for the component(String of type of Relation)
  * for the relationship
  */
  public String getComponent(){
    return this.component;
  }
  public void accept(Visitor v){
    v.visit(this);
  }



}
