var sudokoBoardSize = 9;
var sudokoBoardId ="";

function createSudokoBoard() {
    var sudokBoard="";
    for (var i = 0; i < 81; i++) {
        var id = "VAL" + i;
        var cellValue = document.getElementById(id).value;
        if (cellValue == "") {
            cellValue = ".";
        }
        sudokBoard = sudokBoard.concat(cellValue);

    }

    var JSONObject = {"sudokoBoardAsString": sudokBoard};

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "sudoko/rest/api",
        dataType: "json",
        data: JSON.stringify(JSONObject),
        success: function (resp) {
            if (resp.isValidBoard == true) {
                disableCell();
                document.getElementById("p1").innerHTML = "Sudoko Board Created";
                sudokoBoardId = resp.id;
            } else {
                document.getElementById("p1").innerHTML = "Invalid Sudoko Board";
            }
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });

}
function disableCell() {
    for (var i = 0; i < 81; i++) {
        var id = "VAL" + i;
        var cellValue = document.getElementById(id).value;
        if (cellValue !== "")
        {
            document.getElementById(id).disabled = true;
            document.getElementById(id).style.backgroundColor = "yellow";
        }
    }
}
function validateTheCellValue(obj, column) {
    if (document.getElementById("sudokoHintId").checked) {
        var cellValue = obj.value;
        if (cellValue !== "") {
            var length = column.length;
            var number = column.substring(3, length);
            var row = number / sudokoBoardSize;
            var col = number % sudokoBoardSize;
            validateSuccussiveMove(obj, parseInt(row), parseInt(col), cellValue);
        }
    }
}
function validateSuccussiveMove(obj, row, col, cellValue) {
    
    var JSONObject = {"row": row, "column": col, "cellValue": cellValue,
        "id": sudokoBoardId};
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "sudoko/rest/api/successiveMoves",
        dataType: "json",
        data: JSON.stringify(JSONObject),
        success: function (resp) {
            if (resp.isValidMove == true) {
                obj.style.backgroundColor = "lightgreen";
            } else {
                obj.style.backgroundColor = "red";
            }
            if (resp.isSudokoFinished == true) {
                document.getElementById("sudokoFinishedStatus").innerHTML = "Sudoko is Finished";
            }
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}
function solveSudoko() {
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: "sudoko/rest/api/solve/"+sudokoBoardId,
        dataType: "json",
        success: function (resp) {
            if (resp.solutionExists == true) {
                var solution = resp.sudokoBoadSolutionString;
                setResultToSudokoBoard(solution);
            } else {
                document.getElementById("p1").innerHTML = "No Solution give Sudoko Board";
            }
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });
}
function setResultToSudokoBoard(solution) {

    for (var i = 0; i < 81; i++) {
        var id = "VAL" + i;
        document.getElementById(id).value = solution.charAt(i);
    }

}

function restForm() {
    for (var i = 0; i < 81; i++) {
        var id = "VAL" + i;
        document.getElementById(id).disabled = false;
        document.getElementById(id).style.backgroundColor = "white";
    }
    document.getElementById("myForm").reset();
}


