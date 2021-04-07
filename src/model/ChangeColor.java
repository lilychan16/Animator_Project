package model;

/**
 * Public class for Change Color motion.
 */
public class ChangeColor extends AbstractMotion {

  private Color startColor;
  private Color endColor;

  /**
   * Constructor for Change Color motion.
   * @param startTick the start tick of a color changing motion
   * @param endTick the end tick of a color changing motion
   * @param shapeName the shape name related to a color changing motion
   * @param startColor the color at the beginning of a color changing motion
   * @param endColor the color at the end of a color changing motion
   * @throws IllegalArgumentException if the start tick or end tick is negative,
   *                                  or if the start tick is greater than or equal to end tick,
   *                                  or if shape name is null or empty,
   *                                  or if any intensity value of the start color or end color
   *                                  is less than 0 or greater than 255,
   *                                  or if the start color or end color is null
   */
  public ChangeColor(int startTick, int endTick, String shapeName, Color startColor, Color endColor)
          throws IllegalArgumentException {
    super(startTick, endTick, shapeName);
    if (startColor == null || endColor == null) {
      throw new IllegalArgumentException("Colors cannot be null.");
    }
    this.startColor = startColor;
    this.endColor = endColor;
  }

  /**
   * Method to get the color at the beginning of a color changing motion.
   * @return the color at the beginning of a color changing motion
   */
  public Color getStartColor() {
    return this.startColor;
  }

  /**
   * Method to get the color at the end of a color changing motion.
   * @return the color at the end of a color changing motion
   */
  public Color getEndColor() {
    return this.endColor;
  }

  /**
   * Method to get a string representation of a color changing motion.
   * @return a string representation of a color changing motion
   */
  public String toString() {
    return String.format("Shape %s changes color from (%.1f,%.1f,%.1f) to (%.1f,%.1f,%.1f) "
            + "from t=%d to t=%d", shapeName, startColor.getRed(), startColor.getGreen(),
            startColor.getBlue(), endColor.getRed(), endColor.getGreen(), endColor.getBlue(),
            startTick, endTick);
  }
}
