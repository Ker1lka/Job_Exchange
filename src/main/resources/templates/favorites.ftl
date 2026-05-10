<#import "client/temp-client.ftl" as p>
<@p.pages>
    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2><i class="bi bi-star-fill text-warning me-2"></i>Обрані вакансії</h2>
            <span class="badge bg-primary fs-6">Усього: ${el!0}</span>
        </div>

        <#if favorites?has_content && favorites?size gt 0>
            <div class="row">
                <#list favorites as item>
                    <div class="col-md-12 mb-3">
                        <div class="card shadow-sm border-0">
                            <div class="card-body d-flex justify-content-between align-items-center">
                                <div>
                                    <h5 class="card-title mb-1">
                                        <a href="/vacancy/${item.vacancy.id}"
                                           class="text-decoration-none text-dark fw-bold">
                                            ${item.vacancy.position}
                                        </a>
                                    </h5>
                                    <p class="text-muted mb-0 small">
                                        <i class="bi bi-cash-stack me-1"></i> ${item.vacancy.salary!0} грн. |
                                        <i class="bi bi-clock me-1"></i> ${item.vacancy.workingConditions!""}
                                    </p>
                                </div>

                                <div class="d-flex gap-2">
                                    <a href="/vacancy/${item.vacancy.id}" class="btn btn-sm btn-outline-primary">
                                        Детальніше
                                    </a>

                                    <form action="/deleteFavorite" method="post" style="display:inline;">
                                        <input type="hidden" name="id" value="${item.vacancy.id}">
                                        <button type="submit" class="btn btn-sm btn-outline-danger"
                                                onclick="return confirm('Прибрати з обраного?')">
                                            <i class="bi bi-trash">X</i>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        <#else>
        <#-- Блок, якщо обраних вакансій немає -->
            <div class="text-center py-5">
                <i class="bi bi-bookmark-star text-light-emphasis" style="font-size: 4rem;"></i>
                <h3 class="mt-3 text-muted">Ваш список обраного порожній</h3>
                <p>Додавайте цікаві вакансії, щоб не загубити їх.</p>
                <a href="/" class="btn btn-primary mt-2">
                    Переглянути вакансії
                </a>
            </div>
        </#if>
    </div>
</@p.pages>