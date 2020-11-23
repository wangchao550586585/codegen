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
                onclick="download()">
            提交
        </button>
    </div>

</form>
<script>
    function download() {
        var url = '/generator/code';
        var xhr = new XMLHttpRequest();
        xhr.open('post', url, true);
        xhr.setRequestHeader('content-type', 'application/json');
        xhr.responseType = "blob";
        xhr.onload = function () {
            if (this.status === 200) {
                $modal.modal('hide');
                $table.bootstrapTable('refresh');
                var blob = this.response;
                var name = xhr.getResponseHeader('Content-disposition');
                var filename = name.substring(21, name.length);
                var a = document.createElement('a');
                a.download =filename;
                a.href=window.URL.createObjectURL(blob);
                a.click();
            }
        };
        xhr.send(JSON.stringify($("#saveForm").serializeObject()))
    }

</script>
