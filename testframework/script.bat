javac -d "webapp/WEB-INF/classes/" src/model/Personne.java

cd webapp

jar -cf testframework.war .

move testframework.war ../

cd ../