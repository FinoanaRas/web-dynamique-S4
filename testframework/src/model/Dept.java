package model;
import etu2054.framework.ModelView;
import etu2054.framework.annotations.UrlAnnot;
import java.lang.Integer;
import etu2054.framework.annotations.Scope;

@Scope(property="singleton")
public class Dept {
    String name;
    Integer num;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }

    public Dept() {
    }

    public Dept(String name, Integer num){
        setName(name);
        setNum(num);
    }

    @UrlAnnot(url="instance.do")
    public ModelView info(){
        ModelView modelView = new ModelView();
        Integer nbInstance = getNum();
        if(nbInstance == null){
            nbInstance = 0;
        }
        setNum(nbInstance+1);
        modelView.setView("./instanceCounter.jsp");
        modelView.addItem("num",getNum());
        return modelView;
    }
}
