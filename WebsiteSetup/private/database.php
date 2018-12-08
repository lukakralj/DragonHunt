<?php

require_once('db_credentials.php');

// Status constants:
define(QUERY_SUCCESSFUL, "QUERY_SUCCESSFUL.");
define(QUERY_ERROR, "QUERY_ERROR");
define(INVALID_USERNAME, "INVALID_USERNAME");
define(MISSING_PARAMETERS, "MISSING_PARAMETERS");
define(INVALID_REQUEST, "INVALID_REQUEST");

// Connect to the database and return the connection to it.
function db_connect() {
    $connection = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME);
    confirm_db_connect();
    return $connection;
}

// Close the $connection with the database.
function db_disconnect($connection) {
    if(isset($connection)) {
      mysqli_close($connection);
    }
}

// Escape the query string to prevent injection.
function db_escape($connection, $string) {
    return mysqli_real_escape_string($connection, $string);
}

// Check if the connection was succesful. Exit if not.
function confirm_db_connect() {
    if(mysqli_connect_errno()) {
        $response = [];
        $response["success"] = 0;
      $response["message"] = QUERY_ERROR;
      echo json_encode($response);
      exit;
    }
}

// Confirm that the result set is okay. Exit if not.
function confirm_result_set($result_set) {
    if (!$result_set) {
        $response = [];
        $response["success"] = 0;
        $response["message"] = QUERY_ERROR;
    }
}

?>
