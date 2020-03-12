package sample;

public class Per {
    private int id;
    private String name;
    private String CHclass;
    private int lvl;
    public Per(){

    }
    public  Per(int id, String name, String CHclass, int lvl){
        this.id = id;
        this.name = name;
        this.lvl = lvl;
        this.CHclass = CHclass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCHclass(String CHclass) {
        this.CHclass = CHclass;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCHclass() {
        return CHclass;
    }

    public int getLvl() {
        return lvl;
    }
}
