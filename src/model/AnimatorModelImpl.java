package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * This class represents a concrete Animator model that implements
 * the AnimatorModel interface.
 */
public class AnimatorModelImpl implements AnimatorModel {
  private Map<String, IShape> allShapes;
  private List<IMotion> allMotions;

  /**
   * Constructor for Animator Model.
   */
  public AnimatorModelImpl() {
    this.allShapes = new LinkedHashMap<>();
    this.allMotions = new ArrayList<>();
  }

  /**
   * Add a shape to this model.
   * @param s the given IShape object to be added
   * @throws IllegalArgumentException if the shape already exists in the model.
   */
  @Override
  public void addShape(IShape s) throws IllegalArgumentException {
    if (s == null) {
      throw new IllegalArgumentException("The shape to be added cannot be null");
    }
    String name = s.getName().toUpperCase();
    if (this.allShapes.get(name) != null) {
      throw new IllegalArgumentException("The shape already exists");
    }
    this.allShapes.put(name, s);
  }

  /**
   * Delete a shape from this model.
   * @param s the given IShape object to be removed
   * @throws IllegalArgumentException if the shape doesn't exist in the model.
   */
  @Override
  public void removeShape(IShape s) throws IllegalArgumentException {
    if (s == null) {
      throw new IllegalArgumentException("The shape to be removed cannot be null");
    }
    String name = s.getName().toUpperCase();
    if (this.allShapes.get(name) == null || !this.allShapes.get(name).equals(s)) {
      throw new IllegalArgumentException("The shape doesn't exist");
    }
    // remove this shape from our shape map
    this.allShapes.remove(name);
    // remove all motions that are associated with this shape from our motion list
    this.allMotions = this.allMotions.stream()
                                     .filter(m -> !m.getShapeName().equals(name))
                                     .collect(Collectors.toList());
  }

