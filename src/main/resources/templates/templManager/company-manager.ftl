<#import "../manager/templ-manager.ftl" as p>
<@p.pages>
    <div class="container-fluid mt-4">
        <div class="card shadow border-0">
            <div class="card-header bg-dark text-white d-flex justify-content-between align-items-center">
                <h5 class="mb-0"><i class="bi bi-building me-2"></i>Управління компаніями</h5>
            </div>
            <div class="card-body p-0">
                <div class="table-responsive">
                    <table class="table table-hover align-middle mb-0">
                        <thead class="table-light">
                        <tr>
                            <th style="width: 3%">Id</th>
                            <th>Назва компанії</th>
                            <th>Опис</th>
                            <th>Контакти</th>
                            <th>Адреса</th>
                            <th class="text-center" style="width: 200px;">Дії</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if companies??>
                        <#list companies as company>
                        <tr>
                            <form method="post" action="/updateCompany">
                                <td><input type="text" name="id" class="form-control form-control-sm"
                                           value="${company.id!""}"></td>
                                <td><input type="text" name="name" class="form-control form-control-sm"
                                           value="${company.name!""}"></td>
                                <td><input type="text" name="description" class="form-control form-control-sm"
                                           value="${company.description!""}"></td>
                                <td><input type="text" name="contactInfo" class="form-control form-control-sm"
                                           value="${company.contactInfo!""}"></td>
                                <td><input type="text" name="address" class="form-control form-control-sm"
                                           value="${company.address!""}"></td>
                                <td class="text-center">
                                    <div class="btn-group shadow-sm">
                                        <button type="submit" class="btn btn-sm btn-outline-primary"><i
                                                    class="bi bi-save"></i> Оновити
                                        </button>
                            </form>
                            <form method="post" action="/deleteCompany" style="display:inline;">
                                <input type="hidden" name="id" value="${company.id}">
                                <button type="submit" class="btn btn-sm btn-outline-danger"
                                        onclick="return confirm('Видалити?')">X<i class="bi bi-trash"></i></button>
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
</@p.pages>