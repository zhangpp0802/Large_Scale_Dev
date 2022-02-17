package document;

import java.awt.Point;
import java.io.Serializable;

import view.*;

/**
 * An object that defines a generic line that represents a Subtyping relationship
 * between two classComponents
 *
 */
public class Subtyping implements Relation, Serializable{
  private String component;
  private ClassComponent src;
  private ClassComponent dest;

  public Subtyping(ClassComponent source, ClassComponent destination ){
    this.component = "Subtyping";
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

  public Point getSourcePosition(){
    return this.src.getPosition();
  }

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
