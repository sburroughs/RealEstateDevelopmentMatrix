var MAX_STAGE = 7;
var MAX_TASKS = 8;

var currentStage = 1;
var currentTask = 1;

var stages = [
    "Land Banking",
    "Land Packaging",
    "Land Development",
    "Building Development",
    "Building Operations",
    "Building Renovation",
    "Property Redevelopment",
];


var tasks = [
    "Acquisition",
    "Financing",
    "Market Analysis and Strategies",
    "Environmental",
    "Approvals and Permits",
    "Physical Improvements",
    "Transportation and Accessibility",
    "Sales and Disposition"
];

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

    var contentPath = "pages/matrix/stage" + stage + "/task" + task + ".html";
    $('#modal_view').load(contentPath);

    loadComments(currentStage, currentTask);
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

function loadComments(stageIndex, taskIndex) {


    var url = "https://groups.google.com/forum/embed/?place=forum/real-estate-development-matrix" +
        "&showsearch=true&showpopout=true&" +
        "parenturl=" + encodeURIComponent(window.location.href) +
        '#!categories/real-estate-development-matrix/';

    if (stageIndex <= 0) {
        var taskName = encodeGoogleGroupsSyntax(tasks[taskIndex - 1]);

        url += taskName;
    }
    else if (taskIndex <= 0) {
        var stageName = encodeGoogleGroupsSyntax(stages[stageIndex - 1]);

        url += stageName;
    }
    else {
        var stageName = encodeGoogleGroupsSyntax(stages[stageIndex - 1]);
        var taskName = encodeGoogleGroupsSyntax(tasks[taskIndex - 1]);

        url += stageName + '/' + taskName;
    }

    document.getElementById("comments").src = url;

}

function encodeGoogleGroupsSyntax(name) {
    return name.toLowerCase().split(" ").join("-");
}