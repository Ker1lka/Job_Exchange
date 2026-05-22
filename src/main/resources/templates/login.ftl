<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Вхід в систему</title>
    <link rel="icon" type="image/x-icon" href="/images/site-logo.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="card shadow border-0 p-4" style="width: 400px;">
        <h3 class="fw-bold text-center mb-4">Авторизація</h3>

        <form method="post" action="/login">
            <div class="mb-3">
                <label class="form-label">Логін</label>
                <input type="text" name="username" class="form-control" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Пароль</label>
                <input type="password" name="password" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary w-100 fw-bold">Увійти</button>
        </form>

        <div class="text-center mt-3">
            <a href="/" class="small text-muted">← На головну</a>
        </div>
    </div>
</div>
</body>
</html>