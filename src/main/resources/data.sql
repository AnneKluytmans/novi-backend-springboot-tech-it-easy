INSERT INTO users (username, password, email, enabled)
VALUES ('user', '$2a$10$jmXh8pXJSVn2qsvcpuNoM.9s1UVK2N.o5I6QpOlJ1cr7ruHvikav2','user@test.com', TRUE);

INSERT INTO users (username, password, email, enabled)
VALUES ('admin', '$2a$10$NIW4leawKTFpNjVz5v3avu.k8.oG5etDmOwJcjQcFRsI89z6qzvH2', 'admin@test.com', TRUE);

INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');