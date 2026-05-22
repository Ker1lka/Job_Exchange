<#import "company/templ-company.ftl" as p>
<@p.pages>

    <div class="card shadow border-0 p-4">
        <div class="d-flex justify-content-between align-items-center border-bottom pb-3 mb-4">
            <div>
                <h2 class="fw-bold text-dark mb-1">${(company.name)!"Назва фірми не вказана"}</h2>
                <p class="text-muted mb-0"><i class="bi bi-geo-alt"></i> ${(company.address)!"Адреса не вказана"}</p>
            </div>
            <a href="/profile/company/edit" class="btn btn-outline-primary fw-bold">Редагувати профіль</a>
        </div>

        <div class="row g-3 mb-4">
            <div class="col-md-6">
                <p><strong>Контактна особа / Телефон:</strong> ${(company.contactInfo)!"Не вказано"}</p>
            </div>
            <div class="col-12">
                <p><strong>Про компанію:</strong></p>
                <div class="p-3 bg-light rounded text-muted">
                    ${(company.description)!"Опис компанії відсутній. Додайте інформацію, щоб зацікавити безробітних."}
                </div>
            </div>
        </div>

        <#-- ВНУТРІШНЯ КАРТКА: МОНІТОРИНГ ВЗАЄМОДІЙ -->
        <div class="card shadow border-0 p-4 mt-4">

            <#-- Блок: Вхідні відгуки від безробітних/кандидатів -->
            <h3 class="fw-bold text-dark mb-3"><i class="bi bi-box-arrow-in-down text-primary me-2"></i> Відгуки
                кандидатів на ваші вакансії</h3>
            <div class="table-responsive mb-5">
                <table class="table table-hover align-middle">
                    <thead class="table-light">
                    <tr>
                        <th>Кандидат</th>
                        <th>Вакансія</th>
                        <th>Статус</th>
                        <th class="text-center">Дія</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#assign hasIncoming = false>
                    <#list sentOffers as offer>
                        <#if offer.initiatedBy == "CANDIDATE">
                            <#assign hasIncoming = true>
                            <tr onclick="window.location='/profile/company/applications/candidates/${offer.candidate.id}';"
                                style="cursor: pointer;"
                                class="position-relative">
                                <td>
                                    <span class="fw-bold text-primary">
                                        <i class="bi bi-person-badge me-1"></i> ${(offer.candidate.firstName)!''} ${(offer.candidate.lastName)!'Анонім'}
                                    </span>
                                </td>
                                <td><span class="badge bg-light text-dark">${(offer.vacancy.position)!''}</span></td>
                                <td>
                                    <#if offer.status == "PENDING">
                                        <span class="badge bg-warning text-dark">Новий відгук</span>
                                    <#else>
                                        <span class="badge bg-secondary">${(offer.status)!''}</span>
                                    </#if>
                                </td>
                                <td class="text-center" onclick="event.stopPropagation();">
                                    <#if offer.status == "PENDING">
                                        <form method="post" action="/profile/company/applications/${offer.id}/accept"
                                              style="display:inline;">
                                            <input type="hidden" name="${(_csrf.parameterName)!''}"
                                                   value="${(_csrf.token)!''}"/>
                                            <button type="submit" class="btn btn-sm btn-success"><i
                                                        class="bi bi-check-lg">Прийняти</i></button>
                                        </form>
                                        <form method="post" action="/profile/company/applications/${offer.id}/reject"
                                              style="display:inline;">
                                            <input type="hidden" name="${(_csrf.parameterName)!''}"
                                                   value="${(_csrf.token)!''}"/>
                                            <button type="submit" class="btn btn-sm btn-danger"><i
                                                        class="bi bi-x-lg">Відхилити</i></button>
                                        </form>
                                    <#else>
                                        <span class="text-muted small">Оброблено</span>
                                    </#if>
                                </td>
                            </tr>
                        </#if>
                    </#list>
                    <#if !hasIncoming>
                        <tr>
                            <td colspan="4" class="text-center text-muted py-4">На ваші вакансії поки ніхто не
                                відгукнувся.
                            </td>
                        </tr>
                    </#if>
                    </tbody>
                </table>
            </div>

            <#-- Блок: Вихідні запрошення, які компанія надіслала фахівцям -->
            <h3 class="fw-bold text-dark mb-3"><i class="bi bi-send text-info me-2"></i> Надіслані нами запрошення
                (Інвайти)</h3>
            <div class="table-responsive">
                <table class="table table-hover align-middle">
                    <thead class="table-light">
                    <tr>
                        <th>Кандидат</th>
                        <th>Запропонована вакансія</th>
                        <th>Контактна інформація</th>
                        <th>Статус відповіді</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#assign hasOutgoing = false>
                    <#list sentOffers as offer>
                        <#if offer.initiatedBy == "COMPANY">
                            <#assign hasOutgoing = true>
                            <tr>
                                <td>${(offer.candidate.firstName)!''} ${(offer.candidate.lastName)!'Анонім'}</td>
                                <td>${(offer.vacancy.position)!'Не вказано'}</td>
                                <td>${(offer.candidate.contactInfo)!'Не вказано'}</td>
                                <td>
                                    <#if offer.status == "PENDING">
                                        <span class="badge bg-warning text-dark">Очікує відповіді від кандидата</span>
                                    <#elseif offer.status == "ACCEPTED">
                                        <span class="badge bg-success">Кандидат прийняв запрошення!</span>
                                    <#elseif offer.status == "REJECTED">
                                        <span class="badge bg-danger">Кандидат відхилив</span>
                                    </#if>
                                </td>
                            </tr>
                        </#if>
                    </#list>
                    <#if !hasOutgoing>
                        <tr>
                            <td colspan="3" class="text-center text-muted py-4">Ви ще не надсилали особистих запрошень
                                кандидатам.
                            </td>
                        </tr>
                    </#if>
                    </tbody>
                </table>
            </div>
            <form method="post" action="/profile/company/delete" onsubmit="return confirm('Ви впевнені? Видалення компанії знищить усі створені вакансії та історію відгуків!');">
                <input type="hidden" name="${(_csrf.parameterName)!''}" value="${(_csrf.token)!''}"/>
                <button type="submit" class="btn btn-outline-danger btn-sm">Видалити компанію</button>
            </form>
        </div>
    </div>
</@p.pages>