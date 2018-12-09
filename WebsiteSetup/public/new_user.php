<?php
require_once("../init.php");

$response = [];

if (is_post_request()) {
    if(isset($_POST["username"]) && isset($_POST["password"]) && isset($_POST["name"]) && isset($_POST["surname"])) {
        $username = $_POST["username"];

        if (surname_exists($username)) {
            $response["success"] = 0;
            $response["message"] = INVALID_USERNAME;
            echo json_encode($response);
        }
        else {
            $password = $_POST["password"];
            $name = $_POST["name"];
            $surname = $_POST["surname"];

            if (insert_new_user($username, $password, $name, $surname)) {
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
