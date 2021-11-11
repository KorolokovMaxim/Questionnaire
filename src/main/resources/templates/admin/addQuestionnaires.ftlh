<#import "../parts/common.ftlh" as c>
<@c.page>

    <form action="/admin/saveQuestionnaires" method="post">
        <label for="questionnaire"> Название опроса    </label>
            <input type="text" name="questionnaireName" placeholder="Введите названия вопроса" >

<#--        <p id="createQuestion">Создать ворос</p>-->

<#--TODO-->
<#--        Обработать НОВУЮ форму через ajax и сохранить ее значение в бд-->
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <input type="submit" value="Save">
    </form>


    <script>
        let createQuestion = document.getElementById('createQuestion');
        createQuestion.onclick = function (){
            let  newElem = document.createElement( "input" );
            newElem.type = "text";
            newElem.name="questions";
            document.body.appendChild( newElem );
        }


    </script>
</@c.page>