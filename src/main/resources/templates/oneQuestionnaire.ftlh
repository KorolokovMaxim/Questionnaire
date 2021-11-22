<#import "parts/common.ftlh" as c>
<@c.page>


    <h1>Название опроса - ${questionnaire.name}</h1>
    <form action="/saveUserAnswers" method="POST">
        <input type="hidden" value="${_csrf.token}" name="_csrf"/>
        <#list qam as key,value>

            <ul class="list-group">
                <li class="list-group-item">
                    <p class="question-lead"> ${key.getName()}</p>
                    <ul>
                        <#list value as val>


                            <li>
                                <div class="form-check">

                                    <input class="form-check-input answers-input" type="checkbox"
                                           name="queryAnswers[]"
                                           value="${val.id}"/>

                                    <label class="form-check-label" style="font-size: 22px">
                                        ${val.getName()}
                                    </label>
                                </div>
                            </li>
                        </#list>

                    </ul>
                </li>
            </ul>

        </#list>
        <input class="save_button" type="submit" value="Ответить">
    </form>

</@c.page>