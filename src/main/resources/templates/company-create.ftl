<#import "client/temp-client.ftl" as p>
<@p.pages>
    <div class="container mt-4">
        <div class="card shadow-sm border-0">
            <div class="card-header bg-success text-white py-3">
                <h5 class="mb-0">Додати нову компанію</h5>
            </div>
            <div class="card-body p-4">
                <form action="/companies/create" method="POST">
                    <div class="mb-3">
                        <label class="form-label fw-bold">Назва компанії</label>
                        <#-- Поле: name -->
                        <input type="text" class="form-control" name="name" placeholder="Наприклад: Elenor" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-bold">Контактна інформація (Email/Телефон)</label>
                        <#-- Поле: contactInfo -->
                        <input type="text" class="form-control" name="contactInfo" placeholder="hr@company.com">
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-bold">Адреса</label>
                        <#-- Поле: address -->
                        <input type="text" class="form-control" name="address" placeholder="м. Київ, вул. Центральна 1">
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-bold">Опис компанії</label>
                        <#-- Поле: description -->
                        <textarea class="form-control" name="description" rows="5" placeholder="Розкажіть про діяльність компанії..."></textarea>
                    </div>

                    <hr class="my-4">

                    <div class="d-flex justify-content-end gap-2">
                        <#-- Повернення до створення вакансії, якщо передумали -->
                        <a href="/vacancy/create" class="btn btn-light border">Скасувати</a>
                        <button type="submit" class="btn btn-success px-4">Зберегти компанію</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


</@p.pages>