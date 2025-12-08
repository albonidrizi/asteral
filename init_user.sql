DELETE FROM suser;
-- BCrypt hash of 'test' generated with https://bcrypt-generator.com/
INSERT INTO suser (user_id, username, password, role) VALUES (1, 'admin', '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/X4.MqOmYsJfM0KXMS', 'USER');
