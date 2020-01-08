package com.llm.demo.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "`z_order`")
public class Order implements Serializable {
    @Id
    @Column(name = "`id`")
    private Integer id;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`cid`")
    private Integer cid;

    @Column(name = "`price`")
    private Integer price;

    @Column(name = "`people`")
    private String people;

    @Column(name = "`creattime`")
    private String creattime;

    @Column(name = "`state`")
    private Integer state;

    @Column(name = "`orderno`")
    private String orderno;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return cid
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * @param cid
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * @return price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @return people
     */
    public String getPeople() {
        return people;
    }

    /**
     * @param people
     */
    public void setPeople(String people) {
        this.people = people == null ? null : people.trim();
    }

    /**
     * @return creattime
     */
    public String getCreattime() {
        return creattime;
    }

    /**
     * @param creattime
     */
    public void setCreattime(String creattime) {
        this.creattime = creattime == null ? null : creattime.trim();
    }

    /**
     * @return state
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return orderno
     */
    public String getOrderno() {
        return orderno;
    }

    /**
     * @param orderno
     */
    public void setOrderno(String orderno) {
        this.orderno = orderno == null ? null : orderno.trim();
    }
}