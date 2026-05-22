<#macro pages>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Job Exchange | Admin</title>
        <link rel="icon" type="image/x-icon" href="/images/site-logo.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    </head>
    <body class="bg-light">

    <div class="container-fluid">
        <!--Navigation Bar-->
        <#include "navbar.ftl"/>

        <!-- Main Content Row -->
        <div class="row">
            <!-- Work Area -->
            <div class="col-12 px-4">
                <#nested/>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
</#macro>