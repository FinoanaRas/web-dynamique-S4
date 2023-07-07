<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/css/style.css">
    <title>Document</title>
</head>
    <body>
        <form action="login.do" method="post">
            <input type="text" name="name">
            <input type="text" name="gender">
            <input type="date" name="dtn">
            <input type="number" name="num">
            <input type="checkbox" name="langues" value="fr" id="fr">
            <label for="fr">fr</label>
            <input type="checkbox" name="langues" value="en" id="en">
            <label for="en">en</label>
            <input type="checkbox" name="langues" value="mg" id="mg">
            <label for="mg">mg</label>
            <input type="submit" value="valider">
        </form>
        
    </body>
</html>