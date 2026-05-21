<#import "candidate/templ-candidate.ftl" as p>
<@p.pages>
<div class="card shadow border-0 p-4">

    <div class="d-flex justify-content-between align-items-center mb-4 border-bottom pb-3">
        <div>
            <h2 class="fw-bold text-dark mb-1">Доступні вакансії</h2>
            <p class="text-muted mb-0">Актуальні пропозиції від роботодавців</p>
        </div>
        <span class="badge bg-success fs-6">Активних вакансій: ${vacanciesList?size}</span>
    </div>

    <div class="row">
        <#list vacanciesList as vacancy>
            <#if !vacancy.closed>
                <div class="col-12 mb-3">
                    <div class="card shadow-sm border-0 bg-light p-3">
                        <div class="row align-items-center">

                            <div class="col-md-5">
                                <h5 class="fw-bold text-dark mb-1">${vacancy.position}</h5>
                                <p class="text-primary mb-0 fw-semibold">
                                    <i class="bi bi-building"></i> ${(vacancy.company.name)!"Невідома компанія"}
                                </p>
                            </div>

                            <div class="col-md-3">
                                <span class="fs-5 fw-bold text-success">
                                    <#if vacancy.salary?? && vacancy.salary gt 0>
                                        ${vacancy.salary} грн
                                    <#else>
                                        За результатами співбесіди
                                    </#if>
                                </span>
                            </div>

                            <div class="col-md-4 text-md-end mt-3 mt-md-0">
                                <a href="/candidate/vacancies/${vacancy.id}" class="btn btn-primary fw-bold px-4">
                                    Детальніше
                                </a>
                            </div>

                        </div>
                    </div>
                </div>
            </#if>
        <#else>
            <div class="col-12 text-center py-5">
                <p class="text-muted fs-5">Активних вакансій поки що немає.</p>
            </div>
        </#list>
    </div>
</div>
</@p.pages>