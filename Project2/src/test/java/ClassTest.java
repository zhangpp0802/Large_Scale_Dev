
package test.java;

import java.util.ArrayList;
import java.awt.Point;

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
//import document.ClassDecorator;
import document.ConcreteClassComponent;
import document.Stereotype;
import document.ClassComponent;
import document.InstanceVars;
import document.ConcreteClassComponent;
import document.Methods;

@RunWith(JUnit4.class)
public class ClassTest
{
    private ClassComponent aclass;
    // private ClassComponent c1;
    private ClassComponent ainterface;
    // private Designainterface i1;
    // private Designainterface i2;

    @Before
    public void setUp()
    {
      ainterface = new Stereotype(new Methods (new ConcreteClassComponent()));
              // c2 = new DesignClass();
      aclass = new Stereotype(new InstanceVars(new Methods(new ConcreteClassComponent())));
              // i2 = new Designainterface();
        // i2 = new Designainterface();
    }

    @After
    public void tearDown()
    {
      aclass = null;
      ainterface = null;
    }

    @Test
    public void testName()
    {
      aclass.addClassName("ala");
      assertEquals("class name added or not","ala", aclass.getClassName());
      ainterface.addClassName("oho");
      assertEquals("class ainterface added or not","oho", ainterface.getClassName());
    }

    @Test
    public void testPosition()
    {
      Point p = new Point(3,7);
      aclass.setPosition(p);
      assertEquals("class position added or not",p, aclass.getPosition());
      Point p2 = new Point(3,8);
      ainterface.setPosition(p2);
      assertEquals("ainterface position added or not",p2, ainterface.getPosition());
    }

    @Test
    public void testSter()
    {
      aclass.addProperty("Object");
      assertEquals("class stereotype added or not","Object", aclass.getProperty());
      ainterface.addProperty("ainterface");
      assertEquals("ainterface stereotype added or not","ainterface", ainterface.getProperty());
    }

    @Test
    public void testMethodsInterface()
    {
      ainterface.addOtherProperty("alalal()");
      ainterface.addOtherProperty("alalalalala()");
      ainterface.addOtherProperty("nononono()");
      ArrayList<String> methods = ainterface.getListProperty();
      ArrayList<String> compare = new ArrayList<String>();
      compare.add("alalal()");
      compare.add("alalalalala()");
      compare.add("nononono()");
      assertEquals("class methods added or not",compare, methods);
    }

    @Test
    public void testInsvar()
    {
      aclass.addOtherProperty("alalal");
      aclass.addOtherProperty("alalalalala");
      aclass.addOtherProperty("nononono");
      ArrayList<String> methods = aclass.getListProperty();
      ArrayList<String> compare = new ArrayList<String>();
      compare.add("alalal");
      compare.add("alalalalala");
      compare.add("nononono");
      assertEquals("class methods added or not",compare, methods);
    }

}
