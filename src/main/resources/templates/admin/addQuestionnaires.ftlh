<#import "../parts/common.ftlh" as c>
<@c.page>
    <div class="create-questionnaires">

        <a href="/admin/" class="accordion-button">Назад</a>

        <button class="create-question">Добавить вопросы</button>
        <form action="/admin/saveQuestionnaires" method="POST">
            <input type="hidden" value="${_csrf.token}" name="_csrf">

            <div class="input-group input-group-lg">
                <span class="input-group-text" id="inputGroup-sizing-lg">Название опроса</span>
                <input type="text" name="questionnaireName" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-lg">
            </div>
            <div id="questions"></div>

            <input class="save_button" type="submit" value="Save">
        </form>
    </div>
    <script>
        const createQuestionBtn = document.querySelector('.create-question');
        const questions = document.querySelector('#questions');
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
            const questionLabel = document.createElement('span');
            const question = document.createElement('input');
            const deleteBtn = document.createElement('div');


            const cnt = getQuestionsCount();

            questionWrapper.classList.add('form-inputs');
            questionWrapper.classList.add('input-group');
            questionWrapper.classList.add('mb-3');
            questionWrapper.classList.add('question');
            questionLabel.classList.add('input-group-text');
            question.classList.add('form-control');
            deleteBtn.classList.add('delete-button');

            questionWrapper.dataset.id = '' + cnt + '';


            deleteBtn.innerText = '+';
            questionLabel.innerText = 'Вопрос';
            question.type = 'text';
            question.name = 'questions[]';

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