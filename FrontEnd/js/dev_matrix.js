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
        function populateMatrix(stageJson, taskJson) {

            var stageHeader = JSON.parse(stageJson[0]).headers;
            var taskHeader = JSON.parse(taskJson[0]).headers;

            var stageLength = stageHeader.length;
            var taskLength = taskHeader.length;

            var stageHeadingHtml = "<tr><td></td>";
            for (var i = 1; i <= stageLength; i++) {
                var stageHeadTitle = stageHeader[i - 1].title;
                stageHeadingHtml += "<th class='toggle--modal' onclick='serveContent(" + i + ",0)'><a href='#'>" + (i) + " " + stageHeadTitle + "</a></th>"
            }
            stageHeadingHtml += "</tr>";

            var taskContent = "";
            for (var i = 1; i <= taskLength; i++) {
                var taskTitle = taskHeader[i - 1].title;
                taskContent += "<tr><th class='toggle--modal' onclick='serveContent(0," + i + ")'><a href='#'>" + romanize(i) + " " + taskTitle + "</a></th>";
                for (var j = 1; j <= stageLength; j++) {
                    var stageTitle = stageHeader[j - 1].title;
                    var combinedTitle = romanize(i) + "." + j + "<br>" + stageTitle + "<br>" + taskTitle;
                    taskContent += "<td class='toggle--modal' onclick='serveContent(" + j + "," + i + ")'><a href='#'>" + combinedTitle + "</a></td> ";
                }
                taskContent += "</tr>";
            }

            var matrixContent = stageHeadingHtml + taskContent;
            var tableWrapper = '<table>' + matrixContent + '</table>';

            $("#matrix").html(tableWrapper);
        }
    ).fail(function () {
            console.error("Unable to load content for url: " + url);
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

function serveModal(url) {

    var request = $.get(url);
    $.when(request).done(
        function populateModal(json) {
            var node = JSON.parse(json);
            var content = node.content;
            var title = node.title;
            var header = "<h1>" + title + "</h1>";
            var view = header + "<br>" + content;

            $("#main-view").html(view);
            $("#main-view").scrollTop(0);

        }
    ).fail(function () {
            console.error("Unable to load content for url: " + url);
        });
}


function navigateContent(stageIncrement, taskIncrement) {

    var stage = currentStage + stageIncrement;
    var task = currentTask + taskIncrement;

    if (validate(stage, task)) {
        serveContent(stage, task);
    }
}


function serveContent(stage, task) {

    currentStage = stage;
    currentTask = task;

    var url = null;
    if (stage > 0 && task > 0) {
        url = "/matrix/stage" + stage + "/task" + task;
    }
    else if (task == 0) {
        url = "/matrix/headers/stage/" + stage;
    }
    else if (stage == 0) {
        url = "/matrix/headers/task/" + task;
    }

    serveModal(url);
}

function validate(stageIndex, taskIndex) {

    var valid = true;
    //UP
    if (taskIndex < 0) {
        valid = false;
    }
    //DOWN
    if (taskIndex > MAX_TASKS) {
        valid = false;
    }
    //LEFT
    if (stageIndex < 0) {
        valid = false;
    }
    //RIGHT
    if (stageIndex > MAX_STAGE) {
        valid = false;
    }

    return valid;
}