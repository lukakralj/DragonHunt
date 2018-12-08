<?php
require_once("../init.php");

$response = [];

if (is_post_request()) {
    if (isset($_POST["username"]) && isset($_POST["password"])) {
        if (valid_credentials($username, $password)) {
            $response["success"] = 1;
            $response["message"] = QUERY_SUCCESSFUL;
        }
        else {
            $response["success"] = 0;
            $response["message"] = INVALID_CREDENTIALS;
        }
    }
    else {
        $response["success"] = 0;
        $response["message"] = MISSING_PARAMETERS;
    }
}
else {
    $response["success"] = 0;
    $response["message"] = "Invalid request.";
    echo json_enocode($response);
}



db_disconnect($db);

 ?>
