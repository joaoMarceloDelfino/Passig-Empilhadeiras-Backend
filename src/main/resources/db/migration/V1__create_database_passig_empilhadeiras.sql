
CREATE TABLE role (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) UNIQUE NOT NULL,
	description VARCHAR(100) NULL
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	username VARCHAR(100) NOT NULL,
	cellphone_number VARCHAR(13) NOT NULL,
	password_hash VARCHAR(72) NOT NULL,
	id_role INT NOT NULL,
	created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
	CONSTRAINT pk_roles FOREIGN KEY (id_role) REFERENCES role(id)
);

CREATE TABLE forklift (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	model VARCHAR(50) NOT NULL,
	manufacturer VARCHAR(50) NOT NULL,
	weigth_capacity_kg REAL NOT NULL, 
	fabrication_year INT NOT NULL,
	status CHAR(2) NOT NULL,
	aquisition_date DATE NULL
);

CREATE TABLE forklift_images (
	id SERIAL PRIMARY KEY,
	file_url VARCHAR(200) NOT NULL,
	id_forklift INT NOT NULL,
	CONSTRAINT pk_forklifts FOREIGN KEY (id_forklift) REFERENCES forklift(id)
);

CREATE TABLE forklift_rent_history (
	id SERIAL PRIMARY KEY,
	id_forklift INT NOT NULL,
	start_rent_date TIMESTAMP WITH TIME ZONE NOT NULL,
	end_rent_date TIMESTAMP WITH TIME ZONE NULL,
	CONSTRAINT pk_forklifts FOREIGN KEY (id_forklift) REFERENCES forklift(id)
);

CREATE TABLE scheduled_visit (
	id SERIAL PRIMARY KEY,
	id_user INT NOT NULL,
	type VARCHAR(50) NOT NULL,
	scheduled_time TIMESTAMP WITH TIME ZONE NOT NULL,
	id_forklift INT NULL,
	is_completed BOOLEAN NOT NULL,
	CONSTRAINT pk_users FOREIGN KEY (id_user) REFERENCES users(id),
	CONSTRAINT pk_forklifts FOREIGN KEY (id_forklift) REFERENCES forklift(id)
);

CREATE TABLE visit_attachment(
	id SERIAL PRIMARY KEY,
	file_url VARCHAR(200) NOT NULL,
	id_scheduled_visit INT NOT NULL,
	CONSTRAINT pk_scheduled_visits FOREIGN KEY (id_scheduled_visit) REFERENCES scheduled_visit(id)
);


