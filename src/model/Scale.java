package model;

import java.util.Arrays;

/**
 * Public class for Scale motion.
 */
public class Scale extends AbstractMotion {

  private double[] startDimensions;
  private double[] endDimensions;

  /**
   * Constructor for Scale motion.
   * @param startTick the start tick of a scale motion
   * @param endTick the end tick of a scale motion
   * @param shapeName the shape name related to a scale motion
   * @param startDimensions the dimensions at the beginning of a scale motion
   * @param endDimensions the dimensions at the end of a scale motion
   * @throws IllegalArgumentException if the start tick or end tick is negative,
   *                                  or if the start tick is greater than or equal to end tick,
   *                                  or if shape name is null or empty,
   *                                  or if the start or end dimensions are not positive,
   *                                  or if the start or end dimensions are null,
   *                                  or if the start or end dimensions do not have two values only
   */
  public Scale(int startTick, int endTick, String shapeName,
               double[] startDimensions, double[] endDimensions) throws IllegalArgumentException {
    super(startTick, endTick, shapeName);
    if (!Arrays.stream(startDimensions).allMatch(i -> i >= 0)
            || !Arrays.stream(endDimensions).allMatch(i -> i >= 0)) {
      throw new IllegalArgumentException("Start or end dimensions must be non-negative.");
    }
    else if (startDimensions == null || endDimensions == null) {
      throw new IllegalArgumentException("Input arrays cannot be null.");
    }
    else if (startDimensions.length != 2 || endDimensions.length != 2) {
      throw new IllegalArgumentException("X and Y dimensions must be provided, "
              + "and only these two values should be in the input array.");
    }
    this.startDimensions = startDimensions;
    this.endDimensions = endDimensions;
  }

  /**
   * Method to get the X dimension at the beginning of a scale motion.
   * X dimension indicates width of a rectangle, and X radius of an oval.
   * @return the X dimension at the beginning of a scale motion
   */
  public double[] getStartDimension() {
    return this.startDimensions;
  }

  /**
   * Method to get the X dimension at the end of a scale motion.
   * X dimension indicates width of a rectangle, and X radius of an oval.
   * @return the X dimension at the end of a scale motion
   */
  public double[] getEndDimension() {
    return this.endDimensions;
  }

  /**
   * Method to get a string representation of a scale motion.
   * @return a string representation of a scale motion
   */
  @Override
  public String toString() {
    return String.format("Shape %s scales from Width: %.1f, Height: %.1f to Width: %.1f, "
            + "Height: %.1f from t=%d to t=%d", shapeName, startDimensions[0],
            startDimensions[1], endDimensions[0], endDimensions[1], startTick, endTick);
  }
}
