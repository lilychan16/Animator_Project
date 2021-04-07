package model;

/**
 * Public abstract class for the IMotion interface.
 */
public abstract class AbstractMotion implements IMotion {

  protected int startTick;
  protected int endTick;
  protected String shapeName;

  /**
   * Constructor for AbstractMotion.
   * @param startTick the start tick of a motion
   * @param endTick the end tick of a motion
   * @param shapeName the shape name related to a motion
   * @throws IllegalArgumentException if the start tick or end tick is negative,
   *                                  or if the start tick is greater than or equal to end tick,
   *                                  or if shape name is null or empty
   */
  public AbstractMotion(int startTick, int endTick, String shapeName)
          throws IllegalArgumentException {
    if (startTick < 0 || endTick < 0) {
      throw new IllegalArgumentException("Start tick or end tick cannot be negative.");
    }
    else if (startTick >= endTick) {
      throw new IllegalArgumentException("Start tick cannot be greater than or equal to end tick.");
    }
    else if (shapeName == null || shapeName.equals("")) {
      throw new IllegalArgumentException("Shape name cannot be null or empty.");
    }
    this.startTick = startTick;
    this.endTick = endTick;
    this.shapeName = shapeName;

  }

  /**
   * Method to get the start tick of a motion.
   * @return the start tick of a motion
   */
  @Override
  public int getStartTick() {
    return this.startTick;
  }

  /**
   * Method to get the end tick of a motion.
   * @return the end tick of a motion
   */
  @Override
  public int getEndTick() {
    return this.endTick;
  }

  /**
   * Method to get the shape name related to a motion.
   * @return the shape name related to a motion
   */
  @Override
  public String getShapeName() {
    return this.shapeName;
  }
}
