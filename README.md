# Domination
This project was my final project for AP Computer Science
at Homestead High School.

##Contents
1. [Summary](#summary)
2. [Gameflow](#gameflow)
3. [Features](#features)
4. [Game Decisions](#game-decisions)

##Summary
This was designed to be a hybrid of 
[Canyon Defense](http://www.miniclip.com/games/canyon-defense/en/#t-c-f-C) and
[Double Edged](http://www.nitrome.com/games/doubleedged/#.VXtXShNViko).
This is a two player game, where each person sends different units towards
the opponent on a grid. The game ends when a Monster reaches the lines of 
the opponent's side. 

![Blue Bolt](https://github.com/YangVincent/Domination/blob/master/Animations/BlueFightingBoltMonster/blue1.png)
![Red Bolt](https://github.com/YangVincent/Domination/blob/master/Animations/RedFightingJuggernautMonster/red3.png)

**Sun Tzu**:
> Build your opponent a golden bridge to retreat across

##Gameflow
People get in-game money periodically, which they can then use to buy more units
or powerups, such as extra health, etc.

######Game Decisions
I decided to use in-game sprites as opposed to gifs because gifs operated
at different speeds depending on the computer. Also, certain gifs would
not change to other actions at certain times. For this reason, I decided
to use sprites instead to directly control which gif was shown when, as well as 
to change sprites depending on which action the current Monster was taking - 
Walking, fighting, etc. 

Another decision was to use Object Oriented Programming - Since this was the
final project for AP Computer Science (Java), the choice to have certain 
Monsters extend from others was a relatively simple one. 



##Features
* Sprites
* Money
* Store
* MultiThreading
* Cheats
* Powerups
* Panel switching through CardLayout

