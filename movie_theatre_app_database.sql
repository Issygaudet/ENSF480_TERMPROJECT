DROP DATABASE IF EXISTS MOVIE_THEATRE_APP;
CREATE DATABASE MOVIE_THEATRE_APP; 
USE MOVIE_THEATRE_APP;

DROP DATABASE IF EXISTS MOVIE_THEATRE_APP;
CREATE DATABASE MOVIE_THEATRE_APP; 
USE MOVIE_THEATRE_APP;

DROP TABLE IF EXISTS REGISTERED_USER;
CREATE TABLE REGISTERED_USER (
	ID_no						integer,
	User_Password				varchar(50) not null,
	First_Name					varchar(50) not null,
    Last_Name					varchar(50) not null,
    User_Email					varchar(50) not null,
    User_Bank_Info				integer,
    User_Day					integer,
    User_Month					integer,
    User_Year					integer,
	primary key (ID_no)
);

INSERT INTO REGISTERED_USER (ID_no, User_Password, First_Name, Last_Name, User_Email, User_Bank_Info, User_Day, User_Month, User_Year)
VALUES
    (0, 'pwd', 'Kamand', 'Ghorbanzadeh', 'kghorbanzadeh@gmail.com', 100, 3, 12, 2024),
    (1, 'pwd', 'Spiro', 'Douvis', 'sdouvis@gmail.com', 101, 2, 12, 2024),
    (2, 'pwd', 'Issy', 'Gaudet', 'igaudet@gmail.com', 102, 3, 12, 2024),
    (3, 'pwd', 'Dylan', 'Wenaas', 'dwenaas@gmail.com', 103, 4, 7, 2024);


DROP TABLE IF EXISTS MOVIE;
CREATE TABLE MOVIE (
    ID_no          			integer,
    Movie_Name     			varchar(50) not null,
    Genre          			varchar(50) not null,
    Release_Year 		 	integer,
    Director     		 	varchar(50) not null,
    Duration      		 	float, 
    Rating        		 	float, 
    Movie_Code           	varchar(50) not null,
    Movie_Price          	float, 
    Movie_Description    	varchar(200) not null,
	primary key (ID_no)

);

INSERT INTO MOVIE(ID_no, Movie_Name, Genre, Release_Year, Director, Duration, Rating, Movie_Code, Movie_Price, Movie_Description)
VALUES
    (200, 'The Adventures of Quantum Cat', 'Sci-Fi', 2023, 'Jane Doe', 120.0, 8.5, 'qcat', 12.99, 'Follow the thrilling journey of a cat that can teleport across galaxies.'),
    (201, 'Romance in the Rain', 'Romance', 2023, 'John Smith', 115.0, 7.2, 'rir', 9.99, 'Two strangers meet during a rainstorm and change each other"s lives forever.'),
    (202, 'Battle of the Titans', 'Action', 2023, 'Michael Brown', 140.0, 8.0, 'bot', 14.99, 'An epic showdown between rival warriors fighting for the fate of their world.'),
    (203, 'The Haunted Melody', 'Horror', 2023, 'Emily Clark', 100.0, 6.9, 'hm', 8.99, 'A cursed music box unleashes terror on those who dare to play its tune.'),
    (204, 'Laugh Out Loud', 'Comedy', 2023, 'Chris Wilson', 90.0, 7.4, 'lol', 7.99, 'A group of friends embarks on a hilarious road trip full of unexpected surprises.'),
    (205, 'Planet Ocean', 'Documentary', 2023, 'Sophia Taylor', 95.0, 8.3, 'po', 10.49, 'Explore the wonders of the deep blue sea and the creatures that call it home.'),
    (206, 'Chasing Shadows', 'Mystery', 2023, 'Robert Davis', 110.0, 7.8, 'cs', 11.99, 'A detective uncovers a series of cryptic clues to solve a decades-old disappearance.'),
    (207, 'The Digital Heist', 'Thriller', 2023, 'David Martinez', 130.0, 7.5, 'tdh', 13.49, 'A group of hackers plans the ultimate cyber robbery.'),
    (208, 'Eternal Echoes', 'Drama', 2023, 'Olivia Moore', 105.0, 7.7, 'ee', 9.49, 'A family struggles to overcome the loss of a loved one while finding hope.');

DROP TABLE IF EXISTS THEATRE;
CREATE TABLE THEATRE(
	ID_no				integer,
	Movie_Theatre_Name	varchar(150) not null,
    primary key (ID_no)
);
INSERT INTO THEATRE(ID_no, Movie_Theatre_Name)
VALUES
(400, 'Cineplex Crowfoot'),
(401, 'Cineplex University District'),
(402, 'Landmark Cinemas Country Hills'),
(403, 'Landmark Cinemas Market Mall'),
(404, 'Cineplex Odeon Sunridge'),
(405, 'Cineplex Odeon Westhills');

