package export;

import java.awt.Graphics;
import java.awt.Container;
import java.awt.Component;
import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Dimension;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Frame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import java.util.*;

import view.DiagramView;


/**
 * A subclass of GraphicsExporter. This subclass will be able
 * to take an image of the Diagram editor and export the file
 * locally as a .png file.
 */
public class PNGExporter implements GraphicsExporter {

    /**
     * A strategy for exporting .png files.
     */
    public PNGExporter(){}

    /**
     * Exports the Class Diagram as a '.png' file
     *
     * @param c the content pane of the JFrame
     * @param mainFrame the JFrame to show the export dialog
     */
    public void export(Component c, JFrame mainFrame)
    {
        Container content = (Container) c;
        JFileChooser fc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("PNG Files (*.png)",
                                                        "*.png");
        fc.setFileFilter(filter);
        fc.setMultiSelectionEnabled(false);
        int returnVal = fc.showDialog(mainFrame, "Export");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String path = file.getAbsolutePath();
            BufferedImage img = new BufferedImage(content.getWidth(),
                                                  content.getHeight(),
                                                  BufferedImage.TYPE_INT_RGB);
            content.paint(img.getGraphics());
            try {
                if(!path.endsWith(".png")){
                    path = path + ".png";
                }
                ImageIO.write(img, "png", new File(path));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
