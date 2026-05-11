<#import "client/temp-client.ftl" as p>
<#import "/spring.ftl" as s>
<@p.pages>
    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-md-7">
                <div class="card shadow border-0">
                    <div class="card-header bg-primary text-white py-3 text-center">
                        <h3 class="mb-0">Реєстрація кандидата</h3>
                    </div>
                    <div class="card-body p-4">
                        <form method="POST" action="/registration">
                            <@s.bind "users"/>
                            <h5 class="text-primary mb-3">Дані для входу</h5>
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label class="form-label small fw-bold">Логін *</label>
                                    <@s.formInput "users.username", "class='form-control'"/>
                                    <small class="text-danger"><@s.showErrors ""/></small>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label small fw-bold">Пароль *</label>
                                    <input type="password" name="password" class="form-control" required>
                                </div>
                            </div>

                            <@s.bind "profiles"/>
                            <hr>
                            <h5 class="text-primary mb-3">Особиста інформація</h5>
                            <div class="row mb-3">
                                <div class="col-md-4">
                                    <label class="form-label small fw-bold">Прізвище</label>
                                    <@s.formInput "profiles.lastName", "class='form-control'"/>
                                </div>
                                <div class="col-md-4">
                                    <label class="form-label small fw-bold">Ім'я</label>
                                    <@s.formInput "profiles.firstName", "class='form-control'"/>
                                </div>
                                <div class="col-md-4">
                                    <label class="form-label small fw-bold">По-батькові</label>
                                    <@s.formInput "profiles.middleName", "class='form-control'"/>
                                </div>
                            </div>

                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label class="form-label small fw-bold">Телефон</label>
                                    <@s.formInput "profiles.phone", "class='form-control' placeholder='+380...'"/>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label class="form-label small fw-bold">Вік</label>
                                    <@s.formInput "profiles.age", "class='form-control' "/>
                                </div>
                            </div>

                            <div class="mb-4">
                                <label class="form-label small fw-bold">Адреса проживання</label>
                                <@s.formInput "profiles.address", "class='form-control'"/>
                            </div>

                            <div class="d-grid">
                                <button type="submit" class="btn btn-primary btn-lg">Зареєструватися</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@p.pages>