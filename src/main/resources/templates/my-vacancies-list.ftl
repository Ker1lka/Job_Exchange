<#import "company/templ-company.ftl" as p>
<@p.pages>
    <div class="card shadow border-0 p-4">
        <div class="d-flex justify-content-between align-items-center mb-4 border-bottom pb-3">
            <div>
                <h2 class="fw-bold text-dark mb-1">Ваші вакансії</h2>
                <p class="text-muted mb-0">Управління опублікованими оголошеннями компанії</p>
            </div>
            <a href="/profile/company/vacancies/create" class="btn btn-success fw-bold">
                <i class="bi bi-plus-circle"></i> Створити вакансію
            </a>
        </div>

        <div class="table-responsive">
            <table class="table table-hover align-middle border">
                <thead class="table-light">
                <tr>
                    <th>Назва вакансії</th>
                    <th>Заробітна плата</th>
                    <th>Статус</th>
                    <th class="text-end">Дії</th>
                </tr>
                </thead>
                <tbody>
                <#list myVacancies as vacancy>
                    <tr>
                        <td>
                            <span class="fw-bold text-dark">${vacancy.position}</span>
                        </td>
                        <td>
                            <span class="fw-semibold text-muted">${(vacancy.salary)!"--"} грн </span>
                        </td>

                        <td>
                            <#if vacancy.closed>
                                <span class="badge bg-secondary">Закрита (В архіві)</span>
                            <#else>
                                <span class="badge bg-success">Активна (Йде набір)</span>
                            </#if>
                        </td>

                        <td class="text-end">
                            <div class="btn-group btn-group-sm">
                                <a href="/profile/company/vacancies/edit/${vacancy.id}"
                                   class="btn btn-outline-secondary" title="Редагувати">
                                    <i class="bi bi-pencil">Редагувати</i>
                                </a>
                                <#if !vacancy.closed>
                                    <form method="post" action="/profile/company/vacancies/close/${vacancy.id}"
                                          class="d-inline">
                                        <button type="submit" class="btn btn-outline-danger" title="Закрити вакансію">
                                            <i class="bi bi-archive">Закрити</i>
                                        </button>
                                    </form>
                                </#if>
                            </div>
                        </td>
                    </tr>
                <#else>
                    <tr>
                        <td colspan="4" class="text-center text-muted py-4">
                            Ви ще не створили жодної вакансії. Натисніть кнопку "Створити вакансію" вище.
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@p.pages>