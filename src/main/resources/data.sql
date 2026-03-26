-- 1. Insert Sample Users
INSERT INTO users (username, country, email, tel)
VALUES ('jdoe', 'USA', 'john.doe@example.com', '+15551234');

INSERT INTO users (username, country, email, tel)
VALUES ('asmith', 'UK', 'alice.smith@provider.co.uk', '+44207946');

INSERT INTO users (username, country, email, tel)
VALUES ('mdupont', 'France', 'marc.dupont@mail.fr', '+3314020');


-- 2. Insert Sample Posts
-- Note: 'user_id' must match an 'id' that exists in the users table (1, 2, or 3)

-- John Doe's posts (User ID: 1)
INSERT INTO posts (content, publication_date, user_id)
VALUES ('Hello world! My first post.', CURRENT_TIMESTAMP, 1);

INSERT INTO posts (content, publication_date, user_id)
VALUES ('Spring Boot is amazing for rapid prototyping.', CURRENT_TIMESTAMP, 1);

-- Alice Smith's post (User ID: 2)
INSERT INTO posts (content, publication_date, user_id)
VALUES ('Just finished my first GraphQL schema!', CURRENT_TIMESTAMP, 2);

-- Marc Dupont's post (User ID: 3)
INSERT INTO posts (content, publication_date, user_id)
VALUES ('Bonjour! Testing the H2 database setup.', CURRENT_TIMESTAMP, 3);