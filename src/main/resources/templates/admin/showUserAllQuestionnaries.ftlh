<#import "../parts/common.ftlh" as c>
<@c.page>

    <a href="/admin/showAdminUserAnswers/" class="accordion-button">Назад</a>

    <h1>Все анкеты пользователя</h1>
    <table class="table">
        <thead>
        <tr>
            <th>Номер</th>
            <th>Название</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list questionnaire as q>
            <tr>
                <td>${q.id}</td>
                <td>${q.name}</td>
                <td><a href="/admin/showAdminUserAnswers/${user.id}/${q.id}">Посмотреть</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>