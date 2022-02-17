package view;

import java.awt.Point;
import java.util.ArrayList;

import document.*;
import view.Visitor;
import view.DiagramView;
import theme.ThemeCreator;

import java.awt.Color;

import java.awt.*;
import java.awt.geom.*;
import java.awt.AlphaComposite;
import java.lang.reflect.Array;


// import java.io.Serializable;

/**
 * Interface that defines generic class object
 *
 */
public class PaintVisitor implements Visitor{

  private Graphics g;
  ThemeCreator t;
  private final int classW = 200;
  private final int classH = 200;
  private final int between = 13;
  private final int halfBetween = 7;
  private int line1 = 20;
  private int line2 = 60;
  private int line1End;
  private int line2End;
  private ArrayList<String> classNames;

  public PaintVisitor(Graphics g, ThemeCreator tc){
    this.g = g;
    this.t = tc;
    classNames = new ArrayList<String>();
  }

  @Override
  public void visit(ConcreteClassComponent c){
      String name = c.getClassName();
      if (!(classNames.contains(name))){
        classNames.add(name);
        line1 = 20;
        line2 = 60;
      }
      int classsizex = (int) Array.get(getClassSize(c), 0);
      int classsizey = (int) Array.get(getClassSize(c), 1);
      int sl = name.length();
      int start = (int)classsizex/2 - (int)sl/2-15;
      int x = (int) Array.get(getXY(c), 0);
      int y = (int) Array.get(getXY(c), 1);
      line1End = line2-halfBetween;
      line2End = y+classsizey -halfBetween;
      g.setColor(t.getOutlineColor());
      this.g.drawRect(x,y,classsizex,classsizey);
      Graphics2D g2d = (Graphics2D) g;
      Composite originalComposite = g2d.getComposite();
      AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
      g2d.setComposite(alphaComposite);
      g2d.setColor(t.getBoxFillColor());
      g2d.fillRect(x,y,classsizex,classsizey);
      g2d.setComposite(originalComposite);
      g.setColor(t.getFontColor());
      g.drawString(name,x+start,y+10);
      g.setColor(t.getOutlineColor());
      this.g.drawLine(x,y+line1,x+classsizex,y+line1);
      this.g.drawLine(x,y+line2,x+classsizex,y+line2);
      // this.g.drawLine(x,y+20,x+classsizex,y+20);
      // this.g.drawLine(x,y+60,x+classsizex,y+60);

  }
  //
  private void changeLine(){
    line2 = line2 + between;
    line1End = line2-halfBetween;
  }
  private void changeBottomLine(ClassComponent c){
    int classsizex = (int) Array.get(getClassSize(c), 0);
    int classsizey = (int) Array.get(getClassSize(c), 1);
    c.setSize(new int[]{classsizex,classsizey+between});
    classsizey = (int) Array.get(getClassSize(c), 1);
    line2End = classsizey-halfBetween;
  }

  // private ArrayList<String> getMethods(ArrayList<ArrayList<String>> s){
  //     if (s.get(0) == null){return new ArrayList<String>();}
  //     return s.get(0);
  // }
  //
  // private ArrayList<String> getInstanceVars(ArrayList<ArrayList<String>> s){
  //     if (s.get(1) == null){return new ArrayList<String>();}
  //     return s.get(1);
  // }

  @Override
  public void visit(ConcreteInterfaceComponent c){
    String name = c.getClassName();
    if (!(classNames.contains(name))){
      classNames.add(name);
      line1 = 20;
      line2 = 60;
    }
    int classsizex = (int) Array.get(getClassSize(c), 0);
    int classsizey = (int) Array.get(getClassSize(c), 1);
    int sl = name.length();
    int start = (int)classsizex/2 - (int)sl/2-15;
    int x = (int) Array.get(getXY(c), 0);
    int y = (int) Array.get(getXY(c), 1);
    line1End = y+classsizey -halfBetween;
    line2End = y+classsizey -halfBetween;
    g.setColor(t.getOutlineColor());
    g.drawRect(x,y,classsizex,classsizey);
    Graphics2D g2d = (Graphics2D) g;
    Composite originalComposite = g2d.getComposite();
    AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
    g2d.setComposite(alphaComposite);
    g2d.setColor(t.getBoxFillColor());
    g2d.fillRect(x,y,classsizex,classsizey);
    g2d.setComposite(originalComposite);
    g.setColor(t.getFontColor());
    g.drawString(name,x+start,y+10);
    g.setColor(t.getOutlineColor());
    g.drawLine(x,y+line1,x+classsizex,y+line1);
    // g.drawLine(x,y+20,x+classsizex,y+20);
  }
  @Override
  public void visit(InstanceVars c){
    int classsizex = (int) Array.get(getClassSize(c), 0);
    int classsizey = (int) Array.get(getClassSize(c), 1);
    int x = (int) Array.get(getXY(c), 0);
    int y = (int) Array.get(getXY(c), 1);
    // ArrayList<ArrayList<String>> ivm = c.getListProperty();
    ArrayList<String> iv = c.getListProperty();
    int i;
    g.setColor(t.getFontColor());
    if (!(iv.isEmpty())){
      i = 0;
      for (String s : iv){
        int draw = y+28+i*13;
        if (draw > line1End){changeLine();}
        g.drawString(s,x+5,y+28+i*13);
        i++;
      }
    }
  }
  @Override
  public void visit(Methods c){

    int classsizex = (int) Array.get(getClassSize(c), 0);
    int classsizey = (int) Array.get(getClassSize(c), 1);
    int x = (int) Array.get(getXY(c), 0);
    int y = (int) Array.get(getXY(c), 1);
      // ArrayList<ArrayList<String>> ivm = c.getListProperty();
      ArrayList<String> methods = c.getListProperty();
      int i;
      g.setColor(t.getFontColor());
      if (!(methods.isEmpty())){
          i = 0;
            for (String s : methods){
              int draw = y+line2+8+i*13;
              if (draw >= y-halfBetween){changeBottomLine(c);}
              if ((s.contains("(")) && (s.contains(")"))){
                g.drawString(s,x+5,y+line2+8+i*13);
                i++;
              }
              else{
                g.drawString("("+s+")",x+5,y+line2+8+i*13);
                i++;
              }
            }
          }
  }
  @Override
  public void visit(Stereotype c){
    int classsizex = (int) Array.get(getClassSize(c), 0);
    int classsizey = (int) Array.get(getClassSize(c), 1);
    int x = (int) Array.get(getXY(c), 0);
    int y = (int) Array.get(getXY(c), 1);
    String stereotype = c.getProperty();
    g.setColor(t.getFontColor());
    int sl = stereotype.length() + 4;
    int start = ((int)classsizex/2 - (int)sl/2);
    if (stereotype != ""){g.drawString("<<"+stereotype+">>",x+start-28,y-10);}

  }

