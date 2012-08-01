INSERT INTO participants(mainKNR,description,expressElixirUrl, participantURL) VALUES ('19609443','Bank szybkich transferów pieniężnych','http://localhost:8080/SRPNService/SRPNService?wsdl','http://localhost:8080/AgentService/AgentServicePART0001?wsdl');
INSERT INTO participants(mainKNR,description,expressElixirUrl, participantURL) VALUES ('19606211','Kasa wysokich odsetek','http://localhost:8080/SRPNService/SRPNService?wsdl','http://localhost:8080/AgentService/AgentServicePART002?wsdl');

INSERT INTO users(email,firstName,lastName,login,password,roleName,participant_id) VALUES  ('Lenny.Linux@wp.pl','Lenny','Linux','lenny','veorrS3TzjgPXxqBTy8wS+8BJCjv9rMGrW4jeWfbtOI=','UserRole', (SELECT id FROM Participants WHERE mainKNR='19609443'));
INSERT INTO users(email,firstName,lastName,login,password,roleName,participant_id) VALUES  ('Edmund.Biedny@wp.pl','Edmund','Bogaty','edek','veorrS3TzjgPXxqBTy8wS+8BJCjv9rMGrW4jeWfbtOI=','UserRole', (SELECT id FROM Participants WHERE mainKNR='19606211'));
INSERT INTO users(email,firstName,lastName,login,password,roleName,participant_id) VALUES  ('Zofia.Skąpa@wp.pl','Zofia','Skąpa','zoska12','veorrS3TzjgPXxqBTy8wS+8BJCjv9rMGrW4jeWfbtOI=','UserRole', (SELECT id FROM Participants WHERE mainKNR='19609443'));
INSERT INTO users(email,firstName,lastName,login,password,roleName,participant_id) VALUES  ('Boguslawa.Bogata@wp.pl','Bogusława','Bogata','rich_bogna','veorrS3TzjgPXxqBTy8wS+8BJCjv9rMGrW4jeWfbtOI=','UserRole', (SELECT id FROM Participants WHERE mainKNR='19606211'));

INSERT INTO accounts(iban,balance,blockedBalance) VALUES ('03196094436790468807296212',20110,0);
INSERT INTO accounts(iban,balance,blockedBalance) VALUES ('84196094438173967119776774',99999999.12,1000);
INSERT INTO accounts(iban,balance,blockedBalance) VALUES ('31196062117042871842619874',1035,35);

INSERT INTO users_accounts(users_id,accounts_id) VALUES ((SELECT id FROM Users WHERE login='lenny'),(SELECT id FROM accounts WHERE iban='03196094436790468807296212'));
INSERT INTO users_accounts(users_id,accounts_id) VALUES ((SELECT id FROM Users WHERE login='lenny'),(SELECT id FROM accounts WHERE iban='84196094438173967119776774'));
INSERT INTO users_accounts(users_id,accounts_id) VALUES ((SELECT id FROM Users WHERE login='zoska12'),(SELECT id FROM accounts WHERE iban='31196062117042871842619874'));

INSERT INTO transactions(senderIban,transactionAmount,senderAccount_id,receiverIban,transactionId,transactionDate,status,senderKNR,mainKNR,direction) VALUES('31196062117042871842619874',1000,(SELECT id FROM accounts WHERE id = (SELECT TOP 1 accounts_id FROM users_accounts WHERE users_id = (SELECT id FROM users WHERE login='zoska12'))),'62156027561217746720948993','1340969878280','2012/06/20 22:13','WAITING_TO_SEND','19606211',(SELECT mainKNR FROM participants WHERE id = (SELECT participant_id FROM users WHERE login='zoska12')),'Outgoing');
INSERT INTO transactions(senderIban,transactionAmount,senderAccount_id,receiverIban,transactionId,transactionDate,status,senderKNR,mainKNR,direction) VALUES('03196094436790468807296212',1500,(SELECT id FROM accounts WHERE iban ='03196094436790468807296212'),'52194036632858929558712722','1340969878281','2012/06/26 12:13','WAITING_TO_SEND','19609443',(SELECT mainKNR FROM participants WHERE id = (SELECT participant_id FROM users WHERE login='lenny')),'Outgoing');
INSERT INTO transactions(senderIban,transactionAmount,senderAccount_id,receiverIban,transactionId,transactionDate,status,senderKNR,mainKNR,direction) VALUES('84196094438173967119776774',1600,(SELECT id FROM accounts WHERE iban ='84196094438173967119776774'),'62156027561217746720948993','1340969878283','2012/06/26 12:13','WAITING_TO_SEND','19609443',(SELECT mainKNR FROM participants WHERE id = (SELECT participant_id FROM users WHERE login='lenny')),'Outgoing');

