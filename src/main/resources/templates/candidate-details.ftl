<#import "company/templ-company.ftl" as p>
<@p.pages>

<div class="card shadow border-0 p-4">
    <div class="border-bottom pb-3 mb-4">
        <h2 class="fw-bold text-dark mb-1">
            <#if candidate.lastName?? || candidate.firstName?? || candidate.middleName??>
                ${(candidate.lastName)!''} ${(candidate.firstName)!''} ${(candidate.middleName)!''}
            <#else>
                Анонімний кандидат (Ім'я не вказано)
            </#if></h2>
        <h4 class="text-primary fw-semibold">${(candidate.profession)!"Професія не вказана"}</h4>
    </div>

    <div class="mb-4">
        <h5 class="fw-bold text-secondary mb-2">Особиста інформація</h5>
        <div class="row g-3 bg-light p-3 rounded">
            <div class="col-md-6">
                <p class="mb-2"><strong>Контакти:</strong> ${(candidate.contactInfo)!"Не вказано"}</p>
                <p class="mb-0"><strong>Сімейний стан:</strong> ${(candidate.familyStatus)!"Не вказано"}</p>
            </div>
            <div class="col-md-6">
                <p class="mb-0"><strong>Житлові умови:</strong> ${(candidate.housingConditions)!"Не вказано"}</p>
            </div>
        </div>
    </div>

    <div class="mb-4">
        <h5 class="fw-bold text-secondary mb-2">Минуле місце роботи</h5>
        <div class="row g-3 bg-light p-3 rounded">
            <div class="col-md-6">
                <p class="mb-2"><strong>Компанія:</strong> ${(candidate.lastJobPlace)!"Не вказано / Немає"}</p>
                <p class="mb-0"><strong>Посада:</strong> ${(candidate.lastJobPosition)!"Не вказано"}</p>
            </div>
            <div class="col-md-6">
                <p class="mb-0"><strong>Причина звільнення:</strong> ${(candidate.leavingReason)!"Не вказано"}</p>
            </div>
        </div>
    </div>

    <div class="mb-4">
        <h5 class="fw-bold text-secondary mb-2">Освіта</h5>
        <div class="row g-3 bg-light p-3 rounded">
            <div class="col-md-6">
                <p class="mb-2"><strong>Заклад:</strong> ${(candidate.institution)!"Не вказано"}</p>
                <p class="mb-0"><strong>Спеціалізація:</strong> ${(candidate.specialization)!"Не вказано"}</p>
            </div>
            <div class="col-md-6">
                <p class="mb-2"><strong>Ступінь:</strong> ${(candidate.degree)!"Не вказано"}</p>
                <p class="mb-0"><strong>Роки навчання:</strong> ${(candidate.educationYears)!"Не вказано"}</p>
            </div>
        </div>
    </div>

    <div class="mb-4">
        <h5 class="fw-bold text-secondary mb-2">Вимоги кандидата до майбутньої роботи</h5>
        <div class="p-3 bg-warning bg-opacity-10 border border-warning border-opacity-25 rounded text-dark">
            <p class="mb-0" style="white-space: pre-line;">${(candidate.jobRequirements)!"Особливих вимог не вказано."}</p>
        </div>
    </div>

    <div class="mt-4 p-4 bg-white border rounded shadow-sm">
        <h5 class="fw-bold text-dark mb-3">Запропонувати вакансію цьому кандидату</h5>

        <form method="post" action="/company/candidates/${candidate.id}/invite">
            <div class="mb-3">
                <label class="form-label text-muted small">Оберіть одну з ваших активних вакансій:</label>
                <select name="vacancyId" class="form-select" required>
                    <option value="" disabled selected>-- Виберіть вакансію --</option>
                    <#list myVacancies as vacancy>
                        <option value="${vacancy.id}">${vacancy.position} (оплата: ${vacancy.salary})</option>
                    </#list>
                </select>
            </div>
            <div class="d-flex gap-2">
                <button type="submit" class="btn btn-success fw-bold px-4">Надіслати пропозицію</button>
                <button type="button" class="btn btn-outline-secondary" onclick="window.print()">Друк анкети</button>
            </div>
        </form>
    </div>
</div>
</@p.pages>