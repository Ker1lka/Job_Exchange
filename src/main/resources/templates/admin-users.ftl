<#import "admin/templ-admin.ftl" as p>
<@p.pages>
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="fw-bold m-0 text-dark">База користувачів системи</h2>
        <span class="badge bg-secondary px-3 py-2 fs-6">Всього: ${allUsers?size}</span>
    </div>

    <div class="card shadow-sm border-0 bg-white">
        <div class="table-responsive">
            <table class="table table-hover align-middle mb-0">
                <thead class="table-dark">
                <tr>
                    <th style="width: 80px;">ID</th>
                    <th>Логін (Email)</th>
                    <th>Поточна роль</th>
                    <th>Призначити нову роль</th>
                    <th style="width: 200px; text-align: center;">Дії</th>
                </tr>
                </thead>
                <tbody>
                <#list allUsers as user>
                    <tr>
                        <td>${user.id}</td>
                        <td class="fw-bold text-secondary">${(user.username)!''}</td>
                        <td>
                            <#list user.roles as role>
                                <span class="badge bg-primary px-2.5 py-1.5 fs-7">${(role.name)!''}</span>
                            </#list>
                        </td>

                        <#-- Форма швидкої зміни ролі -->
                        <td>
                            <form method="post" action="/admin/users/${user.id}/role" class="d-flex gap-2">
                                <select name="roleName" class="form-select form-select-sm" style="max-width: 200px;" required>
                                    <option value="" disabled selected>Змінити роль на...</option>
                                    <option value="ROLE_candidate">Кандидат (ROLE_candidate)</option>
                                    <option value="ROLE_company">Компанія (ROLE_company)</option>
                                    <option value="ROLE_manager">Менеджер (ROLE_manager)</option>
                                    <option value="ROLE_admin">Адмін (ROLE_admin)</option>
                                </select>
                                <button type="submit" class="btn btn-sm btn-success">Оновити</button>
                            </form>
                        </td>

                        <#-- Кнопка виклику модального вікна та саме вікно зміни логіну -->
                        <td class="text-center">
                            <button class="btn btn-sm btn-outline-warning" data-bs-toggle="modal" data-bs-target="#editLoginModal${user.id}">
                                <i class="bi bi-pencil-square me-1"></i> Редагувати логін
                            </button>

                            <#-- Модальне вікно для редагування логіну (Username) -->
                            <div class="modal fade" id="editLoginModal${user.id}" tabindex="-1" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content text-start">
                                        <form method="post" action="/admin/users/${user.id}/username">
                                            <div class="modal-header">
                                                <h5 class="modal-title fw-bold">Зміна нікнейму для #${user.id}</h5>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="mb-3">
                                                    <label class="form-label fw-semibold">Новий логін</label>
                                                    <input type="text" name="newUsername" class="form-control" value="${(user.username)!''}" required>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-sm btn-secondary" data-bs-dismiss="modal">Скасувати</button>
                                                <button type="submit" class="btn btn-sm btn-warning fw-bold">Зберегти</button>
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