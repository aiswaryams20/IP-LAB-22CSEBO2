<?php
function factorial($n) {
    if ($n === 0) {
        return 1;
    } else {
        return $n * factorial($n - 1);
    }
}

$number = 5; 
echo "The factorial of $number is: " . factorial($number);
?>