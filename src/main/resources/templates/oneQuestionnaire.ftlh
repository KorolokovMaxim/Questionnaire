<#import "parts/common.ftlh" as c>
<@c.page>


    <h1>Название опроса - ${questionnaire.name}</h1>

    <#list questions as q>
        ${q.getName()}
    </#list>

    <br><br><br>

    <#list qam as key,value>


   ${key.getName()} = <#list value as val> ${val.getName()}</#list>


    </#list>


<#--    <#list answers as a>-->
<#--        ${a.getName()}-->
<#--    </#list>-->

</@c.page>