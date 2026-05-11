<#import "client/temp-client.ftl" as p>
<@p.pages>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card shadow border-0 rounded-4">
                    <div class="card-body p-5">
                        <h2 class="fw-bold text-center mb-4">Вхід у систему</h2>
                        <form method="post" action="/login">
                            <div class="mb-3">
                                <label class="form-label">Логін (Username)</label>
                                <input type="text" name="username" class="form-control" placeholder="Введіть логін" required>
                            </div>
                            <div class="mb-4">
                                <label class="form-label">Пароль</label>
                                <input type="password" name="password" class="form-control" placeholder="••••••••" required>
                            </div>
                            <div class="d-grid gap-2">
                                <button type="submit" class="btn btn-primary btn-lg">Увійти</button>
                            </div>
                        </form>
                        <div class="mt-4 text-center">
                            <p class="text-muted small mb-2">Ще не маєте акаунту?</p>
                            <div class="d-grid gap-2">
                                <a href="/registration" class="btn btn-outline-primary">Зареєструватись як Кандидат</a>
                                <a href="/registration-company" class="btn btn-outline-success">Зареєструвати Компанію</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@p.pages>