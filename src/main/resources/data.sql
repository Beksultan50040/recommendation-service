INSERT INTO user_credentials (age, email, first_name, gender, last_name, password, roles)
VALUES (25, 'example@gmail.com', 'ADMIN', 'male', 'ADMIN',
        '$2a$10$4y06Ay9IuF.Z15sYHhlPNe40xfBsyNznubwod4fm1ChysgDPo33Gq', 'ROLE_ADMIN')
ON CONFLICT (email) DO NOTHING;