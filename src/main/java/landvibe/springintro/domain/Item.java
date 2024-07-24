package landvibe.springintro.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Item {


    @Id
    @GeneratedValue
    private Long id;
    private String pw;
    private String name;
    private Integer price;
    private Integer count;
    public long getId() {
        return id;
    }
    public String getPw(){
        return pw;
    }
    public void setPw(String pw){
        this.pw=pw;
    }



    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    }
