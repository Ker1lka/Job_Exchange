<#import "company/templ-company.ftl" as t>

<@t.pages>
    <div class="card shadow border-0 p-4">
        <div class="d-flex align-items-center border-bottom pb-3 mb-4">
            <a href="/profile/company" class="btn btn-outline-secondary btn-sm me-3">
                <i class="bi bi-arrow-left"></i> Назад до профілю
            </a>
            <h2 class="fw-bold text-dark mb-0">Анкета кандидата</h2>
        </div>

        <div class="row">
            <div class="col-md-4 text-center border-end">
                <div class="bg-light rounded p-4 mb-3">
                    <i class="bi bi-person-square text-secondary" style="font-size: 5rem;"></i>
                    <h3 class="fw-bold mt-2 mb-1">${(candidate.firstName)!''} ${(candidate.lastName)!''}</h3>
                    <p class="badge bg-primary fs-6">${(candidate.profession)!'Професія не вказана'}</p>
                </div>

                <div class="card p-3 border-0 bg-light text-start">
                    <h5 class="fw-bold"><i class="bi bi-telephone-outbound me-2 text-success"></i>Контакти</h5>
                    <hr class="my-2">
                    <p class="mb-1"><strong>Contact Info:</strong> ${(candidate.contactInfo)!'Не вказано'}</p>
                </div>
            </div>

            <div class="col-md-8 ps-md-4">
                <h4 class="fw-bold text-dark mb-3"><i class="bi bi-file-earmark-text text-primary me-2"></i>Про себе та резюме</h4>
                <div class="p-3 bg-light rounded mb-4" style="white-space: pre-line;">
                    Вік: ${(candidate.age)!'Не вказано.'}
                </div>
                <div class="p-3 bg-light rounded mb-4" style="white-space: pre-line;">
                    Остання посада: ${(candidate.lastJobPosition)!'Не вказано.'}
                </div>
                <div class="p-3 bg-light rounded mb-4" style="white-space: pre-line;">
                    Рівень навчання: ${(candidate.degree)!'Ступінь не вказан.'}
                </div>
                <div class="p-3 bg-light rounded mb-4" style="white-space: pre-line;">
                    Профіль навчання(спеціалізація): ${(candidate.specialization)!'Інформація не додана.'}
                </div>
                <div class="p-3 bg-light rounded mb-4" style="white-space: pre-line;">
                    Навчальний заклад: ${(candidate.institution)!'Інформація не додана.'}
                </div>

                <#-- Тут ти можеш додати інші поля твоєї сутності Candidates, наприклад навички, освіта тощо -->
            </div>
        </div>
    </div>
</@t.pages>