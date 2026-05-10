<#import "client/temp-client.ftl" as p>
<@p.pages>
    <div class="col-md-10">
        <div class="row g-4">
            <#if profile??>
                <div class="col-12">
                    <div class="card shadow-sm">
                        <div class="card-body d-flex align-items-center">
                            <div>
                                <img src="${(profile.image)!'/images/avatars/avatar.jpg'}"
                                     class="img-thumbnail rounded-circle shadow-sm"
                                     style="width: 140px; height: 140px; object-fit: cover;"
                                     alt="Avatar">
                            </div>
                            <div class="ms-4">
                                <h3 class="mb-0">${profile.firstName} ${profile.lastName} ${profile.middleName}</h3>
                            </div>
                        </div>
                    </div>
                    <div class="card shadow-sm">
                        <div class="card-header bg-light fw-bold">Контактна інформація</div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">
                                <i class="bi bi-envelope-at me-2"></i>Пошта: ${profile.email}
                            </li>
                            <li class="list-group-item">
                                <i class="bi bi-telephone me-2"></i>Телефон: ${profile.phone}
                            </li>
                            <li class="list-group-item">
                                <i class="bi bi-geo-alt me-2"></i>Місто: ${profile.address!}
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-12">
                    <#if profile.jobRequirements??>
                        <div class="card shadow-sm">
                            <div class="card-header bg-light fw-bold">Резюме</div>
                            <ul class="list-group list-group-flush">
                                <#list profile.experiences as experiences>
                                    <li class="list-group-item">
                                        <i class="bi bi-envelope-at me-2"></i>Досвід: ${experiences.position}, ${experiences.timeOfExperience} роки
                                    </li>
                                </#list>
                                <li class="list-group-item">
                                    <i class="bi bi-telephone me-2"></i>Вимоги до праці: ${profile.jobRequirements.preferredLocation}, ${profile.jobRequirements.minSalary}, ${profile.jobRequirements.profession.title}
                                </li>
                            </ul>
                        </div>
                    </#if>
                </div>
                <div class="col-md-6">
                    <div class="card h-100 shadow-sm">
                        <div class="card-header fw-bold">Освіта</div>
                        <ul class="list-group list-group-flush">
                            <#if profile.educations??>
                                <#list profile.educations as edu>
                                    <li class="list-group-item">
                                        <h6>Освітній заклад: ${edu.institution}, ${edu.specialization}</h6>
                                        <small class="text-muted">Років навчання: ${edu.graduationYear}, ${edu.degree}</small>
                                    </li>
                                </#list>
                            </#if>
                        </ul>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="card h-100 shadow-sm">
                        <div class="card-header fw-bold">Навички</div>
                        <#list profile.profileHasProfessions as profession>
                            <div class="card-body">
                                <span class="badge bg-info text-dark">${profession.profession.title}</span>
                                <span class="badge bg-info text-dark">${profession.skillLevel}</span>
                            </div>
                        </#list>
                    </div>
                </div>
            </#if>
        </div>
    </div>
</@p.pages>