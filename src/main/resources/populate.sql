use paymybuddy;

/* populates table person */
INSERT INTO `person` (`email`) VALUES ('fabien@email.com');
INSERT INTO `person` (`email`) VALUES ('hayley@email.com');
INSERT INTO `person` (`email`) VALUES ('clara@email.com');
INSERT INTO `person` (`email`) VALUES ('smith@email.com');

/* populates table bank_account */
INSERT INTO `bank_account` (`iban`, `bic`) VALUES ('FR0000000000000000000000001', 'CEPAFRPP001');
INSERT INTO `bank_account` (`iban`, `bic`) VALUES ('FR0000000000000000000000002', 'CEPAFRPP002');
INSERT INTO `bank_account` (`iban`, `bic`) VALUES ('FR0000000000000000000000003', 'CEPAFRPP003');
INSERT INTO `bank_account` (`iban`, `bic`) VALUES ('FR0000000000000000000000004', 'CEPAFRPP004');

/* populates table account */
INSERT INTO `account` (`person_id`, `pseudo`, `email`, `pass`, `balance`, `account_status`, `bank_account_id`) VALUES ('1', 'Fabien', 'fabien@email.com', '1234', 200, TRUE, 1);
INSERT INTO `account` (`person_id`, `pseudo`, `email`, `pass`, `balance`, `account_status`, `bank_account_id`) VALUES ('2', 'Hayley', 'hayley@email.com', '5678', 100, TRUE, 2);
INSERT INTO `account` (`person_id`, `pseudo`, `email`, `pass`, `balance`,`account_status`,  `bank_account_id`) VALUES ('3', 'Clara', 'clara@email.com', '1357', 300, TRUE, 3);
INSERT INTO `account` (`person_id`, `pseudo`, `email`, `pass`, `balance`,`account_status`,  `bank_account_id`) VALUES ('4', 'Smith', 'smith@email.com', '2468', 400, TRUE, 4);

/* populates connection_association */
INSERT INTO `connection_association` (`user_id`, `connection_id`) VALUES (1, 2);
INSERT INTO `connection_association` (`user_id`, `connection_id`) VALUES (1, 3);
INSERT INTO `connection_association` (`user_id`, `connection_id`) VALUES (1, 4);

/* populates table commission */
INSERT INTO `commission` (`rate`, `date_modification`) VALUES (0.005, '2022-01-02');

/* populates table transaction */
INSERT INTO `transaction` (`account_id`, `connection_id`, `amount`, `description`, `commission_id`) VALUES (1, 4, 8, 'Movie tickets', 1);
INSERT INTO `transaction` (`account_id`, `connection_id`, `amount`, `description`, `commission_id`) VALUES (1, 3, 25, 'Trip money', 1);
INSERT INTO `transaction` (`account_id`, `connection_id`, `amount`, `description`, `commission_id`) VALUES (1, 2, 10, 'Restaurant bill share', 1);
commit;
