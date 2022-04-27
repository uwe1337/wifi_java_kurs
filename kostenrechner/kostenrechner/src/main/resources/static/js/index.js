$("#date").datepicker({
    changeMonth: true,
    changeYear: true,
    prevText: '&#x3c;zurück',
    prevStatus: '',
    prevJumpText: '&#x3c;&#x3c;',
    prevJumpStatus: '',
    nextText: 'Vor&#x3e;',
    nextStatus: '',
    nextJumpText: '&#x3e;&#x3e;',
    nextJumpStatus: '',
    currentText: 'heute',
    currentStatus: '',
    todayText: 'heute',
    todayStatus: '',
    clearText: '-',
    clearStatus: '',
    closeText: 'schließen',
    closeStatus: '',
    monthNames: ['Januar', 'Februar', 'März', 'April', 'Mai', 'Juni',
        'Juli', 'August', 'September', 'Oktober', 'November', 'Dezember'
    ],
    monthNamesShort: ['Jan', 'Feb', 'Mär', 'Apr', 'Mai', 'Jun',
        'Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Dez'
    ],
    dayNames: ['Sonntag', 'Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag'],
    dayNamesShort: ['So', 'Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa'],
    dayNamesMin: ['So', 'Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa'],
    showMonthAfterYear: false,
    dateFormat: 'dd.mm.yy'
});
$("#editDate").datepicker({
    changeMonth: true,
    changeYear: true,
    prevText: '&#x3c;zurück',
    prevStatus: '',
    prevJumpText: '&#x3c;&#x3c;',
    prevJumpStatus: '',
    nextText: 'Vor&#x3e;',
    nextStatus: '',
    nextJumpText: '&#x3e;&#x3e;',
    nextJumpStatus: '',
    currentText: 'heute',
    currentStatus: '',
    todayText: 'heute',
    todayStatus: '',
    clearText: '-',
    clearStatus: '',
    closeText: 'schließen',
    closeStatus: '',
    monthNames: ['Januar', 'Februar', 'März', 'April', 'Mai', 'Juni',
        'Juli', 'August', 'September', 'Oktober', 'November', 'Dezember'
    ],
    monthNamesShort: ['Jan', 'Feb', 'Mär', 'Apr', 'Mai', 'Jun',
        'Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Dez'
    ],
    dayNames: ['Sonntag', 'Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag'],
    dayNamesShort: ['So', 'Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa'],
    dayNamesMin: ['So', 'Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa'],
    showMonthAfterYear: false,
    dateFormat: 'dd.mm.yy'
});

$(".deleteButton").click(function () {
    $(".copyOf_krItem").remove();

    const deleteID = parseInt($(this).attr('id').replace("delBtn_", ""));

    $(`#krItem_${parseInt(deleteID)}`).clone()
        .prop({
            "id": `copyOf_krItem_${deleteID}`,
            "class": `copyOf_krItem`
        })
        .appendTo("#copyDeleteItemHere");

    $(`#copyOf_krItem_${deleteID} #actionElement`).remove();

    $("#deleteForm #deleteID").attr('value', deleteID).text(deleteID);

    $("#deleteModal").modal("show");
});

$(".editButton").click(function () {
    // $(".copyOf_krItem").remove();

    const editID = parseInt($(this).attr('id').replace("editBtn_", ""));

    // $(`#krItem_${parseInt(deleteID)}`).clone()
    //     .prop({ "id": `copyOf_krItem_${deleteID}`, "class": `copyOf_krItem` })
    //     .appendTo("#copyDeleteItemHere");

    // $(`#copyOf_krItem_${deleteID} #actionElement`).remove();

    $("#editForm #editID").val(editID);

    const amount = $(`#editBtnAmount_${editID}`).text().replace(" ", "");
    $("#editForm #editAmount").val(parseFloat(amount));
    $("#editForm #editDate").val($(`#editBtnDate_${editID}`).text());
    $("#editForm #editCategory").val($(`#editBtnCategory_${editID}`).text());
    $("#editForm #editDescription").val($(`#editBtnDescription_${editID}`).text());
    $("#editModal").modal("show");
});

if ($("#plot").length > 0) {
    let data = [{
        type: "pie",
        values: arrayValues,
        labels: arrayCategorys,
        textinfo: "label+percent",
        insidetextorientation: "radial"
    }]

    var layout = [{
        height: 700,
        width: 700
    }]

    Plotly.newPlot('plot', data, layout);
}

$(document).ready(function () {
    $('#sortTable').DataTable({
        "language": {
            "url": "js/datatables_language_de.json",
            searchPlaceholder: "Suchbegriff eingeben...",
        }
    });
});