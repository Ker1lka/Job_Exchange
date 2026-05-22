<#import "manager/templ-manager.ftl" as p>
<@p.pages>
    <h2 class="fw-bold mb-4">Журнал взаємодії (Відгуки та Інвайти)</h2>

    <div class="card shadow-sm border-0 bg-white">
        <div class="table-responsive">
            <table class="table table-hover align-middle mb-0">
                <thead class="table-dark">
                <tr>
                    <th style="width: 80px;">ID</th>
                    <th>Кандидат</th>
                    <th>Компанія та Вакансія</th>
                    <th>Хто ініціював</th>
                    <th>Поточний статус</th>
                    <th style="width: 100px; text-align: center;">Дії</th>
                </tr>
                </thead>
                <tbody>
                <#list applicationsList as app>
                    <tr>
                        <td>${app.id}</td>
                        <td class="fw-bold">${(app.candidate.lastName)!''} ${(app.candidate.firstName)!''}</td>
                        <td>
                            <span class="text-primary fw-semibold">${(app.vacancy.company.name)!''}</span>
                            <i class="bi bi-arrow-right mx-1 text-muted"></i>
                            <small class="text-muted">${(app.vacancy.position)!''}</small>
                        </td>
                        <td>
                            <#if app.initiatedBy == "CANDIDATE">
                                <span class="badge bg-info text-dark">Відгук безробітного</span>
                            <#else>
                                <span class="badge bg-warning text-dark">Запрошення фірми</span>
                            </#if>
                        </td>
                        <td>
                            <#if app.status == "PENDING">
                                <span class="badge bg-warning">Очікує відповіді</span>
                            <#elseif app.status == "ACCEPTED">
                                <span class="badge bg-success">Прийнято</span>
                            <#else>
                                <span class="badge bg-danger">Відхилено</span>
                            </#if>
                        </td>
                        <td class="text-center">
                            <form method="post" action="/manager/applications/delete/${app.id}" onsubmit="return confirm('Скасувати/видалити цей запис взаємодії?');">
                                <button type="submit" class="btn btn-sm btn-outline-danger"><i class="bi bi-x-circle-fill"></i> Видалити</button>
                            </form>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@p.pages>