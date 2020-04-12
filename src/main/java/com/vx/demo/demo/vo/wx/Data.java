package com.vx.demo.demo.vo.wx;


/**
 * 测试模板实体
 */
public class Data {

    private Content pay;
    private Content address;
    private Content time;
    private Content remark;


    @Override
    public String toString() {
        return "Data{" +
                "pay=" + pay +
                ", address=" + address +
                ", time=" + time +
                ", remark=" + remark +
                '}';
    }

    public Content getPay() {
        return pay;
    }

    public void setPay(Content pay) {
        this.pay = pay;
    }

    public Content getAddress() {
        return address;
    }

    public void setAddress(Content address) {
        this.address = address;
    }

    public Content getTime() {
        return time;
    }

    public void setTime(Content time) {
        this.time = time;
    }

    public Content getRemark() {
        return remark;
    }

    public void setRemark(Content remark) {
        this.remark = remark;
    }
}