  /**
   * Add a motion to the shape that is associated with the provided shape name.
   * @param m the given IMotion object to be added
   * @throws IllegalArgumentException if (1) the motion to be added is null,
   *                                  or (2) the provided shape for adding motion does not exist,
   *                                  or (3) the provided motion is out of the shape's tick range,
   *                                  or (4) if the newly added motion already exists in the list,
   *                                  or (5) if the first motion under a type has start parameter
   *                                  different from the shape's initial values,
   *                                  or (6) if the newly added motion under a type (but not the
   *                                  first motion in that type) has start parameter different from
   *                                  the end parameter of the last motion under the same type,
   *                                  or (7) the newly added motion conflicts with an old motion
   *                                  under the same type
   */
  @Override
  public void addMotion(IMotion m) throws IllegalArgumentException {

    List moveSublist = allMotions.stream().filter(n -> n instanceof Move && n.getShapeName()
                                          .equalsIgnoreCase(m.getShapeName()))
                                          .collect(Collectors.toList());
    List scaleSublist = allMotions.stream().filter(n -> n instanceof Scale && n.getShapeName()
                                          .equalsIgnoreCase(m.getShapeName()))
                                          .collect(Collectors.toList());
    List colorSublist = allMotions.stream().filter(n -> n instanceof ChangeColor && n.getShapeName()
                                          .equalsIgnoreCase(m.getShapeName()))
                                          .collect(Collectors.toList());

    // IllegalArgumentException (1)
    if (m == null) {
      throw new IllegalArgumentException("The motion to be added cannot be null.");
    }
    // IllegalArgumentException (2)
    else if (!allShapes.containsKey(m.getShapeName().toUpperCase())) {
      throw new IllegalArgumentException("No such shape exists. Adding motion is failed.");
    }

    // IllegalArgumentException (3)
    else if (m.getStartTick() < allShapes.get(m.getShapeName().toUpperCase()).getAppearTick()
            || m.getStartTick() >= allShapes.get(m.getShapeName().toUpperCase()).getDisappearTick()
            || m.getEndTick() <= allShapes.get(m.getShapeName().toUpperCase()).getAppearTick()
            || m.getEndTick() > allShapes.get(m.getShapeName().toUpperCase()).getDisappearTick()) {
      throw new IllegalArgumentException("Motion is out of the shape's tick range.");
    }

    // IllegalArgumentException (4)
    else if (allMotions.stream().anyMatch(n -> n.equals(m))) {
      throw new IllegalArgumentException("Motion already exists in the list.");
    }

    // IllegalArgumentException (5)
    else if (m instanceof Move && allShapes.containsKey(m.getShapeName().toUpperCase())
            && allMotions.stream().noneMatch(n -> n instanceof Move && n.getShapeName()
                                              .equalsIgnoreCase(m.getShapeName()))
            && (((Move) m).getStartPosition().getX()
            != allShapes.get(m.getShapeName().toUpperCase()).getPosition().getX()
            || ((Move) m).getStartPosition().getY()
            != allShapes.get(m.getShapeName().toUpperCase()).getPosition().getY())) {
      throw new IllegalArgumentException("Start position of the move motion must equal to "
              + "the initial position of the shape.");
    }
    else if (m instanceof Scale && allShapes.containsKey(m.getShapeName().toUpperCase())
            && allMotions.stream().noneMatch(n -> n instanceof Scale && n.getShapeName()
                                              .equalsIgnoreCase(m.getShapeName()))
            && (((Scale) m).getStartDimension()[0]
            != allShapes.get(m.getShapeName().toUpperCase()).getDimensions()[0]
            || ((Scale) m).getStartDimension()[1]
            != allShapes.get(m.getShapeName().toUpperCase()).getDimensions()[1])) {
      throw new IllegalArgumentException("Start dimensions of the scale motion must equal to "
              + "the initial dimensions of the shape.");
    }
    else if (m instanceof ChangeColor && allShapes.containsKey(m.getShapeName().toUpperCase())
            && allMotions.stream().noneMatch(n -> n instanceof ChangeColor && n.getShapeName()
                                              .equalsIgnoreCase(m.getShapeName()))
            && (((ChangeColor) m).getStartColor().getRed()
            != allShapes.get(m.getShapeName().toUpperCase()).getColor().getRed()
            || ((ChangeColor) m).getStartColor().getGreen()
            != allShapes.get(m.getShapeName().toUpperCase()).getColor().getGreen()
            || ((ChangeColor) m).getStartColor().getBlue()
            != allShapes.get(m.getShapeName().toUpperCase()).getColor().getBlue())) {
      throw new IllegalArgumentException("Start color of the color changing motion must equal to "
              + "the initial color of the shape.");
    }

    // IllegalArgumentException (6)
    else if (m instanceof Move && allShapes.containsKey(m.getShapeName().toUpperCase())
            && allMotions.stream().anyMatch(n -> n instanceof Move && n.getShapeName()
                                              .equalsIgnoreCase(m.getShapeName()))
            && (((Move) m).getStartPosition().getX()
            != ((Move) moveSublist.get(moveSublist.size() - 1)).getEndPosition().getX()
            || ((Move) m).getStartPosition().getY()
            != ((Move) moveSublist.get(moveSublist.size() - 1)).getEndPosition().getY())) {
      throw new IllegalArgumentException("Start position of the new move motion must equal to "
              + "the end position of the last move motion of the shape.");
    }
    else if (m instanceof Scale && allShapes.containsKey(m.getShapeName().toUpperCase())
            && allMotions.stream().anyMatch(n -> n instanceof Scale && n.getShapeName()
                                              .equalsIgnoreCase(m.getShapeName()))
            && (((Scale) m).getStartDimension()[0]
            != ((Scale) scaleSublist.get(scaleSublist.size() - 1)).getEndDimension()[0]
            || ((Scale) m).getStartDimension()[1]
            != ((Scale) scaleSublist.get(scaleSublist.size() - 1)).getEndDimension()[1])) {
      throw new IllegalArgumentException("Start dimensions of the new scale motion must equal to "
              + "the end dimensions of the last scale motion of the shape.");
    }
    else if (m instanceof ChangeColor && allShapes.containsKey(m.getShapeName().toUpperCase())
            && allMotions.stream().anyMatch(n -> n instanceof ChangeColor && n.getShapeName()
                                              .equalsIgnoreCase(m.getShapeName()))
            && (((ChangeColor) m).getStartColor().getRed()
            != ((ChangeColor) colorSublist.get(colorSublist.size() - 1)).getEndColor().getRed()
            || ((ChangeColor) m).getStartColor().getGreen()
            != ((ChangeColor) colorSublist.get(colorSublist.size() - 1)).getEndColor().getGreen()
            || ((ChangeColor) m).getStartColor().getBlue()
            != ((ChangeColor) colorSublist.get(colorSublist.size() - 1)).getEndColor().getBlue())) {
      throw new IllegalArgumentException("Start color of the new color changing motion must equal "
              + "to the end color of the last color changing motion of the shape.");
    }

    // IllegalArgumentException (7)
    for (int i = 0; i < this.allMotions.size(); i++) {
      if (this.allMotions.get(i).getClass().equals(m.getClass())
              && this.allMotions.get(i).getShapeName().equalsIgnoreCase(m.getShapeName())
              && ((this.allMotions.get(i).getStartTick() >= m.getStartTick()
              && this.allMotions.get(i).getStartTick() < m.getEndTick()
              && this.allMotions.get(i).getEndTick() > this.allMotions.get(i).getStartTick())
              || (this.allMotions.get(i).getStartTick() < m.getEndTick()
              && this.allMotions.get(i).getStartTick() < this.allMotions.get(i).getEndTick()
              && this.allMotions.get(i).getEndTick() > m.getStartTick()))) {
        throw new IllegalArgumentException("Newly added motion conflicts with "
                                          + "old motion under same type.");
      }
    }
    this.allMotions.add(m);
  }

