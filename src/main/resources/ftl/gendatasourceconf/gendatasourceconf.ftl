<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
    </button>
    <h4 class="modal-title" id="myModalLabel">
        <#if gendatasourceconf??>
            修改
        <#else>
            新增
        </#if>
    </h4>
</div>
<form id="saveForm">
    <input type="hidden" name="id" value="${(gendatasourceconf.id)!''}">
    <div class="modal-body">
        <div class="form-group">
            <label for="name">数据库名</label>
            <input type="text"
                   class="form-control" id="name" name="name" data-placement="top"
                   data-toggle="popover" data-trigger="focus"
                   value="${(gendatasourceconf.name)!''}">
        </div>
        <div class="form-group">
            <label for="url">数据源url</label>
            <input type="text"
                   class="form-control" id="url" name="url" data-placement="top"
                   data-toggle="popover" data-trigger="focus"
                   value="${(gendatasourceconf.url)!''}">
        </div>
        <div class="form-group">
            <label for="username">账户</label>
            <input type="text"
                   class="form-control" id="username" name="username" data-placement="top"
                   data-toggle="popover" data-trigger="focus"
                   value="${( gendatasourceconf.username)!''}">
        </div>
        <div class="form-group">
            <label for="password">密码</label>
            <input type="text"
                   class="form-control" id="password" name="password" data-placement="top"
                   data-toggle="popover" data-trigger="focus" >
        </div>

    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
        </button>
        <button type="button" class="btn btn-primary"
                onclick=" <#if gendatasourceconf??>addSubmit('/gendatasourceconf','put')<#else>addSubmit('/gendatasourceconf','post')</#if>;">
            提交
        </button>
    </div>

</form>
<script>


</script>
