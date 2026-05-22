<#import "company/templ-company.ftl" as p>
<@p.pages>
<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card shadow border-0 p-4">

                <div class="border-bottom pb-3 mb-4">
                    <h2 class="fw-bold text-dark mb-1">Редагування профілю компанії</h2>
                    <p class="text-muted mb-0">Оновіть інформацію про вашу організацію, щоб зацікавити кандидатів</p>
                </div>

                <#-- Форма редагування компанії -->
                <form method="post" action="/profile/company/edit">

                    <div class="mb-3">
                        <label for="companyName" class="form-label fw-bold">Назва компанії / Фірми</label>
                        <input type="text" class="form-control" id="companyName" name="companyName"
                               value="${(company.name)!''}" required placeholder="Наприклад, ТОВ 'IT-Вектор'">
                    </div>

                    <div class="mb-3">
                        <label for="address" class="form-label fw-bold">Юридична / Фактична адреса</label>
                        <input type="text" class="form-control" id="address" name="address"
                               value="${(company.address)!''}" placeholder="м. Київ, вул. Хрещатик, 1">
                    </div>

                    <div class="mb-3">
                        <label for="contacts" class="form-label fw-bold">Контактна особа та телефон</label>
                        <input type="text" class="form-control" id="contacts" name="contacts"
                               value="${(company.contactInfo)!''}" placeholder="Іван Іванов, +380671234567">
                    </div>

                    <div class="mb-4">
                        <label for="description" class="form-label fw-bold">Про компанію (опис діяльності)</label>
                        <textarea class="form-control" id="description" name="description" rows="6"
                                  placeholder="Розкажіть про сферу діяльності компанії, ваші цінності, проекти чи переваги...">${(company.description)!''}</textarea>
                    </div>

                    <div class="d-flex gap-2 justify-content-end">
                        <a href="/profile/company" class="btn btn-outline-secondary fw-bold px-4">Скасувати</a>
                        <button type="submit" class="btn btn-primary fw-bold px-4">Зберегти зміни</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>
</@p.pages>