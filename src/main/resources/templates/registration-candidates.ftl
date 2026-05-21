<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Реєстрація кандидата — Біржа Праці</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <style>
        body {
            background: linear-gradient(135deg, #f5f7fa 0%, #e4ecf7 100%);
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
            <div class="card shadow border-0 rounded-4 p-4 p-sm-5 bg-white">

                <div class="text-center mb-4">
                    <div class="mb-2 text-success fs-1"><i class="bi bi-person-badge"></i></div>
                    <h2 class="fw-bold text-dark">Шукаю роботу</h2>
                    <p class="text-muted">Створіть профіль кандидата для пошуку вакансій</p>
                </div>

                <form method="post" action="/registration/candidate">

                    <h5 class="fw-bold text-secondary mb-3 border-bottom pb-2">Особисті дані</h5>

                    <div class="mb-3">
                        <label for="lastName" class="form-label fw-semibold">Прізвище</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" required placeholder="Петренко">
                    </div>

                    <div class="mb-3">
                        <label for="firstName" class="form-label fw-semibold">Ім'я</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" required placeholder="Іван">
                    </div>

                    <div class="mb-4">
                        <label for="middleName" class="form-label fw-semibold">По батькові</label>
                        <input type="text" class="form-control" id="middleName" name="middleName" required placeholder="Миколайович">
                    </div>

                    <h5 class="fw-bold text-secondary mb-3 border-bottom pb-2">Дані для входу</h5>

                    <div class="mb-3">
                        <label for="username" class="form-label fw-semibold">Логін</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-envelope"></i></span>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                    </div>

                    <div class="mb-4">
                        <label for="password" class="form-label fw-semibold">Пароль</label>
                        <div class="input-group">
                            <span class="input-group-text"><i class="bi bi-lock"></i></span>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-success btn-lg w-100 fw-bold shadow-sm mb-3">
                        Зареєструватися
                    </button>

                    <div class="text-center">
                        <span class="text-muted">Вже маєте акаунт?</span>
                        <a href="/login" class="text-success fw-semibold text-decoration-none">Увійти</a>
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