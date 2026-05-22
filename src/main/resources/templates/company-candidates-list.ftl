<#import "company/templ-company.ftl" as p>
<@p.pages>

<div class="card shadow border-0 p-4">
    <#-- Хедер сторінки та загальна кількість анкет -->
    <div class="d-flex justify-content-between align-items-center mb-4 border-bottom pb-3">
        <div>
            <h2 class="fw-bold text-dark mb-1">База кандидатів</h2>
            <p class="text-muted mb-0">Пошук спеціалістів серед зареєстрованих безробітних</p>
        </div>
        <span class="badge bg-primary fs-6">Доступно анкет: ${candidatesList?size}</span>
    </div>

    <#-- Сітка з картками кандидатів -->
    <div class="row">
        <#list candidatesList as candidate>
            <#-- Перевірка, що анкета кандидата не знаходиться в архіві -->
            <#if !candidate.archived>
                <div class="col-md-6 col-lg-4 mb-4">
                    <div class="card h-100 shadow-sm border-0 border-top border-primary border-3">
                        <div class="card-body d-flex flex-column p-4">

                            <#-- Відображення ПІБ шукача (з перевіркою на наявність даних) -->
                            <h5 class="card-title fw-bold text-dark mb-1">
                                <#if candidate.lastName?? || candidate.firstName?? || candidate.middleName??>
                                    ${(candidate.lastName)!''} ${(candidate.firstName)!''} ${(candidate.middleName)!''}
                                <#else>
                                    Анонімний кандидат
                                </#if>
                            </h5>

                            <#-- Бажана професія -->
                            <h6 class="text-primary fw-semibold mb-3">
                                <i class="bi bi-briefcase"></i> ${(candidate.profession)!"Професія не вказана"}
                            </h6>

                            <hr class="opacity-25 my-2">

                            <#-- Короткі відомості про освіту та останній досвід -->
                            <div class="small text-muted mb-4">
                                <p class="mb-1"><strong>Освіта:</strong> ${(candidate.degree)!"Не вказано"}</p>
                                <p class="mb-0"><strong>Досвід:</strong> ${(candidate.lastJobPosition)!"Без досвіду"}</p>
                            </div>

                            <a href="/company/candidates/${candidate.id}" class="btn btn-outline-primary mt-auto w-100 fw-bold">
                                Переглянути анкету
                            </a>

                        </div>
                    </div>
                </div>
            </#if>
        <#else>
            <#-- Заглушка, якщо список кандидатів порожній -->
            <div class="col-12 text-center py-5">
                <p class="text-muted fs-5">Наразі немає доступних кандидатів.</p>
            </div>
        </#list>
    </div>
</div>
</@p.pages>