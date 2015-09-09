var MAX_STAGE = 7;
var MAX_TASKS = 8;

var currentStage = 1;
var currentTask = 1;

var matrix;

function servePage() {
    try {
        serveMatrix();
    }
    catch (error) {
        alert(error);
    }

    try {
        serveComments();
    }
    catch (error) {
        alert(error);
    }
}

function serveMatrix() {

    // var dStage = $.get("/matrix/headers/stage");
    //  var dTask = $.get("/matrix/headers/task");
    //  $.when(dStage, dTask).done(
    //      function (stageJson, taskJson) {

    try {
        var stageJson = '{"headers":[{"title":"Land Banking","content":"sdfgdsfd"},{"title":"Land Packaging","content":"dfgfdgfd"},{"title":"Land Development","content":"sagdfsagdf"},{"title":"Building Development","content":"sadfdsafdsf"},{"title":"Building Operations","content":"asdfashah"},{"title":"Building Renovation","content":"gfdsgfdsg"},{"title":"Building Redevelopment","content":"afdsfdsaf"}]}';
        var taskJson = '{"headers":[{"title":"Acquisition","content":"This is content"},{"title":"Financing","content":"This is content"},{"title":"Marketing Studies and Marketing Strategies","content":"This is also content"},{"title":"Environmental Studies","content":"This is content"},{"title":"Approvals and Permits","content":"Content"},{"title":"Improvements","content":"Content"},{"title":"Transportation and Accessability","content":"Content"}]}';

        //TODO: remove ^
        //TODO: change to taskJson[0]
        var stageHeader = JSON.parse(stageJson).headers;
        var taskHeader = JSON.parse(taskJson).headers;

        var stageLength = stageHeader.length;
        var taskLength = taskHeader.length;


        var stageHeadingHtml = "<tr><th></th>";
        for (var i = 0; i < stageLength; i++) {
            var stageHeadTitle = stageHeader[i].title;
            stageHeadingHtml += "<th>" + stageHeadTitle + "</th>";
        }
        stageHeadingHtml += "</tr>"

        var taskContent = "";
        for (var i = 0; i < taskLength; i++) {

            var taskTitle = taskHeader[i].title;
            taskContent += "<tr><th>" + taskTitle + "</th>";
            for (var j = 0; j < stageLength; j++) {
                var stageTitle = stageHeader[j].title;

                var combinedTitle = romanize(i + 1) + "." + (j + 1) + " " + stageTitle + " in " + taskTitle;
                taskContent += "<td onclick='serveContent(" + j + "," + i + ")'><a href='#' class='toggle--modal'>" + combinedTitle + "</a></td> ";

            }

            taskContent += "</tr>";

        }

        var matrixContent = stageHeadingHtml + taskContent;
        var tableWrapper = '<table>' + matrixContent + '</table>';

        $("#matrix").html(matrixContent);

    }
    catch (error) {
        alert(error);
    }


//}
//   ).fail(function () {
//           alert("Unable to load Matrix")
//       });

}

function romanize(num) {
    if (!+num)
        return false;
    var digits = String(+num).split(""),
        key = ["", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM",
            "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC",
            "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"],
        roman = "",
        i = 3;
    while (i--)
        roman = (key[+digits.pop() + (i * 10)] || "") + roman;
    return Array(+digits.join("") + 1).join("M") + roman;
}


function serveContent(stageIndex, taskIndex) {

    currentStage = stageIndex;
    currentTask = taskIndex;

    validateButtons();

    //var url = "matrix/stage" + currentStage + "/task" + currentTask;
    //var request = $.get(url);
    //$.when(request).done(
    //    function (json) {
    var json = '{"title":null,"content":"This is sample content loaded by the thing."}';

    var content = JSON.parse(json).content;
    var header = "Stage: " + currentStage + " Task: " + currentTask;
    var view = header + "<br>" + content;
    $("#main-view").html(view);
    //}).fail(function () {
    //    alert("Unable to serve content")
    //});
}

function incrementContent(stageIncrement, taskIncrement) {

    var stage = currentStage + stageIncrement;
    var task = currentTask + taskIncrement;

    serveContent(stage, task);

}

function serveComments() {

    //var comments = retrieveComments();
    //
    //var insert = "";
    //for (var i = 0; i < comments.length; i++) {
    //
    //    var comment = comments[i].comment;
    //    var name = comment.name;
    //    var value = comment.value;
    //
    //    var nextComment = '<div class="existing-comment">' + value + '</div>';
    //    insert = insert + nextComment;
    //}
    //
    //
    //var node = document.createElement("LI");                 // Create a <li> node
    //var textnode = document.createTextNode("Water");         // Create a text node
    //node.appendChild(textnode);                              // Append the text to <li>
    //document.getElementById("myList").appendChild(node);     // Append <li> to <ul> with id="myList"


}

function retrieveComments() {

    var response = '{"comments":[{"comment":{"name":"Kim","value":"I think this could work"}},{"comment":{"name":"Shane","value":"Do you think this could work?"}}]}';

    var comments = JSON.parse(response);
    return comments.comments;
}


function validateButtons() {

    //UP
    if (currentTask <= 0) {
        document.getElementById("upBtn").disabled = true;
    }
    else {
        document.getElementById("upBtn").disabled = false;
    }

    //DOWN
    if (currentTask >= MAX_TASKS - 1) {
        document.getElementById("downBtn").disabled = true;
    }
    else {
        document.getElementById("downBtn").disabled = false;
    }

    //LEFT
    if (currentStage <= 0) {
        document.getElementById("leftBtn").disabled = true;
    }
    else {
        document.getElementById("leftBtn").disabled = false;
    }

    //RIGHT
    if (currentStage >= MAX_STAGE - 1) {
        document.getElementById("rightBtn").disabled = true;
    }
    else {
        document.getElementById("rightBtn").disabled = false;
    }


}