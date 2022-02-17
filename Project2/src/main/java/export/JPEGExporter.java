package export;

import java.awt.Graphics;
import java.awt.Container;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;

/**
 * A subclass of GraphicsExporter. This subclass will be able
 * to take an image of the Diagram editor and export the file
 * locally as a .jpeg file.
 */
public class JPEGExporter implements GraphicsExporter {
    public JPEGExporter(){}

    public void export(Component c, JFrame mainFrame) {
        Container content = (Container) c;
        JFileChooser fc = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("JPEG File (*.jpeg)",
                                                 "*.jpeg");
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
                if (!path.endsWith(".jpeg")) {
                    path = path + ".jpeg";
                }
                ImageIO.write(img, "jpeg", new File(path));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
