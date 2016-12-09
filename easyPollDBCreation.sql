CREATE DATABASE if not exists easyPoll;

use easyPoll;
CREATE TABLE if not exists RUser
(
Username varchar(35) NOT NULL unique,
Pword varchar(50) NOT NULL,
Email varchar(100),
DateJoined  date NOT NULL,
PRIMARY KEY (Username)
);

CREATE TRIGGER transactionentry_OnInsert BEFORE INSERT ON `easyPoll`.`RUser`
    FOR EACH ROW SET NEW.DateJoined = IFNULL(NEW.DateJoined, curdate());

CREATE TABLE if not exists Polls
(
PollNum int NOT NULL AUTO_INCREMENT,
Username varchar(35) NOT NULL,
isCurrent boolean NOT NULL,
PollName varchar(50) NOT NULL,
Partakers int,
PollType varchar(30),
EndTotal int,
EndTime date,
PRIMARY KEY (PollNum),
CONSTRAINT fk_Username foreign key(Username)
 references RUser(Username)
);

CREATE TABLE if not exists Polls
(
PollNum int NOT NULL AUTO_INCREMENT,
Username varchar(35) NOT NULL,
isCurrent boolean NOT NULL,
PollName varchar(50) NOT NULL,
Partakers int,
PollType varchar(30),
EndTotal int,
EndTime date,
PRIMARY KEY (PollNum),
CONSTRAINT fk_Username foreign key(Username)
 references RUser(Username)
);

CREATE TABLE if not exists PollGroup
(
GroupNum int NOT NULL AUTO_INCREMENT,
PollNum int NOT NULL,
GroupName varchar(50),
AdminUsername varchar(100),
PRIMARY KEY (GroupNum),
CONSTRAINT fk_PollNum foreign key(PollNum)
references Polls (PollNum)
);

CREATE TABLE if not exists UserGroup
(
Username varchar(35) NOT NULL,
GroupNum int NOT NULL,

FOREIGN KEY (Username) REFERENCES RUser(Username),
CONSTRAINT pk_UserGroup PRIMARY KEY (Username,GroupNum),
FOREIGN KEY (GroupNum) REFERENCES PollGroup(GroupNum)
);

CREATE TABLE if not exists AnonUser
(
IPAddress varchar(35) NOT NULL unique,
Email varchar(50),
PRIMARY KEY (IPAddress)
);

CREATE TABLE if not exists AnonPolls
(
IPAddress varchar(35) NOT NULL unique,
PollNum int NOT NULL unique,
PRIMARY KEY (PollNum),
FOREIGN KEY (IPAddress) REFERENCES AnonUser(IPAddress)
);

CREATE TABLE if not exists AnonPoll
(
IPAddress varchar(35) NOT NULL unique,
PollNum int NOT NULL unique,
FOREIGN KEY (PollNum) REFERENCES Polls(PollNum),
CONSTRAINT pk_AnonPoll PRIMARY KEY (IPAddress,PollNum),
FOREIGN KEY (IPAddress) REFERENCES AnonUser(IPAddress)
);

CREATE TABLE if not exists PollData
(
PollNum int NOT NULL unique,
Params int NOT NULL,
Question varchar(1000) NOT NULL,
Description varchar(1000) NOT NULL,
isRadio boolean NOT NULL,
AnsOne varchar(100) NOT NULL,
AnsTwo varchar(100),
AnsThree varchar(100),
AnsFour varchar(100),
AnsFive varchar(100),
AnsSix varchar(100),
AnsSeven varchar(100),
AnsEight varchar(100),
AnsNine varchar(100),
AnsTen varchar(100),
TotalOne int,
TotalTwo int,
TotalThree int,
TotalFour int,
TotalFive int,
TotalSix int,
TotalSeven int,
TotalEight int,
TotalNine int,
TotalTen int,
Primary key (PollNum),
foreign key (PollNum) References Polls(PollNum)
);

CREATE TABLE if not exists PollTags
(
TagNum int NOT NULL AUTO_INCREMENT,
PollNum int NOT NULL,
Tag varchar(35),
Primary key (TagNum),
Foreign key (PollNum) references Polls(PollNum)
);

CREATE TABLE if not exists PollTaker
(
Username varchar(35) NOT NULL,
PollNum int NOT NULL,
publicAnswers boolean default false,
UserAnsOne int,
UserAnsTwo int,
UserAnsThree int,
UserAnsFour int,
UserAnsFive int,
UserAnsSix int,
UserAnsSeven int,
UserAnsEight int,
UserAnsNine int,
UserAnsTen int,
Primary key (Username, PollNum),
Foreign key (PollNum) references Polls(PollNum)
);

CREATE TABLE if not exists AdminUser
(
Username varchar(35) NOT NULL unique,
Email varchar(35) NOT NULL,
Pword varchar(50) NOT NULL,
Primary key (Username)
);

