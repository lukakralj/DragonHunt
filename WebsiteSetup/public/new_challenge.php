<?php
require_once("../init.php");

$response = [];

if (is_post_request()) {
    if(isset($_POST["title"]) &&
        isset($_POST["task"]) &&
        isset($_POST["location"]) &&
        isset($_POST["challengeOwner"]) &&
        isset($_POST["minParticipants"])&&
        isset($_POST["isPrivate"])) {

        if (!surname_exists($_POST["challengeOwner"])) {
            $response["success"] = 0;
            $response["message"] = INVALID_USERNAME;
            echo json_encode($response);
        }
        else {
            if (insert_new_challenge($_POST["title"],
                                    $_POST["task"],
                                    $_POST["location"],
                                    $_POST["challengeOwner"],
                                    $_POST["minParticipants"],
                                    $_POST["isPrivate"])) {
                $response["success"] = 1;
                $response["message"] = QUERY_SUCCESSFUL;
                echo json_encode($response);
            }
            else {
                $response["success"] = 0;
                $response["message"] = QUERY_ERROR;
                echo json_encode($response);
            }
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
