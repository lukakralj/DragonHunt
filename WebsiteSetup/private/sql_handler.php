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

    if (!$result) {
        return $result;
    }

    $sql = "INSERT INTO OngoingChallenges (challengeId) ";
    $sql .= "VALUES ('" . db_escape($db, mysqli_insert_id($db)) . "')";

    $result = mysqli_query($db, $sql);

    return $result;
}

function valid_credentials($username, $password) {
    global $db;

    $sql = "SELECT hashed_password FROM Users ";
    $sql .= "WHERE username='" . db_escape($db, $username) . "'";

    $result = mysqli_query($db, $sql);
    confirm_result_set($result);

    if (mysqli_num_rows($result) == 0) {
        mysqli_free_result($result);
        return false;
    }

    $hashed_password = mysqli_fetch_assoc($result)["hashed_password"];
    mysqli_free_result($result);

    return $hashed_password == $password;
}

function get_ongoing_challenge_by_id($id) {
    global $db;

    $sql = "SELECT * FROM OngoingChallenges NATURAL JOIN Challenges ";
    $sql .= "WHERE id='" . db_escape($db, $id). "'";

    $result = mysqli_query($db, $sql);
    confirm_result_set($result);

    $row = mysqli_fetch_assoc($result);
    mysqli_free_result($result);

    return $row;
}

function is_valid_ongoing_id($id) {
    global $db;

    $sql = "SELECT COUNT(*) AS valid FROM OngoingChallenges ";
    $sql .= "WHERE id='" . db_escape($db, $id) . "' ";

    $result = mysqli_query($db, $sql);
    confirm_result_set($result);
    // Return result set - remember to free memory
    $isValid = (mysqli_fetch_assoc($result)["valid"] > 0);
    mysqli_free_result($result);
    return $isValid;
}

function get_existing_participation($username, $id) {
    global $db;

    $sql = "SELECT * FROM OngoingChallenges INNER JOIN Challenges ON OngoingChallenges.challengeId=Challenges.challengeId INNER JOIN Participation ON Participation.ongoingId=OngoingChallenges.id ";
    $sql .= "WHERE username='" . db_escape($db, $username) . "' AND id='" . db_escape($db, $id) . "'";

    $result = mysqli_query($db, $sql);
    confirm_result_set($result);

    if (mysqli_num_rows($result) == 0) {
        mysqli_free_result($result);
        return NULL;
    }
    $row = mysqli_fetch_assoc($result);
    mysqli_free_result($result);

    return $row;
}

function get_participation($username, $id) {
    global $db;

    $challenge = get_existing_participation($username, $id);
    if (!is_null($challenge)) {
        return $challenge;
    }

    $sql = "INSERT INTO Participation ";
    $sql .= "VALUES ('" . db_escape($db, $username) . "', '" . db_escape($db, $id) . "')";

    $result = mysqli_query($db, $sql);

    if (!$result) {
        return NULL;
    }

    return get_existing_participation($username, $id);

}
 ?>
