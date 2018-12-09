<?php
require_once("../init.php");

$response = [];

if (is_post_request()) {
    if (isset($_POST["username"]) && isset($_POST["password"])) {
        if (valid_credentials($_POST["username"], $_POST["password"])) {
            $response["success"] = 1;
            $response["message"] = QUERY_SUCCESSFUL;
            echo json_encode($response);
        }
        else {
            $response["success"] = 0;
            $response["message"] = INVALID_CREDENTIALS;
            echo json_encode($response);
        }
    }
    else {
        $response["success"] = 0;
        $response["message"] = MISSING_PARAMETERS;
        echo json_encode($response);
    }
}
else {
    $response["success"] = 0;
    $response["message"] = "Invalid request.";
    echo json_encode($response);
}



db_disconnect($db);

 ?>
