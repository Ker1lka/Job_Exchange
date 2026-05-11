<#import "client/temp-client.ftl" as p>
<@p.pages>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6 text-center">
                <div class="card shadow-sm border-0 p-5">
                    <div class="mb-4">
                        <#-- Велика зелена галочка -->
                        <i class="bi bi-check-circle-fill text-success" style="font-size: 5rem;"></i>
                    </div>
                    <h2 class="fw-bold mb-3">Дякуємо за ваш відгук!</h2>
                    <p class="text-muted mb-4">
                        Вашу заявку успішно надіслано. Рекрутер перегляне ваш профіль найближчим часом.
                    </p>
                    <hr class="my-4">
                    <#-- Кнопка повернення на головну -->
                    <a href="/" class="btn btn-primary btn-lg px-5">
                        Повернутися на головну
                    </a>
                </div>
            </div>
        </div>
    </div>
</@p.pages>