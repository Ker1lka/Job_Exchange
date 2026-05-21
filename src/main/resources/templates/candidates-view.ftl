<#import "client/temp-client.ftl" as p>
<@p.pages>
    <div class="col-md-10">
        <div class="row g-4">
            <#if candidate??>
                <div class="col-12">
                    <div class="card shadow-sm">
                        <div class="card-body d-flex align-items-center">
                            <div>
                                <img src="${(candidate.image)!'/images/avatars/avatar.jpg'}"
                                     class="img-thumbnail rounded-circle shadow-sm"
                                     style="width: 140px; height: 140px; object-fit: cover;"
                                     alt="Avatar">
                            </div>
                            <div class="ms-4">
                                <h3 class="mb-0">${candidate.firstName} ${candidate.lastName} ${candidate.middleName}</h3>
                            </div>
                        </div>
                    </div>
                    <div class="card shadow-sm">
                        <div class="card-header bg-light fw-bold">Контактна інформація</div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">
                                <i class="bi bi-envelope-at me-2"></i>Пошта: ${(candidate.email!'не вказано')}
                            </li>
                            <li class="list-group-item">
                                <i class="bi bi-telephone me-2"></i>Телефон: ${(candidate.phone!'не вказано')}
                            </li>
                            <li class="list-group-item">
                                <i class="bi bi-geo-alt me-2"></i>Місто: ${(candidate.address!'не вказано')}
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-12">
                    <#if candidate.jobRequirements??>
                        <div class="card shadow-sm">
                            <div class="card-header bg-light fw-bold">Резюме</div>
                            <ul class="list-group list-group-flush">
                                <#if candidate.experiences??>
                                <#list candidate.experiences as experiences>
                                    <li class="list-group-item">
                                        <i class="bi bi-envelope-at me-2"></i>Досвід: ${(experiences.position!'не вказано')}, ${(experiences.timeOfExperience!'не вказано')} рокии
                                    </li>
                                </#list>
                                </#if>
                                <li class="list-group-item">
                                    <i class="bi bi-telephone me-2"></i>Вимоги до праці: ${(candidate.jobRequirements.preferredLocation!'не вказано')}, ${(candidate.jobRequirements.minSalary!'не вказано')}, ${(candidate.jobRequirements.profession.title!'не вказано')}
                                </li>
                            </ul>
                        </div>
                    </#if>
                </div>
                <div class="col-md-6">
                    <div class="card h-100 shadow-sm">
                        <div class="card-header fw-bold">Освіта</div>
                        <ul class="list-group list-group-flush">
                            <#if candidate.educations??>
                                <#list candidate.educations as edu>
                                    <li class="list-group-item">
                                        <h6>Освітній заклад: ${(edu.institution!'не вказано')}, ${(edu.specialization!'не вказано')}</h6>
                                        <small class="text-muted">Років навчання: ${(edu.graduationYear!'не вказано')}, ${(edu.degree!'не вказано')}</small>
                                    </li>
                                </#list>
                            </#if>
                        </ul>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="card h-100 shadow-sm">
                        <div class="card-header fw-bold">Навички</div>
                        <#if candidate.profileHasProfessions??>
                        <#list candidate.profileHasProfessions as profession>
                            <div class="card-body">
                                <span class="badge bg-info text-dark">${(profession.profession.title!'не вказано')}</span>
                                <span class="badge bg-info text-dark">${(profession.skillLevel!'не вказано')}</span>
                            </div>
                        </#list>
                        </#if>
                    </div>
                </div>
            </#if>
        </div>
    </div>
</@p.pages>
