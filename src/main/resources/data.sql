-- roles
INSERT INTO roles(description) VALUES ('Admin');
INSERT INTO roles(description) VALUES ('Manager');
INSERT INTO roles(description) VALUES ('Employee');

-- users
INSERT INTO users(  firstname,lastname,gender, user_name,enabled,role_id)
VALUES ('John','Doe','MALE','John.Doe@example.com',true,'1')