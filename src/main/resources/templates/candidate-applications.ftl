<#import "candidate/templ-candidate.ftl" as p>

<@p.pages>
    <div class="card shadow border-0 p-4">
        <div class="border-bottom pb-3 mb-4">
            <h2 class="fw-bold text-dark mb-1">Мої відгуки та запрошення</h2>
            <p class="text-muted mb-0">Відстежуйте статус ваших відгуків або переглядайте пропозиції від роботодавців</p>
        </div>

        <div class="table-responsive">
            <table class="table table-hover align-middle">
                <thead class="table-light">
                <tr>
                    <th>Вакансія</th>
                    <th>Компанія</th>
                    <th>Тип запиту</th>
                    <th>Статус</th>
                    <th class="text-center">Дії</th> </tr>
                </thead>
                <tbody>
                <#list applicationsList as app>
                    <tr>
                        <td><strong class="text-dark">${(app.vacancy.position)!''}</strong></td>
                        <td>${(app.vacancy.company.name)!''}</td>
                        <td>
                            <#if app.initiatedBy == "COMPANY">
                                <span class="badge bg-info text-dark px-3 py-2">
                            <i class="bi bi-envelope-open me-1">Запрошення від компанії</i>
                        </span>
                            <#else>
                                <span class="badge bg-secondary px-3 py-2">
                            <i class="bi bi-send me-1">Мій відгук</i>
                        </span>
                            </#if>
                        </td>
                        <td>
                            <#if app.status == "PENDING">
                                <span class="badge bg-warning text-dark fw-bold px-3 py-2">Очікує розгляду</span>
                                <#elseif app.status == "ACCEPTED">
                                <span class="badge bg-success fw-bold px-3 py-2">Прийнято</span>
                                <#elseif app.status == "REJECTED">
                                <span class="badge bg-danger fw-bold px-3 py-2">Відхилено</span>
                            <#else>
                                <span class="badge bg-light text-dark px-3 py-2">${app.status}</span>
                            </#if>
                        </td>

                        <td class="text-center">
                            <div class="d-flex gap-2 justify-content-center">
                                <a href="/candidate/vacancies/${app.vacancy.id}" class="btn btn-sm btn-outline-primary" title="Переглянути вакансію">
                                    <i class="bi bi-eye">Переглянути</i>
                                </a>

                                <#if app.initiatedBy == "COMPANY" && app.status == "PENDING">
                                    <form method="post" action="/profile/candidate/applications/${app.id}/accept" style="display:inline;">
                                        <input type="hidden" name="${(_csrf.parameterName)!''}" value="${(_csrf.token)!''}"/>
                                        <button type="submit" class="btn btn-sm btn-success" title="Прийняти запрошення">
                                            <i class="bi bi-check-lg">Прийняти</i>
                                        </button>
                                    </form>

                                    <form method="post" action="/profile/candidate/applications/${app.id}/reject" style="display:inline;">
                                        <input type="hidden" name="${(_csrf.parameterName)!''}" value="${(_csrf.token)!''}"/>
                                        <button type="submit" class="btn btn-sm btn-danger" title="Відхилити">
                                            <i class="bi bi-x-lg">Відхилити</i>
                                        </button>
                                    </form>
                                </#if>
                            </div>
                        </td>
                    </tr>
                <#else>
                    <tr>
                        <td colspan="5" class="text-center py-5 text-muted">У вас поки немає активних відгуків чи запрошень.</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@p.pages>