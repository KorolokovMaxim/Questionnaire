<#import "../parts/common.ftlh" as c>
<@c.page>

    <h1>${questionnaire.get().name}</h1>




    <table>
        <thead>
        <tr>
            <th>Вопросы</th>
        </tr>
        </thead>
        <tbody>
         <#list questions as quest>
            <tr>
                <td>${quest.name}</td>
                <td><a href="/admin/questionnaires/${questionnaire.get().id}/${quest.id}">Edit</a></td>
            </tr>
         </#list>
        </tbody>
    </table>

</@c.page>