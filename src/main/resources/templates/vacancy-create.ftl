<#import "client/temp-client.ftl" as p>
<@p.pages>
    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-md-10">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/">Вакансії</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Нова вакансія</li>
                    </ol>
                </nav>

                <div class="card shadow-sm border-0">
                    <div class="card-header bg-primary text-white py-3">
                        <h5 class="mb-0"><i class="bi bi-plus-circle me-2"></i>Створення нової вакансії</h5>
                    </div>
                    <div class="card-body p-4">
                        <form action="/vacancies/create" method="POST">
                            <div class="row">
                                <#-- Назва посади -->
                                <div class="col-md-8 mb-3">
                                    <label for="position" class="form-label fw-bold">Назва посади</label>
                                    <input type="text" class="form-control" id="position" name="position" required>
                                </div>

                                <#-- Зарплата -->
                                <div class="col-md-4 mb-3">
                                    <label for="salary" class="form-label fw-bold">Зарплата (грн.)</label>
                                    <div class="input-group">
                                        <input type="number" class="form-control" id="salary" name="salary" placeholder="0">
                                        <span class="input-group-text">₴</span>
                                    </div>
                                </div>
                            </div>

                            <#-- Умови роботи (те саме поле, про яке ми говорили) -->
                            <div class="mb-3">
                                <label for="working_conditions" class="form-label fw-bold">Умови роботи</label>
                                <input type="text" class="form-control" id="working_conditions" name="working_conditions">
                            </div>

                            <div class="mb-3">
                                <label for="working_conditions" class="form-label fw-bold">Назва компанії</label>
                                <input type="text" class="form-control" id="working_conditions" name="working_conditions">
                            </div>
                            <#-- Опис вакансії -->
                            <div class="mb-3">
                                <label for="description" class="form-label fw-bold">Опис вакансії</label>
                                <textarea class="form-control" id="description" name="description" rows="5"></textarea>
                            </div>

                            <#-- Вимоги -->
                            <div class="mb-3">
                                <label for="requirements" class="form-label fw-bold">Вимоги до кандидата</label>
                                <textarea class="form-control" id="requirements" name="requirements" rows="4"
                                          placeholder="Технічні навички, досвід..."></textarea>
                            </div>

                            <hr class="my-4">

                            <div class="d-flex justify-content-end gap-2">
                                <a href="/vacancies" class="btn btn-light border">Скасувати</a>
                                <button type="submit" class="btn btn-primary px-4">Опублікувати вакансію</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@p.pages>