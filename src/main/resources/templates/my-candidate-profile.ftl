<#import "candidate/templ-candidate.ftl" as p>
<@p.pages>
    <div class="container my-5">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="card shadow border-0 p-4">

                    <div class="d-flex justify-content-between align-items-center border-bottom pb-3 mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">
                                <#if candidate.lastName?? || candidate.firstName?? || candidate.middleName??>
                                    ${(candidate.lastName)!''} ${(candidate.firstName)!''} ${(candidate.middleName)!''}
                                <#else>
                                    Новий кандидат (Ім'я не вказано)
                                </#if>
                            </h2>
                            <span class="badge bg-success">Статус: Шукає роботу</span>
                        </div>
                        <a href="/profile/candidate/edit" class="btn btn-primary fw-bold px-4">
                            <i class="bi bi-pencil-square"></i> Редагувати профіль
                        </a>
                    </div>

                    <div class="mb-4">
                        <h5 class="fw-bold text-secondary mb-3"><i class="bi bi-person-card"></i> Особисті дані</h5>
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
                        <h5 class="fw-bold text-secondary mb-3"><i class="bi bi-briefcase"></i> Професійний досвід</h5>
                        <div class="row g-3 bg-light p-3 rounded">
                            <div class="col-12 mb-2">
                                <p class="mb-0"><strong>Бажана професія:</strong> <span class="text-primary fw-bold">${(candidate.profession)!"Не вказано"}</span></p>
                            </div>
                            <div class="col-md-6">
                                <p class="mb-2"><strong>Останнє місце роботи:</strong> ${(candidate.lastJobPlace)!"Немає досвіду / Не вказано"}</p>
                                <p class="mb-0"><strong>Посада:</strong> ${(candidate.lastJobPosition)!"Не вказано"}</p>
                            </div>
                            <div class="col-md-6">
                                <p class="mb-0"><strong>Причина звільнення:</strong> ${(candidate.leavingReason)!"Не вказано"}</p>
                            </div>
                        </div>
                    </div>

                    <div class="mb-4">
                        <h5 class="fw-bold text-secondary mb-3"><i class="bi bi-mortarboard"></i> Освіта</h5>
                        <div class="row g-3 bg-light p-3 rounded">
                            <div class="col-md-6">
                                <p class="mb-2"><strong>Навчальний заклад:</strong> ${(candidate.institution)!"Не вказано"}</p>
                                <p class="mb-0"><strong>Спеціалізація:</strong> ${(candidate.specialization)!"Не вказано"}</p>
                            </div>
                            <div class="col-md-6">
                                <p class="mb-2"><strong>Ступінь:</strong> ${(candidate.degree)!"Не вказано"}</p>
                                <p class="mb-0"><strong>Роки навчання:</strong> ${(candidate.educationYears)!"Не wказано"}</p>
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <h5 class="fw-bold text-secondary mb-3"><i class="bi bi-sliders"></i> Вимоги до майбутньої роботи</h5>
                        <div class="p-3 bg-warning bg-opacity-10 border border-warning border-opacity-25 rounded text-dark">
                            <p class="mb-0" style="white-space: pre-line;">${(candidate.jobRequirements)!"Вимоги ще не сформульовані. Додайте інформацію про бажану зарплату чи графік!"}</p>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</@p.pages>