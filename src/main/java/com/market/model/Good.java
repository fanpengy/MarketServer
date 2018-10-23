package com.market.model;
import com.db.file.model.BaseModel;
import java.io.Serializable;

public class Good extends BaseModel implements Serializable {
    private String name;
    private Integer num;
    private Long sId;

    /**
     * model要创建空构造器
     */
    public Good() {
    }

    public Good(Long sId) {
        this.sId = sId;
    }

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

    public Long getsId() {
        return sId;
    }

    public void setsId(Long sId) {
        this.sId = sId;
    }
}
