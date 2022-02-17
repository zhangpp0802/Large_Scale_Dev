package view;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;

import document.Diagram;
import view.DiagramView;
import view.DiagramListener;
import document.ClassComponent;
import export.GraphicsExporter;
import export.PNGExporter;
import export.JPEGExporter;
// import export.PDFExporter;
import theme.Theme;
import theme.ThemeCreator;
//import theme.ColorChooser;

import memento.Caretaker;
import memento.Memento;

/**
 * Creates a menu bar for the diagram view.
 */
public class DiagramMenu extends JPanel implements ActionListener{

    JMenuBar mb;
    JMenu file, edit, view, export, theme;
    JMenuItem open, save; // Under file drop
    JMenuItem undo, redo;         // Under edit drop (not implemented yet)
    JMenuItem darkMode, lightMode;
    JMenu custom;
    JMenuItem outlineColor, backgroundColor, boxFillColor, fontColor, arrowColor;
    JFileChooser fc;
    JMenuItem png, jpeg, pdf;

    private Caretaker caretaker;
    private Diagram d;
    private DiagramView dv;
    private GraphicsExporter exporter;
    private ThemeCreator t;
    private int numSteps;



    /**
     * DiagramMenu creates a menu bar with menu items that have
     * the capability of performing actions with the Class Diagram
     * Editor.
     */
    public DiagramMenu(Caretaker ct, Diagram d, DiagramView div, ThemeCreator tc) {
        this.d = d;
        this.caretaker = ct;
        dv = div;
        mb = new JMenuBar();
        t = tc;
        this.numSteps = 1;

        file = new JMenu("File");
        file.addActionListener(this);
        open = new JMenuItem("Open...");
        open.addActionListener(this);
        save = new JMenuItem("Save as");
        save.addActionListener(this);
        export = new JMenu("Export");
        export.addActionListener(this);

        file.add(open);
        file.addSeparator();
        file.add(save);
        file.addSeparator();
        file.add(export);

        png = new JMenuItem("as .png");
        png.addActionListener(this);

        jpeg = new JMenuItem("as .jpeg");
        jpeg.addActionListener(this);

        export.add(jpeg);
        export.add(png);

        /* Edit can be used later on (potentially for undo operations) */
        edit = new JMenu("Edit");
        edit.addActionListener(this);
        undo = new JMenuItem("Undo");
        undo.addActionListener(this);
        redo = new JMenuItem("Redo");
        redo.addActionListener(this);

        edit.add(undo);
        edit.add(redo);

        /* View can be used later on (potentially for changing look-and-feel)
           of a the class diagram editor. */
        view = new JMenu("View");
        view.addActionListener(this);
        theme = new JMenu("Change Theme");
        theme.addActionListener(this);
        view.add(theme);
        darkMode = new JMenuItem("Dark Mode");
        darkMode.addActionListener(this);
        lightMode = new JMenuItem("Light Mode");
        lightMode.addActionListener(this);
        custom = new JMenu("Custom");
        custom.addActionListener(this);
        outlineColor = new JMenuItem("Choose Class Outline Color");
        outlineColor.addActionListener(this);
        backgroundColor = new JMenuItem("Choose Background Color");
        backgroundColor.addActionListener(this);
        boxFillColor = new JMenuItem("Choose Class Fill Color");
        boxFillColor.addActionListener(this);
        fontColor = new JMenuItem("Choose Font Color");
        fontColor.addActionListener(this);
        arrowColor = new JMenuItem("Choose Arrow Color");
        arrowColor.addActionListener(this);

        theme.add(darkMode);
        theme.add(lightMode);
        theme.add(custom);

        custom.add(outlineColor);
        custom.add(backgroundColor);
        custom.add(boxFillColor);
        custom.add(fontColor);
        custom.add(arrowColor);

        mb.add(file);
        mb.add(edit);
        mb.add(view);
    }


    /**
     * Sets the exporter accordingly (to the user's preference).
     * @param ge [description]
     */
    public void setExporter(GraphicsExporter ge) {
        this.exporter = ge;
    }

    /**
     * Getter for retrieving the menubar instance.
     * @return the JMenuBar item
     */
    public JMenuBar getMenuBar() {
        return mb;
    }

    /**
     * Exports an image of the class diagram editor as a Graphics
     * file (e.g., as a .jpeg, .png, or .pdf file).
     *
     * @param ge the graphics-exporting strategy based on the user's
     *           request
     */
    public void export(GraphicsExporter ge){
        JFrame jf = dv.getFrame();
        ge.export(jf.getContentPane(), jf);
    }

    public void setDarkMode(){
      //Color outlineColor, Color backgroundColor, Color boxFillColor, Color fontColor, Color arrowColor
      //t = new ThemeCreator(Color.WHITE, Color.BLACK, Color.BLACK, Color.WHITE, Color.WHITE); //
      t.setOutlineColor(Color.WHITE);
      t.setBackgroundColor(Color.BLACK);
      JFrame f = dv.getFrame();
      dv.setBackground(f, t.getBackgroundColor());
      t.setBoxFillColor(Color.BLACK);
      t.setFontColor(Color.WHITE);
      t.setArrowColor(Color.WHITE);
    }

