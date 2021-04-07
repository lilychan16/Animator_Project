package model;

/**
 * Public class for Move motion.
 */
public class Move extends AbstractMotion {

  private Position startPosition;
  private Position endPosition;

  /**
   * Constructor for Move motion.
   * @param startTick the start tick of a move motion
   * @param endTick the end tick of a move motion
   * @param shapeName the shape name related to a move motion
   * @param startPosition the start position of a move motion
   * @param endPosition the end position of a move motion
   * @throws IllegalArgumentException if the start tick or end tick is negative,
   *                                  or if the start tick is greater than or equal to end tick,
   *                                  or if shape name is null or empty,
   *                                  or if start or end position is null
   */
  public Move(int startTick, int endTick, String shapeName,
              Position startPosition, Position endPosition) throws IllegalArgumentException {
    super(startTick, endTick, shapeName);
    if (startPosition == null || endPosition == null) {
      throw new IllegalArgumentException("Positions cannot be null.");
    }
    this.startPosition = startPosition;
    this.endPosition = endPosition;
  }

  /**
   * Method to get the start position of a move motion.
   * @return the start position of a move motion
   */
  public Position getStartPosition() {
    return this.startPosition;
  }

  /**
   * Method to get the end position of a move motion.
   * @return the end position of a move motion
   */
  public Position getEndPosition() {
    return this.endPosition;
  }

  /**
   * Method to get a string representation of a move motion.
   * @return a string representation of a move motion
   */
  @Override
  public String toString() {
    return String.format("Shape %s moves from (%.1f,%.1f) to (%.1f,%.1f) from t=%d to t=%d",
            shapeName, startPosition.getX(), startPosition.getY(), endPosition.getX(),
            endPosition.getY(), startTick, endTick);
  }
}
