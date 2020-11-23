<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <#include "./base.ftl">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户权限管理系统</title>

    <link href="resources/plugins/fullPage/jquery.fullPage.css" rel="stylesheet"/>
    <link href="resources/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="resources/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
    <link href="resources/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
    <link href="resources/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" rel="stylesheet"/>
    <link href="resources/css/admin.css" rel="stylesheet"/>
    <style>
        /** skins **/
        #zheng-upms-server #header {background: #29A176;}
        #zheng-upms-server .content_tab{background: #29A176;}
        #zheng-upms-server .s-profile>a{background: url(resources/images/zheng-upms.png) left top no-repeat;}

        #zheng-cms-admin #header {background: #455EC5;}
        #zheng-cms-admin .content_tab{background: #455EC5;}
        #zheng-cms-admin .s-profile>a{background: url(resources/images/zheng-cms.png) left top no-repeat;}

        #zheng-pay-admin #header {background: #F06292;}
        #zheng-pay-admin .content_tab{background: #F06292;}
        #zheng-pay-admin .s-profile>a{background: url(resources/images/zheng-pay.png) left top no-repeat;}

        #zheng-ucenter-home #header {background: #6539B4;}
        #zheng-ucenter-home .content_tab{background: #6539B4;}
        #zheng-ucenter-home .s-profile>a{background: url(resources/images/zheng-ucenter.png) left top no-repeat;}

        #zheng-oss-web #header {background: #0B8DE5;}
        #zheng-oss-web .content_tab{background: #0B8DE5;}
        #zheng-oss-web .s-profile>a{background: url(resources/images/zheng-oss.png) left top no-repeat;}

        #test #header {background: test;}
        #test .content_tab{background: test;}
        #test .s-profile>a{background: url(test) left top no-repeat;}
    </style>
</head>
<body>
<header id="header">
    <ul id="menu">
        <li id="guide" class="line-trigger">
            <div class="line-wrap">
                <div class="line top"></div>
                <div class="line center"></div>
                <div class="line bottom"></div>
            </div>
        </li>
        <li id="logo" class="hidden-xs">
            <a href="index">
                <img src="resources/images/logo.png"/>
            </a>
            <span id="system_title">权限管理系统</span>
        </li>
        <li class="pull-right">
            <ul class="hi-menu">
                <!-- 搜索 -->
                <li class="dropdown">
                    <a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
                        <i class="him-icon zmdi zmdi-search"></i>
                    </a>
                    <ul class="dropdown-menu dm-icon pull-right">
                        <form id="search-form" class="form-inline">
                            <div class="input-group">
                                <input id="keywords" type="text" name="keywords" class="form-control" placeholder="搜索"/>
                                <div class="input-group-btn">
                                    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                                </div>
                            </div>
                        </form>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
                        <i class="him-icon zmdi zmdi-dropbox"></i>
                    </a>
                    <ul class="dropdown-menu dm-icon pull-right">
                        <li class="skin-switch">
                            请选择系统切换
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="waves-effect switch-systems" href="javascript:;" systemid="1" systemname="zheng-upms-server" systemtitle="权限管理系统"><i class="zmdi zmdi-shield-security"></i> 权限管理系统</a>
                        </li>

                        <li>
                            <a class="waves-effect switch-systems" href="javascript:;" systemid="2" systemname="zheng-cms-admin" systemtitle="内容管理系统"><i class="zmdi zmdi-wikipedia"></i> 内容管理系统</a>
                        </li>

                        <li>
                            <a class="waves-effect switch-systems" href="javascript:;" systemid="3" systemname="zheng-pay-admin" systemtitle="支付管理系统"><i class="zmdi zmdi-paypal-alt"></i> 支付管理系统</a>
                        </li>

                        <li>
                            <a class="waves-effect switch-systems" href="javascript:;" systemid="4" systemname="zheng-ucenter-home" systemtitle="用户管理系统"><i class="zmdi zmdi-account"></i> 用户管理系统</a>
                        </li>

                        <li>
                            <a class="waves-effect switch-systems" href="javascript:;" systemid="5" systemname="zheng-oss-web" systemtitle="存储管理系统"><i class="zmdi zmdi-cloud"></i> 存储管理系统</a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
                        <i class="him-icon zmdi zmdi-more-vert"></i>
                    </a>
                    <ul class="dropdown-menu dm-icon pull-right">
                        <li class="hidden-xs">
                            <a class="waves-effect" data-ma-action="fullscreen" href="javascript:fullPage();"><i class="zmdi zmdi-fullscreen"></i> 全屏模式</a>
                        </li>
                        <li>
                            <a class="waves-effect" data-ma-action="clear-localstorage" href="javascript:;"><i class="zmdi zmdi-delete"></i> 清除缓存</a>
                        </li>
                        <li>
                            <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-face"></i> 隐私管理</a>
                        </li>
                        <li>
                            <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-settings"></i> 系统设置</a>
                        </li>
                        <li>
                            <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-run"></i> 退出登录</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </li>
    </ul>
