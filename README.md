
I developed this application using Spring Boot 
Build application by mvn clean install
running application mvn spring-boot:run



1. I used backtracking algorithm to solve sudoku

REST API

2. Post http://localhost:8080/sudoko/rest/api  for creating new board
  {"sudokoBoardAsString":"7...4.53...5..8.1...85.9.4.539.6...1....1...58..72.9..9.74.........57...6...
...5."}
  "sudokoBoardAsString" is required filed
  value must be contains 1 to 9 or DOT value
  length must be 81 character

 Response : status created 201

{
"id": 1
"sudokoBoardAsString": "9..2....8..8...7.2...48..3.3..7.4..9..2.1.....8....4..8..1....4....4..7..9.8.3..5"
"isValidBoard": true
} 

2: Validating success moves
Post http://localhost:8080/sudoko/rest/api/successiveMoves
parameters

 {
 "row":8,
 "column":6,
 "cellValue":"1",
 "id":1
 }

 row required data type int valid range 0 to 8
 column required data type int valid range 0 to 8
 cellValue required data type int valid range 1 to 9
 id required  data type int id of sudokoboard created before
 
 Response
 
 {
	"id": 1
	"row": 8
	"column": 6
	"isValidMove": true  
	"cellValue": 1
	"isSudokoFinished": false
}
 
 3:
 In order to Solve the Board
GET http://localhost:8080/sudoko/rest/api/solve/{id} Ex:- id =1 id data type int

Response: status ok 200
{

"solutionExists": true
"sudokoBoadSolutionString": "913257648468391752527486931356724189742918563189635427875162394231549876694873215"
}

sudokoBoadSolutionString is solution for board id 1

4.Acces Web interface through http://localhost:8080/

1. Enter the sudoko board values you want to solve
2. Click on create Sudoko board 
3. if your given sudoko board values valid it will create board(message will display on top)
   Note:- once board created you can't edit the values (Highlighted by yellow )
4. Now start playing the Game 
5. if you enable hint option it validate the moves once loss the focus 
6. if it is success move cell turn in to green colour other wise red colour
7. once sudoko finished it shows message sudoko is finished
8. you find  the solution by click solve button




 
 