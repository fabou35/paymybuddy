/* Setting up PayMyBuddy DB */
DROP DATABASE paymybuddy;
CREATE DATABASE paymybuddy;
use paymybuddy;

/* table person */
CREATE TABLE `person` (
`person_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`email` VARCHAR(100) NOT NULL UNIQUE,
`pass` VARCHAR(100) NOT NULL
);

/* table connection_association */
CREATE TABLE `connection_association` (
`association_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`user_id` INT NOT NULL,
`connection_id` INT NOT NULL,
FOREIGN KEY (`user_id`) REFERENCES `person` (`person_id`),
FOREIGN KEY (`connection_id`) REFERENCES `person` (`person_id`)
);

/* table bank_account */
CREATE TABLE `bank_account` (
`bank_account_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`iban` VARCHAR(27) NOT NULL,
`bic` VARCHAR(11) NOT NULL
);

/* table account */
CREATE TABLE `account` (
`account_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`person_id` INT,
`pseudo` VARCHAR (100) NOT NULL,
`balance` DECIMAL(10,2),
`account_status` BOOL NOT NULL,
`bank_account_id` INT,
FOREIGN KEY (`bank_account_id`) REFERENCES `bank_account` (`bank_account_id`) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

/* table commission */
CREATE TABLE `commission`(
`commission_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`rate` FLOAT(2) NOT NULL,
`date_modification` DATE NOT NULL
);

/* table transaction */
CREATE TABLE `transaction` (
`transaction_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`account_id` INT NOT NULL,
`connection_id` INT NOT NULL,
FOREIGN KEY (`connection_id`) REFERENCES `account` (`account_id`),
`amount` DECIMAL(10,2) NOT NULL,
`description` VARCHAR(255),
`commission_id` INT,
FOREIGN KEY (`commission_id`) REFERENCES `commission` (`commission_id`)
);

commit;

