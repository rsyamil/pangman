# pangman
Pangman is a game that blends Hangman and Pacman together! The goal of the player is to guess a word by navigating Pacman to collect correct letters (i.e. belonging to the word). The player also needs to avoid getting eaten by any ghost or choosing too many incorrect letters. 

![Cover](/readme/cover.jpg)

The game is based on Java Swing\AWT as the GUI toolkit and player can choose the category of words and hardness level (which depends on how common the words are, speed of the ghosts etc). 

![Int1](/readme/int1.jpg)

Points are represented as pink circles that Pacman will eat as it traverses the maze. The mazes are different for each level and is pseudo-randomly generated. The letters that Pacman can choose are randomly placed in the maze and there are some bonus tokens that can be collected such as:

* **wild card** - shows up when you have 80 points
* **time slow** - shows up between 25th and 40th second of the game
* **extra life** - gives you one health point

![Int2](/readme/int2.jpg)

The ghosts will continously find the shortest path to Pacman. 

![Int3](/readme/int3.jpg)

One wrong letter selected (i.e. collected by Pacman) means that one (1) health point is lost and when 10 healh points are lost, the player loses the game. 

![Int4](/readme/int4.jpg)

The game was written (rather quickly) when I was an undergrad in Umich in 2012. I liked the overall concept of the game and had always wanted to spend a bit of time creating an improved "remastered" version but never had the time. Some of the improvements that can be made are:

* overall graphics and gameplay, animations
* obviously the code structure needs improvement
* dictionary from where the words are selected, can be pulled from some active online sources
* path-finding algorithms for the ghost (to chase after Pacman) can be determined by level of difficulty
* multi-player option (either with another person or intelligent agent)
  * intelligent agent may use reinforcement learning or a-b-pruning and compete with the other player
