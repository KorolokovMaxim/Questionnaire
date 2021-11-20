<#import "parts/common.ftlh" as c>
<@c.page>


<div class="wrapper">
    <form action="/registration" class="form" method="post">
        <div class="action">
            <span class="load show" id="login-action">ЗАРЕГЕСТРИРОВАТЬСЯ</span>
        </div>
        <div class="login show-page">
            <label>
                <input  style="margin-top: 15px" type="text" name="username" placeholder="Ваше Имя"/>
            </label>
            <label>
                <input  style="margin-top: 15px" type="password" name="password" placeholder="Ваш Пароль"/>
            </label>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button  style="margin-top: 15px" type="submit">Зарегестрироваться</button>
            <a href="/login"  style="margin-top: 15px">Войти</a>
        </div>
    </form>
</div>
</@c.page>
