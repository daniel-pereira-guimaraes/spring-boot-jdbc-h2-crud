/* IMPORTANTE! 
	Sem aspas, os identificadores ficam com todas as letras maiúsculas no H2 Database.
*/
CREATE TABLE Person(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	telephone VARCHAR(15),
	birth_date DATE,
	salary NUMERIC(18,2)
);	