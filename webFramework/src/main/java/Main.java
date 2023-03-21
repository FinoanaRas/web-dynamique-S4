import java.nio.file.Paths;

public class Main {
    public static void main(String[] args){

//        Package[] packages = Package.getPackages();
        Package[] packages = Thread.currentThread().getContextClassLoader().getDefinedPackages();
        System.out.println(Paths.get("framework"));
//        Package b = Package.getPackage("etu2054.framework");
//        System.out.println(b.getName());
        for(Package p: packages){
//                    System.out.println(Class.forName("etu2054.framework.Mapping").getPackageName());
//                    System.out.println(p.get);


        }
    }
}
