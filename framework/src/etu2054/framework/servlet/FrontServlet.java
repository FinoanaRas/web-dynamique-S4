package etu2054.framework.servlet;

// xml import
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import etu2054.framework.FileUpload;
import etu2054.framework.Mapping;
import etu2054.framework.ModelView;
import etu2054.framework.annotations.UrlAnnot;
import etu2054.framework.util.StaxParser;

import java.nio.file.Paths;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Array;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Float;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Enumeration;
import java.sql.Date;
import java.net.URLDecoder;

@MultipartConfig
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
            InputStream in = servletContext.getResourceAsStream("/WEB-INF/web.xml");
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

    public String getFileFolder() throws Exception{
        // Create a DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parse the XML file
        Document document = builder.parse(new File("/WEB-INF/web.xml"));

        // Retrieve elements by tag name
        Element folder = (Element) document.getElementsByTagName("FileFolder").item(0);

        return folder.getTextContent();
    }

    public void setDataHandleTypes(Object obj, String param,String paramgot,Class type,Method setMethod) throws Exception{       
        if (type==Integer.class){
            setMethod.invoke(obj,Integer.valueOf(paramgot));
        }else if ( type==Double.class) {
            setMethod.invoke(obj,Double.valueOf(paramgot));
        }else if ( type==Float.class) {
            setMethod.invoke(obj,Float.valueOf(paramgot));
        }else if(type == Date.class){
            setMethod.invoke(obj,Date.valueOf(paramgot));
        }else{
            Object parametre = paramgot;
            setMethod.invoke(obj,type.cast(parametre));
        }
    }

    public void setData(Class classe,Object obj, String param,String[] paramgot) throws Exception{
        // param is the name of the field, exemple: nom
        Field f = classe.getDeclaredField(param);
        Class type = getTypeMethod(getMethod("get", f,classe));
        Method setMethod = getMethod("set",f,classe);
        // si paramgot n'a qu'une value
        if(!f.getType().isArray()){
            setDataHandleTypes(obj,param,paramgot[0],type,setMethod);
            // ok
        }else{
            // prends le type du component de l'array
            Class typeComponent = type.getComponentType();
            // nouvelle instance de tableau
            Object tableau = Array.newInstance(typeComponent, paramgot.length);
            for(int i=0;i<paramgot.length;i++){
                Array.set(tableau,i,paramgot[i]);
            }
            setMethod.invoke(obj,tableau);
        }
    }

    public Class getTypeMethod(Method method)
    {
        return method.getReturnType();
    }
    public Method getDeclaredMethod(Class classe,String nom){
        Method[] methodes = classe.getDeclaredMethods();
        for(Method m: methodes){
            if(m.getName().equals(nom)){
                return m;
            }
        }
        return null;
    }
    public Method getMethod(String prefix, Field f, Class classe) throws NoSuchMethodException {
        String field = f.getName();
        String name = prefix+field;
        Method[] list = classe.getDeclaredMethods();
        for(Method m: list){
            if(m.getName().equalsIgnoreCase(name)){
                return m;
            }
        }
        return null;
    }

    public boolean checkField(String fieldName, Class classe){
        Field[] fields = classe.getDeclaredFields();
        for(Field f: fields){
            if(fieldName.equals(f.getName())){
                return true;
            }
        }
        return false;
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

    private boolean hasFileUpload(Class classe){
        Field[] fields = classe.getDeclaredFields();
        for(Field f: fields){
            if(f.getType().equals(FileUpload.class)){
                return true;
            }
        }
        return false;
    }

    public void configureUpload(HttpServletRequest request, Class classe, Object obj, String folderPath) throws Exception{
        String contentType = request.getContentType();
        if (contentType != null && contentType.startsWith("multipart/form-data")) {
            System.out.println("multipart/form-data");
            // String folderPath = getFileFolder();
            for (Part filePart : request.getParts()) {
            System.out.println("multipart/form-data boucle");
                System.out.println(filePart.getName()+" "+filePart.getSubmittedFileName());
                // raha mitovy @ ao am field
                if(checkField(filePart.getName(),classe)==true && classe.getDeclaredField(filePart.getName()).getType().equals(FileUpload.class)){
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                    String filePath = folderPath+fileName;
                    InputStream fileContent = filePart.getInputStream();

                    // put into bytes
                    byte[] array = fileContent.readAllBytes();
                    if(!fileName.isEmpty()){
                        OutputStream os = (OutputStream) new FileOutputStream(filePath);
                        os.write(array);
                    }
                    FileUpload fileUpload = new FileUpload(fileName, filePath, array);

                    // set it
                    Field f = classe.getDeclaredField(filePart.getName());
                    Method setMethod = getMethod("set",f,classe);

                    setMethod.invoke(obj,fileUpload);
                }
            }
        }
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        String url = request.getRequestURL().toString();
        out.println(url);
        try{
            StaxParser staxParser = new StaxParser();
            ServletContext servletContext = getServletContext();
            InputStream in = servletContext.getResourceAsStream("/WEB-INF/web.xml");
            String path = staxParser.getRequestUrlHeader(in);
            String[] parts = url.split(path);
            if(parts.length>1){
                url = parts[1];
                out.println(url);
            }
            if(mappingUrls.containsKey(url)){
                Mapping mapping = mappingUrls.get(url);
                out.println("in mapping");
                    Class classe = Class.forName(mapping.getClassName());
                    Method method = getDeclaredMethod(classe,mapping.getMethod());
                    Class returnType = method.getReturnType();
                    Object obj = classe.getConstructor().newInstance();
                    // take parameters
                    Enumeration<String> formParams = request.getParameterNames();
                    ArrayList<String> parametres = new ArrayList<String>();
                    
                    // avy @ form
                    if(formParams!=null){
                        // set all data to default
                        while(formParams.hasMoreElements()){
                            String param = formParams.nextElement();
                            System.out.println("parameter"+param);
                            parametres.add(param);
                            if(checkField(param,classe)==true){
                                String[] paramgot = request.getParameterValues(param);
                                setData(classe,obj,param,paramgot);
                            }
                        }
                    }

                    if(hasFileUpload(classe)){
                        System.out.println("has fileUpload");
                        InputStream in2 = getServletContext().getResourceAsStream("/WEB-INF/web.xml");
                        String folderPath = staxParser.getFileFolder(in2);
                        configureUpload(request,classe, obj, folderPath);
                    }

                    if(returnType.equals(ModelView.class)){
                        out.println("has modelView");
                        ModelView modelView = new ModelView();
                        Parameter[] methodParams = method.getParameters();
                        Class[] types = method.getParameterTypes();

                        // sprint-8: avy @ lien
                        if(methodParams!=null){
                            ArrayList<Object> listArgs = new ArrayList<Object>();
                            for(int i=0;i<methodParams.length;i++){
                                for(String p: parametres){
                                    if(methodParams[i].getName().equals(p)){
                                        if (types[i]==Integer.class){
                                            listArgs.add(Integer.valueOf(request.getParameter(p)));
                                        }else if ( types[i]==Double.class) {
                                            listArgs.add(Double.valueOf(request.getParameter(p)));
                                        }else if ( types[i]==Float.class) {
                                            listArgs.add(Float.valueOf(request.getParameter(p)));
                                        }else if(types[i] == Date.class){
                                            listArgs.add(Date.valueOf(request.getParameter(p)));
                                        }else{
                                            listArgs.add(types[i].cast(request.getParameter(p)));
                                        }
                                    }
                                }
                            }
                            Object[] args = new Object[listArgs.size()];
                            args = listArgs.toArray(args);
                            modelView = (ModelView) method.invoke(obj,args);
                        }else{
                            modelView = (ModelView) method.invoke(obj);
                        }
                        String view = modelView.getView();
                        HashMap<String,Object> modelViewData = modelView.getData();
                        if(modelViewData.size()>0){
                            for(String k: modelViewData.keySet()){
                                request.setAttribute(k,modelViewData.get(k));
                            }
                        }
                        request.getRequestDispatcher(view).forward(request,response);
                    }
                
            }
        }catch(Exception e){
            e.printStackTrace(out);
        }

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
