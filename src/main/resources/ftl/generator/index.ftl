<!DOCTYPE html>
<head>
    <#include "../base.ftl">
</head>
<body>

<div id="main">
    <div id="toolbar">
        <button class="waves-effect waves-button" onclick="genCode()">构造代码</button>
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
            idField: 'id',
            maintainSelected: true,
            toolbar: '#toolbar',
            canEdit: 'true',
            canDel: 'true',
            queryParams: function (params) {
                return {
                    current: params.offset / params.limit + params.offset % params.limit + 1,
                    size: params.limit,
                    tableName: params.search,
                    dsName: "test"
                };
            },
            columns: [
                {field: 'ck', checkbox: true},
                {field: 'tableName', title: '表名', align: 'center'},
                {field: 'tableCollation', title: '字符集', align: 'center'},
                {field: 'tableComment', title: '注释', align: 'center'},
                {field: 'ENGINE', title: '引擎', align: 'center'},
                {field: 'createTime', title: '创建时间', align: 'center'},
                {field: 'action', title: '操作', clickToSelect: false, formatter: 'actionFormatter', align: 'center'}
            ]
        });
    })

    function genCode(idField, idValue) {
        var rows = [];
        if (idField != undefined && idValue != undefined) {
            rows.push({[idField]: idValue})
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
            add('/generator/codePage?' + "dsName=test" + "&tableName=" + rows[0].tableName)
        }
    }
</script>

</body>
</html>
