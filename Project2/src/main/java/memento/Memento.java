package memento;

import document.Diagram;
/**
 *
 */
public class Memento {

  private Diagram state;

  /**
   * Constructs a memento object.
   */
  public Memento(Diagram d){
    this.state = d;
  }

  public Diagram getState(){
    return state;
  }

}
