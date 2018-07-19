package com.fang.alpha.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class VideoSort {
    @Id
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String url;

    @Column
    private String cover;

    @Column
    private String upper;

    @Column
    private String sort;

    @Column
    private int uid;

    @Column
    private Timestamp createAt;

    @Column
    private int sid;

    @Column
    private String description;

    @Column
    private int view;

    public VideoSort() {
    }

    public VideoSort(int id, String name, String url, String cover, String upper, String sort, int uid, Timestamp createAt, int sid, String description, int view) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.cover = cover;
        this.upper = upper;
        this.sort = sort;
        this.uid = uid;
        this.createAt = createAt;
        this.sid = sid;
        this.description = description;
        this.view = view;
    }

    public VideoSort(int id, String name, String cover, String upper, String sort, Timestamp createAt, int view) {
        this.id = id;
        this.name = name;
        this.cover = cover;
        this.upper = upper;
        this.sort = sort;
        this.createAt = createAt;
        this.view = view;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUpper() {
        return upper;
    }

    public void setUpper(String upper) {
        this.upper = upper;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }
}
