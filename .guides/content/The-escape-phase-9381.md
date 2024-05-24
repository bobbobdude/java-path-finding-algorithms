After picking up the Orb, the walls of the cavern shift and a new layout is generated — ouch! In addition, piles of gold fall onto the ground. Luckily, underneath the Orb is a map, revealing the full cavern to you. However, the stress of the moving walls has compromised the integrity of the cavern, beginning a time limit after which the ceiling will collapse, crushing Jeremy! Additionally, picking up the Orb activated the traps and puzzles of the cavern, causing different edges of the graph to have different weights.

![Collecting gold during the escape phase](.guides/img/escape.png)

The goal of the escape phase is to make it to the cavern's entrance before it collapses. However, a score component is based on two additional factors:

1. The amount of gold that you pick up during the escape phase, and
2. Your score from the exploration phase.

Your score will simply be the amount of gold picked up times the score from the exploration phase.

You will write your solution code to this part of the problem in a method `escape()` in the class `Explorer` in the package `student`. To escape, simply return from this method while standing on the entrance tile. Returning while at any other position or failing to return before time runs out will cause the game to end and result in a score of 0.

An important point to clarify is that *time* during this phase is not defined as the CPU time your solution takes to compute but rather the number of steps the explorer takes: the time remaining decrements regardless of how long you spent deciding which move to make. Because of this, you can be guaranteed to always be given enough time to escape the cavern should you take the shortest path out. Note that there are differing amounts of gold on the different tiles. Picking up gold on the tile you are standing on takes no time.

When writing this method, you are given an `EscapeState` object to learn about your environment. Every time you move, this object will automatically change to reflect the new location of the explorer. This object includes the following methods:

+ `Node getCurrentNode()`: return the `Node` corresponding to the explorer's location.
+ `Node getExit()`: return the `Node` corresponding to the exit to the cavern (the destination).
+ `Collection<Node> getVertices()`: return a collection of all traversable nodes in the graph.
+ `int getTimeRemaining()`: return the number of steps the explorer has left before the ceiling collapses.
+ `void moveTo(Node n)`: move the explorer to node n. this will fail if the given node is not adjacent to the explorer's
current location. Calling this function will decrement the time remaining.
+ `void pickUpGold()`: Collect all gold on the current tile. This will fail if there is no gold on the current tile or if it has already been collected.

Class `Node` (and the corresponding class `Edge`) has methods that are likely to be useful. Look at the documentation for these classes to learn what additional methods are available. A good starting point is to write an implementation that will always escape the cavern before time runs out. From there, you can consider trying to pick up gold to optimize your score using more advanced techniques.

However, the most important part is always that your solution successfully escapes the cavern — if you improve on your solution, make sure that it never fails to escape in time!