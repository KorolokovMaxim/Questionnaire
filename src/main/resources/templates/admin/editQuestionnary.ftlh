<#import "../parts/common.ftlh" as c>
<@c.page>

    <h1>${questionnaire.get().name}</h1>


    <table class="table">
        <thead >
        <tr>

            <th>Вопросы</th>
            <th>Редактировать</th>
            <th style="color:darkred">Удалить</th>
        </tr>
        </thead>
        <tbody>
         <#list questions as quest>
            <tr>
                <td>${quest.name}</td>
                <td><a href="/admin/questionnaires/${questionnaire.get().id}/${quest.id}">Редактировать</a></td>
                <td>
                    <form action="/admin/deleteQuestion" method="POST">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <input type="hidden" name="questionnaire" value="${questionnaire.get().getId()}"/>
                        <input type="hidden" name="deleteQuestion" value="${quest.getId()}">
                        <input type="submit" value="Удалить">
                    </form>
                </td>
            </tr>
         </#list>
        </tbody>
    </table>

</@c.page>