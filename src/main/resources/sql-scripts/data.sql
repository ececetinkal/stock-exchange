INSERT INTO app_role (id, role_name, description) VALUES (1, 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO app_role (id, role_name, description) VALUES (2, 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');

-- USER
-- non-encrypted password: jwtpass
INSERT INTO app_user (id, first_name, last_name, password, username, email) VALUES (1, 'Ece', 'Cetinkal', '$2a$10$qtH0F1m488673KwgAfFXEOWxsoZSeHqqlB/8BTt3a6gsI5c2mdlfe', 'ececetinkal', 'ecec@xyz.com');
INSERT INTO app_user (id, first_name, last_name, password, username, email) VALUES (2, 'Admin', 'Admin', '$2a$10$qtH0F1m488673KwgAfFXEOWxsoZSeHqqlB/8BTt3a6gsI5c2mdlfe', 'admin', 'admin@xyz.com');


INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);
