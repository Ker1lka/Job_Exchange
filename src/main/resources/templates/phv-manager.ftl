<#import "manager/templ-manager.ftl" as p>
<@p.pages>
    <div class="container-fluid mt-4">
        <div class="card shadow border-0">
            <div class="card-header bg-success text-white">
                <h5 class="mb-0"><i class="bi bi-clipboard-check me-2"></i>Управління відгуками (PHV)</h5>
            </div>
            <div class="card-body p-0">
                <table class="table table-hover align-middle mb-0 text-nowrap">
                    <thead class="table-light">
                    <tr>
                        <th style="width: 5%">Id</th>
                        <th style="width: 10%">Статус</th>
                        <th>Кандидат (ID/Прізвище)</th>
                        <th>Вакансія (ID/Компанія)</th>
                        <th>Дата створення</th>
                        <th class="text-center">Дії</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if phv??>
                        <#list phv as item>
                            <tr>
                                <form method="post" action="/updatePHV">
                                    <td><input type="text" name="id" class="form-control form-control-sm" value="${item.id!""}"></td>
                                    <td><input type="text" name="status" class="form-control form-control-sm" value="${item.status!""}"></td>
                                    <td style="white-space: nowrap;">
                                        <input type="text" name="profileId" class="form-control form-control-sm d-inline-block" style="width: 55px; display: inline-block !important;" value="${item.profile.id!""}">
                                        <span class="ms-1">${item.profile.lastName!""}</span>
                                    </td>
                                    <td style="white-space: nowrap;">
                                        <input type="text" name="vacancyId" class="form-control form-control-sm d-inline-block" style="width: 55px; display: inline-block !important;" value="${item.vacancy.id!""}">
                                        <span class="ms-1">${(item.vacancy.company.name)!"-"}</span>
                                    </td>
                                    <td><input type="text" readonly class="form-control-plaintext form-control-sm px-2" value="${(item.createdDate)!""}"></td>
                                    <td class="text-center">
                                        <button type="submit" class="btn btn-sm btn-primary">Оновити</button>
                                </form>
                                <form method="post" action="/deletePHV" class="d-inline">
                                    <input type="hidden" name="id" value="${item.id}">
                                    <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Видалити?')">X<i class="bi bi-x-lg"></i></button>
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
</@p.pages>