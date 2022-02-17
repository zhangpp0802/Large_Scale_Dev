package document;

import java.util.ArrayList;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;

import document.ClassComponent;
import document.Relation;
import document.Delegation;
import document.Subtyping;
import document.Containment;

import view.DiagramListener;

import memento.Memento;

public class Diagram implements Serializable{

  private static final long serialVersionUID = 1L;

  private ArrayList<ClassComponent> classes;
  private ArrayList<Relation> relationships;
  private transient ArrayList<DiagramListener> observers;

  /**
   * Constructs a new diagram object.
   */
  public Diagram() {
    classes = new ArrayList<ClassComponent>();
    relationships = new ArrayList<Relation>();
    observers = new ArrayList<DiagramListener>();
  }

  /**
   * Custom serialization method - writes the Diagram object.
   * @param  out
   * @throws IOException
   */
  private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
  }

  /**
   * Custom deserialization method - reads the Diagram object.
   * @param  is
   * @throws IOException
   * @throws ClassNotFoundException
   */
  private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
    is.defaultReadObject();
    observers = new ArrayList<DiagramListener>();
  }

  /**
   * Show all class names for debugging purposes.
   */
  public void showClassNames() {
    for (ClassComponent comp: classes) {
      System.out.println("Classes");
    }
  }

  /**
  * Adds a relationship between two classes
  */
  public Relation addRelationship(String relationship, ClassComponent from, ClassComponent to){
    Relation newRelation;
    if (relationship.equals("Delegation")){
      newRelation = new Delegation(from, to);
      //relationships.add(newRelation);
    }
    else if (relationship.equals("Containment")){
      newRelation = new Containment(from, to);
      //relationships.add(newRelation);
    }
    else{
      newRelation = new Subtyping(from, to);
      //relationships.add(newRelation);
    }
    return newRelation;
  }

  public void addRelationToList(Relation r){
    relationships.add(r);
    this.notifyListeners();
  }

  /*
  * Deletes a relationship between two classes
  * if the relationship exists
  */
  public void deleteRelationship(Relation r){
    if(relationships.contains(r)){
      relationships.remove(r);
    }
    this.notifyListeners();
  }

  /*
  * Adds a ClassComponent to the list of classes
  */
  public void addClass(ClassComponent classToAdd){
    if(!classes.contains(classToAdd)){
      classes.add(classToAdd);
    }
    this.notifyListeners();
  }

  public void deleteClass(ClassComponent toRemove){
    if(classes.contains(toRemove)){
      classes.remove(toRemove);
    }
    this.notifyListeners();
  }

  /*
  * Gets the list of all the classes in the diagram
  */
  public ArrayList<ClassComponent> getAllClasses(){
    return classes;
  }

  /*
  * Gets the list of all the relationships in the diagram
  */
  public ArrayList<Relation> getAllRelationships(){
    return relationships;
  }


  public Memento createMemento(){
    return new Memento(this);
  }

  public void setMemento(Memento m){
    // this = m.getState();
    this.relationships = m.getState().getAllRelationships();
    this.classes = m.getState().getAllClasses();
    this.notifyListeners();
  }


  /*
  * Updates each DiagramListener in the list of DiagramListeners
  */
  public void notifyListeners(){
    for (DiagramListener dListener : observers){
      dListener.update();
    }
  }

  /*
  * Add the DiagramListener to list of DiagramListeners
  * If the listener is already in the list it does nothing
  */
  public void addListener(DiagramListener dListener){
    if (!observers.contains(dListener)){
      observers.add(dListener);
    }
  }

  /*
  * Removes the DiagramListener to list of DiagramListeners
  * If the listener is not in the list it does nothing
  */
  public void removeListener(DiagramListener dListener){
    if (observers.contains(dListener)){
      observers.remove(dListener);
    }
  }
}
