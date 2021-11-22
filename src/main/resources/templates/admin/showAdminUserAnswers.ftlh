<#import "../parts/common.ftlh" as c>
<@c.page>

    <a href="/admin/" class="accordion-button">Назад</a>

    <h1>Все пользователи</h1>
    <table class="table">
        <thead>
        <tr>

            <th>Имя</th>
            <th></th>
            <th></th>

        </tr>
        </thead>
        <tbody>

        <#list users as user>

            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td><a href="/admin/showAdminUserAnswers/${user.id}">Пройденные опросы</a></td>
            </tr>
        </#list>



        </tbody>
    </table>


</@c.page>