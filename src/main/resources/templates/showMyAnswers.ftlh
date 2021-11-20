<#import "parts/common.ftlh" as c>
<@c.page>


    <h1>Название опроса - ${questionnaire.name}</h1>


<#list viewUserAnswers as val>
            <ul class="list-group" >

                ${val.getQuestion().getName()}
                <li class="list-group-item">

                    <ul>

                        <#list val.getAnswers() as answer>
                            <li>${answer.name}</li>
                        </#list>

                    </ul>
                </li>

            </ul>
</#list>




</@c.page>