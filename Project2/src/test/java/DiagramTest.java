package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import document.*;
import view.DiagramListener;

@RunWith(JUnit4.class)
 public class DiagramTest
 {
   private Diagram d;

   @Before
   public void setup(){
     d = new Diagram();
   }

   @After
   public void tearDown(){
     d = null;
   }

   @Test
   public void addRelationship(){
     ConcreteClassComponent from = new ConcreteClassComponent();
     ConcreteClassComponent to = new ConcreteClassComponent();
     Relation r = d.addRelationship("Subtyping", from, to);
     d.addRelationToList(r);

     assertEquals("Diagram contains relationship bewtween class from and to", d.getAllRelationships().get(0), r);
     d.deleteRelationship(r);
   }

   @Test
   public void deleteRelationship(){
     ConcreteClassComponent from = new ConcreteClassComponent();
     ConcreteClassComponent to = new ConcreteClassComponent();
     Relation r = d.addRelationship("Subtyping", from, to);
     d.addRelationToList(r);
     d.deleteRelationship(r);

     assertFalse("Diagram does not contain the relationship bewtween class from and to", d.getAllRelationships().contains(r));

   }

   @Test
   public void addClass(){
     ConcreteClassComponent aClass = new ConcreteClassComponent();
     d.addClass(aClass);
     assertTrue("aClass should be in the diagram", d.getAllClasses().contains(aClass));
     d.deleteClass(aClass);
   }

   @Test
   public void deleteClass(){
     ConcreteClassComponent aClass = new ConcreteClassComponent();
     d.addClass(aClass);
     d.deleteClass(aClass);
     assertFalse("aClass should not be in the diagram", d.getAllClasses().contains(aClass));
   }

   // @Test
   // public void getClass(){
   //
   // }

   @Test
   public void getAllClasses(){
     ConcreteClassComponent class1 = new ConcreteClassComponent();
     ConcreteClassComponent class2 = new ConcreteClassComponent();
     ConcreteClassComponent class3 = new ConcreteClassComponent();
     d.addClass(class1);
     d.addClass(class2);
     d.addClass(class3);
     assertEquals("getAllClasses() should have size 3", d.getAllClasses().size(), 3);
   }

   // @Test
   // public void getRelationship(){
   //
   // }

   // @Test
   public void getAllRelationships(){
     ConcreteClassComponent class1 = new ConcreteClassComponent();
     ConcreteClassComponent class2 = new ConcreteClassComponent();
     Relation r1 = d.addRelationship("Subtyping", class1, class2);
     Relation r2 = d.addRelationship("Delegation", class1, class2);
     Relation r3 = d.addRelationship("Containment", class1, class2);
     d.addRelationToList(r1);
     d.addRelationToList(r2);
     d.addRelationToList(r3);
     assertEquals("getAllRelationships() should have size 3", d.getAllRelationships().size(), 3);
   }
 }
