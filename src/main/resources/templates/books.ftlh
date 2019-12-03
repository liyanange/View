<DOCTYPE html>
 <html lang="en">
 <head>
     <meta charset="UTF-8">
     <title>图书列表</title>
 </head>
 <body>
 <@dic method="tagList" >
     <#if tagsList?exists && (tagsList?size > 0)>
         <#list tagsList as item>
             <li class="tag-li">
                 ${item.name?if_exists}
             </li>
         </#list>
     </#if>

 </@dic>
 <#macro greet>
     <font size="+2">Hello Joe!</font>
 </#macro>
 <@greet/>
    <table border="1">
        <tr>
            <td>图书编号<p></td>
            <td>图书名称</td>
            <td>图书作者</td>
        </tr>
<#--        这个俩问号可以判断这个变量不存在和为null两种情况 因为Freemaker不接受这两种 下面的list也得判断size>0-->
        <#if books??>
            aaaaaaaaa
      <#list books.list as book>
          <tr>
              <td>撒 ${book}</td>
          </tr>
          </#list>
        </#if>

    </table>
 <#include "/coythat.ftlh">


 <p>List of users:
     <#assign users = [{"name":"Joe",        "hidden":false},
     {"name":"James Bond", "hidden":true},
     {"name":"Julia",      "hidden":false}]>
 <ul>
     <#list users as user>
     <#if !user.hidden><br>
     <li>${user.name}<br>
         </#if><br>
         </#list><br>
 </ul><br>
 <p>That's all.
    </body>