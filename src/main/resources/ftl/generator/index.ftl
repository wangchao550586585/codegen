<!DOCTYPE html>
<head>
    <#include "../base.ftl">
</head>
<body>

<div id="main">
    <div id="toolbar">
        <button class="waves-effect waves-button" onclick="genCode()">构造代码</button>
        <select id="select" name="name" style="width: 100%">
        </select>
    </div>
    <table id="table"></table>
</div>
<div class="modal fade" id="modal" aria-labelledby="myModalLabel" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lm">
        <div class="modal-content">
        </div>
    </div>
</div>

<script>
    var $table = $('#table');
    $(function () {
        $('select').select2({
            /*     placeholder: '请选择',
                      cache: true,
             ajax: {
                 url: "/gendatasourceconf/listBYDsName",
                 dataType: 'json',

                 processResults: function (data) {
                     var options = [];
                     for (var i = 0; i < data.length; i++) {
                         var option = {
                             "id": data[i].name,
                             "text": data[i].name
                         };
                         options.push(option);
                     }
                     return {
                         results: options
                     };
                 }
             }*/
        });
        $.ajax({
            url: "/gendatasourceconf/listBYDsName",
            dataType:'json',
            success: function (data) {
                for (var d = 0; d < data.length; d++) {
                    var item = data[d];
                    var option = new Option(item.name, item.name, true, true);
                    $('select').append(option);
                }
                $('select').val(['master']).trigger('change');
            }
        });

        $('select').on("change", function (e) {
            $table.bootstrapTable('refresh');
        });

        $table.bootstrapTable({
            url: '/generator/page',
            striped: true,
            search: true,
            showRefresh: true,
            showColumns: true,
            minimumCountColumns: 2,
            clickToSelect: true,
            pagination: true,
            paginationLoop: false,
            sidePagination: 'server',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            idField: 'tableName',
            maintainSelected: true,
            toolbar: '#toolbar',
            canEdit: 'true',
            queryParams: function (params) {
                return {
                    current: params.offset / params.limit + params.offset % params.limit + 1,
                    size: params.limit,
                    tableName: params.search,
                    dsName: $('select').select2("val")
                };
            },
            columns: [
                {field: 'ck', checkbox: true},
                {field: 'tableName', title: '表名', align: 'center'},
                {field: 'tableCollation', title: '字符集', align: 'center'},
                {field: 'tableComment', title: '注释', align: 'center'},
                {field: 'ENGINE', title: '引擎', align: 'center'},
                {field: 'createTime', title: '创建时间', align: 'center'},
                {
                    field: 'action',
                    title: '操作',
                    clickToSelect: false,
                    formatter: 'genCodeActionFormatter',
                    align: 'center'
                }
            ]
        });
    })

    function genCodeActionFormatter(value, row, index) {
        var options = $table.bootstrapTable('getOptions');
        var idField = options.idField;
        var html = [];
        html.push('<a class="update" href="javascript:;" onclick="genCode(\'' + idField + '\',\'' + row[idField] + '\') " data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>');
        <#--html.push(`<a class="update" href="javascript:;" onclick="genCode('${idField}', ${row[idField]})"  data-toggle="tooltip" title="Edit"><i class="glyphicon glyphicon-edit"></i></a>`);-->
        return html.join(' ');
    }

    function genCode(idField, idValue) {
        var rows = [];
        if (idField != undefined && idValue != undefined) {
            rows.push({[idField]: idValue});
        } else {
            rows = $table.bootstrapTable('getSelections');
        }
        if (rows.length == 0) {
            $.confirm({
                title: false,
                content: '请至少选择一条记录!',
                autoClose: 'cancel|3000',
                backgroundDismiss: true,
                buttons: {
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        } else {
            add('/generator/codePage?' + "dsName=" + $('select').select2("val") + "&tableName=" + rows[0].tableName)
        }
    }
</script>

</body>
</html>
