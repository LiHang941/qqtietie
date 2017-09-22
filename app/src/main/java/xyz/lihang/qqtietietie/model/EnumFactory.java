package xyz.lihang.qqtietietie.model;

/**
 * Created by LiHang on 2017/9/21.
 */

public class EnumFactory {
}


enum Way {
    DIV(null,1), X("x",0), Y("y",2);

    private int flag ;
    private String name;
    private Way(String name,int flag){
        this.flag = flag;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getFlag() {
        return flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }
}

enum Attr {
    X("x"),Y("y"),WIDTH("width"),HEIGHT("height");
    private String name;
    private Attr(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}