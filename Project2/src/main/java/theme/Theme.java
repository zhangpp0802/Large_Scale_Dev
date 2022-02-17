package theme;

import java.awt.Color;
import java.awt.Graphics;

/**
 * An interface for themes. 
 */
public interface Theme {
    public void setOutlineColor(Color color);

    public void setBackgroundColor(Color color);

    public void setBoxFillColor(Color color);

    public void setFontColor(Color color);

    public void setArrowColor(Color color);

    public Color getOutlineColor();

    public Color getBackgroundColor();

    public Color getBoxFillColor();

    public Color getFontColor();

    public Color getArrowColor();
}
