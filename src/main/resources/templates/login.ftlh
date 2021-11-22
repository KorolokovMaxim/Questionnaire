<#import "parts/common.ftlh" as c>
<@c.page>

<div class="wrapper">


    <h2>ВХОД ДЛЯ АДМИНИСТРАТОРА</h2>
    <h3>username - admin</h3>
    <h3>password - admin</h3>
    <h3>страница входа - http://localhost:8080/admin</h3>

    <form action="/login" class="form" method="post">
        <div class="action">
            <span class="load show" id="login-action">ВОЙТИ</span>
        </div>
        <div class="login show-page">
            <label>
                <input  style="margin-top: 15px" type="text" name="username" placeholder="Ваше Имя"/>
            </label>
            <label>
                <input  style="margin-top: 15px" type="password" name="password" placeholder="Ваш Пароль"/>
            </label>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button  style="margin-top: 15px" type="submit">Войти</button>
            <a href="/registration"  style="margin-top: 15px">Зарегестрироваться</a>
        </div>
    </form>

</div>
</@c.page>
