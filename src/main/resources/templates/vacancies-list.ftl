<#import "client/temp-client.ftl" as p>
<@p.pages>
    <div class="row g-4">
        <#if vacancies??>
            <#list vacancies as vacancy>
                <div class="col-sm-6">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">${(vacancy.position!'')}</h5>
                            <p class="card-text"><b>Requirements: ${(vacancy.requirements!'')}</b>
                                <br>Description: Company name ${(vacancy.company.name!'')}. ${(vacancy.company.description!'')} <br> ${(vacancy.salary!'')}₴
                            </p>
                            <a href="/vacancy/${vacancy.id}" class="btn btn-primary">Details</a>
                            <form action="/addFavorite" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="${vacancy.id}">
                                <button type="submit" class="btn btn-outline-warning">
                                    <i class="bi bi-star"></i> В обране
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </#list>
        </#if>
    </div>
</@p.pages>