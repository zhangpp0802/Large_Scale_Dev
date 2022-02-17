package memento;

import memento.Memento;
import java.util.ArrayList;


/**
 * The Caretaker of the Memento design pattern.
 */
public class Caretaker {
  private ArrayList<Memento> mementos;
  // private int index;

  /**
   * Constructs a memento object.
   */
  public Caretaker(){
    this.mementos = new ArrayList<Memento>();
    // this.index = 0;
  }

  /**
   * Adds a Memento state to the list of Mementos.
   * @param state a given memento state
   */
  public void addMemento(Memento state){
    this.mementos.add(state);
  }

  /**
   * Returns the size of the memento list. i.e., number of
   * memento states
   * @return # of memento states
   */
  public int numMementos() {
    return mementos.size();
  }

  /**
   * Gets a specified memento state via index.
   * @param  index a number specifying which memento state to get
   * @return the state of memento at the given index
   */
  public Memento get(int index){
    return this.mementos.get(index);
  }

  public void showMementoStates() {
    for (Memento m: this.mementos){
      System.out.println(m.getState().getAllClasses());
    }
  }

  // public Memento undo() {return new Memento()};

  // public Memento redo() {}

}
