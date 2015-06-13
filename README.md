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
Through using the arrow keys as well as WASD, each player may control the row
at which they deploy units. Meanwhile, they can press Enter or Space respectively
to actually deploy each unit. 

Units stop when they meet an enemy unit, and deal a set amount of damage until
the target enemy loses all of its health. Standard Monsters cost less money
to deploy but are weaker than certain others. Meanwhile, Juggernauts have a 
great amount of health but very little damage. On the other hand, other Monsters
are extremely quick and can cover considerable distances and spatial advantage
over the opponent. In return, they have very little health and damage. 

When a player has enough money, he or she can use the money to buy Monsters.
Alternatively, he or she can use it to buy boosts. Boosts have different 
capabilities - some restore health, others give extra strength. 

Near the end of the project, I decided that there should also be cheats. This is
merely for the purpose of being able to and enabling some certain advantages.

######Game Decisions
I used VioletUML Creator to design and plan out my initial project via
a UML. The UML went through many changes, as core portions of the project
were shifted around and others were brought in place, such as the XML. This 
actually went through several iterations - It started out as a two person
tower defense game - Each person would be able to designate offenses
and defenses on their sides of the grid, and the first player to breach
the other's defenses would win the game. This idea was later scrapped as 
it was not feasible within the limited timeframe given - a mere two weeks.

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

