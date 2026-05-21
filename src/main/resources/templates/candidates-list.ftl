<#import "client/temp-client.ftl" as p>
<@p.pages>
    <div class="col-md-10">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white">
                <h5 class="mb-0">База кандидатів</h5>
            </div>
            <div class="card-body">
                <table class="table table-hover align-middle">
                    <thead class="table-light">
                    <tr>
                        <th>ПІБ</th>
                        <th>Спеціальність</th>
                        <th>Рівень</th>
                        <th>Досвід</th>
                        <th class="text-end">Дії</th>
                    </tr>
                    </thead>
                    <#if candidates??>
                        <#list candidates as candidate>
                            <tbody>
                            <tr>
                                <td>${candidate.firstName!""} ${candidate.lastName!""} ${candidate.middleName!""}</td>
                                <#if candidate.profileHasProfessions??>
                                <#list candidate.profileHasProfessions as item>
                                    <td>${item.profession.title!"Не вказано"}</td>
                                    <td>${item.skillLevel!"Не вказано"}</td>
                                </#list>
                                </#if>
                                <#if candidate.experiences??>
                                <#list candidate.experiences as experience>
                                <td>${experience.timeOfExperience!"Не вказано"} роки</td>
                                </#list></#if>
                                <td class="text-end">
                                    <a href="/candidates/${candidate.id}"
                                       class="btn btn-sm btn-outline-primary">Переглянути</a>
                                </td>
                            </tr>
                            </tbody>
                        </#list>
                    </#if>
                </table>
            </div>
        </div>
    </div>
</@p.pages>
