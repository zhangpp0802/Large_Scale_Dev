package theme;

import java.awt.Color;
import java.awt.Graphics;
// import theme.ColorChooser;

/**
 * A class that stores the theme components for diagram listeners.
 */
public class ThemeCreator implements Theme{
  private Color outlineColor;
  private Color backgroundColor;
  private Color boxFillColor;
  private Color fontColor;
  private Color arrowColor;

  public ThemeCreator(){
    this.outlineColor = Color.BLACK;
    this.backgroundColor = Color.WHITE;
    this.boxFillColor = Color.WHITE;
    this.fontColor = Color.BLACK;
    this.arrowColor = Color.BLACK;
  }

  public ThemeCreator(Color outlineColor, Color backgroundColor, Color boxFillColor, Color fontColor, Color arrowColor){
    this.outlineColor = outlineColor;
    this.backgroundColor = backgroundColor;
    this.boxFillColor = boxFillColor;
    this.fontColor = fontColor;
    this.arrowColor = arrowColor;
  }

  /**
  * Sets the class box's outline color
  * @param color the color to set
  */
  public void setOutlineColor(Color color){
    this.outlineColor = color;
  }

  /**
  * Sets the editor window's background color
  * @param color the color to set
  */
  public void setBackgroundColor(Color color){
    this.backgroundColor = color;
  }

  /**
  * Sets the class/interface box's fill color
  * @param color the color to set
  */
  public void setBoxFillColor(Color color){
    this.boxFillColor = color;
  }

  /**
  * Sets the font's color
  * @param color the color to set
  */
  public void setFontColor(Color color){
    this.fontColor = color;
  }

  /**
  * Sets the arrow's color
  */
  public void setArrowColor(Color color){
    this.arrowColor = color;
  }

  /**
  * Gets the class box's outline color
  * @return the outline color
  */
  public Color getOutlineColor(){
    return this.outlineColor;
  }

  /**
  * Gets the editor window's background color
  * @return the background color
  */
  public Color getBackgroundColor(){
    return this.backgroundColor;
  }

  /**
  * Gets the class/interface box's fill color
  * @return the class/interface box's fill color
  */
  public Color getBoxFillColor(){
    return this.boxFillColor;
  }

  /**
  * Gets the font's color
  * @return the font color
  */
  public Color getFontColor(){
    return this.fontColor;
  }

  /**
  * Gets the arrow's color
  * @return the arrow's color
  */
  public Color getArrowColor(){
    return this.arrowColor;
  }

}
