package com.retail.kynaara.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "table_channel")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int channel_id;

    @Nullable
    private String channel_link;

    private String channel_name;

    @Nullable
    private LocalDateTime channel_creation_date;

    private int channel_creator_id;

    public Channel(@Nullable String channel_link, String channel_name, @Nullable LocalDateTime channel_creation_date, int channel_creator_id) {
        this.channel_link = channel_link;
        this.channel_name = channel_name;
        this.channel_creation_date = channel_creation_date;
        this.channel_creator_id = channel_creator_id;
    }

    public Channel() {
        this.channel_link = null;
        this.channel_name = "";
        this.channel_creation_date = null;
        this.channel_creator_id = 0;
    }

    @Nullable
    public String getChannel_link() {
        return channel_link;
    }

    public void setChannel_link(@Nullable String channel_link) {
        this.channel_link = channel_link;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    @Nullable
    public LocalDateTime getChannel_creation_date() {
        return channel_creation_date;
    }

    public void setChannel_creation_date(@Nullable LocalDateTime channel_creation_date) {
        this.channel_creation_date = channel_creation_date;
    }

    public int getChannel_creator_id() {
        return channel_creator_id;
    }

    public void setChannel_creator_id(int channel_creator_id) {
        this.channel_creator_id = channel_creator_id;
    }
}
