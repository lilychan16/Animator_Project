package model;

/**
 * Public interface for shape motions.
 */
public interface IMotion {

  /**
   * Method to get the start tick of a motion.
   * @return the start tick of a motion
   */
  int getStartTick();

  /**
   * Method to get the end tick of a motion.
   * @return the end tick of a motion
   */
  int getEndTick();

  /**
   * Method to get the shape name related to a motion.
   * @return the shape name related to a motion
   */
  String getShapeName();

  /**
   * Method to get a string representation of a motion.
   * @return a string representation of a motion
   */
  String toString();
}
