package view;

import java.util.*;
import java.awt.Point;
import javax.swing.border.Border;
import javax.swing.*;
import document.Diagram;
import document.ConcreteClassComponent;
import document.ConcreteInterfaceComponent;
import document.InstanceVars;
import document.ClassComponent;
import document.Methods;
import document.Stereotype;
import document.Relation;
import view.DiagramView;

import memento.Caretaker;
import memento.Memento;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
* Class for defining the toolbar and its buttons
* The toolbar will have buttons for the user to press
* these buttons will allow users to add and edit the classes
* and add relationships
*/
public class ToolbarView extends JPanel implements ActionListener{

    private final int defx = 100;
    private final int defy = 100;
    JToolBar toolBar;
    JButton addInterface, addDesignClass, addRelationship;
    Diagram d;
    DiagramView dv;
    private Caretaker caretaker;

    /**
     * Constructs a ToolBarView object. This toolbar Object
     * will be used in the DiagramView class to edit the Class Diagram
     * drawing board.
     */
    public ToolbarView(Caretaker ct, Diagram diagram, DiagramView dv) {

        // d = Diagram.getInstance();
        this.dv = dv;
        this.caretaker = ct;
        // d = Diagram.getInstance();
        d = diagram;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.toolBar = new JToolBar("Diagram Elements", JToolBar.VERTICAL);

        addDesignClass = new JButton("Add Class");
        addDesignClass.addActionListener(this);

        addInterface = new JButton("Add Interface");
        addInterface.addActionListener(this);


        addRelationship = new JButton("Add Relationship");
        addRelationship.addActionListener(this);


        toolBar.setFloatable(false);

        toolBar.add(addDesignClass);
        toolBar.addSeparator();
        toolBar.add(addInterface);
        toolBar.addSeparator();
        toolBar.add(addRelationship);


        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        add(toolBar,gbc);

    }


    /**
     * Returns the toolbar item.
     * @return a JToolBar instance of the toolbar and its buttons
     */
    public JToolBar getToolBar(){
        return this.toolBar;
    }


    /**
    * Method for handling action performed when each of the buttons
    * in the toolbar are pressed
    **/
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addRelationship && d.getAllClasses().size() >= 2) {
          addRelationship();
         }

        if (e.getSource() == addDesignClass) {
          addDesignClass();
        }

        else if (e.getSource() == addInterface) {
          addInterface();
        }

    }

    // HELPERS FOR BUTTON CLICKED ACTIONS


      private void addRelationship(){
        JFrame popup = new JFrame("Add a Relationship");
        JPanel p = new JPanel();
        JLabel r1 = new JLabel("Select a Relation type:");
        JLabel s1 = new JLabel("Select a Source Class:");
        JLabel d1 = new JLabel("Select a Destination:");
        popup.setSize(300,175);
        popup.setLocationRelativeTo(null);
        String[] names = getClassNames();
        String[] relations = {"Subtyping", "Delegation", "Containment"};
        JComboBox<String> rela = new JComboBox<String>(relations);
        JComboBox<String> src = new JComboBox<String>(names);
        JComboBox<String> dest = new JComboBox<String>(names);
        JButton b = new JButton("Confirm");
        p.add(r1);
        p.add(rela);
        p.add(s1);
        p.add(src);
        p.add(d1);
        p.add(dest);
        p.add(b);
        popup.add(p);
        popup.setVisible(true);
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              String type = "" + rela.getItemAt(rela.getSelectedIndex());
              String src1 = "" + src.getItemAt(src.getSelectedIndex());
              String dest1 = "" + dest.getItemAt(dest.getSelectedIndex());
              if (! src1.equals(dest1)){
                ClassComponent srcClass = getClassByName(src1);
                ClassComponent destClass = getClassByName(dest1);
                Relation r = d.addRelationship(type, srcClass, destClass);
                Memento state = d.createMemento();
                caretaker.addMemento(state);
                d.addRelationToList(r);
                popup.dispose();
            }
          }
        });

      }

      private String[] getClassNames(){
        ArrayList<String> namesList = new ArrayList<String>();
        ArrayList<ClassComponent> classes = d.getAllClasses();
        for (ClassComponent c : classes){
          namesList.add(c.getClassName());
        }
        String[] names = namesList.toArray(new String[namesList.size()]);
        return names;
      }

      private ClassComponent getClassByName(String name){
        ArrayList<ClassComponent> classes = d.getAllClasses();
        for (ClassComponent c : classes){
          if (name.equals(c.getClassName())){
            return c;
          }

        }
        return null;
      }



      private void addInterface(){
        String interfaceName = JOptionPane.showInputDialog(
          null,
          "Enter an Interface Name",
          "Name:",
          JOptionPane.QUESTION_MESSAGE);

          ClassComponent cc = new Stereotype(new Methods(new ConcreteInterfaceComponent()));
          Point p = new Point(defx,defy);
          cc.addClassName(interfaceName);
          cc.setPosition(p);
          // Memento memento = new Memento(d);
          Memento state = d.createMemento();
          this.caretaker.addMemento(state);
          d.addClass(cc);
      }
      /**
      * Helper for steps taken after add Designclass
      * button is pressed
      */
      private void addDesignClass(){
        String designClassName = JOptionPane.showInputDialog(
          null,
          "Enter a Class Name",
          "Name:",
          JOptionPane.QUESTION_MESSAGE);
          // ClassComponent cc = new ConcreteClassComponent();
          ClassComponent cc = new Stereotype(new InstanceVars(new Methods(new ConcreteClassComponent())));
          Point p = new Point(defx,defy);
          cc.addClassName(designClassName);
          cc.setPosition(p);
          // System.out.println(d);
          System.out.println(d.getAllClasses());
          Memento state = d.createMemento();
          this.caretaker.addMemento(state);
          d.addClass(cc);
          // Memento state1 = d.createMemento();
          // this.caretaker.addMemento(state1);
      }


}
