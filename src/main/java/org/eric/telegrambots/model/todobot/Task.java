package org.eric.telegrambots.model.todobot;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    public final static String TODO_STATUS = "todo";
    public final static String DONE_STATUS = "done";

    @Id
    @GeneratedValue(strategy=SEQUENCE)
    private long id;

    @Column(name = "hash")
    private String hash;

    @Column(name = "chat_id")
    private long chatId;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private String status;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Date createTime;

    @Column(name = "update_time", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static String getTodoStatus() {
        return TODO_STATUS;
    }

    public static String getDoneStatus() {
        return DONE_STATUS;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
