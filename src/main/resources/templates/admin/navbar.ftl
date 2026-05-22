<nav class="navbar navbar-expand-lg bg-dark navbar-dark rounded-bottom shadow-sm mb-4">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold text-danger" href="/"><i class="bi bi-shield-lock-fill me-2"></i>Admin Panel</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#adminNavbar" aria-controls="adminNavbar" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="adminNavbar">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 gap-2">
                <li class="nav-item">
                    <a href="/admin/users" class="btn btn-outline-light btn-sm"><i class="bi bi-people me-2"></i>Користувачі</a>
                </li>
                <li class="nav-item">
                    <a href="/admin/settings" class="btn btn-outline-light btn-sm"><i class="bi bi-gear me-2"></i>Налаштування сайту</a>
                </li>
            </ul>
            <div class="d-flex">
                <a href="/logout" class="btn btn-sm btn-danger fw-bold">Вихід</a>
            </div>
        </div>
    </div>
</nav>