package landvibe.springintro.item.controller;
public class MemberForm {
    private String name;
    private Integer id;
    private Integer pwd;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getPwd() {
        return pwd;
    }
    public void setPwd(Integer pwd) {
        this.pwd = pwd;
    } }