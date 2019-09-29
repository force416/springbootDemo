package org.eric.telegrambots.model.todobot;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "alerts")
public class Alert implements Serializable {
    @Id
    @GeneratedValue(strategy=SEQUENCE)
    private long id;

    @Column(name = "chat_id")
    private long chatId;

    @Column(name = "content")
    private String content;

    @Column(name = "alert_time", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Date alertTime;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Date alertTime) {
        this.alertTime = alertTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
