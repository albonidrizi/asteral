-- Auto-create admin user on startup
INSERT INTO suser (user_id, username, password, role)
VALUES (1, 'admin', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'USER')
ON CONFLICT (user_id) DO NOTHING;

-- Fix sequence desynchronization
SELECT setval(pg_get_serial_sequence('suser', 'user_id'), COALESCE((SELECT MAX(user_id) FROM suser), 1), true);
