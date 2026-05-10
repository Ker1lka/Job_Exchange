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
                    <#if profiles??>
                        <#list profiles as profile>
                            <tbody>
                            <tr>
                                <td>${profile.firstName} ${profile.lastName}</td>
                                <#list profile.profileHasProfessions as profession>
                                    <td>${profession.profession.title}</td>
                                    <td>${profession.skillLevel}</td>
                                </#list>
                                <#list profile.experiences as experience>
                                <td>${experience.timeOfExperience} роки</td>
                                </#list>>
                                <td class="text-end">
                                    <a href="/profiles/${profile.id}"
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