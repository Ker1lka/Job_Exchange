<#import "manager/templ-manager.ftl" as p>
<@p.pages>
    <h3 class="fw-bold text-dark mb-3"><i class="bi bi-people me-2 text-primary"></i>База кандидатів</h3>
    <div class="card shadow-sm border-0 bg-white mb-5">
        <div class="table-responsive">
            <table class="table table-hover align-middle mb-0">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>ПІБ Кандидата</th>
                    <th>Бажана Професія</th>
                    <th style="width: 150px; text-align: center;">Дії</th>
                </tr>
                </thead>
                <tbody>
                <#list candidatesList as candidate>
                    <tr>
                        <td>${candidate.id}</td>
                        <td class="fw-bold">${candidate.lastName} ${candidate.firstName}</td>
                        <td><span class="badge bg-light text-dark border">${candidate.profession!'-'}</span></td>
                        <td class="text-center">
                            <div class="d-flex justify-content-center gap-1">
                                <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#editCand${candidate.id}"><i class="bi bi-pencil"></i></button>
                                <form method="post" action="/manager/candidates/delete/${candidate.id}" onsubmit="return confirm('Видалити користувача-кандидата разом із профілем?');">
                                    <button type="submit" class="btn btn-sm btn-danger"><i class="bi bi-trash"></i></button>
                                </form>
                            </div>

                            <div class="modal fade" id="editCand${candidate.id}" tabindex="-1" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content text-start">
                                        <form method="post" action="/manager/candidates/edit/${candidate.id}">
                                            <div class="modal-header">
                                                <h5 class="modal-title fw-bold">Анкета кандидата #${candidate.id}</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="mb-3"><label class="form-label">Ім'я</label><input type="text" name="firstName" class="form-control" value="${candidate.firstName}" required></div>
                                                <div class="mb-3"><label class="form-label">Прізвище</label><input type="text" name="lastName" class="form-control" value="${candidate.lastName}" required></div>
                                                <div class="mb-3"><label class="form-label">Професія</label><input type="text" name="profession" class="form-control" value="${candidate.profession!''}" required></div>
                                            </div>
                                            <div class="modal-footer"><button type="submit" class="btn btn-sm btn-primary">Зберегти</button></div>
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

    <h3 class="fw-bold text-dark mb-3"><i class="bi bi-building me-2 text-success"></i>Зареєстровані компанії</h3>
    <div class="card shadow-sm border-0 bg-white">
        <div class="table-responsive">
            <table class="table table-hover align-middle mb-0">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Назва Компанії</th>
                    <th>Адреса офісу</th>
                    <th style="width: 150px; text-align: center;">Дії</th>
                </tr>
                </thead>
                <tbody>
                <#list companiesList as company>
                    <tr>
                        <td>${company.id}</td>
                        <td class="fw-bold text-success">${(company.name)!''}</td>
                        <td>${company.address!'-'}</td>
                        <td class="text-center">
                            <div class="d-flex justify-content-center gap-1">
                                <button class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target="#editComp${company.id}"><i class="bi bi-pencil"></i></button>
                                <form method="post" action="/manager/companies/delete/${company.id}" onsubmit="return confirm('Видалити компанію та всі її вакансії?');">
                                    <button type="submit" class="btn btn-sm btn-danger"><i class="bi bi-trash"></i></button>
                                </form>
                            </div>

                            <div class="modal fade" id="editComp${company.id}" tabindex="-1" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content text-start">
                                        <form method="post" action="/manager/companies/edit/${company.id}">
                                            <div class="modal-header">
                                                <h5 class="modal-title fw-bold">Анкета фірми #${(company.name)!''}</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="mb-3"><label class="form-label">Назва бренду</label><input type="text" name="name" class="form-control" value="${(company.name)!''}" required></div>
                                                <div class="mb-3"><label class="form-label">Фізична адреса</label><input type="text" name="address" class="form-control" value="${(company.address)!''}" required></div>
                                            </div>
                                            <div class="modal-footer"><button type="submit" class="btn btn-sm btn-primary">Оновити</button></div>
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