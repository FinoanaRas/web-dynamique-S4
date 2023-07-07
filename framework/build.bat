javac -d "./" src/etu2054/framework/annotations/UrlAnnot.java
javac -d "./" src/etu2054/framework/annotations/Scope.java
javac -d "./" src/etu2054/framework/Mapping.java
javac -d "./" src/etu2054/framework/ModelView.java
javac -d "./" src/etu2054/framework/FileUpload.java
javac -d "./" src/etu2054/framework/util/StaxParser.java
javac -d "./" src/etu2054/framework/servlet/FrontServlet.java

jar cf framework.jar etu2054

copy framework.jar ..\testframework\webapp\WEB-INF\lib

copy framework.jar "D:\S4"
exit /b 0