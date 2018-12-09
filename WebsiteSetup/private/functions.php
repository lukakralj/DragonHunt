<?php

// $location needs to be a URL string - use url_for() to produce it.
function redirect_to($location) {
  header("Location: " . $location);
  exit();
}

// This produces the valid URL for the path specified.
// $script_path must be a path that starts in public folder (URLs cannot acces private folder!!!)
// Use this when setting a href paramenter dynamically, for example.
function url_for($script_path) {
  // add the leading '/' if not present

  if(strlen($script_path) == 0 || $script_path[0] != '/') {
    $script_path = "/" . $script_path;
  }
  return WWW_ROOT . $script_path;
}

// Converts a string into a valid string that can be used in the QUERY PART OF A URL (after the ?).
// Use this every time before passing something as a query parameter!
// Use urldecode() to decode, if necessary.
function u($string="") {
  return urlencode($string);
}

// Use this to convert the url before the ? symbol.
// Rarely used if the appropriate file names are used.
function raw_u($string="") {
  return rawurlencode($string);
}

// Converts a string into a valid HTML text that can then be echoed to the page.
// It replaces, for instance:
//  < --> &lt;
//  > --> &gt;
//  ...and so on...
// Use this every time before echoing something on the page!
function h($string="") {
  return htmlspecialchars($string);
}

// Check if the page that is being returned is a POST request.
// (Usually a POST request will be used when submitting a forum.)
function is_post_request() {
  return $_SERVER['REQUEST_METHOD'] == 'POST';
}

// Check if the page that is being returned is a GET request.
// (Usually a GET request will be used when a user enters a URL and presses enter, for example.)
function is_get_request() {
  return $_SERVER['REQUEST_METHOD'] == 'GET';
}


?>
