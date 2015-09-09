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

    $('.toggle--modal').click(function (e) {
        e.preventDefault();
        e.stopPropagation();
        $('.modal__overlay').toggleClass('is-active');
        $(this).toggleClass('is-active');
    });

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
    var json = '{"title":null,"content":"\n Acquisitions – Land Banking (I.1.) Long term trends to increase value of the subject property: Population growth usually caused by increased employment in the region Transportation improvements usually related to government sponsored project Interstate Highway or major local highway extensions or new exit Transit extensions and stations Bus (rubber tire) Street car lines Light rail Heavy rail Airport Expansions or new carriers Port facility expansions Intermodal developments Technology changes : such as driverless vehicles, renewable energy generation and storage, food production and processing, water treatment and recycling Solid waste processing Capital partners be patient until the market changes occur. Business cycles are 5-7 years in duration, usually. Government approvals and funding are indeterminist time wise. Federal, state and local government polices toward growth or land uses change constantly. Analyze additional parcels for acquisitions (assemblage). Adjacent land parcels may become available. Determine the value of the combined parcels (plottage value) relative to the cost the combined cos.t Implement interim land use plan to minimize the carrying cost over time, if possible. Interim land uses should lower the net holding costs of the property. Interim land uses should not lower the value of the property or upset the neighbors. Interim land uses should be relatively short term in nature. Identify the critical conditions for the vision to occur: Sustained regional economic growth Changed land use regulations Construction of transportation improvement Infrastructure capacity Identify the major events or conditions that will make the vision obvious to the general market. Determine the vision for the property over the next 5 – 10 years: A concept master plan will help the investors and the general public image what the property could be some day. Artists’ renderings are a great tool to demonstrate the vision. New employers in the region. New ad different elected political leaders. Changing tax policies. International trade opportunities. Determine how the property be acquired and held for the expected holding period or longer if necessary. Seller financing; The seller lends the buyer the money to property. This also referred to as a purchase money mortgage. Option agreements: buyer acquires the right to purchase the property at a specified sometime some time in the future. Extension provisions can very important Sales contract with conditions, pre-conditions to close: A sales contract can be used to control the property until certain condition are met prior to closing. Sales contract usually have non-refundable good faith deposits, and there are usually extension provisions in the contract. Lease with option to buy: land leases allow the lessee (prospective buyer) to control the property (subject to the lease provisions), and the lease has provisions allowing the lessor to purchase the property at certain times during the lease term. The price of the property should be specified, but often it is not so the purchase price is at the market value or at a price mutually agreed upon between the lessor and the lessee Land contracts: The land contract provides for the title to pass from the seller to the buyer after certain specific terms and conditions are met. Interim land uses: These can provide rental income to the land banker which help to defray the holding costs. These uses can be as simple as seasonal hunting lease or as complex as a long term commercial lease with termination provisions. Establish minimum return requirements and expectations: the land banker should clarify the return expectations for her investors and partners. For example the property will double in value in: Five years if the annual appreciation rate is 15% Ten years if the annual appreciation rate is 7% Fifteen years if the annual appreciation rate is 5% Clearly, the faster the general trends occur, the sooner that property can be sold to the land packager, and the higher the return is to the land banker. Examples of this is at the following links: http://www.dankohlhepp.com https://dankohlhepp.com/uploads/3/0/6/8/3068213/bssv2_-_sustainability.pdf Click on the link below to take you to the next Stage: Acquisition Land Packaging Stage "}';

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