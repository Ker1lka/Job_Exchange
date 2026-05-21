<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Реєстрація компанії — Біржа Праці</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <style>
        body {
            background: linear-gradient(135deg, #2c3e50 0%, #3498db 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
        }
    </style>
</head>
<body>

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-lg border-0 rounded-4 p-4 p-sm-5 bg-white">

                <div class="text-center mb-4">
                    <div class="mb-2 text-primary fs-1"><i class="bi bi-building"></i></div>
                    <h2 class="fw-bold text-dark">Я роботодавець</h2>
                    <p class="text-muted">Зареєструйте компанію для публікації вакансій</p>
                </div>

                <form method="post" action="/registration/company">

                    <h5 class="fw-bold text-secondary mb-3 border-bottom pb-2">Інформація про фірму</h5>

                    <div class="mb-3">
                        <label for="companyName" class="form-label fw-semibold">Назва компанії / Організації</label>
                        <input type="text" class="form-control" id="companyName" name="companyName" required placeholder="Наприклад: ТОВ 'Інновація'">
                    </div>

                    <div class="mb-3">
                        <label for="address" class="form-label fw-semibold">Юридична адреса</label>
                        <input type="text" class="form-control" id="address" name="address" required placeholder="м. Київ, вул. Польова, 12">
                    </div>

                    <div class="mb-4">
                        <label for="contacts" class="form-label fw-semibold">Контактна особа (Телефон / ПІБ)</label>
                        <input type="text" class="form-control" id="contacts" name="contacts" required placeholder="+380670000000, Олег">
                    </div>

                    <h5 class="fw-bold text-secondary mb-3 border-bottom pb-2">Обліковий запис</h5>

                    <div class="mb-3">
                        <label for="username" class="form-label fw-semibold">(Логін)</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-envelope"></i></span>
                            <input type="text" class="form-control" id="username" name="username" required placeholder="hr@company.com">
                        </div>
                    </div>

                    <div class="mb-4">
                        <label for="password" class="form-label fw-semibold">Пароль</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-lock"></i></span>
                            <input type="password" class="form-control" id="password" name="password" required placeholder="Створіть пароль для входу">
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary btn-lg w-100 fw-bold shadow-sm mb-3">
                        Зареєструвати компанію
                    </button>

                    <div class="text-center">
                        <span class="text-muted">Вже маєте акаунт?</span>
                        <a href="/login" class="text-primary fw-semibold text-decoration-none">Увійти</a>
                        <br>
                        <a href="/" class="small text-secondary d-inline-block mt-3"><i class="bi bi-arrow-left"></i> На головну</a>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>