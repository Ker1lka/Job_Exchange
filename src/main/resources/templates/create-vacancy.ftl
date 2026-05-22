<#import "company/templ-company.ftl" as p>
<@p.pages>
<div class="card shadow border-0 p-4">

    <div class="border-bottom pb-3 mb-4">
        <h2 class="fw-bold text-dark mb-1">Створення нової вакансії</h2>
        <p class="text-muted mb-0">Заповніть деталі, щоб залучити найкращих фахівців</p>
    </div>

    <#-- Форма створення вакансії -->
    <form method="post" action="/profile/company/vacancies/create">

        <#-- Поле: Назва посади -->
        <div class="mb-3">
            <label for="title" class="form-label fw-bold">Назва посади</label>
            <input type="text" class="form-control" id="title" name="position" required
                   placeholder="Наприклад: Менеджер з продажу, Java розробник...">
        </div>

        <#-- Поле: Заробітна плата -->
        <div class="mb-3">
            <label for="salary" class="form-label fw-bold">Заробітна плата (грн)</label>
            <div class="input-group">
                <input type="number" step="0.01" class="form-control" id="salary" name="salary"
                       placeholder="Наприклад: 25000">
                <span class="input-group-text">₴</span>
            </div>
            <div class="form-text">Залиште порожнім, якщо оплата за результатами співбесіди.</div>
        </div>

        <#-- Поле: Вимоги до кандидата -->
        <div class="mb-3">
            <label for="requirements" class="form-label fw-bold">Вимоги до кандидата</label>
            <textarea class="form-control" id="requirements" name="requirements" rows="5" required
                      placeholder="Опишіть необхідні навички, освіту та досвід роботи..."></textarea>
        </div>

        <#-- Поле: Умови праці -->
        <div class="mb-4">
            <label for="conditions" class="form-label fw-bold">Умови праці</label>
            <textarea class="form-control" id="conditions" name="conditions" rows="5" required
                      placeholder="Графік роботи, локація офісу, соціальний пакет, надання житла..."></textarea>
        </div>

        <#-- Поле: Житлові умови -->
        <div class="mb-3">
            <label for="housingConditions" class="form-label fw-bold">Житлові умови</label>
            <textarea class="form-control" id="housingConditions" name="housingConditions" rows="5" required
                      placeholder="Наприклад: Надається гуртожиток / Не надається"></textarea>
        </div>

        <#-- Кнопки керування формою (Скасувати / Надіслати) -->
        <div class="d-flex gap-2 justify-content-end border-top pt-4">
            <a href="/profile/company/vacancies" class="btn btn-outline-secondary fw-bold px-4">Скасувати</a>
            <button type="submit" class="btn btn-success fw-bold px-5">Опублікувати вакансію</button>
        </div>

    </form>
</div>
</@p.pages>