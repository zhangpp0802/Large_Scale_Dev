package document;

import java.awt.Point;
import java.io.Serializable;
import view.Visitor;
/**
 * An object that defines a generic line that represents a containment relationship
 * between two classComponents
 *
 */
public class Containment implements Relation, Serializable{
  private String component;
  private ClassComponent src;
  private ClassComponent dest;

  public Containment(ClassComponent source, ClassComponent destination){
    this.component = "Containment";
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
   * Gets the source coordinates
   * @return [description]
   */
  public Point getSourcePosition(){
    return this.src.getPosition();
  }

  /**
   * Gets the destination coordinates
   * @return [description]
   */
  public Point getDestinationPosition(){
    return this.dest.getPosition();
  }

  /**
  * getter for the Destination classComponent
  * in the relationship
  */
  public ClassComponent getDestination(){
    return this.dest;
  }

  /**
  * getter for the component(String of type of Relation)
  * for the relationship
  */
  public String getComponent(){
    return this.component;
  }

  /**
   * Accept method for accepting vistors
   * @param v vistor 
   */
  public void accept(Visitor v){
    v.visit(this);
  }


}
