# BallWorld
An interactive ball world game that uses visitor and accumulator pattern.

Ball Types:
1. BullyBall
2. FriendBall
3. RainbowBall

Type Bully or Friend or Rainbow to use these three balls. When using BullyBall and FriendBall with the
interaction strategy "Bully", if a bully ball interacts with a friend ball, the 
friend ball would shrink the size and turns red. Recommend use Overlap update strategy to 
test it. Rainbow balls are black if we don't use any update strategy. 

Type-dependent interaction strategy:
1. BullyInteractionStrategy

Type Bully to use this interaction strategy. When using BullyBall and FriendBall with the
interaction strategy "Bully", if a bully ball interacts with a friend ball, the 
friend ball would shrink the size and turns red. Recommend use Bully or Friend Ball - Overlap 
Update strategy - Ball Paint Strategy - Bully Interact Strategy - TurnBlack Algo and disable it.

Type-dependent update stratgey:
1. RainbowStrategy

Type Rainbow to use this update strategy. Before using this strategy, all rainbow balls are black. After using it,
they will start to move in circle and have changing color. 
   
Dynamic generated control panels strategies:
1. SizeConfigAlgo

Type Size to use this configAlgo. The new control panel would show two change size interaction tab.
Be sure to use the left one to make it work. The new control panel allows you to change 
the size of the balls on the entire screen. Recommend use Default Ball - Overlap Update strategy
- Ball Paint Strategy - ExactBounce Interact Strategy - Size Config Algo.
2. HulkConfigAlgo

Type Hulk to use this configAlgo. This allows two interacted balls to both turn green and 
increase their size like Hulk. Recommend use Default Ball - Overlap Update strategy
- Ball Paint Strategy - ExactBounce Interact Strategy - Hulk Config Algo.
