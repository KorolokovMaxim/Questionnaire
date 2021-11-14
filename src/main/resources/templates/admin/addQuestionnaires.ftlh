<#import "../parts/common.ftlh" as c>
<@c.page>
    <div class="create-questionnaires">
        <button class="create-question">Добавть влпрос</button>
        <form action="/admin/saveQuestionnaires" method="POST">
            <input type="hidden" value="${_csrf.token}" name="_csrf">

            <div class="form-inputs">
                <label for="questionnaire">Название опроса</label>
                <input type="text" name="questionnaireName" placeholder="Введите названия вопроса">
            </div>
            <div id="questions"></div>

            <input type="submit" value="Save">
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
            const questionLabel = document.createElement('label');
            const question = document.createElement('input');


            const cnt = getQuestionsCount();

            questionWrapper.classList.add('form-inputs');
            questionWrapper.classList.add('question');

            questionWrapper.dataset.id = '' + cnt + '';

            questionLabel.innerText = 'Вопрос';
            question.type = 'text';
            question.name = 'questions[]';


            questionWrapper.appendChild(questionLabel);
            questionWrapper.appendChild(question);

            wrapper.appendChild(questionWrapper);

            return cnt;
        }
    </script>
</@c.page>