## How to set it up
1. Put the <b>jar framework</b> in your CLASSPATH in order to use it, as well as the <b>gson-2.8.2.jar</b>
2. Then import them into your web-app project, put them in the WEB-INF/lib/ folder
3. All classes must be placed in one package, for example in "model"
    - In the web.xml you must include the front servlet class: <b> etu2054.framework.servlet.FrontServlet</b> 
    and set the following initial parameters
            ```xml
                <servlet>
                    <servlet-name>FrontServlet</servlet-name>
                    <servlet-class>etu2054.framework.servlet.FrontServlet</servlet-class>
                    <init-param>
                        <param-name>sessionName</param-name>
                        <param-value>isConnected</param-value>
                    </init-param>
                    <init-param>
                        <param-name>sessionProfile</param-name>
                        <param-value>profile</param-value>
                    </init-param>
                    <init-param>
                        <param-name>Package</param-name>
                        <param-value>model/</param-value>
                    </init-param>
                    <init-param>
                        <param-name>Url</param-name>
                        <param-value>http://localhost:8080/yourFramework/</param-value>
                    </init-param>
                    <init-param>
                        <param-name>FileFolder</param-name>
                        <param-value>Path/to/Folder</param-value>
                    </init-param>
                </servlet>
            ```
    - Then send all links to the front servlet:
            ```xml
                <servlet-mapping>
                    <servlet-name>FrontServlet</servlet-name>
                    <url-pattern>*.do</url-pattern>
                </servlet-mapping>
            ```

4. You need Java 17 and later and Use the servlet-api.jar. This framework is using javax


## Instructions: How to use it
- You need to import the class: ModelView, and all the annotations from the package etu2054.framework.annotations
- All of your functions except Getter/Setter/Constructor must return a ModelView object
- In order to access to your method, add an url annotation that will be used as a link
    - Example :
        ```Java
            @Url( name = "/your_link" )
            public ModelView function_name(){}
        ```

- Set the view using modelView. The view is the name of the page to display
    - Example :
        ```Java
            ...  
                ModelView modelView = new ModelView();
                modelView.setView("./page.jsp");
            ...
        ```

- You can put some data in it to get them later
    - Example :
        ```Java
            ...
                ModelView mv = new ModelView();
                modelView.setView("./page.jsp");
                mv.addItem("A_name_for_your_data" , your_data);
                return mv;
            ...
        ```
- You can also pass a parameter in the function 
    - Example :
        ```Java
            @Url( name = "/your_link")
            public ModelView function_name( Integer id )
        ```
- To add a  Session
    ```Java
        ModelView modelView = new ModelView();
        modelView.addSession("isConnected", "true");
    ```

- To remove a  Session
    ```Java
        ModelView modelView = new ModelView();
        modelView.removeSession("isConnected");
    ```

- To remove all sessions
    ```Java
        ModelView modelView = new ModelView();
        modelView.setInvalidate(true);
    ```

- To transform an object to json
    - Method 1:
        ```Java
            ModelView modelView = new ModelView();
            modelView.setIsJson(true);
        ```
    - Method 2:
        ```Java
            @RestAPI
            public YourObject function(){}
        ```
- To upload a file:
    Add a FileUpload field in your controller, from etu2054.framework.FileUpload
    ```Java
        public class FileUpload{
            String name;
            String path;
            byte[] image;
        }
    ```
    Its fields will be set automatically when you upload a file from a form

## Constraints
1. This framework doesn't use primitive types
2. When using a form, the input field should have the same name as the class field
   -   Example :
        - In the class
            ```Java
                ....
                    Integer id;
                ....
            ```
        - In the form field
            ```html
                ...
                    <input type="text" name="id">               
                ...
            ```
3. Compile your class use the "-parameters" option in the javac cmd
    - Example :
        ```bash
            javac -parameters ...
        ```
4. All the url should end in <b>.do</b>