  /**
   * Delete a motion from the shape that is associated with the provided shape name.
   * @param m the given IMotion object to be removed
   * @throws IllegalArgumentException if the motion to be removed is null,
   *                                  or the provided shape for removing motion does not exist,
   *                                  or the list fails to find the motion to remove due to
   *                                  incorrect inputs
   * @throws NoSuchElementException if no motion is matched with the given shape name
   */
  @Override
  public void removeMotion(IMotion m) throws
          IllegalArgumentException, NoSuchElementException {

    if (m == null) {
      throw new IllegalArgumentException("The motion to be removed cannot be null.");
    }
    else if (!allShapes.containsKey(m.getShapeName().toUpperCase())) {
      throw new IllegalArgumentException("No such shape exists. Removing motion is failed.");
    }
    else if (this.allMotions.stream()
              .noneMatch(n -> n.getShapeName().equalsIgnoreCase(m.getShapeName()))) {
      throw new NoSuchElementException("No motion is matched with the given shape name.");
    }
    try {
      this.allMotions.remove(m);
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to remove the motion. Please check your inputs.");
    }
  }

  /**
   * Gets all the shapes on the screen at a specific tick.
   * @param tick the given tick
   * @return a list of IShape objects that are on the screen
   */
  @Override
  public List<IShape> getShapeAtTick(int tick) {
    return null;
  }

  /**
   * Produces a "read-back" of the animation, perhaps for devices
   * that cannot show the animation, or for users who are visually impaired but have
   * screen readers.
   * The output will describe the shapes that are part of the animation and their details,
   * and how these shapes will transform as the animation proceeds from start to finish.
   * @return the text description of the animation as a String
   */
  @Override
  public String toString() {
    // check if shape map is empty
    if (this.allShapes.size() == 0) {
      return "";
    }
    // output all shapes in our shape map
    String s = "Shapes: \n";
    List<IShape> shapes = new LinkedList<>(this.allShapes.values());
    for (IShape shape : shapes) {
      s += shape.toString() + "\n\n";
    }
    // output all motions in chronological order
    this.allMotions.sort(Comparator.comparingInt(m -> m.getStartTick()));
    for (int j = 0; j < this.allMotions.size(); j++) {
      if (j < this.allMotions.size() - 1) {
        s += this.allMotions.get(j).toString() + "\n";
      } else {
        s += this.allMotions.get(j).toString(); // last motion has no newline
      }
    }
    return s;
  }
}
