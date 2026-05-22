<#-- Імпорт шаблонів залежно від ролі користувача -->
<#if userRole?? && userRole == "ROLE_company">
    <#import "company/templ-company.ftl" as p>
<#else>
    <#import "candidate/templ-candidate.ftl" as p>
</#if>
<@p.pages>
    <#-- Верхній банер -->
    <div class="bg-primary text-white py-5 mb-5 shadow-sm">
        <div class="container text-center">
            <h1 class="display-4 fw-bold">Про наш проект</h1>
            <p class="lead">${(aboutTitle)!'Найкраща платформа для пошуку роботи та кандидатів'}</p>
        </div>
    </div>
    <#-- Основний контент сторінки -->
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-10">
                <div class="card border-0 shadow-lg overflow-hidden">
                    <div class="row g-0">

                        <#-- Блок з іконкою -->
                        <div class="col-md-4 bg-light d-flex align-items-center justify-content-center p-4">
                            <i class="bi bi-info-circle-fill text-primary" style="font-size: 8rem;"></i>
                        </div>

                        <#-- Текстовий блок "Хто ми такі" -->
                        <div class="col-md-8">
                            <div class="card-body p-5">
                                <h2 class="card-title mb-4">Хто ми такі?</h2>
                                <p class="card-text text-secondary fs-5" style="line-height: 1.8;">
                                    ${(aboutText)!'Ми — молода команда, що прагне спростити процес взаємодії між роботодавцями та шукачами роботи. Наша мета — створити прозорий та ефективний ринок праці.'}
                                </p>

                                <#-- Переваги платформи (Надійність та Швидкість) -->
                                <div class="row mt-5 g-4">
                                    <div class="col-sm-6">
                                        <div class="p-3 border-start border-4 border-success bg-light rounded shadow-sm">
                                            <h5 class="mb-1"><i class="bi bi-shield-check me-2 text-success"></i>Надійність</h5>
                                            <p class="small text-muted mb-0">Тільки перевірені компанії та вакансії.</p>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="p-3 border-start border-4 border-info bg-light rounded shadow-sm">
                                            <h5 class="mb-1"><i class="bi bi-lightning-charge me-2 text-info"></i>Швидкість</h5>
                                            <p class="small text-muted mb-0">Миттєвий відгук та зручний інтерфейс.</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@p.pages>