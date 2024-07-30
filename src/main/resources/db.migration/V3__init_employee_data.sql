INSERT INTO employee (address_id, date_of_birth, department_id, email, first_name, identifier, last_name, role)
VALUES (nextVal('hibernate_sequence'), '1985-08-15', 2, 'john.doe@example.com', 'John', 'EMP001', 'Doe', 'ROLE_USER');
