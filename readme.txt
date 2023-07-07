Framework:

Ajout dans web.xml:

    <servlet>
        <servlet-name>FrontServlet</servlet-name>
        <servlet-class>etu2054.framework.servlet.FrontServlet</servlet-class>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>FrontServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    
    <Package>Le package a partir duquel on cherche les methodes de controlleur</Package>
    <Url>http://localhost:8082/votreWebContext/</Url>

Une fonction pour dispatcher une view doit retourner un ModelView, 
où l'on set le view (l'url jsp a dispatcher), et les données à emporter s'il y en a.

Exemple:
    ModelView modelView = new ModelView();
    Object data = new Object();
    modelView.setView("Votre-url.jsp");
    modelView.addItem("nom pour identifier",data);

Les méthodes de controlleur doivent être précédées d'une annotation UrlAnnot
Exemple:

    @UrlAnnot(url="votreUrl/")
    votreFonction

l'url doit être suivi d'un /