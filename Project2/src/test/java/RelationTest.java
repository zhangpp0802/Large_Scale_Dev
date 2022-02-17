package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

// import document.*;
import document.ConcreteClassComponent;
import document.Subtyping;
import document.Containment;
import document.Delegation;

@RunWith(JUnit4.class)
public class RelationTest{

  private ConcreteClassComponent c1;
  private ConcreteClassComponent c2;

  @Before
  public void setUp()
  {
      c1 = new ConcreteClassComponent();
      c2 = new ConcreteClassComponent();
      c1.addClassName("Class1");
      c2.addClassName("Class2");
  }
  @After
  public void tearDown()
  {
      c1 = null;
      c1 = null;
  }

  @Test
  public void construct()
  {
      Subtyping r1 = new Subtyping(c1,c2);
      assertTrue("Classname of the source shoud be Class1", r1.getSource().getClassName().equals("Class1"));
      assertTrue("Classname of the destination shoud be Class2", r1.getDestination().getClassName().equals("Class2"));
  }


  @Test
  public void componentTest1()
  {
      Subtyping r1 = new Subtyping(c1,c2);
      assertTrue("The Type of RELATION SHOULD BE Subtyping ", r1.getComponent().equals("Subtyping"));
  }

  @Test
  public void componentTest2()
  {
      Delegation r1 = new Delegation(c1,c2);
      assertTrue("The Type of RELATION SHOULD BE Delegation", r1.getComponent().equals("Delegation"));
  }

  @Test
  public void componentTest3()
  {
      Containment r1 = new Containment(c1,c2);
      assertTrue("The Type of RELATION SHOULD BE Containment", r1.getComponent().equals("Containment"));
  }

/**



  @Test
  public void construct()
  {
assertEquals("New graph has no vertices", 0, g.numVertices());
assertEquals("New graph has no edges", 0, g.numEdges());
  }

  @Test
  public void construct()
  {
assertEquals("New graph has no vertices", 0, g.numVertices());
assertEquals("New graph has no edges", 0, g.numEdges());
  }
  @Test
  public void construct()
  {
assertEquals("New graph has no vertices", 0, g.numVertices());
assertEquals("New graph has no edges", 0, g.numEdges());
  }

  @Test
  public void construct()
  {
assertEquals("New graph has no vertices", 0, g.numVertices());
assertEquals("New graph has no edges", 0, g.numEdges());
  }

  @Test
  public void construct()
  {
assertEquals("New graph has no vertices", 0, g.numVertices());
assertEquals("New graph has no edges", 0, g.numEdges());
  }


*/













}
