package model;

/**
 * This class represents a rectangle.
 */
public class Rectangle extends AbstractShape {
  private double width;
  private double height;

  /**
   * Constructor for the subclass -- Rectangle.
   * @param width the width of the rectangle
   * @param height the height of the rectangle
   * @throws IllegalArgumentException if a negative double value is passed in as the width or
   *         height of the rectangle.
   */
  public Rectangle(String name, Position position, Color color, int appearTick,
                   int disappearTick, double width, double height)
          throws IllegalArgumentException {
    super(name, position, color, appearTick, disappearTick);
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width or height cannot be negative");
    }
    this.width = width;
    this.height = height;
  }

  @Override
  public double[] getDimensions() {
    return new double[]{this.width, this.height};
  }

  @Override
  public void setDimensions(double[] dimensions) {
    if (dimensions == null) {
      throw new IllegalArgumentException("Input array cannot be null");
    }
    if (dimensions.length != 2) {
      throw new IllegalArgumentException("New width and height must be provided, "
              + "and only these two values should be in the input array");
    }
    if (dimensions[0] < 0 || dimensions[1] < 0) {
      throw new IllegalArgumentException("New width or length cannot be negative");
    }
    this.width = dimensions[0];
    this.height = dimensions[1];
  }

  @Override
  public IShape copy() {
    return new Rectangle(this.name, this.position, this.color, this.appearTick,
            this.disappearTick, this.width, this.height);
  }

  @Override
  public String toString() {
    return "Name: " + this.name + "\n"
            + "Type: rectangle" + "\n"
            + String.format("Min corner: (%.1f,%.1f), Width: %.1f, Height: %.1f, "
                    + "Color: (%.1f,%.1f,%.1f)", this.position.getX(),
            this.position.getY(), this.width, this.height, this.color.getRed(),
            this.color.getGreen(), this.color.getBlue()) + "\n"
            + "Appears at t=" + this.appearTick + "\n"
            + "Disappears at t=" + this.disappearTick;
  }
}