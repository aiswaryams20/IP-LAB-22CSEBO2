<!DOCTYPE html>
<html>
<head>
    <title>Temperature Converter</title>
</head>
<body>

<h2>Temperature Converter</h2>

<form method="post" action="">
    Enter the temperature: <input type="text" name="temperature" required><br>
    Select the unit:
    <input type="radio" name="unit" value="Celsius" checked> Celsius
    <input type="radio" name="unit" value="Fahrenheit"> Fahrenheit<br>
    <input type="submit" value="Convert">
</form>

<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $temperature = $_POST["temperature"];
    $unit = $_POST["unit"];

    if ($unit === 'Celsius') {
        $fahrenheit = ($temperature * 9/5) + 32;
        echo "$temperature Celsius is $fahrenheit Fahrenheit.";
    } elseif ($unit === 'Fahrenheit') {
        $celsius = ($temperature - 32) * 5/9;
        echo "$temperature Fahrenheit is $celsius Celsius.";
    } else {
        echo "Invalid unit. Please select either Celsius or Fahrenheit.";
    }
}
?>

</body>
</html>