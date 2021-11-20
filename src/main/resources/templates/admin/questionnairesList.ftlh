<#import "../parts/common.ftlh" as c>
<@c.page>

    <h1>Все анткеты</h1>
    <table class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>NAME</th>
        <th>Редактировать</th>
        <th>Удалить</th>
    </tr>
    </thead>
    <tbody>
    <#list questionnaires as q>
        <tr>
            <td>${q.id}</td>
            <td>${q.name}</td>
            <td><a href="/admin/questionnaires/${q.id}">Редактировать</a></td>
            <td>
                <form action="/admin/deleteQuestionnaire" method="POST">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <input type="hidden" name="questionnaire" value="${q.getId()}"/>
                    <input class="delete-button" type="submit" value="Удалить">
                </form></td>
        </tr>
    </#list>
    </tbody>
    </table>

    <a href="/admin/questionnaires">Создать Опрос</a>

</@c.page>