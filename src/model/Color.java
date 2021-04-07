package model;

/**
 * Public color reference class for shapes.
 */
public class Color {
  private double red;
  private double green;
  private double blue;

  /**
   * Constructor for color.
   * @param red red intensity
   * @param green green intensity
   * @param blue blue intensity
   * @throws IllegalArgumentException if any intensity value is less than 0 or greater than 255
   */
  public Color(double red, double green, double blue)
          throws IllegalArgumentException {
    if (red < 0 || red > 255
            || green < 0 || green > 255
            || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Invalid color intensity");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Method to get red intensity of a color.
   * @return red intensity of a color
   */
  public double getRed() {
    return this.red;
  }

  /**
   * Method to get green intensity of a color.
   * @return green intensity of a color
   */
  public double getGreen() {
    return this.green;
  }

  /**
   * Method to get blue intensity of a color.
   * @return blue intensity of a color
   */
  public double getBlue() {
    return this.blue;
  }
}