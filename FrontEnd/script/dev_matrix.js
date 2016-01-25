var MAX_STAGE = 7;
var MAX_TASKS = 8;

var currentStage = 1;
var currentTask = 1;

var matrix;

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

    //$('#main-view').empty();

    var contentPath = "pages/matrix/stage" + stage + "/task" + task + ".html";
    $('#main-view').load(contentPath);
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