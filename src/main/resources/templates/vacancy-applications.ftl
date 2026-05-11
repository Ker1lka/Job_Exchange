<#import "client/temp-client.ftl" as p>
<@p.pages>
    <div class="container mt-4">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/vacancies/my">Мої вакансії</a></li>
                <li class="breadcrumb-item active">${vacancy.position}</li>
            </ol>
        </nav>

        <div class="card shadow-sm border-0 mb-4">
            <div class="card-body bg-light">
                <h2 class="fw-bold mb-1">Відгуки на вакансію</h2>
                <p class="text-primary fs-5 mb-0">${vacancy.position} <span class="text-muted">в ${(vacancy.company.name)!"вашій компанії"}</span></p>
            </div>
        </div>

        <div class="table-responsive">
            <table class="table table-hover align-middle shadow-sm bg-white rounded">
                <thead class="table-dark">
                <tr>
                    <th>Кандидат</th>
                    <th>Контакти</th>
                    <th>Дата відгуку</th>
                    <th class="text-center">Дії</th>
                </tr>
                </thead>
                <tbody>
                <#if applications?has_content>
                    <#list applications as app>
                        <tr>
                            <td>
                                <div class="d-flex align-items-center">
                                    <div class="bg-secondary text-white rounded-circle d-flex align-items-center justify-content-center me-3" style="width: 40px; height: 40px;">
                                        ${app.profile.firstName?substring(0,1)}${app.profile.lastName?substring(0,1)}
                                    </div>
                                    <div>
                                        <div class="fw-bold">${app.profile.firstName} ${app.profile.lastName}</div>
                                        <small class="text-muted">ID: ${app.profile.id}</small>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div><i class="bi bi-envelope me-2"></i>${(app.profile.email)!"не вказано"}</div>
                                <div><i class="bi bi-telephone me-2"></i>${(app.profile.phoneNumber)!"не вказано"}</div>
                            </td>
                            <td>
                                    <span class="badge bg-light text-dark border">
                                        ${(app.applyDate.format('dd.MM.yyyy HH:mm'))!"щойно"}
                                    </span>
                            </td>
                            <td class="text-center">
                                <#-- Посилання на повний профіль/резюме кандидата -->
                                <a href="/profiles/${app.profile.id}" class="btn btn-sm btn-outline-primary">
                                    Переглянути профіль
                                </a>
                            </td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td colspan="4" class="text-center py-5 text-muted">
                            <i class="bi bi-people fs-1 d-block mb-2"></i>
                            На цю вакансію ще ніхто не відгукнувся.
                        </td>
                    </tr>
                </#if>
                </tbody>
            </table>
        </div>
    </div>
</@p.pages>