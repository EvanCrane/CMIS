USE cmis_1;
insert into app_user (USER_ID, USER_NAME, ENCRYPTED_PASSWORD, ENABLED)
values (3, 'dbreader1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into app_user (USER_ID, USER_NAME, ENCRYPTED_PASSWORD, ENABLED)
values (1, 'dbadmin1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);

insert into app_user (USER_ID, USER_NAME, ENCRYPTED_PASSWORD, ENABLED)
values (2, 'dbmanager1', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1); 
---
 
insert into app_role (ROLE_ID, ROLE_NAME)
values (1, 'ROLE_ADMIN');
 
insert into app_role (ROLE_ID, ROLE_NAME)
values (3, 'ROLE_READER');

insert into app_role (ROLE_ID, ROLE_NAME)
values (2, 'ROLE_MANAGER');
 
---
-- This is the admin role 
insert into user_role (ID, USER_ID, ROLE_ID)
values (1, 1, 1);
-- This is the manager role
insert into user_role (ID, USER_ID, ROLE_ID)
values (2, 2, 2);
-- This is the player role
insert into user_role (ID, USER_ID, ROLE_ID)
values (3, 3, 3);
