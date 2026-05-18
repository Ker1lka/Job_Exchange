<#import "manager/templ-manager.ftl" as p>
<@p.pages>
    <div class="container-fluid mt-4 pb-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="h4">Керування вакансіями</h2>
        <span class="badge bg-primary">Усього: ${vacancies?size}</span>
    </div>

    <div class="row row-cols-1 row-cols-xl-2 g-4">
    <#if vacancies??>
        <#list vacancies as vacancy>
            <div class="col">
                <div class="card h-100 shadow-sm border-0 border-start border-4 border-primary">
                    <div class="card-body py-3">
                        <form method="post" action="/updateVacancy">
                            <input type="hidden" name="id" value="${vacancy.id}">
                            <div class="row g-2 align-items-end">
                                <div class="col-md-1">
                                    <label class="small text-muted mb-1">Id</label>
                                    <input name="vacancyId" type="text" class="form-control bg-light" value="${(vacancy.id)!""}">
                                </div>
                                <div class="col-md-5">
                                    <label class="small text-muted mb-1">Компанія</label>
                                    <span type="text" class="form-control bg-light">${(vacancy.company.name)!""}</span>
                                </div>
                                <div class="col-md-6">
                                    <label class="small text-muted mb-1">Позиція</label>
                                    <input type="text" name="position" class="form-control" value="${vacancy.position!""}">
                                </div>
                                <div class="col-12 mt-3">
                                    <label class="small text-muted mb-1">Вимоги</label>
                                    <textarea name="requirements" class="form-control" rows="2">${vacancy.requirements!""}</textarea>
                                </div>
                                <div class="col-md-4 mt-3">
                                    <label class="small text-muted mb-1">Зарплата (₴)</label>
                                    <input type="number" name="salary" class="form-control fw-bold text-success" value="${vacancy.salary!""}">
                                </div>
                                <div class="col-md-8 mt-3 d-flex gap-2 justify-content-end">
                                    <button type="submit" class="btn btn-primary"><i class="bi bi-arrow-repeat"></i> Оновити</button>
                        </form>
                        <form method="post" action="/deleteVacancy">
                            <input type="hidden" name="id" value="${vacancy.id}">
                            <button type="submit" class="btn btn-outline-danger" onclick="return confirm('Видалити?')">X<i class="bi bi-trash"></i></button>
                        </form>
                    </div>
                </div>
            </div>
            </div>
            </div>
        </#list>
    </#if>
    </div>
    </div>
</@p.pages>