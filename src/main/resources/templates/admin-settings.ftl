<#import "admin/templ-admin.ftl" as p>
<@p.pages>
    <h2 class="fw-bold text-dark mb-4">Конфігурація контенту платформи</h2>

    <div class="card shadow-sm border-0 p-4 bg-white" style="max-width: 800px;">
        <h5 class="fw-bold text-primary mb-3"><i class="bi bi-file-earmark-text me-2"></i>Редагування сторінки "Про нас"</h5>
        <p class="text-muted small">Введений нижче текст буде динамічно відображатися на загальній сторінці опису нашої біржі праці.</p>

        <form method="post" action="/admin/settings/about">
            <div class="mb-3">
                <textarea name="newText" class="form-control" rows="8" required>${currentAboutText!''}</textarea>
            </div>
            <button type="submit" class="btn btn-primary px-4 fw-bold">Зберегти нові дані</button>
        </form>
    </div>
</@p.pages>