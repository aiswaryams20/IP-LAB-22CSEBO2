<!DOCTYPE html>
<html>
<head>
    <title>Bank Account</title>
</head>
<body>

<h2>Bank Account</h2>

<?php
class BankAccount {
    private $balance;

    public function __construct($initialBalance = 0) {
        $this->balance = $initialBalance;
    }

    public function deposit($amount) {
        if ($amount > 0) {
            $this->balance += $amount;
            return true;
        } else {
            return false;
        }
    }

    public function withdraw($amount) {
        if ($amount > 0 && $amount <= $this->balance) {
            $this->balance -= $amount;
            return true;
        } else {
            return false;
        }
    }

    public function checkBalance() {
        return $this->balance;
    }
}

class Customer {
    private $name;
    private $bankAccount;

    public function __construct($name, $initialBalance = 0) {
        $this->name = $name;
        $this->bankAccount = new BankAccount($initialBalance);
    }

    public function deposit($amount) {
        return $this->bankAccount->deposit($amount);
    }

    public function withdraw($amount) {
        return $this->bankAccount->withdraw($amount);
    }

    public function checkBalance() {
        return $this->bankAccount->checkBalance();
    }
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $name = $_POST["name"];
    $initialBalance = $_POST["initialBalance"];
    $customer = new Customer($name, $initialBalance);

    $action = $_POST["action"];
    $amount = $_POST["amount"];

    if ($action === "deposit") {
        $customer->deposit($amount);
    } elseif ($action === "withdraw") {
        $customer->withdraw($amount);
    }

    echo "<p>Balance for $name: $" . $customer->checkBalance() . "</p>";
}
?>

<form method="post" action="">
    Name: <input type="text" name="name" required><br>
    Initial Balance: <input type="number" name="initialBalance" value="0" required><br>
    Action: 
    <select name="action">
        <option value="deposit">Deposit</option>
        <option value="withdraw">Withdraw</option>
    </select><br>
    Amount: <input type="number" name="amount" required><br>
    <input type="submit" value="Submit">
</form>

</body>
</html>