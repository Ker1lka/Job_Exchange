<#import "client/temp-client.ftl" as p>
<@p.pages>
    <div class="container mt-4">
        <div class="card shadow-sm border-0">
            <div class="card-header bg-primary text-white py-3">
                <h5 class="mb-0">Створення нової вакансії</h5>
            </div>
            <div class="card-body p-4">
                <form action="/vacancies/create" method="POST">
                    <div class="row">
                        <div class="col-md-8 mb-3">
                            <label class="form-label fw-bold">Назва посади</label>
                            <#-- Поле: position -->
                            <input type="text" class="form-control" name="position" required>

                        </div>
                        <div class="col-md-4 mb-3">
                            <label class="form-label fw-bold">Зарплата (грн.)</label>
                            <#-- Поле: salary (Double) -->
                            <input type="number" step="0.01" class="form-control" name="salary">

                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-bold">Оберіть компанію</label>
                        <div class="input-group">
                            <input list="companyOptions" name="companyName">
                            <#-- Кнопка переходу на створення компанії -->
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-bold">Умови роботи</label>
                        <input type="text" class="form-control" name="workingConditions">

                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-bold">Вимоги до кандидата</label>
                        <#-- Поле: requirements -->
                        <textarea class="form-control" name="requirements" rows="4"></textarea>

                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-check form-switch">
                                <#-- Поле: housingProvided -->
                                <input class="form-check-input" type="checkbox" name="housingProvided" id="housing">
                                <label class="form-check-label" for="housing">Надається житло</label>
                            </div>
                        </div>
                    </div>

                    <hr class="my-4">

                    <div class="d-flex justify-content-end gap-2">
                        <a href="/" class="btn btn-light border">Скасувати</a>
                        <button type="submit" class="btn btn-primary px-4">Зберегти</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@p.pages>