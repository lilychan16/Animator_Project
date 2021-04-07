package model;

/**
 * This abstract class is to store common fields and methods for Rectangle and Oval class.
 */
public abstract class AbstractShape implements IShape {
  protected String name;
  protected Position position;
  protected Color color;
  protected int appearTick;
  protected int disappearTick;

  /**
   * Constructor for the super class -- AbstractShape.
   * @param name the name of the shape to be created
   * @param position the position of the point of reference of the shape
   * @param color the color of the shape
   * @param appearTick the tick at which the shape appears on the screen
   * @param disappearTick the tick at which the shape disappears
   * @throws IllegalArgumentException if an empty String or null is passed in as the shape name,
   *         or if null is passed in as the position or color of the shape,
   *         or if an invalid int value is passed in as the appear or disappear tick of the shape
   *         (e.g. a tick cannot be negative, appear tick cannot be greater than or equal to
   *         disappear tick).
   */
  public AbstractShape(String name, Position position, Color color, int appearTick,
                       int disappearTick) throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    } else if (position == null) {
      throw new IllegalArgumentException("Position cannot be null");
    } else if (color == null) {
      throw new IllegalArgumentException("Color cannot be null");
    } else if (appearTick < 0 || disappearTick < 0 || appearTick >= disappearTick) {
      throw new IllegalArgumentException("Invalid tick values for appearance/disappearance");
    }
    this.name = name;
    this.position = position;
    this.color = color;
    this.appearTick = appearTick;
    this.disappearTick = disappearTick;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Position getPosition() {
    return position;
  }

  @Override
  public void setPosition(Position position)
          throws IllegalArgumentException {
    if (position == null) {
      throw new IllegalArgumentException("New position cannot be null");
    }
    this.position = position;
  }

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public void setColor(Color color)
          throws IllegalArgumentException {
    if (color == null) {
      throw new IllegalArgumentException("New color cannot be null");
    }
    this.color = color;
  }

  @Override
  public int getAppearTick() {
    return appearTick;
  }

  @Override
  public int getDisappearTick() {
    return disappearTick;
  }
}