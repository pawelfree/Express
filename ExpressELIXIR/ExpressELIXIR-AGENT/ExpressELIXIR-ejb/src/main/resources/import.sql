INSERT INTO participants(mainKNR,description,expressElixirUrl, participantURL) VALUES ('60000001','Bank szybkich transferów pieniężnych','http://localhost:8080/SRPNService/SRPNService?wsdl','http://localhost:8080/AgentService/AgentServicePART0001?wsdl');
INSERT INTO participants(mainKNR,description,expressElixirUrl, participantURL) VALUES ('60000002','Kasa wysokich odsetek','http://localhost:8080/SRPNService/SRPNService?wsdl','http://localhost:8080/AgentService/AgentServicePART002?wsdl');

INSERT INTO users(email,firstName,lastName,login,password,roleName,participant_id) VALUES  ('Lenny.Linux@wp.pl','Lenny','Linux','lenny','veorrS3TzjgPXxqBTy8wS+8BJCjv9rMGrW4jeWfbtOI=','UserRole', (SELECT id FROM Participants WHERE mainKNR='60000001'));
INSERT INTO users(email,firstName,lastName,login,password,roleName,participant_id) VALUES  ('Edmund.Biedny@wp.pl','Edmund','Bogaty','edek','veorrS3TzjgPXxqBTy8wS+8BJCjv9rMGrW4jeWfbtOI=','UserRole', (SELECT id FROM Participants WHERE mainKNR='60000002'));
INSERT INTO users(email,firstName,lastName,login,password,roleName,participant_id) VALUES  ('Zofia.Skąpa@wp.pl','Zofia','Skąpa','zoska12','veorrS3TzjgPXxqBTy8wS+8BJCjv9rMGrW4jeWfbtOI=','UserRole', (SELECT id FROM Participants WHERE mainKNR='60000001'));
INSERT INTO users(email,firstName,lastName,login,password,roleName,participant_id) VALUES  ('Boguslawa.Bogata@wp.pl','Bogusława','Bogata','rich_bogna','veorrS3TzjgPXxqBTy8wS+8BJCjv9rMGrW4jeWfbtOI=','UserRole', (SELECT id FROM Participants WHERE mainKNR='60000002'));

INSERT INTO accounts(version,iban,balance,blockedBalance) VALUES (1,'95600000010000000000000001',2000110,0);
INSERT INTO accounts(version,iban,balance,blockedBalance) VALUES (1,'68600000010000000000000002',99999999.12,1000);
INSERT INTO accounts(version,iban,balance,blockedBalance) VALUES (1,'41600000010000000000000003',2000,0);
INSERT INTO accounts(version,iban,balance,blockedBalance) VALUES (1,'14600000010000000000000004',3000,0);
INSERT INTO accounts(version,iban,balance,blockedBalance) VALUES (1,'84600000010000000000000005',4000,0);
INSERT INTO accounts(version,iban,balance,blockedBalance) VALUES (1,'57600000010000000000000006',5000,0);
INSERT INTO accounts(version,iban,balance,blockedBalance) VALUES (1,'30600000010000000000000007',6000,0);
INSERT INTO accounts(version,iban,balance,blockedBalance) VALUES (1,'03600000010000000000000008',7000,0);
INSERT INTO accounts(version,iban,balance,blockedBalance) VALUES (1,'73600000010000000000000009',8000,0);
INSERT INTO accounts(version,iban,balance,blockedBalance) VALUES (1,'46600000010000000000000010',9000,0);
INSERT INTO accounts(version,iban,balance,blockedBalance) VALUES (1,'70600000020000000000000001',1035,35);

INSERT INTO users_accounts(users_id,accounts_id) VALUES ((SELECT id FROM Users WHERE login='lenny'),(SELECT id FROM accounts WHERE iban='95600000010000000000000001'));
INSERT INTO users_accounts(users_id,accounts_id) VALUES ((SELECT id FROM Users WHERE login='lenny'),(SELECT id FROM accounts WHERE iban='68600000010000000000000002'));
INSERT INTO users_accounts(users_id,accounts_id) VALUES ((SELECT id FROM Users WHERE login='zoska12'),(SELECT id FROM accounts WHERE iban='70600000020000000000000001'));

INSERT INTO transactions(senderIban,transactionAmount,senderAccount_id,receiverIban,transactionId,transactionDate,status,senderKNR,mainKNR,direction) VALUES('70600000020000000000000001',1000,(SELECT id FROM accounts WHERE id = (SELECT TOP 1 accounts_id FROM users_accounts WHERE users_id = (SELECT id FROM users WHERE login='zoska12'))),'62156027561217746720948993','1340969878280','2012/06/20 22:13','WAITING_TO_SEND','60000002',(SELECT mainKNR FROM participants WHERE id = (SELECT participant_id FROM users WHERE login='zoska12')),'Outgoing');
INSERT INTO transactions(senderIban,transactionAmount,senderAccount_id,receiverIban,transactionId,transactionDate,status,senderKNR,mainKNR,direction) VALUES('95600000010000000000000001',1500,(SELECT id FROM accounts WHERE iban ='95600000010000000000000001'),'68600000010000000000000002','1340969878281','2012/06/26 12:13','WAITING_TO_SEND','60000001',(SELECT mainKNR FROM participants WHERE id = (SELECT participant_id FROM users WHERE login='lenny')),'Outgoing');
INSERT INTO transactions(senderIban,transactionAmount,senderAccount_id,receiverIban,transactionId,transactionDate,status,senderKNR,mainKNR,direction) VALUES('68600000010000000000000002',1600,(SELECT id FROM accounts WHERE iban ='68600000010000000000000002'),'95600000010000000000000001','1340969878283','2012/06/26 12:13','WAITING_TO_SEND','60000001',(SELECT mainKNR FROM participants WHERE id = (SELECT participant_id FROM users WHERE login='lenny')),'Outgoing');
