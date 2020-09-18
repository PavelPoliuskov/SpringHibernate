package org.example.SpringHibernate.domain;

import javax.persistence.*;

@Entity
public class Message {

    //DB - @Id - identification field. @GeneratedValue(strategy = GenerationType.AUTO) - auto-generate ID for each object
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String text;
    private String tag;

    //connecting user and message tables by "user_id" field. One user can have multiple messages.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id") //linking user and message tables by "user_id. Specifying column name, otherwise it would be named "author_id" by defualt
    private User author;

    private String filename;

    //It is necessary to create empty constructor for Spring, otherwise the app will drop
    public Message() {
    }

    public Message(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    //for test purposes
    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
