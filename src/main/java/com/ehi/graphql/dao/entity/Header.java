package com.ehi.graphql.dao.entity;


import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class Header implements Serializable {

    public String ID;
    public String enable_ehmp;
    public String menu_item_parent;
    public String menu_order;
    public String nav_label;
    public String url;
    public String product_line;
    public String title;
}
