<#import "manager/templ-manager.ftl" as p>
<@p.pages>
    <h2 class="fw-bold mb-4">Глобальний список вакансій</h2>

    <div class="card shadow-sm border-0 bg-white">
        <div class="table-responsive">
            <table class="table table-hover align-middle mb-0">
                <thead class="table-dark">
                <tr>
                    <th style="width: 80px;">ID</th>
                    <th>Назва посади (Position)</th>
                    <th>Зарплата (UAH)</th>
                    <th>Статус вакансії</th>
                    <th style="width: 250px; text-align: center;">Дії</th>
                </tr>
                </thead>
                <tbody>
                <#list vacanciesList as vacancy>
                    <tr>
                        <td>${vacancy.id}</td>
                        <td class="fw-bold text-dark">${(vacancy.position)!''}</td>
                        <td class="text-success fw-bold">${(vacancy.salary)!0} грн</td>
                        <td>
                            <#if vacancy.closed>
                                <span class="badge bg-danger">В архіві</span>
                            <#else>
                                <span class="badge bg-success">Активна</span>
                            </#if>
                        </td>
                        <td>
                            <div class="d-flex gap-2 justify-content-center">
                                <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#editVacancy${vacancy.id}"><i class="bi bi-pencil"></i></button>
                                <form method="post" action="/manager/vacancies/delete/${vacancy.id}" onsubmit="return confirm('Видалити цю вакансію безповоротно?');">
                                    <button type="submit" class="btn btn-sm btn-danger"><i class="bi bi-trash"></i></button>
                                </form>
                            </div>

                            <div class="modal fade" id="editVacancy${vacancy.id}" tabindex="-1" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content text-start">
                                        <form method="post" action="/manager/vacancies/edit/${vacancy.id}">
                                            <div class="modal-header">
                                                <h5 class="modal-title fw-bold">Редагувати вакансію #${vacancy.id}</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="mb-3">
                                                    <label class="form-label">Назва посади</label>
                                                    <input type="text" name="position" class="form-control" value="${(vacancy.position)!''}" required>
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label">Заробітна плата</label>
                                                    <input type="number" step="0.01" name="salary" class="form-control" value="${(vacancy.salary)!0}" required>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-sm btn-secondary" data-bs-dismiss="modal">Закрити</button>
                                                <button type="submit" class="btn btn-sm btn-primary fw-bold">Зберегти</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@p.pages>