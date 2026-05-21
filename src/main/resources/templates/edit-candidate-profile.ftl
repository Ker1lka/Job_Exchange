<#import "candidate/templ-candidate.ftl" as p>
<@p.pages>
    <div class="container my-5">
        <div class="card shadow border-0 p-4" style="max-width: 700px; margin: 0 auto;">
            <h3 class="fw-bold mb-4 text-center text-primary">Редагування профілю кандидата</h3>

            <form method="post" action="/profile/candidate/edit">

                <h5 class="fw-bold mb-3 text-secondary border-bottom pb-2">Особисті дані</h5>
                <div class="mb-3">
                    <label class="form-label">Ім'я</label>
                    <input type="text" name="firstName" class="form-control" value="${(candidate.firstName)!''}" required placeholder="Іванов Іван Іванович">
                </div>
                <div class="mb-3">
                    <label class="form-label">Прізвище</label>
                    <input type="text" name="lastName" class="form-control" value="${(candidate.lastName)!''}" required placeholder="Іванов Іван Іванович">
                </div>
                <div class="mb-3">
                    <label class="form-label">По батькові</label>
                    <input type="text" name="middleName" class="form-control" value="${(candidate.middleName)!''}" required placeholder="Іванов Іван Іванович">
                </div>

                <div class="mb-3">
                    <label class="form-label">Контактні координати</label>
                    <input type="text" name="contactInfo" class="form-control" value="${(candidate.contactInfo)!''}" placeholder="+380... або email@example.com">
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Сімейний стан</label>
                        <input type="text" name="familyStatus" class="form-control" value="${(candidate.familyStatus)!''}" placeholder="Одружений / неодружена">
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Житлові умови</label>
                        <input type="text" name="housingConditions" class="form-control" value="${(candidate.housingConditions)!''}" placeholder="Власна квартира / орендоване житло">
                    </div>
                </div>

                <h5 class="fw-bold mt-4 mb-3 text-secondary border-bottom pb-2">Професія та досвід</h5>
                <div class="mb-3">
                    <label class="form-label">Ваша професія / Спеціальність</label>
                    <input type="text" name="profession" class="form-control" value="${(candidate.profession)!''}" required placeholder="Наприклад: Java Developer, Бухгалтер">
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Останнє місце роботи (Фірма)</label>
                        <input type="text" name="lastJobPlace" class="form-control" value="${(candidate.lastJobPlace)!''}" placeholder="ТОВ Назва Компанії">
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Посада на останній роботі</label>
                        <input type="text" name="lastJobPosition" class="form-control" value="${(candidate.lastJobPosition)!''}" placeholder="Наприклад: Менеджер, Інженер">
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Причина звільнення</label>
                    <input type="text" name="leavingReason" class="form-control" value="${(candidate.leavingReason)!''}" placeholder="За власним бажанням / скорочення">
                </div>

                <h5 class="fw-bold mt-4 mb-3 text-secondary border-bottom pb-2">Освіта</h5>
                <div class="mb-3">
                    <label class="form-label">Навчальний заклад</label>
                    <input type="text" name="institution" class="form-control" value="${(candidate.institution)!''}" placeholder="Наприклад: КНЕУ ім. Вадима Гетьмана">
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Спеціалізація</label>
                        <input type="text" name="specialization" class="form-control" value="${(candidate.specialization)!''}" placeholder="Наприклад: 121 Інженерія ПЗ">
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Ступінь</label>
                        <select name="degree" class="form-select">
                            <option value="Середня" ${(candidate.educationDegree?? && candidate.degree == "Середня")?string("selected", "")}>Середня</option>
                            <option value="Бакалавр" ${(candidate.educationDegree?? && candidate.degree == "Бакалавр")?string("selected", "")}>Бакалавр</option>
                            <option value="Магістр" ${(candidate.educationDegree?? && candidate.degree == "Магістр")?string("selected", "")}>Магістр</option>
                        </select>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Роки навчання</label>
                    <input type="text" name="educationYears" class="form-control" value="${(candidate.educationYears)!''}" placeholder="Наприклад: 2022-2026">
                </div>

                <h5 class="fw-bold mt-4 mb-3 text-secondary border-bottom pb-2">Вимоги до майбутньої роботи</h5>
                <div class="mb-4">
                    <label class="form-label">Побажання щодо роботи</label>
                    <textarea name="jobRequirements" class="form-control" rows="3" placeholder="Очікувана зарплата, графік (віддалено/офіс), умови праці...">${(candidate.jobRequirements)!''}</textarea>
                </div>

                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-success w-100 py-2 fw-bold">Зберегти зміни</button>
                    <a href="/profile/candidate" class="btn btn-outline-secondary w-100 py-2">Скасувати</a>
                </div>
            </form>
        </div>
    </div>
</@p.pages>