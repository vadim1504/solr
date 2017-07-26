<!DOCTYPE html>

<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
    <link href="css/full-slider.css" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="jumbotron">
        <div class="row">
            <div class="col-lg-6">
                <form action="http://localhost:8080/solr/search" method="get" style="position: ">
                    <div class="input-group">
                        <input name="text" type="text" class="form-control" placeholder="Search for book">
                        <span class="input-group-btn">
                            <button id="search" class="btn btn-default" type="submit">Go!</button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="row">
    <#if result??>
        <#list result as item ><div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4>
                        <i class="fa fa-fw fa-check"></i>
                    ${item.name}
                    </h4>
                </div>
                <div class="panel-body">
                    <p>
                        author: ${item.author}
                    </p>
                    <p>
                        date: ${item.year}
                    </p>
                    <form action="http://localhost:8080/solr/view" method="get">
                        <input type="hidden" name="name" value="${item.name}">
                        <input class="btn btn-default" type="submit" value="View">
                    </form>
                </div>
            </div>
        </div>
        </#list>
    </#if>
    </div>
</div>
</body>
</html>