package ru.itmo.hps.lab1.chat.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import java.util.Date;

@Data
@Table("messages")
public class Message {
    @Id
    @GeneratedValue
    @Column("id")
    private Long chat_id;

    @Column("username")
    private String username;

    @Column("message")
    private String message;

    @Column("date")
    private Date date;
}
