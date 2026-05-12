<#import "admin/templ-admin.ftl" as p>
<@p.pages>
    <div class="container mt-4">
        <div class="row">
            <div class="col-md-8">
                <div class="card shadow-sm mb-4">
                    <div class="card-header bg-primary text-white">Список ролей користувачів</div>
                    <table class="table table-striped mb-0">
                        <thead>
                        <tr><th>User ID</th><th>Role ID</th><th>Дії</th></tr>
                        </thead>
                        <tbody>
                        <#if roles?? && users??>
                            <#list roles as role>
                                <#list users as my_user>
                                    <tr>
                                        <form method="post" action="/updateRole">
                                            <td><input type="text" name="userId" class="form-control" value="${my_user.id!""}"></td>
                                            <td><input type="text" name="roleId" class="form-control" value="${role.id!""}"></td>
                                            <td>
                                                <button type="submit" class="btn btn-warning btn-sm">Змінити</button>
                                        </form>
                                        <form method="post" action="/deleteRole" class="d-inline">
                                            <input type="hidden" name="id" value="${role.id}">
                                            <button type="submit" class="btn btn-danger btn-sm">Видалити</button>
                                        </form>
                                        </td>
                                    </tr>
                                </#list>
                            </#list>
                        </#if>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card border-primary mb-3 shadow-sm">
                    <div class="card-header">Додати роль користувачу</div>
                    <div class="card-body">
                        <form method="post" action="/addRoleToUser">
                            <div class="mb-3">
                                <label class="form-label">User ID:</label>
                                <input type="text" name="userId" class="form-control shadow-sm">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Role ID:</label>
                                <input type="text" name="roleId" class="form-control shadow-sm">
                            </div>
                            <button type="submit" class="btn btn-primary w-100 mb-3">Призначити роль</button>
                        </form>
                        <table class="table table-sm small table-bordered">
                            <tr class="table-light text-center"><th>Роль</th><th>ID</th></tr>
                            <tr><td>ROLE_user</td><td class="text-center">1</td></tr>
                            <tr><td>ROLE_manager</td><td class="text-center">2</td></tr>
                            <tr><td>ROLE_admin</td><td class="text-center">3</td></tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@p.pages>