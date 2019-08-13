/**
 * Created by shanu on 3/11/2017.
 */
function filter() {

    var tasks = $('#taskSelect').val();
    var stages = $('#stageSelect').val();

    var allTaskSelector = "#tasks > div[id^='task']";
    var allStageSelector = allTaskSelector + "> div > div[id*='stage']";
    var taskElements = $(allTaskSelector);
    var stageElements = $(allStageSelector);

    //Unhides All Cards
    taskElements.show();
    stageElements.show();

    //Hides unselected stages
    stageElements.each(function () {
        // removes stage + 1 single digit.
        // Will not support muli digit stages or tasks
        var match = this.id.slice(7, 8);
        if (!stages.includes(match)) {
            $(this).hide();
        }
    });

    //Hides unselected tasks
    var taskSelector = "";
    tasks.forEach(function (taskNumber) {
        taskSelector += "#task" + taskNumber + ",";
    });
    taskSelector = taskSelector.slice(0, -1); //removes trailing ,
    taskElements.not(taskSelector).hide();

    //Filters by text input
    var searchText = $('#searchText').val().toUpperCase();
    var searchTextPath = "#tasks > div > div > div > div > h4";

    $(searchTextPath).parent().show().filter(function () {
        var title = $(this).find("h4").text().toUpperCase();
        var includes = title.includes(searchText);
        return !includes;
    }).hide();
}
