使用方法：
1.将jar包导入lib文件夹下
2.将config.properties导入bin文件夹下,根据需要更改
3.将webapp内任意工程的webinfo下web.xml配置监听器
<listener>
    <listener-class>com.version2.deal.TomcatStatusListener</listener-class>
 </listener>
4.开启tomcat(非安装版)

---------新方法
直接覆盖到tomcat目录下
配置信息在bin下的config.properties