    public void setLightMode(){
      //t = new ThemeCreator(); //no parameters - default is light mode anyway
      t.setOutlineColor(Color.BLACK);
      t.setBackgroundColor(Color.WHITE);
      JFrame f = dv.getFrame();
      dv.setBackground(f, t.getBackgroundColor());
      t.setBoxFillColor(Color.WHITE);
      t.setFontColor(Color.BLACK);
      t.setArrowColor(Color.BLACK);
    }

    /**
     * Determines which button is pressed from the file menu
     * and performs the appropriate respective operation
     * (i.e., *open* will open a saved class diagram)
     *
     * @param e an action event from the menu
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == open) {
            this.d = this.open();
            dv.setDiagram(d);
        } else if (e.getSource() == save) {
            this.save();
        } else if (e.getSource() == png) {
            GraphicsExporter exp = new PNGExporter();
            this.setExporter(exp);
            this.export(exporter);
        } else if (e.getSource() == jpeg) {
            GraphicsExporter exp = new JPEGExporter();
            this.setExporter(exp);
            this.export(exporter);
        } else if (e.getSource() == undo) {
            System.out.println(caretaker.numMementos());
            if (numSteps <= caretaker.numMementos()-1) {
              numSteps++;
            }
            int index = caretaker.numMementos();
            Memento backState = caretaker.get(index-numSteps);

            System.out.println(backState.getState());
            // dv.setDiagram(backState.getState());
            d.setMemento(backState);
            caretaker.showMementoStates();
            // System.out.println(caretaker.numMementos());
        } else if (e.getSource() == redo) {
            if (numSteps > 1) {
              numSteps--;
            }
            int index = caretaker.numMementos();
            Memento forwardState = caretaker.get(index-numSteps);
            dv.setDiagram(forwardState.getState());
        }
        else if(e.getSource() == darkMode){
          this.setDarkMode();
          dv.update();
        }
        else if(e.getSource() == lightMode){
          this.setLightMode();
          dv.update();
        }
        else if(e.getSource() == outlineColor){
          JColorChooser cc1 = new JColorChooser();
          JDialog d1 = JColorChooser.createDialog(lightMode, "Please choose a class box outline color", true, cc1, null, null);
          d1.setVisible(true);
          System.out.println("color chosen" + cc1.getColor());
          t.setOutlineColor(cc1.getColor());
          dv.update();
        }
        else if(e.getSource() == backgroundColor){
          JColorChooser cc2 = new JColorChooser();
          JDialog d2 = JColorChooser.createDialog(backgroundColor, "Please choose a background color", true, cc2, null, null);
          d2.setVisible(true);
          t.setBackgroundColor(cc2.getColor());
          JFrame f = dv.getFrame();
          dv.setBackground(f, t.getBackgroundColor());
          dv.update();
        }
        else if(e.getSource() == boxFillColor){
          JColorChooser cc3 = new JColorChooser();
          JDialog d3 = JColorChooser.createDialog(boxFillColor, "Please choose a class box fill color", true, cc3, null, null);
          d3.setVisible(true);
          t.setBoxFillColor(cc3.getColor());
          dv.update();
        }
        else if(e.getSource() == fontColor){
          JColorChooser cc4 = new JColorChooser();
          JDialog d4 = JColorChooser.createDialog(fontColor, "Please choose a font color", true, cc4, null, null);
          d4.setVisible(true);
          t.setFontColor(cc4.getColor());
          dv.update();
        }
        else if(e.getSource() == arrowColor){
          JColorChooser cc5 = new JColorChooser();
          JDialog d5 = JColorChooser.createDialog(arrowColor, "Please choose an arrow color", true, cc5, null, null);
          d5.setVisible(true);
          t.setArrowColor(cc5.getColor());
          dv.update();
        }
    }


    /**
     * Opens a file
     */
    public Diagram open(){ /* This Function should eventually be moved to Document*/
      fc = new JFileChooser();
      int returnVal = fc.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        String path = file.getAbsolutePath();
        try {
          FileInputStream fis = new FileInputStream(path);
          ObjectInputStream is = new ObjectInputStream(fis);
          d = (Diagram) is.readObject();
          is.close();
          fis.close();
          return d;
        } catch (IOException ioe) {
            System.out.println("Exception found");
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Diagram class not found");
            c.printStackTrace();
        }
      }
      return null;

    }

    /**
     * Saves a file
     */
    public void save() {
      fc = new JFileChooser();
      int returnVal = fc.showSaveDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        String path = file.getAbsolutePath();
        try {
          FileOutputStream fos = new FileOutputStream(path);
          ObjectOutputStream os = new ObjectOutputStream(fos);
          os.writeObject(d);
          os.close();
          fos.close();
          // System.out.println("\nObject Serialization completed.");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
      }
    }
}
