<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

    <title>Test</title>
</head>
<body>
<form action="/zoominfo" method="post" class="form-inline">
    <div class="form-group" style="margin: auto;margin-top: 5%;width: 60%;">
        <input type="text" class="form-control" style="width: 80%;" name="url" id="company" aria-describedby="compnayHelp" placeholder="Enter houzz url">
        <button type="submit" style="margin: auto;" class="btn btn-primary">Submit</button>
    </div>

</form>


<table id="tablePreview" class="table" style="width: 60%;margin: auto;margin-top: 1%;">
    <!--Table head-->
    <thead>
    <tr>
        <th>#</th>
        <th>URL</th>
        <th>Status</th>
        <th>Available</th>
    </tr>
    </thead>
    <!--Table head-->
    <!--Table body-->
    <tbody>
<#list hash as prop,propval>
    <tr>
        <th scope="row">1</th>
        <td>${prop}</td>
        <td>${propval}</td>
        <td>
            <#if (propval >= 100)>
                <a href="/downloadZoomInfo" class="btn btn-primary">download</a>
            <#else >
                <button type="submit" disabled class="btn btn-primary">download</button>
            </#if>
        </td>
    </tr>
    <#else>
    </#list>
    </tbody>
    <!--Table body-->
</table>
<a class="btn btn-primary" href="/reset" role="button" style="    width: 36%;margin: auto; margin-top: 2%; display: block;">Reset</a>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</body>
</html>