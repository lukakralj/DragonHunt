<?php

function surname_exists($username) {
    global $db;

    $sql = "SELECT COUNT(*) AS valid FROM Users ";
    $sql .= "WHERE username = '" . db_escape($db, $username). "' ";

    $result = mysqli_query($db, $sql);
    confirm_result_set($result);

    $isValid = (mysqli_fetch_assoc($result)["valid"] > 0);
    mysqli_free_result($result);
    return $isValid;
}

function insert_new_user($username, $password, $name, $surname) {
    global $db;

    $sql = "INSERT INTO Users ";
    $sql .= "VALUES ('" . db_escape($db, $username) . "', '"
                        . db_escape($db, $password) . "', '"
                        . db_escape($db, $name) . "', '"
                        . db_escape($db, $surname) . "')";

    $result = mysqli_query($db, $sql);
    return $result;
}

function insert_new_challenge($title, $task, $location, $owner, $minParticipants, $isPrivate) {
    global $db;

    $sql = "INSERT INTO Challenges (title, task, location, challengeOwner, minParticipants, isPrivate) ";
    $sql .= "VALUES ('" . db_escape($db, $title) . "', '"
                        . db_escape($db, $task) . "', '"
                        . db_escape($db, $location) . "', '"
                        . db_escape($db, $owner) . "', '"
                        . db_escape($db, $minParticipants) . "', '"
                        . db_escape($db, $isPrivate) . "')";

    $result = mysqli_query($db, $sql);
    return $result;
}
 ?>
