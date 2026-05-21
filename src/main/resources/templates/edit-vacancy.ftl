<#import "company/templ-company.ftl" as t>

<@t.pages>
<div class="card shadow border-0 p-4">

    <div class="border-bottom pb-3 mb-4">
        <h2 class="fw-bold text-dark mb-1">Редагування вакансії</h2>
        <p class="text-muted mb-0">Внесіть необхідні зміни в умови або вимоги до посади</p>
    </div>

    <form method="post" action="/profile/company/vacancies/edit/${vacancy.id}">

        <div class="mb-3">
            <label for="title" class="form-label fw-bold">Назва посади</label>
            <input type="text" class="form-control" id="title" name="position"
                   value="${vacancy.position}" required placeholder="Наприклад: Java розробник">
        </div>

        <div class="mb-3">
            <label for="salary" class="form-label fw-bold">Заробітна плата (грн)</label>
            <div class="input-group">
                <input type="number" step="0.01" class="form-control" id="salary" name="salary"
                       value="${(vacancy.salary?c)!''}" placeholder="Наприклад: 25000">
                <span class="input-group-text">₴</span>
            </div>
            <div class="form-text">Залиште порожнім, якщо оплата за результатами співбесіди.</div>
        </div>

        <div class="mb-3">
            <label for="requirements" class="form-label fw-bold">Вимоги до кандидата</label>
            <textarea class="form-control" id="requirements" name="requirements" rows="5" required
                      placeholder="Опишіть навички...">${vacancy.requirements}</textarea>
        </div>

        <div class="mb-4">
            <label for="conditions" class="form-label fw-bold">Умови праці</label>
            <textarea class="form-control" id="conditions" name="conditions" rows="5" required
                      placeholder="Графік роботи...">${vacancy.conditions}</textarea>
        </div>

        <div class="d-flex gap-2 justify-content-end border-top pt-4">
            <a href="/profile/company/vacancies" class="btn btn-outline-secondary fw-bold px-4">Скасувати</a>
            <button type="submit" class="btn btn-primary fw-bold px-5">Зберегти зміни</button>
        </div>

    </form>
</div>
</@t.pages>