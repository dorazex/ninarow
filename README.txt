ninarow - ex1
------------
Name: Dor Azouri
ID: 204034300
------------

Instructions
============
1.	Double click the given batch file.
	The batch file executes the main module's jar: 'ninarow.jar'.
2.	There are 4 modules in this project (including the main ninarow), each with a corresponding JAR artifact:
	ninarow -				main, depends on the other 3
	GameEngine -			the core game logic and classes needed
	XmlLoader - 			handles the loading of the config XML into java variables
	ConsoleUserInterface	handles the interaction with the user through the console.
							Depends of both GameEngine and XmlLoader
3.	Running one of the non-main JARs will only print a stub text of the module name


Assumptions
============
1.	All assumptions described in the exercise
2.	Ex1 requires one human player and one computer player, so I skip the redundant steps of asking the user which is which, but still randomize which of the players starts playing first.
3.	Written with Java version 10.0.1