</header>
<section id="main">
    <!-- 左侧导航区 -->
    <aside id="sidebar">
        <!-- 个人资料区 -->
        <div class="s-profile">
            <a class="waves-effect waves-light" href="javascript:;">
                <div class="sp-pic">
                    <img src="resources/images/avatar.jpg"/>
                </div>
                <div class="sp-info">
                    wangchao，您好！
                    <i class="zmdi zmdi-caret-down"></i>
                </div>
            </a>
            <ul class="main-menu">
                <li>
                    <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-account"></i> 个人资料</a>
                </li>
                <li>
                    <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-face"></i> 隐私管理</a>
                </li>
                <li>
                    <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-settings"></i> 系统设置</a>
                </li>
                <li>
                    <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-run"></i> 退出登录</a>
                </li>
            </ul>
        </div>
        <!-- /个人资料区 -->
        <!-- 菜单区 -->
        <ul class="main-menu">
            <li>
                <a class="waves-effect" href="javascript:Tab.addTab('首页', 'home');"><i class="zmdi zmdi-home"></i> 首页</a>
            </li>
            <li class="sub-menu system_menus system_1 3">
                <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-accounts"></i> 代码生成器</a>
                <ul>
                    <li><a class="waves-effect" href="javascript:Tab.addTab('数据源管理', '/gendatasourceconf/index');">数据源管理</a></li>
                    <li><a class="waves-effect" href="javascript:Tab.addTab('构建代码', '/generator/index');">构建代码</a></li>
                </ul>
            </li>
            <li>
                <div class="upms-version">
                    &copy; wangchao
                </div>
            </li>
        </ul>
        <!-- /菜单区 -->
    </aside>
    <!-- /左侧导航区 -->
    <section id="content">
        <div class="content_tab">
            <div class="tab_left">
                <a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-left"></i></a>
            </div>
            <div class="tab_right">
                <a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-right"></i></a>
            </div>
            <ul id="tabs" class="tabs">
                <li id="tab_home" data-index="home" data-closeable="false" class="cur">
                    <span class="waves-effect waves-light">首页</span>
                </li>
            </ul>
        </div>
        <div class="content_main">
            <div id="iframe_home" class="iframe cur">
                <p><h4>我是首页</h4></p>
            </div>
        </div>
    </section>
</section>
<footer id="footer"></footer>

<script src="resources/plugins/jquery.1.12.4.min.js"></script>
<script src="resources/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="resources/plugins/waves-0.7.5/waves.min.js"></script>
<script src="resources/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="resources/plugins/BootstrapMenu.min.js"></script>
<script src="resources/plugins/device.min.js"></script>
<script src="resources/plugins/fullPage/jquery.fullPage.min.js"></script>
<script src="resources/plugins/fullPage/jquery.jdirk.min.js"></script>
<script src="resources/plugins/jquery.cookie.js"></script>
<script src="resources/js/admin.js"></script>


</body>
</html>