CREATE TABLE if not exists ReportedQuestions
(
PollNum int NOT NULL,
Username varchar(35) NOT NULL,
Question varchar(100) NOT NULL,
Description varchar(1000),
PollName varchar(100),
Primary key (Username, PollNum)
);

CREATE TABLE if not exists AdminPoll
(
Username varchar(35) NOT NULL unique,
PollNum int NOT NULL unique,
FOREIGN KEY (Username) REFERENCES AdminUser(Username),
CONSTRAINT pk_AdminPoll PRIMARY KEY (Username,PollNum),
FOREIGN KEY (PollNum) REFERENCES Polls(PollNum)
);

CREATE TABLE if not exists Feedback
(MessageNum int NOT NULL UNIQUE AUTO_INCREMENT,
Message varchar(10000),
CONSTRAINT pk_messageNum PRIMARY KEY (messageNum));

CREATE TABLE if not exists SupportTicket
(TicketNum int NOT NULL UNIQUE AUTO_INCREMENT,
Message varchar(10000),
TicketUsername varchar(100),
CONSTRAINT pk_ticketNum PRIMARY KEY (TicketNum));

insert into RUser (Username, Pword, email)
SELECT 'mfletch1', 'password44', 'mfleth1@ilstu.edu'
  FROM dual
 WHERE NOT EXISTS (SELECT 1 
                     FROM RUser
                    WHERE username = 'mfletch1'
);

insert into RUser (Username, Pword, email)
SELECT 'TomSmith', 'TommyBoy', 'toms1@ilstu.edu'
  FROM dual
 WHERE NOT EXISTS (SELECT 1 
                     FROM RUser
                    WHERE username = 'TomSmith'
);


/* Insert Admin users */

INSERT INTO AdminUser (Username, Email, Pword)
	VALUES ('Nick(Admin)', 'nick@easypoll.com', 'p');

INSERT INTO AdminUser (Username, Email, Pword)
	VALUES ('Casey(Admin)', 'casey@easypoll.com', 'p');

INSERT INTO AdminUser (Username, Email, Pword)
	VALUES ('Matt(Admin)', 'matt@easypoll.com', 'p');

INSERT INTO AdminUser (Username, Email, Pword)
	VALUES ('Kevin(Admin)', 'kevin@easypoll.com', 'p');

/* Insert dummy users */

INSERT INTO RUser (Username, Email, Pword)
	VALUES ('Sam', 'sam@gmail.com', 'p');

INSERT INTO RUser (Username, Email, Pword)
	VALUES ('Noah', 'noah@hotmail.com', 'p');
    
INSERT INTO RUser (Username, Email, Pword)
	VALUES ('CoolMan', 'coolio@gmail.com', 'p');
    
INSERT INTO RUser (Username, Email, Pword)
	VALUES ('Rick', 'rick@intergalacticmail.com', 'p');
    
INSERT INTO RUser (Username, Email, Pword)
	VALUES ('Morty', 'morty@awgeez.com', 'p');
    
INSERT INTO RUser (Username, Email, Pword)
	VALUES ('hacker', 'hack@hack.com', 'p');
    
INSERT INTO RUser (Username, Email, Pword)
	VALUES ('joe', 'j@joe.com', 'p');
    
INSERT INTO RUser (Username, Email, Pword)
	VALUES ('Damon', 'Damon@gmail.com', 'p');
    
INSERT INTO RUser (Username, Email, Pword)
	VALUES ('Nick', 'nfriche@ilstu.edu', 'p');
/* Inserting dummy polls */

INSERT INTO Polls (Username, isCurrent, PollName, Partakers, PollType, EndTotal)
	VALUES ('hacker', '1', 'Suspicious Poll', 0, 'public', 1);

insert into PollData(PollNum, Params, isRadio, Question, Description,
AnsOne, AnsTwo, AnsThree, TotalOne, TotalTwo, TotalThree)
	values(last_insert_id(), 
	3, 
    	true, 
    	'<script>alert(''You got hacked!'');</script>', 
    	'01000100 01101001 01100100 00100000 01111001 01101111 01110101 00100000 01110010 01100101 01100001 01101100 01101100 01111001 00100000 01110100 01110010 01100001 01101110 01110011 01101100 01100001 01110100 01100101 00100000 01110100 01101000 01101001 01110011 00111111',
    	'0',
    	'000',
    	'110',
    	0, 0, 0);

INSERT INTO Polls (Username, isCurrent, PollName, Partakers, PollType, EndTotal)
	VALUES ('Morty', '1', 'Best Show?', 0, 'public', 3);

