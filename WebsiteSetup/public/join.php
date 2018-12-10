<?php
require_once("../init.php");

$response = [];

if (is_post_request()) {
    if (isset($_POST["username"]) && isset($_POST["id"])) {

        if (is_valid_ongoing_id($_POST["id"])) {
            $participation = get_participation($_POST["username"], $_POST["id"]);
            if (!is_null($participation)) {
                $response["success"] = 1;
                $response["message"] = QUERY_SUCCESSFUL;
                $response["challenge"] = $participation;
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
            $response["message"] = QUERY_ERROR;
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
    $response["message"] = INVALID_REQUEST;
    echo json_encode($response);
}



db_disconnect($db);

 ?>
