<#import "../parts/common.ftlh" as c>
<@c.page>

    <h1>Все анткеты</h1>
    <table>
    <thead>
    <tr>
        <th>#</th>
        <th>NAME</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list questionnaires as q>
        <tr>
            <td>${q.id}</td>
            <td>${q.name}</td>
            <td><a href="/admin/questionnaires/${q.id}">Edit</a></td>
        </tr>
    </#list>
    </tbody>
    </table>

    <a href="/admin/questionnaires">Create</a>

</@c.page>