<#macro pages>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Job Exchange</title>
        <link rel="icon" type="image/x-icon" href="/images/site-logo.png">
        <link rel="stylesheet" href="/static/css/print.css" media="print">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

    <div class="container-fluid">
        <!--Navigation Bar-->
        <#include "navbar.ftl"/>

        <!-- Main Content Row -->
        <div class="row g-4">
            <!-- Left Panel -->
            <div class="col-md-2">
                <#include "left-panel.ftl"/>
            </div>
            <!-- Work Area -->
            <div class="col-md-10">
                <#nested/>
            </div>
        </div>

        <hr class="mt-5 mb-4">

        <!--Footer -->
        <#include "footer.ftl"/>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
</#macro>