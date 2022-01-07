use paymybuddy;

/* populates table person */
INSERT INTO `person` (`email`) VALUES ('fabien@email.com');
INSERT INTO `person` (`email`) VALUES ('jean@email.com');
INSERT INTO `person` (`email`) VALUES ('anne@email.com');

/* populate table bank_account */
INSERT INTO `bank_account` (`iban`, `bic`) VALUES ('FR0000000000000000000000001', 'CEPAFRPP001');
INSERT INTO `bank_account` (`iban`, `bic`) VALUES ('FR0000000000000000000000002', 'CEPAFRPP002');
INSERT INTO `bank_account` (`iban`, `bic`) VALUES ('FR0000000000000000000000003', 'CEPAFRPP003');

/* populate table account */
INSERT INTO `account` (`email`, `pseudo`, `pass`, `balance`, `account_status`, `bank_account_id`) VALUES ('fabien@email.com', 'fabien', '1234', 200, TRUE, 1);
INSERT INTO `account` (`email`, `pseudo`, `pass`, `balance`, `account_status`, `bank_account_id`) VALUES ('jean@email.com', 'jean', '5678', 100, TRUE, 2);
INSERT INTO `account` (`email`, `pseudo`, `pass`, `balance`,`account_status`,  `bank_account_id`) VALUES ('anne@email.com', 'anne', '1357', 300, TRUE, 3);

/* populate connection_association */
INSERT INTO `connection_association` (`user_id`, `connection_id`) VALUES (1, 2);
INSERT INTO `connection_association` (`user_id`, `connection_id`) VALUES (1, 3);

/* populate table commission */
INSERT INTO `commission` (`rate`, `date_modification`) VALUES (0.005, '2022-01-02');

/* populate table transaction */
INSERT INTO `transaction` (`account_id`, `connection_id`, `amount`, `description`, `commission_id`) VALUES (1, 2, 20.50, 'transaction example 1', 1);
INSERT INTO `transaction` (`account_id`, `connection_id`, `amount`, `description`, `commission_id`) VALUES (1, 3, 40, 'transaction example 2', 1);

commit;
