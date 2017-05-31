var ajaxMealUrl = 'ajax/meals/';
var datatableApiMeal;

$(function () {
    datatableApiMeal = $('#datatable').DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});

function deleteRow(id) {
    $.ajax({
        url: ajaxMealUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
            successNoty('Deleted');
        }
    });
}

function updateTable() {
    var form = $('#filter');
    form.clear();
    $.get(ajaxMealUrl, function (data) {
        datatableApiMeal.clear();
        $.each(data, function (key, item) {
            datatableApiMeal.row.add(item);
        });
        datatableApiMeal.draw();
    });
}

function save() {
    var form = $('#detailsForm');
    $.ajax({
        type: "POST",
        url: ajaxMealUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            // updateTable();
            between();
            successNoty('Saved');
        }
    });
}

function between() {
    var form = $('#filter');
    $.ajax({
        type: "POST",
        url: ajaxMealUrl + 'filter',
        data: form.serialize(),
        success: function (data) {
          datatableApiMeal.clear();
            $.each(data, function (key, item) {
                datatableApiMeal.row.add(item);
            });
            datatableApiMeal.draw();
        }
    })


}