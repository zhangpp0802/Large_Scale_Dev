package view;

import java.lang.Math;

import java.io.Serializable;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.geom.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Toolkit;

// import view.DiagramMenu;
// import view.ToolbarView;
import document.Diagram;
import document.ClassComponent;
import document.Relation;
import view.*;

import document.InstanceVars;
import document.Methods;
import document.Stereotype;

import theme.ThemeCreator;

import memento.Caretaker;

/**
 * The main view class for running and displaying
 * a GUI application of a Class Diagram.
 */
public class DiagramView extends JComponent implements DiagramListener,
                                                       MouseListener,
                                                       MouseMotionListener,
                                                       Serializable{

    // private static volatile DiagramView dv;
    private final int classW = 200;
    private final int classH = 200;
    private Diagram diagram;
    private static ThemeCreator t;
    boolean dragging = false;
    private ArrayList<Point> classLocations;
    // DiagramMenu menu;
    private Point currentClicked;
    public ClassComponent currentClass;
    public ClassComponent draggingClass;
    // private ArrayList<ClassComponent> classes;
    private JFrame jf;
    private Caretaker caretaker;
    // private static DiagramView dv;

    /**
     * Constructs the DiagramView object.
     */
    public DiagramView() {
        jf = new JFrame("Diagram Editor");
        t = new ThemeCreator();
        // this.diagram = diagram;

        Font viewFont = new Font("TimesRoman", Font.PLAIN, 12);
        // currentClicked = new Point(0,0);
        // toolbar = new ToolbarView();
        this.setFont(viewFont);
        this.classLocations = new ArrayList<Point>();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // frame.setSize(screenSize.width, screenSize.height);

        setBackground(Color.BLACK);
        setSize(new Dimension(1250,800));
        setPreferredSize(new Dimension(1250, 800));
    }

    public void getAllLocations(){
      try{
      ArrayList<ClassComponent> classes = diagram.getAllClasses();
      classLocations = new ArrayList<Point>();
      // System.out.println(classes.toString());
      for (ClassComponent c : classes){
        Point p = c.getPosition();
        classLocations.add(c.getPosition());
      }
    }
      // System.out.println(classLocations.toString());}
      catch(Exception e){System.out.println("something wrong in getAllLocations");}
    }

    public Diagram getDiagram(){
      return this.diagram;
    }

    public void setDiagram(Diagram d){
      this.diagram = d;
      update();
    }

    /**
     * Gets the main application frame.
     * @return the main application frame as a JFrame object
     */
    public JFrame getFrame(){
        return this.jf;
    }

    /**
     * Creates the Diagram object, adds DiagramView as a concrete
     * observer ("Listener"). Also adds a mouse listener to this
     *
     * @param diagram a given Diagram object
     */
    public void go(DiagramView display) {
        // this.diagram = Diagram.getInstance();
        this.diagram = new Diagram();
        this.caretaker = new Caretaker();
        jf.getContentPane().add(display);
        ToolbarView toolbar = new ToolbarView(caretaker, this.diagram, display);
        jf.getContentPane().add(toolbar, BorderLayout.EAST);
        DiagramMenu menu = new DiagramMenu(caretaker, diagram, display, t);
        JMenuBar menuBar = menu.getMenuBar();
        jf.add(menuBar);
        jf.setJMenuBar(menuBar);
        diagram.addListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * Registers a mouse clicked mouse event - then performs an action
     * to the diagram editor.
     *
     * @param e a mouse click on the screen.
     */
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        currentClicked = new Point(x,y);
        currentClass = getClassByLocation(x,y);
        if (e.getModifiers() == MouseEvent.BUTTON3_MASK && currentClass != null)
        {
          JPopupMenu pop = new JPopupMenu("Edit");
          JMenuItem stereo = new JMenuItem("Add Stereotype");
          JMenuItem method = new JMenuItem("Add Method");
          JMenuItem inst = new JMenuItem("Add Instance Variable");
          JMenuItem delete = new JMenuItem("Delete Class");
          pop.add(stereo); pop.add(method); pop.add(inst); pop.add(delete);
          pop.show(this, e.getX(), e.getY());
          delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              diagram.deleteClass(currentClass);
              pop.setVisible(false);
            }});

          stereo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (currentClass != null){
                  addingStereotypes(pop);
                  }
                }
              });

          inst.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent e) {
                if (currentClass != null){
                addingInstances(pop);
                }
              }
            });

            method.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e) {
                 if (currentClass != null){
                 addingMethod(pop);
                  }
                }
              });
            }
          }

    private void addingMethod(JPopupMenu p){
      String method = JOptionPane.showInputDialog(
        null,
        "Enter a Method",
        "Name:",
        JOptionPane.QUESTION_MESSAGE);
        if (!(method.contains("("))){
          method = method+"(";
        }
        if (!(method.contains(")"))){
          method = method+")";
        }
        p.setVisible(false);
        currentAddOther(method);
    }
    private void addingInstances(JPopupMenu p){
      String instancev = JOptionPane.showInputDialog(
        null,
        "Enter an Instance Variable",
        "Name:",
        JOptionPane.QUESTION_MESSAGE);
        p.setVisible(false);
        currentAddOther(instancev);
      }

    private void addingStereotypes(JPopupMenu p){
      String stereotypeName = JOptionPane.showInputDialog(
        null,
        "Enter a Stereotype",
        "Name:",
        JOptionPane.QUESTION_MESSAGE);
        p.setVisible(false);
        currentAddProperty(stereotypeName);
      }

    /**
     * Gets the latest class.
     * @return latest class
     */
    public ClassComponent getLatestClass(){
      return currentClass;
    }


      /**
       * Registers that the mouse has been dragged
       * and moves the class
       * @param e a mouse drag on the screen
       */
       public void mouseDragged(MouseEvent e){
         getAllLocations();
         if (dragging){
              ClassComponent c  = getClassByLocation(e.getX(), e.getY());
              moveClass(draggingClass, e.getX(), e.getY());
          }

        }

       /**
       * Checks whether a set of coordinates are within a certain area
       * @param x1 the first x coord, y1 the first y coord,
       * x1 the second x coord, y1 the second y coord,
       * @return true if it is, false if not
       */
       private boolean inArea(int xMouse, int yMouse, int x2, int y2){
         if ((xMouse>= x2-1 && xMouse <= x2 +classW) && (yMouse > y2-1 && yMouse <= y2 +classH)){
           return true;
         }
         else{
           return false;
         }
       }

      /**
      * Gets the class at a given x, y position
      * @param x the x position, y the y position
      * @return the class at the position we want
      */
      private ClassComponent getClassByLocation(int x, int y){
        int count = 0;
        getAllLocations();

        for (Point p : classLocations){
          if(inArea(x,y,(int)p.getX(),(int)p.getY())){
            // System.out.println("getClass"+diagram.getAllClasses().get(count));
            return diagram.getAllClasses().get(count);
          }
          count += 1;
        }
        return null;
      }

      /**
      * Registers that the mouse has been pressed
      * and moves the class
      * @param e a mouse press on the screen
      */
      public void mousePressed(MouseEvent e){
        getAllLocations();
        for (Point p : this.classLocations){
          if(inArea(e.getX(), e.getY(), (int) p.getX(), (int) p.getY())){
            draggingClass = getClassByLocation((int) p.getX(), (int) p.getY());
            dragging = true;
            break;
          }
        }
      }

      /**
      * Moves the class
      * @param c the class to move, x the x position to move to,
      * y the y getPosition to move to
      */
      public void moveClass(ClassComponent c, int x, int y){
          Point pos = new Point(x-classW/2, y-classH/2);
          c.setPosition(pos);
          getAllLocations();
          repaint();
      }

    /**
     * Not necessary for our program
     * Registers that the mouse has been moved
     * @param e a mouse movement on the screen
     */
     public void mouseMoved(MouseEvent e){

     }

    /**
     * Not necessary for our program
     */
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Not necessary for our program
     * @param e [description]
     */
    public void mouseExited(MouseEvent e) {

    }


    /**
     * When the mouse is released dragging is set to false
     * @param e the mouse event
     */
    public void mouseReleased(MouseEvent e) {
      dragging = false;
      draggingClass = null;
    }


    /**
     * Marks the diagram editor as "dirty".
     */
    @Override
    public void update() {
        repaint();
    }

    /**
     * The paint component
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<ClassComponent> classes = diagram.getAllClasses();
        Visitor v = new PaintVisitor(g, t);
        for (ClassComponent c : classes){
            c.accept(v);
          }
        ArrayList<Relation> relations = diagram.getAllRelationships();
        for(Relation r: relations){
          r.accept(v);
        }
    }

    /**
     * [currentAddProperty description]
     * @param s [description]
     */
    public void currentAddProperty(String s){
      currentClass.addProperty(s);
      update();
    }

    /**
     * [currentAddOther description]
     * @param s [description]
     */
    public void currentAddOther(String s){
      currentClass.addOtherProperty(s);
      update();
    }

    /**
    * Gets the width of the class rectangle
    * @return the width
    */
    public int getClassW(){
      return classW;
    }

    /**
    * Gets the height of the class rectangle
    * @return the height
    */
    public int getClassH(){
      return classH;
    }


   /**
    * The main function for rendering the GUI
    * for a Diagram View.
   */
   public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable() {
           @Override
           public void run() {
               createAndShowGUI();
           }
       });
   }

   /**
    * Sets the background of the jframe based on the theme selection
    * @param f [description]
    * @param c [description]
    */
   public void setBackground(JFrame f, Color c){
     f.getContentPane().setBackground(c);
   }

   /**
    * Helper for manin function that renders the GUI for a
    * Diagram View. 
    */
   private static void createAndShowGUI() {
       DiagramView display = new DiagramView();
       JFrame frame = display.getFrame();
       display.go(display);
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.getContentPane().setBackground(Color.WHITE);  // background color
       frame.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
       frame.pack();
       frame.setVisible(true);
   }
}
