<#import "../parts/common.ftlh" as c>
<@c.page>

    <h2 class="align-items-center">Название опроса - ${questionnaire.getName()}</h2>

    <h1 class="align-items-center">Название вопроса - ${question.getName()}</h1>



    <div class="create-questionnaires">

        <a href="/admin/questionnaires/${questionnaire.id}" class="accordion-button">Вернуться</a>
    <ul class="list-group">
        <table class="table">
            <thead>
            <tr>
                <th>Название</th>
                <th>Удалить</th>
            </tr>
            </thead>
            <tbody>
            <#list answers as answer>
                <tr>
                    <td>${answer.name}</td>
                    <td>
                        <form action="/admin/deleteAnswers" method="POST">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <input type="hidden" name="answers[]" value="${answer.getId()}"/>
                            <input type="hidden" name="questionId" value="${question.id}"/>
                            <input type="submit" value="Удалить">
                        </form>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>

    </ul>




    <button class="create-question">Добавить ответы</button>
    <form action="/admin/saveAnswers" method="POST">
        <input type="hidden" value="${_csrf.token}" name="_csrf">


        <div id="answers">
        </div>

        <input type="hidden" name="questionId" value="${question.id}">
        <input class="save_button" type="submit" value="Сохранить">
    </form>
    </div>

    <script>
        const createQuestionBtn = document.querySelector('.create-question');
        const questions = document.querySelector('#answers');
        const form = document.querySelector('form');
        const deleteBtn = document.createElement('div');


        createQuestionBtn.addEventListener('click', () => {
            const wrapper = document.createElement('div');
            wrapper.classList.add('question-wrapper');
            deleteBtn.classList.add('delete-button');


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
            const deleteBtn = document.createElement('div');



            const cnt = getQuestionsCount();

            questionWrapper.classList.add('form-inputs');
            questionWrapper.classList.add('question');
            questionWrapper.classList.add('input-group');
            questionWrapper.classList.add('mb-3');
            questionLabel.classList.add('input-group-text');
            question.classList.add('form-control');
            deleteBtn.classList.add('delete-button');

            questionWrapper.dataset.id = '' + cnt + '';

            deleteBtn.innerText = '+';
            questionLabel.innerText = 'Ответ';
            question.type = 'text';
            question.name = 'answers[]';

            deleteBtn.addEventListener('click', () => {

                if (wrapper.querySelectorAll('.question').length === 1) {
                    wrapper.remove();
                    return;
                }

                questionWrapper.remove();
            })

            questionWrapper.appendChild(questionLabel);
            questionWrapper.appendChild(question);
            questionWrapper.appendChild(deleteBtn);
            wrapper.appendChild(questionWrapper);

            return cnt;
        }

    </script>


</@c.page>