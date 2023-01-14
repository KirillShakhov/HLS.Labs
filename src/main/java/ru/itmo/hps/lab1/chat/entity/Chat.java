package ru.itmo.hps.lab1.chat.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chats", schema = "public")
public class Chat {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "admin")
    private String adminUser;

    @Column(name = "user_id")
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private Set<String> users;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="chat_messages",
            joinColumns = @JoinColumn( name="chat_id"),
            inverseJoinColumns = @JoinColumn( name="message_id")
    )
    private List<Message> messages;
}
