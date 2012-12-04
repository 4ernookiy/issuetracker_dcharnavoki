@echo off

rem mysql home directory
rem set mysql_home=c:\mysql-5.1.52-win32
set mysql_home=u:\usr\local\mysql-5.5

rem database root user for schema, user and tables creation
set db_user=root

set PATH=%mysql_home%\bin;
if exist "%mysql_home%" goto OK
echo Please set correct MySQL path into mysql_home variable.
pause
exit
:OK

echo Creating schema, user and tables...
mysql --user=%db_user% --force < issuetracker.sql

pause