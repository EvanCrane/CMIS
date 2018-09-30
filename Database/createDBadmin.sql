CREATE USER 'CMISdbadmin' @'localhost' IDENTIFIED BY 
'hunter2_4lyfe';
GRANT ALL PRIVILEGES ON `CMIS%` . * TO 
'CMISdbadmin'@'localhost';

