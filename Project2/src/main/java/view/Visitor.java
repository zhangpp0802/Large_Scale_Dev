package view;

import java.awt.Point;
import java.util.ArrayList;

// import java.io.Serializable;
import document.*;
// import view.Visitor;
// import view.DiagramView;
import view.*;

/**
 * Interface that defines generic class object
 *
 */
public interface Visitor{

  public void visit(Subtyping r);

  public void visit(Delegation r);

  public void visit(Containment r);

  public void visit(ConcreteClassComponent c);

  public void visit(ConcreteInterfaceComponent c);

  public void visit(InstanceVars c);

  public void visit(Methods c);

  public void visit(Stereotype c);
}
