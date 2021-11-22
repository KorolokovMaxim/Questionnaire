<#import "parts/common.ftlh" as c>
<@c.page>

    <table class="table">
        <thead >
        <tr>
            <th>Название</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list questionnaires as q>
        <tr>
                <th>${q.name}</th>
            <td><a href="/questionnaire/${q.id}">Пройти</a></td>
            <td><a href="/showMyAnswers/${q.id}">Посмотреть ответы</a></td>

        </tr>
        </#list>
        </tbody>
    </table>

</@c.page>