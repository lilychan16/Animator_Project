package model;

/**
 * This class represents an oval.
 */
public class Oval extends AbstractShape {
  private double xRadius;
  private double yRadius;

  /**
   * Constructor for the subclass -- Oval.
   * @param xRadius the x radius of the oval
   * @param yRadius the y radius of the oval
   * @throws IllegalArgumentException if a negative double value is passed in as the x radius or
   *         y radius of the oval.
   */
  public Oval(String name, Position position, Color color, int appearTick,
              int disappearTick, double xRadius, double yRadius)
          throws IllegalArgumentException {
    super(name, position, color, appearTick, disappearTick);
    if (xRadius < 0 || yRadius < 0) {
      throw new IllegalArgumentException("X radius or y radius cannot be negative");
    }
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  @Override
  public double[] getDimensions() {
    return new double[]{this.xRadius, this.yRadius};
  }

  @Override
  public void setDimensions(double[] dimensions) {
    if (dimensions == null) {
      throw new IllegalArgumentException("Input array cannot be null");
    }
    if (dimensions.length != 2) {
      throw new IllegalArgumentException("New x radius and y radius must be provided, "
              + "and only these two values should be in the input array");
    }
    if (dimensions[0] < 0 || dimensions[1] < 0) {
      throw new IllegalArgumentException("New x radius or y radius cannot be negative");
    }
    this.xRadius = dimensions[0];
    this.yRadius = dimensions[1];
  }

  @Override
  public IShape copy() {
    return new Oval(this.name, this.position, this.color, this.appearTick,
            this.disappearTick, this.xRadius, this.yRadius);
  }

  @Override
  public String toString() {
    return "Name: " + this.name + "\n"
            + "Type: oval" + "\n"
            + String.format("Center: (%.1f,%.1f), X radius: %.1f, Y radius: %.1f, "
                    + "Color: (%.1f,%.1f,%.1f)", this.position.getX(),
            this.position.getY(), this.xRadius, this.yRadius, this.color.getRed(),
            this.color.getGreen(), this.color.getBlue()) + "\n"
            + "Appears at t=" + this.appearTick + "\n"
            + "Disappears at t=" + this.disappearTick;
  }
}