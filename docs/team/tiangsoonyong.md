# Soon Yong - Project Portfolio Page

## Overview
RollaDie is a Dungeon & Dragons (DnD) text-based RPG, optimized to play using Command Line Interface (CLI) and has a simple text-ui display that reminisces games of the 1960s. 
This program is meant for CS2113 students as a stress reliever and it aims to provide a fun and replayable experience!

### Summary of Contributions
#### Code contributed:
[My Code Dashboard](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=tiangsoonyong&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2025-02-21&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=TiangSoonYong&tabRepo=AY2425S2-CS2113-T13-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=falseNothing)

#### Enhancements implemented:
My main contribution to the Rolladie game was the
`Storage` class: Handles the translation between game data and local text save file. It mainly features:
- Autosave
- Ability to load save file

In order to achieve **scalability** of the class in future development, I implemented abstract method `toText()` for `Character` classes
and an additional `getEventIcon()` method for `Event`.
This helped streamline the process for saving game data of new Events and Characters subclasses.
However, the loading of data still required manual inspection of code in order to load the correct objects.

I also contributed to the initial version of:  
`Game` class: It manages all game logic, particularly event generation and event queuing, to ensure a sequential flow of events for the user to enjoy.  
`Parser` class: It handles the interpretation of user inputs into `Action` objects that it used to execute different commands, reducing nested if-else and switch cases.


#### Contributions to the UG:
- Mainly contributed to the initial version of the UG `before v1.0`

#### Contributions to the DG:
- Created the UMLs for the `Storage` class, including the class diagram and the two sequence diagram for loading and saving of game.
- Peer checking 

#### Contributions to team-based tasks
- Exploratory testing

