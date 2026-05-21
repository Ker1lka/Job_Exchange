<#import "../manager/templ-manager.ftl" as p>
<@p.pages>
    <div class="container-fluid mt-4 pb-5">
        <h4 class="mb-3 text-secondary">Редактор профілів користувачів</h4>
        <div class="table-responsive shadow-sm rounded border">
            <table class="table table-sm table-hover bg-white mb-0">
                <thead class="table-dark">
                <tr>
                    <th style="width: 5%">ID</th>
                    <th>Ім'я</th>
                    <th>Прізвище</th>
                    <th>По батькові</th>
                    <th>Адреса</th>
                    <th>Контакт</th>
                    <th>Вік</th>
                    <th class="text-center">Дія</th>
                </tr>
                </thead>
                <tbody>
                <#if candidates??>
                <#list candidates as candidate>
                <tr>
                    <form method="post" action="/updateCandidate">
                        <td><input type="text" name="id" class="form-control form-control-sm fw-bold px-2" value="${candidate.id!""}"></td>
                        <td><input type="text" name="firstName" class="form-control form-control-sm" value="${candidate.firstName!""}"></td>
                        <td><input type="text" name="lastName" class="form-control form-control-sm" value="${candidate.lastName!""}"></td>
                        <td><input type="text" name="middleName" class="form-control form-control-sm" value="${candidate.middleName!""}"></td>
                        <td><input type="text" name="address" class="form-control form-control-sm" value="${candidate.address!""}"></td>
                        <td><input type="text" name="contactInfo" class="form-control form-control-sm" value="${candidate.contactInfo!""}"></td>
                        <td><input type="number" name="age" class="form-control form-control-sm" value="${candidate.age!""}" style="width: 70px;"></td>
                        <td class="text-center">
                            <div class="btn-group">
                                <button type="submit" class="btn btn-xs btn-success">Оновити<i class="bi bi-check2"></i></button>
                    </form>
                    <form method="post" action="/deleteCandidate">
                        <input type="hidden" name="id" value="${candidate.id}">
                        <button type="submit" class="btn btn-xs btn-danger">X<i class="bi bi-trash3"></i></button>
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
</@p.pages>

