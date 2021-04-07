package model;

/**
 * This interface contains all operations that all types of shapes should support.
 */
public interface IShape {

  /**
   * Gives back the name of this shape.
   * @return a String value that contains the name of this shape
   */
  String getName();

  /**
   * Gives back the location of this shape.
   * @return a model.Position object that contains the x-coordinate
   *         and y-coordinate of the point of reference
   */
  Position getPosition();

  /**
   * Set this shape to a specific location.
   * @param position the new position of this shape
   */
  void setPosition(Position position);

  /**
   * Gives back the color of this shape.
   * @return a model.Color object that contains the r,g,b values
   */
  Color getColor();

  /**
   * Set this shape to a specific color.
   * @param color the new color of this shape
   */
  void setColor(Color color);

  /**
   * Gives back an array of N non-negative double values that specify the sizes of
   * each dimension of this shape.
   * The number of dimensions and items in the array is defined by shape type.
   * @return an array that contains width and height if this shape is a rectangle,
   *         or x radius and y radius if it is a oval
   */
  double[] getDimensions();

  /**
   * Set the sizes of each dimension of this shape.
   * @param dimensions an array that contains new width and new height if it is a rectangle,
   *                   or new x radius and new y radius if it is a oval
   */
  void setDimensions(double[] dimensions);

  /**
   * Gives back the tick at which this shape appears.
   * @return an int value that represents the appearance tick
   */
  int getAppearTick();

  /**
   * Gives back the tick at which this shape disappears.
   * @return an int value that represents the disappearance tick
   */
  int getDisappearTick();

  /**
   * Gives back a deep copy of this shape.
   * @return a new model.IShape object that contains the same data but
   *         is independent of this shape
   */
  IShape copy();

  /**
   * Gives back a text description of this shape.
   * @return a String value that contains the name, type, position,
   *         dimensions, color, appearance tick, and disappearance
   *         tick that are stored in this shape
   */
  String toString();
}