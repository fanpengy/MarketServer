package com.market.model;
import com.db.file.model.BaseModel;
import java.io.Serializable;

public class Good extends BaseModel implements Serializable {
    private String goodName;
    private Integer num;
    private Long merchantId;

    /**
     * model要创建空构造器
     */
    public Good() {
    }
    public Good(Long id){
        this.setMerchantId(id);
    }

    /*public Good(Long sId) {
        this.sId = sId;
    }
*/

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
