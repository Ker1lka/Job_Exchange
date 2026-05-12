<#import "admin/templ-admin.ftl" as p>
<@p.pages>
    <div class="container mt-4">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/">Головна</a></li>
                <li class="breadcrumb-item active">Редактор About Us</li>
            </ol>
        </nav>

        <div class="card shadow border-0">
            <div class="card-header bg-dark text-white p-3">
                <h5 class="mb-0"><i class="bi bi-pencil-square me-2"></i>Керування контентом "Про нас"</h5>
            </div>
            <div class="card-body p-4">
                <form action="/updateAboutUs" method="post">
                    <#-- Поле для заголовку -->
                    <div class="mb-4">
                        <label class="form-label fw-bold text-secondary">
                            <i class="bi bi-type-h1 me-2"></i>Головний заголовок сторінки
                        </label>
                        <input type="text" name="aboutTitle" class="form-control form-control-lg shadow-sm"
                               placeholder="Введіть короткий заголовок..." value="${(aboutTitle)!''}">
                        <div class="form-text">Цей текст відображається у синій секції зверху.</div>
                    </div>

                    <#-- Поле для основного тексту -->
                    <div class="mb-4">
                        <label class="form-label fw-bold text-secondary">
                            <i class="bi bi-justify-left me-2"></i>Основний опис проекту
                        </label>
                        <textarea name="aboutText" class="form-control shadow-sm" rows="8"
                                  placeholder="Напишіть детальну історію проекту...">${(aboutText)!''}</textarea>
                        <div class="form-text">Використовуйте зрозумілу та приязну мову.</div>
                    </div>

                    <#-- Кнопки управління -->
                    <div class="d-flex justify-content-between align-items-center mt-5 border-top pt-4">
                        <a href="/about-us" target="_blank" class="btn btn-outline-secondary">
                            <i class="bi bi-eye me-2"></i>Попередній перегляд
                        </a>
                        <button type="submit" class="btn btn-primary btn-lg px-5 shadow">
                            <i class="bi bi-cloud-check me-2"></i>Зберегти зміни
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</@p.pages>