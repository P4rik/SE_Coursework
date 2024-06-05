<#import "add/template.ftlh" as t>
<@t.template user>
    <!DOCTYPE html>
    <html lang="uk">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Перегляд рецепту</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f8f9fa;
            }
            .recipe-container {
                max-width: 800px;
                margin: 20px auto;
                padding: 20px;
                border: 5px solid #ccc;
                border-radius: 15px;
                background-color: #fff;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            .recipe-title {
                font-size: 36px;
                font-weight: 700;
                margin-bottom: 20px;
                color: #333;
            }
            .recipe-details {
                font-size: 18px;
                line-height: 1.6;
                color: #555;
            }
            .recipe-details b {
                color: #333;
            }
            .carousel-container {
                margin-bottom: 20px;
            }
        </style>
    </head>
    <body>
    <nav class="navbar navbar-light" style="background-color: #966224;">
        <style>
            .navbar-link {
                text-decoration: none; /* Відміна підсвічування посилань */
                color: white; /* Колір тексту */
            }
            .navbar-link:hover {
                text-decoration: none; /* Відміна підкреслення при наведенні */
                color: white; /* Колір тексту при наведенні */
            }
        </style>
        <div class="container">
            <a href="/" class="navbar-link">
                <span style="font-family: 'Homenaje', sans-serif; font-size: 48px; display: inline-block; vertical-align: middle;">ProstoFOOD</span>
                <img src="/static/images/Logo.png" alt="Logo" height="78" style="display: inline-block; vertical-align: middle;">
            </a>

            <#if user.email??>
                <div class="dropdown text-end">
                    <a href="/myprofile" class="navbar-link">
                        <img src="/static/images/avatar.png" alt="mdo" width="32" height="32">
                    </a>
                </div>
            <#else>
                <button class="btn btn-light my-2 my-sm-0" type="button" onclick="window.location.href = '/login'">
                    Увійти
                </button>
            </#if>
        </div>
    </nav>
    <div class="recipe-container">
        <h1 class="recipe-title">${recipe.title}</h1>
        <div id="carouselExampleDark" class="carousel carousel-white slide carousel-container">
            <div class="carousel-indicators">
                <#list images as image>
                    <#if image?index == 0>
                        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="${image?index}" class="active" aria-current="true" aria-label="Slide ${image?index + 1}"></button>
                    <#else>
                        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="${image?index}" aria-label="Slide ${image?index + 1}"></button>
                    </#if>
                </#list>
            </div>
            <div class="carousel-inner">
                <#list images as image>
                    <div class="carousel-item <#if image.previewImage>active</#if>">
                        <svg class="bd-placeholder-img bd-placeholder-img-lg d-block w-100" width="800" height="400" xmlns="http://www.w3.org/2000/svg"
                             role="img" aria-label=" :  " preserveAspectRatio="xMidYMid slice" focusable="false">
                            <title></title>
                            <rect width="100%" height="100%" fill="#966224"></rect>
                            <text x="50%" y="50%" fill="#777" dy=".3em"></text>
                        </svg>
                        <div class="container">
                            <div class="carousel-caption">
                                <a href="/images/${image.id}">
                                    <img class="recipe" style="height: 300px" src="/images/${image.id}">
                                </a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        <div class="recipe-details">
            <b>Короткий опис: </b>${recipe.description}<br>
            <b>Рецепт: </b>${recipe.process}<br>
            <b>Категорія: </b>${recipe.catalogy}<br>
            <b>Автор: </b><a href="/user/${recipe.user.id}" style="color: #fcb02a;">${recipe.user.name}</a><br>
        </div>
    </div>
    <footer style="background-color: #966224; color: white; padding: 1px 0; position: fixed; bottom: 0; left: 0; width: 100%;">
        <div class="container">
            <div style="text-align: center;">
                <p style="font-size: 20px;">Зв'яжіться з нами!</p>
            </div>
            <ul class="nav justify-content-center">
                <li class="nav-item" style="margin: 0 80px;">
                    <a class="nav-link active" aria-current="page" href="https://t.me/ProstoRyder" style="color: white;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" fill="currentColor" class="bi bi-telegram" viewBox="0 0 16 16">
                            <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M8.287 5.906q-1.168.486-4.666 2.01-.567.225-.595.442c-.03.243.275.339.69.47l.175.055c.408.133.958.288 1.243.294q.39.01.868-.32 3.269-2.206 3.374-2.23c.05-.012.12-.026.166.016s.042.12.037.141c-.03.129-1.227 1.241-1.846 1.817-.193.18-.33.307-.358.336a8 8 0 0 1-.188.186c-.38.366-.664.64.015 1.088.327.216.589.393.85.571.284.194.568.387.936.629q.14.092.27.187c.331.236.63.448.997.414.214-.02.435-.22.547-.82.265-1.417.786-4.486.906-5.751a1.4 1.4 0 0 0-.013-.315.34.34 0 0 0-.114-.217.53.53 0 0 0-.31-.093c-.3.005-.763.166-2.984 1.09"/>
                        </svg>
                    </a>
                </li>
                <li class="nav-item" style="margin: 0 80px;">
                    <a class="nav-link" href="https://x.com/Prosto_Ryder" style="color: white;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" fill="currentColor" class="bi bi-twitter-x" viewBox="0 0 16 16">
                            <path d="M12.6.75h2.454l-5.36 6.142L16 15.25h-4.937l-3.867-5.07-4.425 5.07H.316l5.733-6.57L0 .75h5.063l3.495 4.633L12.601.75Zm-.86 13.028h1.36L4.323 2.145H2.865z"/>
                        </svg>
                    </a>
                </li>
                <li class="nav-item" style="margin: 0 80px;">
                    <a class="nav-link" href="https://www.instagram.com/prosto__ryder/" style="color: white;">
                        <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" fill="currentColor" class="bi bi-instagram" viewBox="0 0 16 16">
                            <path d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.9 3.9 0 0 0-1.417.923A3.9 3.9 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.9 3.9 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.9 3.9 0 0 0-.923-1.417A3.9 3.9 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599s.453.546.598.92c.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.5 2.5 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.5 2.5 0 0 1-.92-.598 2.5 2.5 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233s.008-2.388.046-3.231c.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92s.546-.453.92-.598c.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92m-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217m0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334"/>
                        </svg>
                    </a>
                </li>
            </ul>
        </div>
    </footer>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
    </body>
    </html>
</@t.template>