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
}

function serveMatrix() {

    var dStage = $.get("/matrix/headers/stage");
    var dTask = $.get("/matrix/headers/task");
    $.when(dStage, dTask).done(
        /**
         * Takes in json for an object defining the stage and task structure and content.
         * Content outlined in javadoc for backend layer.
         * @param stageJson
         * @param taskJson
         */
        function (stage, task) {
            try {
                var stageHeader = JSON.parse(stage[0]).headers;
                var taskHeader = JSON.parse(task[0]).headers;

                var stageLength = stageHeader.length;
                var taskLength = taskHeader.length;

                var stageHeadingHtml = "<tr><td></td>";
                for (var i = 1; i <= stageLength; i++) {
                    var stageHeadTitle = stageHeader[i - 1].title;
                    stageHeadingHtml += "<th onclick='serveStageHeader(" + i + ")'><a href='#' class='toggle--modal'>" + (i) + " " + stageHeadTitle + "</a></th>"
                }
                stageHeadingHtml += "</tr>";

                var taskContent = "";
                for (var i = 1; i <= taskLength; i++) {
                    var taskTitle = taskHeader[i - 1].title;
                    taskContent += "<tr><th onclick='serveTaskHeader(" + i + ")'><a href='#' class='toggle--modal'>" + romanize(i) + " " + taskTitle + "</a></th>";
                    for (var j = 1; j <= stageLength; j++) {
                        var stageTitle = stageHeader[j - 1].title;
                        var combinedTitle = romanize(i) + "." + j + "<br>" + stageTitle + "<br>" + taskTitle;
                        taskContent += "<td onclick='serveContent(" + j + "," + i + ")'><a href='#' class='toggle--modal'>" + combinedTitle + "</a></td> ";
                    }
                    taskContent += "</tr>";
                }

                var matrixContent = stageHeadingHtml + taskContent;
                var tableWrapper = '<table>' + matrixContent + '</table>';

                $("#matrix").html(tableWrapper);

            }
            catch (error) {
                alert(error);
            }


        }
    ).fail(function () {
            alert("Unable to load Matrix.")
        });

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

    var url = "/matrix/stage" + currentStage + "/task" + currentTask;
    serveModal(url);

}

function serveTaskHeader(index) {
    var url = "/matrix/headers/task/" + index;
    serveModal(url);
}

function serveStageHeader(index) {
    var url = "/matrix/headers/stage/" + index;
    serveModal(url);
}

function serveModal(url) {

    var request = $.get(url);
    $.when(request).done(
        function (json) {
            var node = JSON.parse(json);
            var content = node.content;
            var title = node.title;
            var header = "<h1>" + title + "</h1>";
            var view = header + "<br>" + content;
            $("#main-view").html(view);

            var commentsBlock = "";


            var rootComment = node.rootComment;
            var rootChildren = rootComment.childComments;
            for (var i = 0; i < rootChildren.length; i++) {
                var comment = rootChildren[i];
                commentsBlock += "<div class='comment'><span>Name: " + comment.name + "</span>";
                commentsBlock += "<p>" + comment.commentText + "</p>";
                commentsBlock += "<span>Timestamp: " + comment.timestamp + " </span>";
                var commentChildren = comment.childComments;
                for (var j = 0; j < commentChildren.length; j++) {
                    var childComment = commentChildren[j];
                    commentsBlock += "<br>";
                    commentsBlock += "<div class='child_comment'><span>Name: " + childComment.name + "</span><br>";
                    commentsBlock += "<p>" + childComment.commentText + "</p>";
                    commentsBlock += "<span>Timestamp: " + childComment.timestamp + " </span>";
                    commentsBlock += "</div>";
                }

                commentsBlock += "</div>";

            }

            $("div#comments").replaceWith(commentsBlock);

        }
    ).
        fail(function () {
            alert("Unable to retrieve content: " + url);
        });

}


function incrementContent(stageIncrement, taskIncrement) {

    var stage = currentStage + stageIncrement;
    var task = currentTask + taskIncrement;

    serveContent(stage, task);

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