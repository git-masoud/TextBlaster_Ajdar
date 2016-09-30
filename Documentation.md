|
# Text Blaster:

# A Multi-Player

# Touchscreen Typing Game
 |
| --- |
|
#

#
 |
|   |

| Fahimeh Mohammadi , Masoud Allahyari    |
| --- |

# Contents

Introduction        2

Gamemechanics        3

Project in detail        4

How different parts communicate        5

Database        6

Web API        7

Web Page        8

Mobile APP        9

Issues we faced during the project        11

Differences between our implementation and the original paper        11

Refrences        12



# Introduction

In this paper we describe our implementation of a Multi-player game based on [CITATION Ver14 \l 1033], which is created based around users typing sentences on a touchscreen mobile device. Our game depends on players typing sentences both quickly and accurately. A player&#39;s shot is in?uenced by how fast a given sentence is entered. The exact path of a player&#39;s shot is in?uenced by how accurately a sentence is typed. Players attempt to be the last player standing by using the speed, precision, and timing of their typing to destroy competing players.  Not only is this  game fun, but it can also serve as a research platform for investigating  performance and design questions related to touchscreen keyboard text entry. This is similar to past work that has attempted to advance text entry research by developing a game.



 

 [CITATION Ver14 \l 1031]
 ![alt tag](https://raw.githubusercontent.com/username/projectname/branch/path/to/img.png)

This picture shows a group playing that each player uses an Android mobile device to control their ship. The global state of the game is shown on the large projection wall. This game encourages users to enter text both quickly and accurately.

# Gamemechanics

The game is played by two or more players. Each player&#39;s name is located at the vertex of a polygon. One of users should make a board and then the other users could see board&#39;s name and join to the board. After all users join to the board, Admin (the user who made the board) could start the game.

 

This image shows a screenshot of a player&#39;s device during the game. Text at the top is the next sentence to be entered. At the top of screen, the player&#39;s name, health, weapons, errors are shown as well.

 

The last two players are situated on a line and the last player remaining is declared the winner.



# Project in detail

There are some ways in multiplayer games to make the communication between them but the most common way is to make an online server and then the other user communicate with it. We are using of this method as you can see in this figure:

Here we have a web client to show the game in a big screen and maybe show it on a big wall with a video projector. All data save in a database which is in the server and by using the API on that server web client and mobile apps can sync their data and communicate with each other.

## How different parts communicate

App and web client check the API every second to show the game and send a request to it to make an action like shoot or get new sentence.

We have the whole procedure here to explain how it is happening in detail:

- **--**** Select a user name by one of the users**
- **--**** Load available boards**
  - _App send a request to API to get available boards : boards which are ready waiting for the other users to join_
  - _API get boards name from database and return a list of them_
  - _Load boards in a list which we have in the first activity_
- **--**** Make a board**
  - _App send a request to API to make a board with current user name as admin_
  - _API insert new records in Database for this board and username_
- **--**** Admin App wait for other users to join**
  - _App checks the API every second and update the list of users_
  - _API get users of the selected board from database and return a list of them_
- **--**** Other users can see the board name in their app and they select the board to join**
  - _Other app send a request to API to join the current user name to the selected board_
  - _API insert the username to data  base_
- **--**** Admin starts the game**
  - _Admin&#39;s App send a request to API to start the game_
  - _API change the status of board in database to ready to start_
- **--**** Someone select the board in web client and start it**
  - _Web client send start request to API_
  - _API change the status of board in database_
- **--**** Web client draws the board and users start the game procedure**
  - _Web client send and check requests every second and animates the objects_
- **--**** Apps update user status and send shoot request during the game**
- **--**** Apps finish the game when the health of user goes less or equal to zero**
  - _App send a request to API to remove the users_
- **--**** Game finishes when just one user remain**

## Database

Because we used &quot;DotNet&quot; technology we preferred to use MSSQL Server 2014. The database contains four tables as you can see in the diagram:

 

**Sentences** : _All the sentences which we want to ask the users to enter should be saved here._

**GameBoards** : _Active and inactive boards are here and Started field contains three value which are null: waiting to start, true: playing and false :game is finished._

**Shoots** : _New orders to shoot are here. Username is for shooter, TargetUserName is the target and CER is the angel of shoot._

**AjdarUsers**** :** _User information is here. They have Health, Weapon, Error and status to show user alive or not._

## Web API

We used Asp.net MVC to build this API. Input should be send by GET method and output are JSON. All the processes are happening here which we are describe them below.

**Important functions:**

**GetBoards():**_Return a list of boards which are waiting to start._

**MakeBoard(name,adminName):**_Name is the board name and adminName is the user name of_ board creator.

**GetUsers(boardId):**_Return a list of users which belong to the board with this boardId._

**RequestToStartGame(boardId):**_Make the status of board to ready for start the first time and for the second time make the status of board is started, because we have two steps for starting the game. First is with admin app when all users join to the game then he should start the game from app. Second, someone in web browser should find the board name in list and start it as well._

**IsGameStarted(boardId):**_Return the board status. Using in web browser._

**GetNewSentence():**_Return a new sentence by random._

**Shoot(boardId, userName, cer):**_Select a target user by random and then save a new shoot in database._

**GetNewShoots(boardId):**_Return all new shoots from database._

**Shooted(ID):**_Find a shoot with this Id in database and remove it._

**UpdateUsersStatus(jsonUsers,boardId):**_In jsonUsers we have a JSON format of all users with their new status then we update all user status in database with this new status._

**GetUserStatus(name,boardId):**_Return an object of this user._

## Web Page

There are two pages for this part based on HTML and JQuery. First page is showing board names which are just created and has not started yet. After selecting the board, we have another simple HTML page which is showing the game using HTML5 canvas objects and communicating with server using JQuery.

**Important functions:**

**drawBoard:** _Get__users from server and draw the board._

**removeUser:** _Remove user if it&#39;s health comes less than one._

**fire:** _Send a shoot request to server_.

**getNewShoots:** _Get shoots every second from server._

**updateUserStatus:** _Get user status every second from server._

 

## Mobile APP

There are three activities:

**MainActivity** : _here user can see boards and make a board._

**Important functions:**

**MakeBoard:** _Send a request to API for making a board._

**joinToABoard(gameboard):** _Send a request to API for joining current user to the selected board._

**updateUsersList:** _This function is called every second to update the list of users._

**checkIsStartedGame:** _This function is called every second to check if the game status is stared then calls startGame()._

**startGame:** _Send a request to API to start the game and show the boardActivity._

**refreshList:** _ This function is called every second to update the list of boards._

 
 

**BoardActivity:** _Here user can see a sentence, write it and send a shoot request._ **   **

**submitSentence:** _The similarity between the requested sentence and user given sentence will be calculated and then a float number between zero to One will be generated. Then App sends a request to API for a shoot to a random user with this similarity._

**getNewSentence:** _App send a request to ApI to get a new sentence and then shows it to user._

**gameOver:** _App will lock the user activity by showing &quot;GAME OVER!&quot; screen._

**win:** _App will lock the user activity by showing &quot;win&quot; screen._

 

**updateStatus:** _App send a request to API to get the status of current user every second and call win or gameOver function if the health of user goes less than 1 or the status changed to 1._

 

# Issues we faced during the project

Firstly we had some difficulty in drawing and animating objects in Html5.We had some problem for calculating the vertices of polygon which we have to  show the user ships. For example if we have 5 users then we should draw a 5 vertices polygon, it sounds simple but because we used iio.net for drawing canvas we had to follow its rules and for drawing such a polygon we had to give it all of the vertices positions. What we did is find a center point position and then by following formula we calculated each position:

_angle = 360 / users.length ;_

_radius=200;_

_x = Math.round(Math.cos(angle \* Math.PI / 180) \* radius + x);_

_y = Math.round(Math.sin(angle \* Math.PI / 180) \* radius + y);_

The second issue which we faced was sentence similarity. There are some ways to calculate it but we tried a lot of them and each one has a problem for us then we decide to use a library and we found SimMetric [https://github.com/Simmetrics/simmetrics] Java library which is calculating similarity and distance metrics between two strings.

# Differences between our implementation and the original paper

In the original paper ,the method of selecting target user in shoot is different with our method. We select target user randomly but in the paper it is not clear how they select it but they mentioned that the angel of ship will change by considering the similarity of user sentence.

# Refrences

_Text Blaster: A Multi-player Touchscreen Typing Game._ **Vertanen, Keith and Emge, Justin and Memmi, Haythem and Kristensson, Per Ola. 2014.** New York, NY, USA : ACM, 2014. pp. 379--382.
