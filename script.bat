copy framework\framework.jar .

javac -d "testframework/webapp/WEB-INF/classes/" -parameters testframework/src/model/Personne.java
javac -d "testframework/webapp/WEB-INF/classes/" -parameters testframework/src/model/Dept.java
javac -d "testframework/webapp/WEB-INF/classes/" -parameters testframework/src/model/Client.java
mkdir temp

cd temp

mkdir WEB-INF

mkdir WEB-INF\classes
mkdir WEB-INF\classes\model
mkdir WEB-INF\lib

copy ..\testframework\webapp\form.jsp .
copy ..\testframework\webapp\info.jsp .
copy ..\testframework\webapp\spec.jsp .
copy ..\testframework\webapp\instanceCounter.jsp .
copy ..\testframework\webapp\client.jsp .
copy ..\testframework\webapp\clientForm.jsp .

copy ..\testframework\webapp\WEB-INF\classes\model WEB-INF\classes\model
copy ..\testframework\webapp\WEB-INF\web.xml WEB-INF

cd ../
move framework.jar temp/WEB-INF/lib

cd temp

jar -cf testframework.war .

move testframework.war ../

cd ../

move testframework.war "%CATALINA_HOME%\webapps"

rmdir /s temp

call "%CATALINA_HOME%\bin\startup"

timeout /t 20

start firefox http://localhost:8082/testframework/form.jsp
