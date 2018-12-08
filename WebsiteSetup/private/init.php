<?php

// Assign file paths to PHP constants.
// __FILE__ returns the current path to this file
// dirname() returns the path to the parent directory
define("PRIVATE_PATH", dirname(__FILE__));
define("PROJECT_PATH", dirname(PRIVATE_PATH));
define("PUBLIC_PATH", PROJECT_PATH . '/public');
define("SHARED_PATH", PRIVATE_PATH . '/shared');

// Assign the root URL to a PHP constant.
$public_end = strpos($_SERVER['SCRIPT_NAME'], '/public');

$doc_root = "";
if ($public_end != 0) { // Domain root and project root differ.
    $doc_root = substr($_SERVER['SCRIPT_NAME'], 0, $public_end + 7);
}

define("WWW_ROOT", $doc_root);


require_once('functions.php');
require_once('database.php');
require_once('sql_handler.php');

$db = db_connect(); // Connect to database.

 ?>
