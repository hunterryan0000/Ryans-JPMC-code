-- Database hotelDB
BEGIN TRANSACTION;

DROP TABLE IF EXISTS hotel, reservation CASCADE;

CREATE TABLE hotel (
	hotel_id serial,
	name varchar(100) NOT NULL,
	address varchar(100),
	address2 varchar(100),
	city varchar(50),
	state char(2),
	zip varchar(9),
	stars int NOT NULL,
	rooms_available int NOT NULL,
	cost_per_night DECIMAL(8,2) NOT NULL,
	cover_image varchar(100),
	CONSTRAINT PK_hotel PRIMARY KEY (hotel_id)
);

CREATE TABLE reservation (
	reservation_id serial,
	hotel_id int NOT NULL,
	full_name varchar(100) NOT NULL,
	checkin_date date NOT NULL,
	checkout_date date NOT NULL,
	guests int NOT NULL DEFAULT 1,
	CONSTRAINT PK_reservation PRIMARY KEY (reservation_id),
	CONSTRAINT FK_reservation_hotel FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id)
);

-- Fill hotel before reservation because it has no foreign key dependencies
INSERT INTO hotel 
(name, address, address2, city, state, zip, stars, rooms_available, cost_per_night, cover_image) 
VALUES
('Aloft Cleveland',					'1111 W 10th St', 		null, 'Cleveland',      'OH', '44115', 4, 48,   274.00, 'aloft.png'),
('Hilton Cleveland Downtown',       '100 Lakeside Ave',		null, 'Cleveland',      'OH', '44114', 4, 12,   287.99, 'hilton.png'),
('Metropolitan at the 9',			'2017 E 9th St',		null, 'Cleveland',      'OH', '48226', 5, 22,   319.99, 'metro-at-9.png'),
('The Westin Pittsburgh',			'1000 Penn Ave',		null, 'Pittsburgh',     'PA', '15222', 4, 60,   131.00, 'westin.png'),
('Hilton Columbus Downtown',		'401 N High St',		null, 'Columbus',	    'OH', '43215', 4, 34,   190.00, 'hilton.png'),
('The Summit A Dolce Hotel',		'5345 Medpace Way',		null, 'Cincinnati',	    'OH', '43215', 4, 43,   218.99, 'summit-dolce.png'),
('Greektown Detroit',				'1200 St Antoine St',	null, 'Detroit',	    'MI', '48226', 4, 75,   185.00, 'greektown.png'),
('MGM Grand Detroit',				'1777 3rd St',  	    null, 'Detroit',	    'MI', '48226', 4, 125,  229.00, 'mgm-detroit.png'),
('Royal Park Hotel',        		'600 E University Dr',	null, 'Rochester',	    'MI', '48307', 5, 45,   199.00, 'hotel.png'),
('Pittsburgh Marriott City Center', '112 Washington Place',	null, 'Pittsburgh',	    'PA', '15219', 5, 150,  199.00, 'marriott.png'),
('Four Seasons Hotel Comcast Ctr',  'One North 19th St',	null, 'Philadelphia',   'PA', '19103', 5, 95,   865.00, 'four-seasons.png')
;

-- Now add reservations
INSERT INTO reservation
(hotel_id, full_name, checkin_date, checkout_date, guests)
VALUES
((SELECT hotel_id FROM hotel WHERE name = 'Aloft Cleveland'), 'Honorius Lottie', 	CURRENT_DATE+0,	CURRENT_DATE+3,     2),
((SELECT hotel_id FROM hotel WHERE name = 'Aloft Cleveland'), 'Denes Kuczkowski', 	CURRENT_DATE+0,	CURRENT_DATE+5,     4),
((SELECT hotel_id FROM hotel WHERE name = 'Aloft Cleveland'), 'Fagen Nedry',	 	CURRENT_DATE+7, CURRENT_DATE+10,    3),
((SELECT hotel_id FROM hotel WHERE name = 'Aloft Cleveland'), 'Leopoldine Drovin', 	CURRENT_DATE+0,	CURRENT_DATE+5,     4),
((SELECT hotel_id FROM hotel WHERE name = 'Aloft Cleveland'), 'Decia Berntsen',	    CURRENT_DATE+14, CURRENT_DATE+21,   1),
((SELECT hotel_id FROM hotel WHERE name = 'Aloft Cleveland'), 'Gelsy Brezinka',	    CURRENT_DATE+12, CURRENT_DATE+14,   1),
((SELECT hotel_id FROM hotel WHERE name = 'Hilton Cleveland Downtown'), 'Caroll Simeona',       CURRENT_DATE+2, CURRENT_DATE+4,     1),
((SELECT hotel_id FROM hotel WHERE name = 'Hilton Cleveland Downtown'), 'Fidelity Tennis',	    CURRENT_DATE+4, CURRENT_DATE+5,     2),
((SELECT hotel_id FROM hotel WHERE name = 'Hilton Cleveland Downtown'), 'Klementyna Kolarik',   CURRENT_DATE+6, CURRENT_DATE+12,    3),
((SELECT hotel_id FROM hotel WHERE name = 'Hilton Cleveland Downtown'), 'Dionisa Brandy',	    CURRENT_DATE+1, CURRENT_DATE+4,     4),
((SELECT hotel_id FROM hotel WHERE name = 'Greektown Detroit'), 'Tobiah Pepperman', CURRENT_DATE+0,     CURRENT_DATE+4,     5),
((SELECT hotel_id FROM hotel WHERE name = 'Greektown Detroit'), 'Aloin Dahnke',	    CURRENT_DATE+1,     CURRENT_DATE+4,     5),
((SELECT hotel_id FROM hotel WHERE name = 'Greektown Detroit'), 'Warmond Predom',	CURRENT_DATE+2,     CURRENT_DATE+4,     1),
((SELECT hotel_id FROM hotel WHERE name = 'Greektown Detroit'), 'Addy Patricia',	CURRENT_DATE+3,     CURRENT_DATE+6,     1),
((SELECT hotel_id FROM hotel WHERE name = 'Greektown Detroit'), 'Servas Becks',	    CURRENT_DATE+11,    CURRENT_DATE+13,    2),
((SELECT hotel_id FROM hotel WHERE name = 'The Westin Pittsburgh'), 'Dainard Penberthy',    CURRENT_DATE+1,    CURRENT_DATE+3,    2),
((SELECT hotel_id FROM hotel WHERE name = 'The Westin Pittsburgh'), 'Currey Withee',	    CURRENT_DATE+4,    CURRENT_DATE+11,   3),
((SELECT hotel_id FROM hotel WHERE name = 'The Westin Pittsburgh'), 'Meldryk Elm',	        CURRENT_DATE+1,    CURRENT_DATE+6,    4),
((SELECT hotel_id FROM hotel WHERE name = 'The Westin Pittsburgh'), 'Janek Osterfeld',	    CURRENT_DATE+8,    CURRENT_DATE+11,   1)
;

COMMIT;
