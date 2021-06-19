# NALS-JavaTest

SQL DATA

use nals_todolist;

INSERT INTO `status`(status_name)
VALUES ("Planning", "Doing", "Complete");

INSERT INTO `work`(work_name, starting_date, ending_date, status_id)
VALUES 	("TASK 1", '2021-06-22', '2021-06-24', 1),
		("TASK 2", '2021-06-13', '2021-06-14', 3),
		("TASK 3", '2021-06-14', '2021-06-16', 3),
		("TASK 4", '2021-06-16', '2021-06-19', 1),
		("TASK 5", '2021-06-17', '2021-06-20', 2),
		("TASK 6", '2021-06-18', '2021-06-20', 2),
		("TASK 7", '2021-06-23', '2021-06-26', 1),
		("TASK 8", '2021-06-15', '2021-06-18', 3),
		("TASK 9", '2021-06-17', '2021-06-21', 2),
		("TASK 10", '2021-06-19', '2021-06-21', 1),
		("TASK 11", '2021-06-23', '2021-06-24', 1);
