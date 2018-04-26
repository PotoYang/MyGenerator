@echo off
echo start
pause
echo runing....
java -jar mybatis-generator-core-1.3.6.jar -configfile generatorConfig.xml -overwrite
echo stop
pause
exit