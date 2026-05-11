<#import "client/temp-client.ftl" as p>
<#import "/spring.ftl" as s>
<@p.pages>
    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-md-7">
                <div class="card shadow border-0">
                    <div class="card-header bg-success text-white py-3 text-center">
                        <h3 class="mb-0">Реєстрація компанії</h3>
                    </div>
                    <div class="card-body p-4">
                        <form method="POST" action="/registration-company">
                            <@s.bind "users"/>
                            <h5 class="text-success mb-3">Акаунт представника</h5>
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label class="form-label small fw-bold">Логін (Username)</label>
                                    <@s.formInput "users.username", "class='form-control'"/>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label small fw-bold">Пароль</label>
                                    <input type="password" name="password" class="form-control" required>
                                </div>
                            </div>

                            <@s.bind "profiles"/>
                            <hr>
                            <h5 class="text-success mb-3">Дані компанії</h5>
                            <div class="mb-3">
                                <label class="form-label small fw-bold">Назва компанії</label>
                                <#-- Записуємо в firstName або middleName для простоти, якщо немає окремих полів -->
                                <@s.formInput "profiles.firstName", "class='form-control' placeholder='ТОВ Назва'"/>
                            </div>

                            <div class="mb-3">
                                <label class="form-label small fw-bold">Контактний телефон компанії</label>
                                <@s.formInput "profiles.phone", "class='form-control'"/>
                            </div>

                            <div class="mb-3">
                                <label class="form-label small fw-bold">Юридична адреса</label>
                                <@s.formInput "profiles.address", "class='form-control'"/>
                            </div>

                            <div class="mb-4">
                                <label class="form-label small fw-bold">Про компанію</label>
                                <textarea name="description" class="form-control" rows="3"></textarea>
                            </div>

                            <div class="d-grid">
                                <button type="submit" class="btn btn-success btn-lg">Створити бізнес-акаунт</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@p.pages>