INSERT INTO users (id, username, password, role, created_at, updated_at)
VALUES
    (1, 'test', '$2a$10$jAkm342z0fAPlKZBjmSrH.DndspqidWOKaLPMJ2Vc4LoCQ2/g2jhe', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO users (id, username, password, role, created_at, updated_at)
VALUES
    (2, 'admin', '$2a$10$jAkm342z0fAPlKZBjmSrH.DndspqidWOKaLPMJ2Vc4LoCQ2/g2jhe', 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO category (name) VALUES ('java');
INSERT INTO category (name) VALUES ('spring');
INSERT INTO category (name) VALUES ('react');
INSERT INTO posts (id, title, content, category_id, author_id, created_at, updated_at)
VALUES
    (1, 'Sample Post 1', 'Content of post 1', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Sample Post 2', 'Content of post 2', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Sample Post 3', 'Content of post 3', 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'Sample Post 4', 'Content of post 4', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'Sample Post 5', 'Content of post 5', 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (6, 'Sample Post 6', 'Content of post 6', 3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (7, 'Sample Post 7', 'Content of post 7', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (8, 'Sample Post 8', 'Content of post 8', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (9, 'Sample Post 9', 'Content of post 9', 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (10, 'Sample Post 10', 'Content of post 10', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (11, 'Sample Post 11', 'Content of post 11', 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (12, 'Sample Post 12', 'Content of post 12', 3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (13, 'Sample Post 13', 'Content of post 13', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (14, 'Sample Post 14', 'Content of post 14', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (15, 'Sample Post 15', 'Content of post 15', 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (16, 'Sample Post 16', 'Content of post 16', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (17, 'Sample Post 17', 'Content of post 17', 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (18, 'Sample Post 18', 'Content of post 18', 3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (19, 'Sample Post 19', 'Content of post 19', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (20, 'Sample Post 20', 'Content of post 20', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (21, 'Sample Post 21', 'Content of post 21', 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (22, 'Sample Post 22', 'Content of post 22', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (23, 'Sample Post 23', 'Content of post 23', 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (24, 'Sample Post 24', 'Content of post 24', 3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (25, 'Sample Post 25', 'Content of post 25', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (26, 'Sample Post 26', 'Content of post 26', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (27, 'Sample Post 27', 'Content of post 27', 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (28, 'Sample Post 28', 'Content of post 28', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (29, 'Sample Post 29', 'Content of post 29', 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (30, 'Sample Post 30', 'Content of post 30', 3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (31, 'Sample Post 31', 'Content of post 31', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (32, 'Sample Post 32', 'Content of post 32', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (33, 'Sample Post 33', 'Content of post 33', 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (34, 'Sample Post 34', 'Content of post 34', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (35, 'Sample Post 35', 'Content of post 35', 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (36, 'Sample Post 36', 'Content of post 36', 3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (37, 'Sample Post 37', 'Content of post 37', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (38, 'Sample Post 38', 'Content of post 38', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (39, 'Sample Post 39', 'Content of post 39', 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (40, 'Sample Post 40', 'Content of post 40', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (41, 'Sample Post 41', 'Content of post 41', 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (42, 'Sample Post 42', 'Content of post 42', 3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (43, 'Sample Post 43', 'Content of post 43', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (44, 'Sample Post 44', 'Content of post 44', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (45, 'Sample Post 45', 'Content of post 45', 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (46, 'Sample Post 46', 'Content of post 46', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (47, 'Sample Post 47', 'Content of post 47', 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (48, 'Sample Post 48', 'Content of post 48', 3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (49, 'Sample Post 49', 'Content of post 49', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (50, 'Sample Post 50', 'Content of post 50', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
