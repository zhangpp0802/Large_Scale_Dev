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

import document.Diagram;
import document.Relation;
import test.java.DiagramListenerSpy;
import document.ConcreteClassComponent;
import document.Subtyping;
import document.Containment;
import document.Delegation;

public class SpyTest{
  private DiagramListenerSpy spy;
  private Diagram d;
  private ConcreteClassComponent from;
  private ConcreteClassComponent to;
  private Relation r;

  @Before
  public void setup(){
    spy = new DiagramListenerSpy();
    d = new Diagram();
    from = new ConcreteClassComponent();
    to = new ConcreteClassComponent();
    r = d.addRelationship("Subtyping", from, to);
    from.addClassName("from");
    to.addClassName("To");
  }

  @After
  public void tearDown(){
    spy = null;
    d = null;
  }

  @Test
  public void testAddRelationToList(){
    d.addListener(spy);
    d.addRelationToList(r);
    System.out.println(spy.getUpdateCount());
    assertEquals(1, spy.getUpdateCount());
  }

  @Test
  public void testDeleteRelationship(){
    d.addListener(spy);
    d.deleteRelationship(r);
    assertEquals(1, spy.getUpdateCount());
  }

  @Test
  public void testAddClass(){
    d.addListener(spy);
    d.addClass(from);
    assertEquals(1, spy.getUpdateCount());
  }

  @Test
  public void testDeleteClass(){
    d.addListener(spy);
    d.deleteClass(from);
    assertEquals(1, spy.getUpdateCount());
  }




}
