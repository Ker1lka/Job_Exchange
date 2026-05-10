<#import "client/temp-client.ftl" as p>
<@p.pages>
    <div class="container mt-4">
        <div class="row">
            <div class="col-md-8">
                <div class="card shadow-sm mb-4">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-start">
                            <div>
                                <h2 class="text-primary mb-1">${vacancy.position}</h2>
                                <p class="text-muted"><i class="bi bi-building me-2"></i>${vacancy.company.name}</p>
                            </div>
                            <span class="badge bg-success fs-6">${vacancy.salary} грн.</span>
                        </div>
                        <hr>
                        <h5 class="fw-bold mt-4">Опис вакансії</h5>
                        <div class="mt-3">
                            ${vacancy.company.description}
                        </div>
                        <h5 class="fw-bold mt-4">Вимоги</h5>
                        <p>${vacancy.requirements}</p>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card shadow-sm mb-4">
                    <div class="card-header bg-light fw-bold">Умови роботи</div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <i class="bi bi-clock me-2 text-primary"></i>
                            <b>Умови:</b> ${vacancy.workingConditions}
                        </li>
                        <li class="list-group-item">
                            <i class="bi bi-geo-alt me-2 text-primary"></i>
                            <b>Локація:</b> ${vacancy.requirements}
                        </li>
                        <li class="list-group-item text-center py-3">
                            <button class="btn btn-primary w-100">Відгукнутися</button>
                        </li>

                    </ul>
                    <form action="/addFavorite" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${vacancy.id}">
                        <button type="submit" class="btn btn-outline-warning">
                            <i class="bi bi-star"></i> В обране
                        </button>
                    </form>
                </div>

                <a href="/" class="btn btn-outline-secondary w-100">
                    <i class="bi bi-arrow-left me-2"></i>Назад до списку
                </a>
            </div>
        </div>
    </div>
</@p.pages>