DROP TABLE IF EXISTS SCREENING_ROOM;
CREATE TABLE SCREENING_ROOM (
	ID_no					integer,
    Row_Numbers				integer,
    Column_Numbers			integer,
    Theatre_Number			integer,
    primary key (ID_no),
    foreign key (Theatre_Number) references THEATRE(ID_no)
);

INSERT INTO SCREENING_ROOM(ID_no, Row_Numbers, Column_Numbers, Theatre_Number)
VALUES
	(300, 8, 7, 400),
	(301, 6, 6, 403), 
	(302, 7, 8, 401), 
	(303, 6, 8, 402), 
	(304, 8, 5, 403); 

DROP TABLE IF EXISTS BANK_INFO;
CREATE TABLE BANK_INFO (
	ID_no				integer,
    First_Name			varchar(50) not null,
    Last_Name			varchar(50) not null,
    Card_Number			integer,
    primary key (ID_no)
);
INSERT INTO BANK_INFO(ID_no, First_Name, Last_Name, Card_Number)
VALUES
	(100, 'Kamand', 'Ghorbanzadeh', 1290283),
	(101, 'Spiro', 'Douvis', 732792), 
    (102, 'Issy', 'Douvis', 732792), 
    (103, 'Spiro', 'Douvis', 732792); 
    
DROP TABLE IF EXISTS SHOWS;
CREATE TABLE SHOWS(
	ID_no				integer,
    Movie_ID			integer,
    Screening_Room		integer,
    Time_in_Hours		integer, 
    Time_in_Minutes		integer,
    primary key (ID_no), 
    foreign key (Movie_ID) references MOVIE(ID_no), 
    foreign key (Screening_Room) references SCREENING_ROOM(ID_no)
);

INSERT INTO SHOWS(ID_no, Movie_ID, Screening_Room, Time_in_Hours, Time_in_Minutes)
VALUES
	(700, 200, 300, 1, 15),
    (701, 201, 300, 0, 55),
    (702, 200, 301, 2, 15), 
    (703, 202, 301, 1, 30),
    (704, 203, 302, 1, 11),
    (705, 204, 304, 1, 9);


DROP TABLE IF EXISTS ANNOUNCEMENTS;
CREATE TABLE ANNOUNCEMENTS(
	ID_no				integer,
    Date_Private_Day	integer, 
    Date_Private_Month	integer, 
    Date_Private_Year	integer, 
    Date_Public_Day		integer,
    Date_Public_Month	integer,
    Date_Public_Year	integer,
    Movie_ID			integer,
    primary key (ID_no)
);

INSERT INTO ANNOUNCEMENTS (ID_no, Date_Private_Day, Date_Private_Month, Date_Private_Year, Date_Public_Day, Date_Public_Month, Date_Public_Year, Movie_ID)
VALUES
	(800, 15, 11, 2024, 18, 11, 2022, 200), 
	(801, 20, 11, 2024, 25, 11, 2022, 201), 
    (802, 1, 12, 20224, 25, 12, 2024, 202), 
    (803, 10, 12, 2024, 15, 12, 2024, 203),
    (804, 20, 12, 2024, 25, 2, 2024, 204);

-- Drop roles if they exist
DROP ROLE IF EXISTS db_admin@localhost, read_access@localhost, employee_access@localhost;

-- Create roles
CREATE ROLE db_admin@localhost, read_access@localhost, employee_access@localhost;

-- Grant permissions to roles
GRANT ALL PRIVILEGES ON MOVIE_THEATRE_APP.* TO db_admin@localhost;
GRANT SELECT ON MOVIE_THEATRE_APP.* TO read_access@localhost;
GRANT SELECT, INSERT, UPDATE ON MOVIE_THEATRE_APP.* TO employee_access@localhost;

-- Drop users if they exist
DROP USER IF EXISTS ordinary_user@localhost;
DROP USER IF EXISTS registered_user@localhost;
DROP USER IF EXISTS admin@localhost;

-- Create users with passwords
CREATE USER ordinary_user@localhost IDENTIFIED WITH mysql_native_password BY 'ordinary_pass';
CREATE USER registered_user@localhost IDENTIFIED WITH mysql_native_password BY 'registered_pass';
CREATE USER admin@localhost IDENTIFIED WITH mysql_native_password BY 'admin_pass';

-- Grant roles to users
GRANT read_access@localhost TO ordinary_user@localhost;
GRANT employee_access@localhost TO registered_user@localhost;
GRANT db_admin@localhost TO admin@localhost;

-- Set default roles for each user
SET DEFAULT ROLE ALL TO ordinary_user@localhost;
SET DEFAULT ROLE ALL TO registered_user@localhost;
SET DEFAULT ROLE ALL TO admin@localhost;