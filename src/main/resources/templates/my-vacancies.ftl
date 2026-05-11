<#import "client/temp-client.ftl" as p>
<@p.pages>
    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="fw-bold">Мої вакансії</h2>
            <a href="/vacancies/create" class="btn btn-success">+ Створити нову</a>
        </div>

        <div class="row">
            <#if vacancies?has_content>
                <#list vacancies as v>
                    <div class="col-md-12 mb-3">
                        <div class="card shadow-sm border-0">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <h5 class="mb-1 text-primary fw-bold">${v.position}</h5>
                                    <p class="mb-0 text-muted small">
                                        <i class="bi bi-calendar3"></i> Опубліковано: ${(v.createdAt)!"нещодавно"}
                                    </p>
                                </div>
                                <div class="btn-group">
                                    <#-- Кнопка для перегляду тих, хто відгукнувся -->
                                    <a href="/vacancies/${v.id}/applications" class="btn btn-outline-primary">
                                        Відгуки <span class="badge bg-primary ms-1">${(v.applications?size)!0}</span>
                                    </a>
                                    <a href="/vacancies/edit/${v.id}" class="btn btn-outline-secondary">
                                        <i class="bi bi-pencil"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            <#else>
                <div class="text-center py-5">
                    <p class="text-muted">Ви ще не опублікували жодної вакансії.</p>
                </div>
            </#if>
        </div>
    </div>
</@p.pages>