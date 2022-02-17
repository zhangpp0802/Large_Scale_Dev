package export;

import java.awt.Graphics;
import java.awt.Container;
import java.awt.Component;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Graphics2D;

/**
 * An exporting interface for exporting different types
 * of Graphics files (i.e., .png, .jpeg, ... etc.).
 */
public interface GraphicsExporter {
    public void export(Component c, JFrame mainFrame);
}
