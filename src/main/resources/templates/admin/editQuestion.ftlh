<#import "../parts/common.ftlh" as c>
<@c.page>

    <h2>Название опроса - ${questionnaire.getName()}</h2>

    <h1>Название вопроса - ${question.getName()}</h1>





    <div class="create-questionnaires">
        <form action="/admin/deleteAnswers" method="POST">
            <input type="hidden" value="${_csrf.token}" name="_csrf"/>
        <#list answers as answer>
            <br>
            <label>Ответ</label>
            <label><input  type="checkbox" name="answers[]" value="${answer.id}"> ${answer.name}</label>
        </#list>
            <input type="hidden" name="questionId" value="${question.id}"/>
            <input type="submit" value="Delete">
        </form>

        <button class="create-question">Добавить ответ</button>
        <form action="/admin/saveAnswers" method="POST">
            <input type="hidden" value="${_csrf.token}" name="_csrf">


            <div id="answers">
            </div>

            <input type="hidden" name="questionId" value="${question.id}">
            <input type="submit" value="Save">
        </form>
    </div>
    <script>
        const createQuestionBtn = document.querySelector('.create-question');
        const questions = document.querySelector('#answers');
        const form = document.querySelector('form');


        createQuestionBtn.addEventListener('click', () => {
            const wrapper = document.createElement('div');

            wrapper.classList.add('question-wrapper');


            const qIndex = createQuestion(wrapper);


            questions.appendChild(wrapper);
        });

        const getQuestionsCount = () => {
            return document.querySelectorAll('.question-wrapper').length;
        };


        const createQuestion = (wrapper) => {
            const questionWrapper = document.createElement('div');
            const questionLabel = document.createElement('label');
            const question = document.createElement('input');


            const cnt = getQuestionsCount();

            questionWrapper.classList.add('form-inputs');
            questionWrapper.classList.add('question');

            questionWrapper.dataset.id = '' + cnt + '';

            questionLabel.innerText = 'Ответ';
            question.type = 'text';
            question.name = 'answers[]';


            questionWrapper.appendChild(questionLabel);
            questionWrapper.appendChild(question);

            wrapper.appendChild(questionWrapper);

            return cnt;
        }
    </script>


</@c.page>