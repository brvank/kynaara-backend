package com.retail.kynaara.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "table_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;
    private String product_link;
    private String product_image_link;
    @Nullable
    private Integer product_assignee_id;
    private int product_creator_id;
    private LocalDateTime product_date_created;
    @Nullable
    private LocalDateTime product_date_assigned;
    private int product_channel_id;

    public Product(String product_link, String product_image_link, @Nullable Integer product_assignee_id, int product_creator_id, LocalDateTime product_date_created, @Nullable LocalDateTime product_date_assigned, int product_channel_id) {
        this.product_link = product_link;
        this.product_image_link = product_image_link;
        this.product_assignee_id = product_assignee_id;
        this.product_creator_id = product_creator_id;
        this.product_date_created = product_date_created;
        this.product_date_assigned = product_date_assigned;
        this.product_channel_id = product_channel_id;
    }

    public Product(){
        this.product_link = "";
        this.product_image_link = "";
        this.product_assignee_id = null;
        this.product_creator_id = 0;
        this.product_date_created = null;
        this.product_date_assigned = null;
        this.product_channel_id = 0;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_link() {
        return product_link;
    }

    public void setProduct_link(String product_link) {
        this.product_link = product_link;
    }

    public String getProduct_image_link() {
        return product_image_link;
    }

    public void setProduct_image_link(String product_image_link) {
        this.product_image_link = product_image_link;
    }

    @Nullable
    public Integer getProduct_assignee_id() {
        return product_assignee_id;
    }

    public void setProduct_assignee_id(@Nullable Integer product_assignee_id) {
        this.product_assignee_id = product_assignee_id;
    }

    public int getProduct_creator_id() {
        return product_creator_id;
    }

    public void setProduct_creator_id(int product_creator_id) {
        this.product_creator_id = product_creator_id;
    }

    public LocalDateTime getProduct_date_created() {
        return product_date_created;
    }

    public void setProduct_date_created(LocalDateTime product_date_created) {
        this.product_date_created = product_date_created;
    }

    @Nullable
    public LocalDateTime getProduct_date_assigned() {
        return product_date_assigned;
    }

    public void setProduct_date_assigned(@Nullable LocalDateTime product_date_assigned) {
        this.product_date_assigned = product_date_assigned;
    }

    public int getProduct_channel_id() {
        return product_channel_id;
    }

    public void setProduct_channel_id(int product_channel_id) {
        this.product_channel_id = product_channel_id;
    }
}
