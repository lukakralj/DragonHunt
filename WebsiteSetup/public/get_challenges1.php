<?php
require_once("../init.php");

$response = [];

if (is_post_request()) {
    /*
    1. check credentials
    2. validate inputs?
    3. if parameter is missing reject
    4.
    */
}
else {
    $response["success"] = 0;
    $response["message"] = "Invalid request.";
    echo json_encode($response);
}



db_disconnect($db);

 ?>
