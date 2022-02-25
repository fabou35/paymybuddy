use paymybuddy;

/* populates table person */
INSERT INTO `person` (`email`, `pass`) VALUES ('fabien@email.com', '$2a$10$x1Iy61XUc8PDF1bZKg31qOQFXgGiMmYJpzZ/zPAQuPLN102fknw2e');
INSERT INTO `person` (`email`, `pass`) VALUES ('hayley@email.com', '$2a$10$lrY6STePdXdf.B5Q6HPLceYEiZ3nKu/k0z7JInCO2L7Q.B44F02gO');
INSERT INTO `person` (`email`, `pass`) VALUES ('clara@email.com', '$2a$10$FSpu1JHph.mMy7LKCOS4regyVs4wBQhCxm/lVIsTIdOQ973nih5NS');
INSERT INTO `person` (`email`, `pass`) VALUES ('smith@email.com', '$2a$10$sEWxn64lsXKEMOlN9qWow.L0Cv05PYJeWt0xLIOjU2yFAu4ompxKu');

/* populates table bank_account */
INSERT INTO `bank_account` (`iban`, `bic`) VALUES ('FR0000000000000000000000001', 'CEPAFRPP001');
INSERT INTO `bank_account` (`iban`, `bic`) VALUES ('FR0000000000000000000000002', 'CEPAFRPP002');
INSERT INTO `bank_account` (`iban`, `bic`) VALUES ('FR0000000000000000000000003', 'CEPAFRPP003');
INSERT INTO `bank_account` (`iban`, `bic`) VALUES ('FR0000000000000000000000004', 'CEPAFRPP004');

/* populates table account */
INSERT INTO `account` (`person_id`, `pseudo`, `balance`, `account_status`, `bank_account_id`) VALUES ('1', 'Fabien',  200, TRUE, 1);
INSERT INTO `account` (`person_id`, `pseudo`, `balance`, `account_status`, `bank_account_id`) VALUES ('2', 'Hayley',  100, TRUE, 2);
INSERT INTO `account` (`person_id`, `pseudo`, `balance`,`account_status`,  `bank_account_id`) VALUES ('3', 'Clara',  300, TRUE, 3);
INSERT INTO `account` (`person_id`, `pseudo`, `balance`,`account_status`,  `bank_account_id`) VALUES ('4', 'Smith',  400, TRUE, 4);

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
