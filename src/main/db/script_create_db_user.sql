--mysql --user=root mysql --password=<your root password>
CREATE USER 'dbuser'@'localhost' IDENTIFIED BY 'mypass';
CREATE DATABASE salesreport;
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP ON salesreport.* TO 'dbuser'@'localhost';
--mysql --user=dbuser mysql --password=mypass

