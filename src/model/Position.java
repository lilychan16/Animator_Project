package model;

/**
 * Public position reference class for shapes.
 */
public class Position {
  private double x;
  private double y;

  /**
   * Constructor for position.
   * @param x X coordinate
   * @param y Y coordinate
   */
  public Position(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Method to get the X coordinate of a position.
   * @return the X coordinate of a position
   */
  public double getX() {
    return x;
  }

  /**
   * Method to get the Y coordinate of a position.
   * @return the Y coordinate of a position
   */
  public double getY() {
    return y;
  }
}