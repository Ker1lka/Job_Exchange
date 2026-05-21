<#import "../admin/templ-admin.ftl" as p>
<@p.pages>
    <div class="container mt-4">
        <div class="row">
            <div class="col-md-8">
                <div class="card shadow-sm">
                    <div class="card-header bg-primary text-white">Список ролей користувачів</div>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>User ID</th>
                            <th>Info About Roles</th>
                            <th>Role</th>
                            <th>Do</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if candidates??>
                            <#list candidates as candidate>
                                <tr>
                                    <td>
                                        <div class="fw-bold text-dark">${candidate.user.username!""}</div>
                                        <div class="small text-muted">ID: ${candidate.user.id}</div>
                                    </td>
                                    <td>
                                        <#if profile.user.roles?? && profile.user.roles?size gt 0>
                                            <#list profile.user.roles as role>
                                                <span class="badge rounded-pill bg-info text-dark mb-1">
                            ${role.name}
                        </span>
                                            </#list>
                                        <#else>
                                            <span class="text-muted small">Немає ролей</span>
                                        </#if>
                                    </td>
                                    <td>
                                        <form method="post" action="/updateRole"
                                              class="d-flex align-items-center gap-2">
                                            <input type="hidden" name="userId" value="${candidate.user.id}">
                                            <select name="roleId" class="form-select form-select-sm"
                                                    style="max-width: 120px;">
                                                <option value="1">User</option>
                                                <option value="2">Manager</option>
                                                <option value="3">Admin</option>
                                            </select>
                                            <button type="submit" class="btn btn-warning btn-sm shadow-sm">
                                                <i class="bi bi-plus-circle">Додати</i>
                                            </button>
                                        </form>
                                    </td>
                                    <td>
                                        <form method="post" action="/deleteRole"
                                              class="d-flex align-items-center gap-2">
                                            <input type="hidden" name="userId" value="${candidate.user.id}">
                                            <select name="roleId" class="form-select form-select-sm"
                                                    style="max-width: 120px;">
                                                <#list profile.user.roles as role>
                                                    <option value="${role.id}">${role.name}</option>
                                                </#list>
                                            </select>
                                            <button type="submit" class="btn btn-danger btn-sm shadow-sm">
                                                <i class="bi bi-trash">Видалити</i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </#list>
                        </#if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</@p.pages>