  private int[] getClassSize(ClassComponent c){
    int classsizex = classW;
    int classsizey = classH;
    return new int[]{classsizex,classsizey};
  }

  private int[] getXY(ClassComponent c){
    Point position = c.getPosition();
    int x = (int) position.getX();
    int y = (int) position.getY();
    return new int[]{x,y};
  }

  @Override
  public void visit(Subtyping r){

    Point x = r.getSourcePosition();
    Point y = r.getDestinationPosition();
    Point above;
    Point below;
    if (x.getY()> y.getY()){below = x; above = y;}
    else{below = y; above = x;}

    //straight line with black stroked triangle on dest
    g.setColor(t.getArrowColor());
    g.drawLine((int)above.getX()+classW/2,(int)above.getY()+(int)classH,(int)below.getX()+classW/2, (int)below.getY());
    // g.setColor(Color.white);
    if(x.equals(above)){
      //dest is below
      int m = (int) below.getX()+classW/2;
      int n = (int) below.getY();
      g.drawPolygon(new int[] {m, m+5, m-5}, new int[] {n, n-10, n-10}, 3);
    }
    else{
      //dest is above
      int m = (int) above.getX()+classW/2;
      int n = (int) above.getY()+classH;
      g.drawPolygon(new int[] {m, m+5, m-5}, new int[] {n, n+10, n+10}, 3);
    }
  }

  @Override
  public void visit(Delegation r){
    Point x = r.getSourcePosition();
    Point y = r.getDestinationPosition();
    Point above;
    Point below;
    if (x.getY()> y.getY()){below = x; above = y;}
    else{below = y; above = x;}

    //dashed line with trangle on dest
    Graphics2D g2d = (Graphics2D) g;
    Stroke solid = g2d.getStroke();
    Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
    g2d.setStroke(dashed);
    g.setColor(t.getArrowColor());
    g2d.drawLine((int)above.getX()+classW/2,(int)above.getY()+(int)classH,(int)below.getX()+classW/2, (int)below.getY());
    // g.setColor(Color.white);
    g2d.setStroke(solid);
    if(x.equals(above)){
      //dest is below
      int m = (int) below.getX()+classW/2;
      int n = (int) below.getY();
      g.drawPolygon(new int[] {m, m+5, m-5}, new int[] {n, n-10, n-10}, 3);
    }
    else{
      //dest is above
      int m = (int) above.getX()+classW/2;
      int n = (int) above.getY()+classH;
      g.drawPolygon(new int[] {m, m+5, m-5}, new int[] {n, n+10, n+10}, 3);
    }
  }

  @Override
  public void visit(Containment r){
    Point x = r.getSourcePosition();
    Point y = r.getDestinationPosition();
    Point above;
    Point below;
    if (x.getY()> y.getY()){below = x; above = y;}
    else{below = y; above = x;}

    //straight line with black triangle on dest
    g.setColor(t.getArrowColor());
    g.drawLine((int)above.getX()+classW/2,(int)above.getY()+(int)classH,(int)below.getX()+classW/2, (int)below.getY());
    //g.setColor(Color.black);
    if(x.equals(above)){
      //dest is below
      int m = (int) below.getX()+classW/2;
      int n = (int) below.getY();
      g.setColor(t.getArrowColor());
      g.fillPolygon(new int[] {m, m+5, m-5}, new int[] {n, n-10, n-10}, 3);
    }
    else{
      //dest is above
      int m = (int) above.getX()+classW/2;
      int n = (int) above.getY()+classH;
      g.setColor(t.getArrowColor());
      g.fillPolygon(new int[] {m, m+5, m-5}, new int[] {n, n+10, n+10}, 3);
    }
  }
}
