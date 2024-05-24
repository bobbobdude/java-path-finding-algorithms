On the way to the Orb (see Figure[1](#1), the cavern's layout is unknown. You know only the status of the tile on which you are standing and those immediately around you and perhaps those that you remember. Your goal is to make it to the
Orb in as few steps as possible.

![Searching for the Orb during the exploration
phase](.guides/img/exploration.png)

This is not a blind search, as you will know the distance to the Orb. This is equal to the number of tiles on the shortest path to the Orb, if there were not  any walls or obstacles in the way.

You will develop the solution to this phase in the method `explore()` in the class `Explorer` within the package `student`. There is no time limit on the number of steps you can take, but you will receive a higher score for finding the Orb in fewer steps. To pick up the Orb, return from this method once you have arrived on its tile. Returning when Jeremy is not on the Orb tile will cause an exception to be thrown, halting the game.

When writing the method `explore()`, you are given an `ExplorationState` object to learn about your environment. Every time you move, this object automatically changes to reflect the new location of the explorer. This object includes the following methods:

+ `long getCurrentLocation()`: return a unique identifier for the tile the explorer is currently on.
+  `int getDistanceToTarget()`: return the distance from the explorer's current location to the Orb.
+ `Collection<NodeStatus> getNeighbours()`: return information about the tiles to which the explorer can move from their current location.
+  `void moveTo(long id)`: move the explorer to the tile with ID id. This fails if that tile is not adjacent to the current location.

Notice that the method`getNeighbours()` returns a collection of `NodeStatus` objects. This is simply an object that contains, for each neighbour, the ID corresponding to that neighbour, as well as that neighbour's distance from the Orb.

You can examine the documentation for this class for more information on how to use `NodeStatus` objects. A suggested first implementation that will always find the Orb but likely as won't receive a significant bonus score is a depth-first search. More advanced solutions might incorporate the distance of tiles from the Orb.