insert into PollData(PollNum, Params, isRadio, Question, Description,
AnsOne, AnsTwo, AnsThree, AnsFour, AnsFive, AnsSix, AnsSeven, AnsEight,
TotalOne, TotalTwo, TotalThree, TotalFour, TotalFive, TotalSix, TotalSeven, TotalEight)
	values(last_insert_id(), 
	8, 
    	true, 
    	'What do you think is the best show on television?', 
    	'I really like tv but I cant pin down what to watch next. Can you help?',
        'Law and Order SVU',
    	'Game of Thrones',
    	'NCIS',
        'Blacklist',
        'Ghost in the Shell: Stand Alone Complex',
        'Rick and Morty',
        'Westworld',
        'Halt and Catch Fire',
    	0,0,0,0,0,0,0,0);

INSERT INTO Polls (Username, isCurrent, PollName, Partakers, PollType, EndTotal)
	VALUES ('CoolMan', '1', 'A Question of Criminality?', 0, 'public', 1);

insert into PollData(PollNum, Params, isRadio, Question, Description,
AnsOne, AnsTwo, AnsThree, TotalOne, TotalTwo, TotalThree)
	values(last_insert_id(), 
	3, 
    	true, 
    	'Is Dabbing a Crime?', 
    	'I am aware that this is an age old question, but I still need to know. Is Dabbing a crime?',
        'Yes',
    	'No',
    	'*Ignore Question and Dab*',
    	0,0,0);

INSERT INTO Polls (Username, isCurrent, PollName, Partakers, PollType, EndTotal)
	VALUES ('Nick', '1', 'Nervous', 0, 'public', 2);

insert into PollData(PollNum, Params, isRadio, Question, Description,
AnsOne, AnsTwo, TotalOne, TotalTwo)
	values(last_insert_id(), 
	2, 
    	true, 
    	'Is This a Cool Website?', 
    	'Team 3 of IT 326 made this site. Is it cool?',
        'Yes',
    	'No',
    	0,0);

INSERT INTO Polls (Username, isCurrent, PollName, Partakers, PollType, EndTotal)
	VALUES ('Noah', '1', 'Math HW Help', 0, 'public', 5);

insert into PollData(PollNum, Params, isRadio, Question, Description,
AnsOne, AnsTwo, AnsThree, AnsFour, AnsFive, TotalOne, TotalTwo, TotalThree, TotalFour, TotalFive)
	values(last_insert_id(), 
	5, 
    	true, 
    	'What is the value of 1+1', 
	'I really need help with my math homework. If you could explain how you got it that would be great! Thx!! <3 <3 <3 :D *Rawr* XD',
    	'0',
        '1',
    	'2',
	'3',
	'439102923',
    	0,0,0,0,0);

/* Display Info about polls when done */

INSERT INTO PollTags (PollNum, Tag)
VALUES ( 1, 'hack');

INSERT INTO PollTags (PollNum, Tag)
VALUES ( 2, 'TV');

INSERT INTO PollTags (PollNum, Tag)
VALUES ( 2, 'Show');

INSERT INTO PollTags (PollNum, Tag)
VALUES ( 2, 'Entertainment');


INSERT INTO Polls (Username, isCurrent, PollName, Partakers, PollType, EndTotal)
	VALUES ('Morty', '1', 'Best Cat', 0, 'public', 5);

insert into PollData(PollNum, Params, isRadio, Question, Description,
AnsOne, AnsTwo, AnsThree, AnsFour, AnsFive, AnsSix, AnsSeven, AnsEight,
TotalOne, TotalTwo, TotalThree, TotalFour, TotalFive, TotalSix, TotalSeven, TotalEight)
	values(last_insert_id(), 
	8, 
    	true, 
    	'Favorite Cat', 
    	'I really cats but dont know which is best. Can you help?',
        'big cat',
    	'smelly cat',
    	'clean cat',
        'orange cat',
        'white cat',
        'dumb cat',
        'dead cat',
        'baby cat',
    	0,0,0,0,0,0,0,0);

insert into PollTaker (Username, PollNum, publicAnswers, UserAnsOne, UserAnsTwo, UserAnsThree, UserAnsFour, UserAnsFive, UserAnsSix, UserAnsSeven, UserAnsEight)
values ('mfletch1', 2, 1, 0,0,0,0,1,0,0,0);

insert into PollTaker (Username, PollNum, publicAnswers, UserAnsOne, UserAnsTwo, UserAnsThree, UserAnsFour, UserAnsFive, UserAnsSix, UserAnsSeven, UserAnsEight)
values ('CoolMan', 2, 1, 0,0,1,0,0,0,0,0);

update PollData set TotalFive = 1 WHERE PollNum = 2;
update PollData set TotalThree = 1 WHERE PollNum = 2;

update Polls
set Partakers = 2
where pollnum=2;


SELECT *
FROM Polls p
left Join PollData on PollData.PollNum = p.PollNum
left join PollTaker on PollTaker.PollNum = PollData.PollNum
where p.PollNum = '2'
order by p.pollNum, PollTaker.Username;

SELECT *
FROM Polls p
where p.Username = 'mfletch1';

use easyPoll;
SELECT COUNT(pollNum) from Polls;

SELECT *
from RUser;

Select * 
from PollTaker;



