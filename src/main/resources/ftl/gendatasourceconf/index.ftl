<!DOCTYPE html>
<head>
    <#include "../base.ftl">
</head>
<body>

<div id="main">
    <div id="toolbar">
        <button class="waves-effect waves-button" onclick="add('/gendatasourceconf/save')">新增</button>
        <button class="waves-effect waves-button" onclick="update()">修改</button>
        <button class="waves-effect waves-button" onclick="del()">删除</button>
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
            url: '/gendatasourceconf/page',
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
                    current: params.offset / params.limit+params.offset % params.limit+1,
                    size: params.limit,
                    name: params.search
                };
            },
            columns: [
                {field: 'ck', checkbox: true},
                {field: 'id', title: '编号', align: 'center'},
                {field: 'name', title: '数据库名', align: 'center'},
                {field: 'url', title: '数据源url', align: 'center', formatter: 'urlSubString'},
                {field: 'username', title: '账户', align: 'center'},
                {field: 'createDate', title: '创建时间', align: 'center'},
                {field: 'action', title: '操作', clickToSelect: false, formatter: 'actionFormatter', align: 'center'}
            ]
        });
    })

    function urlSubString(value, row, index) {
        return '<span class="label label-success">' + value.substring(0, 60) + '...</span>';
    }

    function update(idField, idValue) {
        var rows = [];
        if (idField != undefined && idValue != undefined) {
            rows.push({[idField]: idValue})
        } else {
            rows = $table.bootstrapTable('getSelections');
        }
        if (rows.length != 1) {
            $.confirm({
                title: false,
                content: '请选择一条记录!',
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
            $(".modal-content").load("/gendatasourceconf/update/" + rows[0].id);
            $modal = $('#modal');
            $modal.modal({
                show: true,
                backdrop: false,
                keyboard: false
            });
        }
    }

    function del(idField, idValue) {
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
            var deleteDialog = $.confirm({
                title: false,
                animationSpeed: 300,
                content: '确认删除该设备吗？',
                type: 'red',
                buttons: {
                    confirm: {
                        text: '确定',
                        btnClass: 'waves-effect waves-button',
                        action: function () {
                            var ids = new Array();
                            for (var i in rows) {
                                ids.push(rows[i].id);
                            }
                            $.ajax({
                                type: "delete",
                                url: '/gendatasourceconf/' + ids.join("_"),
                                success: function () {
                                    deleteDialog.close();
                                    $table.bootstrapTable('refresh');
                                },
                                error: function (XMLHttpRequest, textStatus) {
                                    $.confirm({
                                        theme: 'dark',
                                        animation: 'rotateX',
                                        closeAnimation: 'rotateX',
                                        title: false,
                                        content: '服务器内部错误,请重新尝试' + textStatus,
                                        buttons: {
                                            confirm: {
                                                text: '确认',
                                                btnClass: 'waves-effect waves-button waves-light'
                                            }
                                        }
                                    })
                                }

                            })
                        }
                    },
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        }
    }
</script>

</body>
</html>
