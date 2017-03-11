/**
 * Created by shanu on 3/11/2017.
 */
function filter() {

    var tasks = $('#taskSelect').val();
    var stages = $('#stageSelect').val();

    var allTaskSelector = "#tasks > div[id^='task']";
    var taskElements = $(allTaskSelector);

    //Unhides All Cards
    taskElements.show();


    var taskSelector = "";
    tasks.forEach(function (taskNumber) {
        taskSelector += "#task" + taskNumber + ",";
    });
    taskSelector = taskSelector.slice(0, -1); //removes trailing ,

    //Hides unselected tasks
    taskElements.not(taskSelector).hide();

    //Hides unselected stages


}

