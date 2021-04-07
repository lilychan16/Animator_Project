# Easy Animator Model #

## Summary ##

Easy Animator Model is an application model to represent animations of 2D shapes (currently it focuses on rectangles and ovals only). The model supports adding or removing various types of animations to shapes, such as moving, scaling, and changing color.

## Breakdown - Shape ##

### IShape Interface ###

This interface contains all operations that all types of shapes should support.

### AbstractShape Class ###

This abstact class stores common fields and methods for Rectangle and Oval classes, including getters for shape name, position, color, appear tick, and disappear tick, as well as setters for position and color.

### Position Class ###

This class provides reference for shape positions.

### Color Class ###

This class provides reference for shape colors.

### Rectangle Class ###

This class represents a rectangle. It stores the relevant methods of a rectangle, including those stored in AbstractShape class, copy, toString, as well as getter and setter for dimensions.  

### Oval Class ###

This class represents an oval. It stores the relevant methods of an oval, including those stored in AbstractShape class, copy, toString, as well as getter and setter for dimensions.

## Breakdown - Motion ##

### IMotion Interface ###

This interface contains all operations that all types of motions should support.

### AbstractMotion Class ###

This abstract class stores common fields and methods for Move, Scale, and ChangeColor classes.

### Move Class ###

This class represents a move motion. It stores the relevant methods of a move motion, including those stored in AbstractMotion class, getters for start and end positions, and toString.

### Scale Class ###

This class represents a scale motion. It stores the relevant methods of a scale motion, including those stored in AbstractMotion class, getters for start and end dimensions, and toString.

### ChangeColor Class ###

This class represents a color changing motion. It stores the relevant methods of a color changing motion, including those stored in AbstractMotion class, getters for start and end colors, and toString. 

## Breakdown - Model Implementation ##

### AnimatorModel Interface ###

This interface contains all operations offered by the animator model. One object of the model represents one animation.

### AnimatorModelImpl Class ###

This class implements the AnimatorModel interface, including methods of adding and removing a shape, adding and removing a motion, and toString to get current state of the animator in chronological order. 
