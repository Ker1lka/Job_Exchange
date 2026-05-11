<#import "client/temp-client.ftl" as p>
<@p.pages>
    <div class="container mt-4">
        <div class="row">
            <#-- Ліва колонка: Інформація про профіль -->
            <div class="col-lg-4">
                <div class="card shadow-sm border-0 mb-4">
                    <div class="card-body text-center py-5">
                        <div class="bg-primary text-white rounded-circle d-inline-flex align-items-center justify-content-center mb-3" style="width: 100px; height: 100px; font-size: 2.5rem;">
                            ${profile.firstName?substring(0,1)}${profile.lastName?substring(0,1)}
                        </div>
                        <h3 class="fw-bold mb-1">${profile.firstName} ${profile.lastName}</h3>
                        <p class="text-muted mb-4">Кандидат / Користувач</p>
                        <div class="d-grid gap-2">
                            <a href="/profile/edit" class="btn btn-outline-primary">
                                <i class="bi bi-pencil-square me-2"></i>Редагувати профіль
                            </a>
                        </div>
                    </div>
                    <ul class="list-group list-group-flush border-top">
                        <li class="list-group-item d-flex justify-content-between align-items-center py-3">
                            <span class="text-muted"><i class="bi bi-envelope me-2"></i>Email</span>
                            <span class="fw-bold">${(profile.email)!"не вказано"}</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-center py-3">
                            <span class="text-muted"><i class="bi bi-telephone me-2"></i>Телефон</span>
                            <span class="fw-bold">${(profile.phoneNumber)!"не вказано"}</span>
                        </li>
                    </ul>
                </div>
            </div>

            <#-- Права колонка: Історія відгуків -->
            <div class="col-lg-8">
                <div class="card shadow-sm border-0">
                    <div class="card-header bg-white py-3 d-flex justify-content-between align-items-center">
                        <h5 class="mb-0 fw-bold">Мої відгуки на вакансії</h5>
                        <span class="badge bg-primary rounded-pill">${applications?size}</span>
                    </div>
                    <div class="card-body p-0">
                        <#if applications?has_content>
                            <div class="table-responsive">
                                <table class="table table-hover align-middle mb-0">
                                    <thead class="table-light">
                                    <tr>
                                        <th>Посада</th>
                                        <th>Компанія</th>
                                        <th>Дата подачі</th>
                                        <th class="text-end">Дія</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#list applications as app>
                                        <tr>
                                            <td>
                                                <span class="fw-bold text-primary">${app.vacancy.position}</span>
                                            </td>
                                            <td>${(app.vacancy.company.name)!"Компанію не вказано"}</td>
                                            <td class="text-muted small">
                                                ${(app.applyDate.format('dd.MM.yyyy'))!"щойно"}
                                            </td>
                                            <td class="text-end">
                                                <a href="/vacancies/${app.vacancy.id}" class="btn btn-sm btn-light border">
                                                    <i class="bi bi-eye"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </#list>
                                    </tbody>
                                </table>
                            </div>
                        <#else>
                            <div class="text-center py-5">
                                <i class="bi bi-clipboard-x text-muted" style="font-size: 3rem;"></i>
                                <p class="mt-3 text-muted">Ви ще не відгукувалися на жодну вакансію.</p>
                                <a href="/" class="btn btn-primary btn-sm mt-2">Знайти вакансії</a>
                            </div>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@p.pages>