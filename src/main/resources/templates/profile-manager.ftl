<#import "manager/templ-manager.ftl" as p>
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
                    <th>Телефон</th>
                    <th>Email</th>
                    <th>Вік</th>
                    <th class="text-center">Дія</th>
                </tr>
                </thead>
                <tbody>
                <#if profiles??>
                <#list profiles as profile>
                <tr>
                    <form method="post" action="/updateProfile">
                        <td><input type="text" name="id" class="form-control form-control-sm fw-bold px-2" value="${profile.id!""}"></td>
                        <td><input type="text" name="firstName" class="form-control form-control-sm" value="${profile.firstName!""}"></td>
                        <td><input type="text" name="lastName" class="form-control form-control-sm" value="${profile.lastName!""}"></td>
                        <td><input type="text" name="middleName" class="form-control form-control-sm" value="${profile.middleName!""}"></td>
                        <td><input type="text" name="address" class="form-control form-control-sm" value="${profile.address!""}"></td>
                        <td><input type="text" name="phone" class="form-control form-control-sm" value="${profile.phone!""}"></td>
                        <td><input type="email" name="email" class="form-control form-control-sm" value="${profile.email!""}"></td>
                        <td><input type="number" name="age" class="form-control form-control-sm" value="${profile.age!""}" style="width: 70px;"></td>
                        <td class="text-center">
                            <div class="btn-group">
                                <button type="submit" class="btn btn-xs btn-success">Оновити<i class="bi bi-check2"></i></button>
                    </form>
                    <form method="post" action="/deleteProfile">
                        <input type="hidden" name="id" value="${profile.id}">
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