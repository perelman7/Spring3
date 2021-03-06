$(document).ready(function() {

    getAllDeps();
    setupButtonClickHandlersD();
});
//locale variables
var currantPageD = 1;
var maxPageD = 0;
var allDeps = [];
var changedDep = {};

function setupButtonClickHandlersD() {
    $(document).on("click", "button.update_deps", update_form_dep);
    $(document).on("click", "button.delete_deps", delete_dep);
    $(document).on("click", "button.add_deps", add_form_dep);
    $(document).on("click", "button.forward_dep", forward_dep);
    $(document).on("click", "button.back_dep", back_dep);
    $(document).on("click", "button.close_dialog", close_dialog);
}

//Read all departments
function getAllDeps(){
    $.ajax({
        url : 'deps/all',
        type: 'get',
        success : function(data) {
            console.log(data);
            allDeps = data;
            console.log(allDeps);
            createTableD();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#resp_d').html("<dialog id=\"dialog\"><p>"+"Server error, can`t find data about employees"+
                "</p><button class=\"close_dialog btn btn-outline-dark\">Close</button></dialog>");
            var dialog = document.getElementById('dialog');
            dialog.show();
        }
    });
}
//CRUD for Departments
function update_form_dep(){
    console.log("up_d click");
    createTableD($(this).attr("value"));
}
function delete_dep(){
    console.log("del_d click");
    var id = $(this).attr("value");

    $.ajax({
        url : 'deps/delete',
        type: 'delete',
        data: {
            id: id
        },
        success : function(data) {

            var index = allDeps.map(function(e) { return e.id; }).indexOf(Number(id));
            allDeps.splice(index, 1);
            createTableD();

            $('#resp_d').html("<dialog id=\"dialog\"><p>"+"Department was deleted"+
                "</p><button class=\"close_dialog btn btn-outline-dark\">Close</button></dialog>");
            var dialog = document.getElementById('dialog');
            dialog.show();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#resp_d').html("<dialog id=\"dialog\"><p>"+"Department can`t be deleted"+
                "</p><button class=\"close_dialog btn btn-outline-dark\">Close</button></dialog>");
            var dialog = document.getElementById('dialog');
            dialog.show();
        }
    });
}
function add_form_dep(){
    console.log("add_d click");

    currantPageD = maxPageD;
    createTableD();
    $(".departments_table").append(function(){
        console.log("rows: " + $(".departments_table").find(".add_row").length);
        if($(".departments_table").find(".add_row").length === 0){
            return "<tr class=\"add_row\"><form><td></td>" +
                "<td><input type=\"text\" id=\"dep_name_add\"/></td>" +
                "<td><input type=\"text\" id=\"dep_descr_add\"/></td>" +
                "<td><button class=\"cancel_add_deps btn btn-warning\">cancel</button></td>" +
                "<td><button class=\"save_add_deps btn btn-success\">save</button></td></form></tr>";
        }
    });
    $(".cancel_add_deps").on("click", cansel_update_dep);
    $(".save_add_deps").on("click", save_add_dep);
}
function cansel_update_dep(){
    console.log("cansel_update_dep click");
    createTableD();
}
function save_update_dep(){
    console.log("save_update_dep click");
    var id_value = document.getElementById("dep_id").textContent;
    var name_dep = document.getElementById("dep_name").value;
    var desc = document.getElementById("dep_descr").value;
    changedDep = {};

    $.ajax({
        url : 'deps/update',
        type: 'put',
        data: {
            id: id_value,
            depName: name_dep,
            description: desc
        },
        success : function(data) {

            console.log(data);

            var index = allDeps.map(function(e) { return e.id; }).indexOf(Number(id_value));
            allDeps[index].name = name_dep;
            allDeps[index].description = desc;
            changedDep = {'id': id_value, 'departmmentName': name_dep, 'description': desc};
            console.log("isUpdate");
            createTable();
            createTableD();


            $('#resp_d').html("<dialog id=\"dialog\"><p>"+"Department was updated"+
                "</p><button class=\"close_dialog btn btn-outline-dark\">Close</button></dialog>");

            var dialog = document.getElementById('dialog');
            dialog.show();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#resp_d').html("<dialog id=\"dialog\"><p>"+"Fill in field: name"+
                "</p><button class=\"close_dialog btn btn-outline-dark\">Close</button></dialog>");
            var dialog = document.getElementById('dialog');
            dialog.show();
        }
    });
}
function save_add_dep(){
    console.log("save_add_dep click");
    var name_dep = document.getElementById("dep_name_add").value;
    var desc = document.getElementById("dep_descr_add").value;
    var validName = false;

    if(name_dep === ""){
        document.getElementById("dep_name_add").setAttribute("placeholder", "Fill in name");
    }
    else{
        validName = true;
    }
    if(validName){
        $.ajax({
            url : 'deps/add',
            type: 'POST',
            data: {
                depName: name_dep,
                description: desc
            },
            success : function(data) {

                allDeps.push({'id': Number(data), 'depName': name_dep, 'description': desc});
                createTableD();

                $('#resp_d').html("<dialog id=\"dialog\"><p>"+"Department was added"+
                    "</p><button class=\"close_dialog btn btn-outline-dark\">Close</button></dialog>");
                var dialog = document.getElementById('dialog');
                dialog.show();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#resp_d').html("<dialog id=\"dialog\"><p>"+"Fill in fields: name"+
                    "</p><button class=\"close_dialog btn btn-outline-dark\">Close</button></dialog>");
                var dialog = document.getElementById('dialog');
                dialog.show();
            }
        });
    }
}
function createTableD(val){

    var start = 0 + (10 * (currantPageD-1));
    var end = 10 + (10 * (currantPageD-1));
    var table = allDeps.slice(start, end);
    maxPageD = Math.ceil(allDeps.length / 10);
    var index = Number(val);

    var result="<fieldset><legend>Departments table</legend><button class=\"add_deps btn btn-info\" id=\"add_dep\">Add</button>"+
        "<table class=\"departments_table table table-bordered table-sm table-hover\"><thead>"+
        "<tr class=\"active\"><td scope=\"col\">Id</td><td scope=\"col\">Name</td><td scope=\"col\">Description</td><td colspan=\"2\">Actions</td></tr></thead>";
    for (i=0; i<table.length;i++) {
        var id = table[i].id;
        var name = table[i].depName;
        var desc = table[i].description;
        if(index === id){
            result+="<tr scope=\"row\"><form><td id=\"dep_id\">" + id + "</td>" +
                "<td><input type=\"text\" id=\"dep_name\" value=\"" + name +"\"/></td>" +
                "<td><input type=\"text\" id=\"dep_descr\" value=\"" + desc +"\"/></td>" +
                "<td><button class=\"cancel_deps btn btn-warning\">cancel</button></td>" +
                "<td><button class=\"save_deps btn btn-success\">save</button></td></form></tr>";
        }
        else{
            result+="<tr scope=\"row\"><td>"+id+"</td><td>"+name+"</td><td>"+desc+"</td>"+
                "<td><button class=\"delete_deps btn btn-outline-danger\" value=\""+id+"\">Delete</button></td>"+
                "<td><button class=\"update_deps btn btn-outline-primary\" value=\""+id+"\">Update</button></td></tr>";
        }
    }
    result+="</table><button style=\"float: left;\" class=\"back_dep page-link\"><-</button>"+
        "<div class=\"page-link\" style=\"float: left;\">"+currantPageD + " / " + maxPageD+"</div>"+
        "<button style=\"float: left;\" class=\"forward_dep page-link\">-></button>"+
        "</fieldset>";
    $('#resp_ajax_deps').html(result);
    $(".cancel_deps").on("click", cansel_update_dep);
    $(".save_deps").on("click", save_update_dep);
}
function close_dialog(){
    var dialog = document.getElementById('dialog');
    dialog.close();
}
//Pagination
function forward_dep(){
    console.log("forward_dep click");
    if(currantPageD < maxPageD){
        currantPageD++;
    }
    console.log("page: " + currantPageD);
    createTableD();
}
function back_dep(){
    console.log("back_dep click");
    if(currantPageD > 1){
        currantPageD--;
    }
    console.log("page: " + currantPageD);
    createTableD();
}