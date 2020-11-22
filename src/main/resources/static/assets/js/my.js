var $modal;

function add(url) {
    $(".modal-content").load(url);
    $modal = $('#modal');
    $modal.modal({
        show: true,
        backdrop: false,
        keyboard: false
    });
}

function addSubmit(url, type) {
    console.log(JSON.stringify($("#saveForm").serializeObject()));
    $.ajax({
        type: type,
        url: url,
        data: JSON.stringify($("#saveForm").serializeObject()),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function () {
            $modal.modal('hide')
            $table.bootstrapTable('refresh');
        },
        error: function (XMLHttpRequest) {
            var results = JSON.parse(XMLHttpRequest.responseText).params;
            if (results instanceof Array) {
                $.each(results, function (index, value) {
                    $.confirm({
                        theme: 'dark',
                        animation: 'rotateX',
                        closeAnimation: 'rotateX',
                        title: false,
                        content: value,
                        buttons: {
                            confirm: {
                                text: '确认',
                                btnClass: 'waves-effect waves-button waves-light'
                            }
                        }
                    });
                });
            }
        }
    })
    ;

}


$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

// 格式化操作按钮
function actionFormatter(value, row, index) {
    var options = $table.bootstrapTable('getOptions');
    var idField = options.idField;
    var html = [];
    if (idField) {
        if (options.canEdit == 'true') {
            html.push(`<a class="update" href="javascript:;" onclick="update('${idField}', ${row[idField]})"  data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>`);
        }
        if (options.canDel == 'true') {
            html.push(`<a class="delete" href="javascript:;" onclick="del('${idField}', ${row[idField]})" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>`);
        }
    } else {
        if (options.canEdit == 'true') {
            html.push('<a class="update" href="javascript:;" onclick="update()" data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>');
        }
        if (options.canDel == 'true') {
            html.push('<a class="delete" href="javascript:;" onclick="del()" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>');
        }
    }
    return html.join(' ');
}