<#import "../admin/templ-admin.ftl" as p>
<@p.pages>
    <div class="container-fluid mt-4">
        <div class="row">
            <div class="col-lg-12">
                <div class="card shadow-sm border-0">
                    <div class="card-header bg-dark text-white d-flex justify-content-between align-items-center">
                        <h5 class="mb-0"><i class="bi bi-people-fill me-2"></i>Управління користувачами</h5>
                        <span class="badge bg-secondary">Всього: ${users?size}</span>
                    </div>
                    <div class="card-body p-0">
                        <div class="table-responsive">
                            <table class="table table-hover align-middle mb-0">
                                <thead class="table-light">
                                <tr>
                                    <th style="width: 10%;">ID</th>
                                    <th style="width: 60%;">Username</th>
                                    <th style="width: 30%;" class="text-center">Дії</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if users??>
                                <#list users as my_user>
                                <tr>
                                    <form method="post" action="/updateUser">
                                        <td>
                                            <input type="text" name="id" readonly class="form-control-plaintext fw-bold px-2" value="${my_user.id!""}">
                                        </td>
                                        <td>
                                            <div class="input-group input-group-sm">
                                                <span class="input-group-text bg-white"><i class="bi bi-person text-muted"></i></span>
                                                <input type="text" name="username" class="form-control" value="${my_user.username!""}">
                                            </div>
                                        </td>
                                        <td class="text-center">
                                            <div class="btn-group">
                                                <button type="submit" class="btn btn-sm btn-primary">
                                                    <i class="bi bi-arrow-repeat">Оновити</i>
                                                </button>
                                    </form>
                                    <form method="post" action="/deleteUser" class="d-inline">
                                        <input type="hidden" name="id" value="${my_user.id}">
                                        <button type="submit" class="btn btn-sm btn-outline-danger" onclick="return confirm('Видалити користувача ${my_user.username}?')">
                                            <i class="bi bi-trash">X</i>
                                        </button>
                                    </form>
                        </div>
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
    </div>
    </div>
</@p.pages>