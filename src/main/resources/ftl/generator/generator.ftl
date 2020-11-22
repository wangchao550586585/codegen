<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
    </button>
    <h4 class="modal-title" id="myModalLabel">
            新增
    </h4>
</div>
<form id="saveForm">
    <input type="hidden" name="tableName" value="${(tableName)!''}">
    <input type="hidden" name="dsName" value="${(dsName)!''}">
    <div class="modal-body">
        <div class="form-group">
            <label for="author">作者</label>
            <input type="text"
                   class="form-control" id="author" name="author" data-placement="top"
                   data-toggle="popover" data-trigger="focus"
                   value="">
        </div>
        <div class="form-group">
            <label for="packageName">包名</label>
            <input type="text"
                   class="form-control" id="packageName" name="packageName" data-placement="top"
                   data-toggle="popover" data-trigger="focus"
                   value="">
        </div>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
        </button>
        <button type="button" class="btn btn-primary"
                onclick="getCode('/generator/code')">
            提交
        </button>
    </div>

</form>
<script>
    function getCode(url) {
        console.log(JSON.stringify($("#saveForm").serializeObject()));
        $.ajax({
            type: "post",
            url: url,
            data: JSON.stringify($("#saveForm").serializeObject()),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function () {
                $modal.modal('hide');
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
</script>
