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

import theme.ThemeCreator;

import java.awt.Color;

@RunWith(JUnit4.class)
 public class ThemeCreatorTest
 {
   private ThemeCreator t;

   @Before
   public void setup(){
     t = new ThemeCreator();
   }

   @After
   public void tearDown(){
     t = null;
   }

   @Test
   public void setOutlineColor(){
     t.setOutlineColor(Color.RED);
     assertTrue("Outline color should be set to red", t.getOutlineColor().equals(Color.RED));
   }

   @Test
   public void setBackgroundColor(){
     t.setBackgroundColor(Color.BLUE);
     assertTrue("Background color should be set to blue", t.getBackgroundColor().equals(Color.BLUE));
   }

   @Test
   public void setBoxFillColor(){
     t.setBoxFillColor(Color.GREEN);
     assertTrue("Box fill color should be set to green", t.getBoxFillColor().equals(Color.GREEN));
   }

   @Test
   public void setFontColor(){
     t.setFontColor(Color.PINK);
     assertTrue("Font color should be set to pink", t.getFontColor().equals(Color.PINK));
   }

   @Test
   public void setArrowColor(){
     t.setArrowColor(Color.BLACK);
     assertTrue("Arrow color should be set to black", t.getArrowColor().equals(Color.BLACK));
   }

 }
