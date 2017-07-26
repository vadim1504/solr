<!DOCTYPE html>

<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
    <link href="css/full-slider.css" rel="stylesheet">

</head>
<body>

<div id="myCarousel" class="carousel slide">
    <div class="carousel-inner">
        <#list pages as page>
            <#if page?index == 0>
                <div class="item active">
            <#else >
                <div class="item">
            </#if>
                <div class="fill">
                    <#list page as p>
                        <p class="txt">
                            ${p}
                        </p>
                    </#list>
                </div>
                <div class="carousel-caption">
                    <h2>${page?index}</h2>
                </div>
            </div>
        </#list>
    </div>
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
        <span class="icon-prev"></span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
        <span class="icon-next"></span>
    </a>

</div>


<script src="js/jquery.js"></script>

<script src="js/bootstrap.min.js"></script>
</body>
</html>