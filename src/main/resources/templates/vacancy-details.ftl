<#import "candidate/templ-candidate.ftl" as t>

<@t.pages>
    <div class="container py-3">

        <div class="mb-4">
            <a href="/candidate/vacancies" class="btn btn-link text-secondary text-decoration-none p-0">
                <i class="bi bi-arrow-left"></i> Назад до списку вакансій
            </a>
        </div>

        <div class="card shadow border-0 rounded-4 overflow-hidden mb-4">
            <div class="card-body p-5">

                <div class="d-flex justify-content-between align-items-start border-bottom pb-4 mb-4">
                    <div>
                        <span class="badge bg-primary px-3 py-2 rounded-pill mb-2">Активна вакансія</span>
                        <h1 class="fw-bold text-dark display-6 mb-2">${(vacancy.position)!''}</h1>
                        <h4 class="text-secondary fw-semibold">
                            <i class="bi bi-building me-2"></i>${(vacancy.company.name)!''}
                        </h4>
                    </div>
                    <div class="text-end">
                        <span class="text-muted small">Пропонована оплата</span>
                        <h2 class="fw-bold text-success mt-1">
                            <#if vacancy.salary??>
                                ${vacancy.salary?string(",##0.00")} ₴
                            <#else>
                                По результатам співбесіди
                            </#if>
                        </h2>
                    </div>
                </div>

                <div class="row g-4 mb-5">
                    <div class="col-md-6">
                        <div class="p-4 bg-light rounded-4 h-100 shadow-sm border-start border-4 border-warning">
                            <h5 class="fw-bold text-dark mb-3">
                                <i class="bi bi-file-earmark-person me-2 text-warning">Вимоги до кандидата</i>
                            </h5>
                            <p class="text-secondary style-text" style="white-space: pre-line; line-height: 1.7;">
                                ${(vacancy.requirements)!''}
                            </p>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="p-4 bg-light rounded-4 h-100 shadow-sm border-start border-4 border-info">
                            <h5 class="fw-bold text-dark mb-3">
                                <i class="bi bi-briefcase me-2 text-info">Умови праці та бенефіти</i>
                            </h5>
                            <p class="text-secondary style-text" style="white-space: pre-line; line-height: 1.7;">
                                ${(vacancy.conditions)!''}
                            </p>
                        </div>
                    </div>
                </div>

                <div class="p-4 rounded-4 bg-dark text-white mb-5">
                    <h5 class="fw-bold mb-2"><i class="bi bi-info-circle me-2">Про роботодавця:</i></h5>
                    <p class="mb-1 text-light opacity-75"><strong>Юридична адреса:</strong> ${(vacancy.company.address)!''}</p>
                    <p class="mb-0 text-light opacity-75"><strong>Контакти HR / Менеджера:</strong> ${(vacancy.company.contactInfo)!''}</p>
                </div>

                <div class="d-flex justify-content-center border-top pt-4">
                    <#if application??>
                        <#if application.status == "PENDING">
                            <div class="alert alert-warning text-center fw-bold px-5 py-3 rounded-pill mb-0 shadow-sm">
                                <i class="bi bi-clock-history me-2"></i>
                                <#if application.initiatedBy == "COMPANY">
                                    Ви отримали запрошення! Перевірте розділ "Пропозиції та відгуки" для відповіді.
                                <#else>
                                    Ви вже відгукнулися на цю вакансію. Очікуйте на відповідь роботодавця.
                                </#if>
                            </div>

                            <#elseif application.status == "ACCEPTED">
                            <div class="alert alert-success text-center fw-bold px-5 py-3 rounded-pill mb-0 shadow-sm">
                                <i class="bi bi-check-circle-fill me-2">Ви прийняли цю пропозицію! З вами зв'яжуться.</i>
                            </div>

                            <#elseif application.status == "REJECTED">
                            <div class="alert alert-danger text-center fw-bold px-5 py-3 rounded-pill mb-0 shadow-sm">
                                <i class="bi bi-x-circle-fill me-2"></i>
                                <#if application.initiatedBy == "COMPANY">
                                    Ви відхилили запрошення на цю вакансію.
                                <#else>
                                    Роботодавець відхилив ваш відгук або ви скасували заявку.
                                </#if>
                            </div>
                        </#if>

                    <#else>
                        <form method="post" action="/candidate/vacancies/apply/${vacancy.id}">
                            <input type="hidden" name="${(_csrf.parameterName)!''}" value="${(_csrf.token)!''}"/>
                            <button type="submit" class="btn btn-success btn-lg fw-bold px-5 py-3 rounded-pill shadow">
                                <i class="bi bi-send-check me-2">Відгукнутися на вакансію</i>
                            </button>
                        </form>
                    </#if>
                </div>
                <button onclick="window.print();" class="btn btn-light border fw-semibold text-dark shadow-sm">
                    <i class="bi bi-file-earmark-pdf text-danger me-2">Зберегти вакансію в PDF / Друк</i>
                </button>
            </div>
        </div>
    </div>
</@t.pages>