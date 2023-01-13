package ru.itmo.hps.lab1.chat.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import java.util.Set;

@Data
@Builder
@Table("chats")
public class Chat {
    @Id
    @GeneratedValue
    @Column("id")
    private Long id;

    @Column("title")
    private String title;

    @Column("admin")
    private String adminUser;

    @JoinTable(name = "chat_users")
    private Set<String> users;
}
