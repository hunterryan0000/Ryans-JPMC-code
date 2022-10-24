-- select the park name, campground name, open_from_mm, open_to_mm & daily_fee 
-- order by park name and then by campground name
-- (expected: 7 rows, starting with "Blackwoods")
select 
	p.name as park, 
	c.name as campground,
	c.open_from_mm,
	c.open_to_mm,
	daily_fee
from park p
join campground c on c.park_id = p.park_id
order by park, campground;

-- select the park name and the total number of campgrounds for each park
-- order alphabetically by park name
-- (expected: 3 rows, starting with "Acadia")
select 
	p.name, 
	count(c.*)
from park p
join campground c on c.park_id = p.park_id
group by p.park_id
order by p.name;

-- select the park name, campground name, site number, max occupancy, accessible, max rv length, utilities where the campground name is 'Blackwoods'
-- order alphabetically by park name
-- (expected: 12 rows)
select
	p.name as park,
	c.name as campground,
	s.site_number,s.max_occupancy,s.accessible,s.max_rv_length,s.utilities
from park p
join campground c on c.park_id = p.park_id
join site s on s.campground_id = c.campground_id
where c.name = 'Blackwoods';

-- select site number, reservation name, reservation from and to date 
-- order by reservation from date
-- (expected: 44 rows, starting with the earliest date)
select
	s.site_number, 
	r.name, 
	r.from_date,
	r.to_date
from reservation r
join site s on s.site_id = r.site_id
order by from_date;

-- select the park name, animal name, and estimated population for all animals in all parks. 
-- order the results by park name then by animal name
-- (expected: 31 rows, starting with Big Brown Bat in Acadia and ending with White-Tailed Deer in Cuyahoga Valley)
select 
	p.name as park, 
	a.name as animal, 
	pa.est_population as population
from animal a
join park_animal pa on a.animal_id = pa.animal_id
join park p on pa.park_id = p.park_id
order by p.name, a.name; 

-- select the name of all parks that have a Common Snapping Turtle population followed by their estimated population.
-- order the results by population (highest to lowest)
-- (expected: 2 rows, Acadia 1000 and Cuyahoga Valley 400)
select 
	p.name as park, 
	pa.est_population as population
from park p
join park_animal pa on p.park_id = pa.park_id
join animal a on pa.animal_id = a.animal_id
where a.name = 'Common Snapping Turtle'
order by population;

-- select the name of each park that contains Black Bears followed by the 'area_per_bear' (number of acres per bear) of all parks that contain bears.
-- order the results by area_per_bear
-- (expected: 3 rows, starting with Cuyahoga at 1095 and ending with Acadia at 4738)
select 
	p.name as park, 
	p.area / pa.est_population AS area_per_bear
from park p
join park_animal pa on p.park_id = pa.park_id
join animal a on a.animal_id = pa.animal_id
where a.name = 'Black Bear'
order by area_per_bear; 

-- select the name and Largemouth Bass population of all parks that have a Largemouth Bass population greater than 1500. 
-- order the results by population (highest to lowest).
-- (expected: 2 rows, Cuyahoga 10000 followed by Acadia 3000)
select 
	p.name as park, 
	pa.est_population as population
from park p
join park_animal pa on p.park_id = pa.park_id
join animal a on pa.animal_id = a.animal_id
where a.name = 'Largemouth Bass' and pa.est_population > 1500
order by population desc;



