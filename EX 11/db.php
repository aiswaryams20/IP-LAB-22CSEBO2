<?php
// Database connection parameters
$servername = "localhost";
$username = "aishu";
$password = "1964";
$database = "Adresses_database";

// Create connection
$conn = new mysqli($servername, $username, $password, $database);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Define variables and initialize with empty values
$address = $city = $state = "";
$addressErr = $cityErr = $stateErr = "";
$success_message = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Validate address
    if (empty($_POST["address"])) {
        $addressErr = "Address is required";
    } else {
        $address = test_input($_POST["address"]);
    }

    // Validate city
    if (empty($_POST["city"])) {
        $cityErr = "City is required";
    } else {
        $city = test_input($_POST["city"]);
    }

    // Validate state
    if (empty($_POST["state"])) {
        $stateErr = "State is required";
    } else {
        $state = test_input($_POST["state"]);
        // Check if state is a valid two-letter abbreviation
        if (!preg_match("/^[a-zA-Z]{2}$/", $state)) {
            $stateErr = "Invalid state abbreviation";
        }
    }

    // If all fields are valid, insert data into database
    if (empty($addressErr) && empty($cityErr) && empty($stateErr)) {
        $sql = "INSERT INTO addresses (address, city, state) VALUES ('$address', '$city', '$state')";
        if ($conn->query($sql) === TRUE) {
            $success_message = "Address successfully added";
        } else {
            $success_message = "Error: " . $sql . "<br>" . $conn->error;
        }
    }
}

function test_input($data) {
    $data = trim($data);
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data;
}

$conn->close();
?>

<!DOCTYPE html>
<html>
<head>
    <title>Address Form</title>
</head>
<body>

<h2>Address Form</h2>
<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
    Address: <input type="text" name="address">
    <span class="error"><?php echo $addressErr;?></span>
    <br><br>
    City: <input type="text" name="city">
    <span class="error"><?php echo $cityErr;?></span>
    <br><br>
    State: <input type="text" name="state" maxlength="2">
    <span class="error"><?php echo $stateErr;?></span>
    <br><br>
    <input type="submit" name="submit" value="Submit">
</form>

<?php echo $success_message; ?>

</body>
</html>
