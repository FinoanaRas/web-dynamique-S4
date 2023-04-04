package etu2054.framework.servlet;

import etu2054.framework.Mapping;
import etu2054.framework.ModelView;
import etu2054.framework.annotations.UrlAnnot;
import etu2054.framework.util.StaxParser;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.URLDecoder;

@WebServlet(name = "FrontServlet", value = "/")
public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> mappingUrls;

    @Override
    public void init() throws ServletException {
        super.init();
        mappingUrls = new HashMap<String, Mapping>();
        File directory = null;
        try {
            StaxParser staxParser = new StaxParser();
            ServletContext servletContext = getServletContext();
            InputStream in = servletContext.getResourceAsStream("/WEB-INF/webConfig.xml");
            String path = staxParser.getConfig(in);
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            if (classLoader == null) {
                throw new ClassNotFoundException("Can't get class loader.");
            }
            URL resource = classLoader.getResource(path);
            if (resource == null) {
                throw new ClassNotFoundException("No resource for " + path);
            }
            directory = new File(URLDecoder.decode(resource.getFile()));
            path = path.replace('/','.');
            if (directory.exists()) {
                ArrayList<Class> classes = getAllClasses(directory,path);
                for(Class c:classes){
                    configureMapping(c);
                }
            } else {
                throw new Exception("package not found: "+resource.getFile());
            }
        } catch (Exception x) {
            throw new ServletException(x.getMessage());
        }
    }
    private ArrayList<Class> getAllClasses(File directory,String path){
        ArrayList<Class> classes = new ArrayList<Class>();
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            if(files[i].isDirectory()){
                String chemin = path+files[i].getName()+".";
                classes.addAll(getAllClasses(files[i],chemin));

            }else {

                if(files[i].getName().endsWith(".class")){
                    String filename = path+files[i].getName().substring(0,files[i].getName().length()-6);
                    try {
                        Class c = Class.forName(filename);
                        classes.add(c);

                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }
            }
        }
        return classes;
    }

    private void configureMapping(Class classe){
        Method[] methods = classe.getDeclaredMethods();
        for(Method m: methods){
            if(m.isAnnotationPresent(UrlAnnot.class)){
                String url = m.getAnnotation(UrlAnnot.class).url();
                Mapping mapping = new Mapping(classe.getName(),m.getName());
                mappingUrls.put(url,mapping);
            }
        }
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        String url = request.getRequestURL().toString();
        out.println(url);
        String[] parts = url.split("http://localhost:8082/testframework/");
        if(parts.length>1){
            url = parts[1];
            out.println(url);
        }
        if(mappingUrls.containsKey(url)){
            Mapping mapping = mappingUrls.get(url);
            out.println("in mapping");
            try {
                Class classe = Class.forName(mapping.getClassName());
                Method method = classe.getDeclaredMethod(mapping.getMethod());
                Class returnType = method.getReturnType();
                if(returnType.equals(ModelView.class)){
                    out.println("has modelView");
                    Object obj = classe.getConstructor().newInstance();
                    ModelView modelView = (ModelView) method.invoke(obj);
                    String view = modelView.getView();
                    request.getRequestDispatcher(view).forward(request,response);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace(out);
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
//        for(String k: mappingUrls.keySet()){
//            out.println("key: "+k);
//            out.println("class: "+mappingUrls.get(k).getClassName()+" method: "+mappingUrls.get(k).getMethod());
//        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
