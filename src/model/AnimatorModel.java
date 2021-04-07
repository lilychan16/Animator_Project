package model;

import java.util.List;

/**
 * This interface contains all operations offered by the Animator model.
 * One object of the model represents one animation.
 */
public interface AnimatorModel {

  /**
   * Add a shape to this model.
   * @param s the given IShape object to be added
   */
  void addShape(IShape s);

  /**
   * Delete a shape from this model.
   * @param s the given IShape object to be removed
   */
  void removeShape(IShape s);

  /**
   * Add a motion to the shape that is associated with the provided shape name.
   * @param m the given IMotion object to be added
   */
  void addMotion(IMotion m);

  /**
   * Delete a motion from the shape that is associated with the provided shape name.
   * @param m the given IMotion object to be removed
   */
  void removeMotion(IMotion m);

  /**
   * Gets all the shapes on the screen at a specific tick.
   * @param tick the given tick
   * @return a list of IShape objects that are on the screen
   */
  List<IShape> getShapeAtTick(int tick);

  /**
   * Produces a "read-back" of the animation, perhaps for devices
   * that cannot show the animation, or for users who are visually impaired but have
   * screen readers.
   * The output will describe the shapes that are part of the animation and their details,
   * and how these shapes will transform as the animation proceeds from start to finish.
   * @return the text description of the animation as a String
   */
  String toString();
}
