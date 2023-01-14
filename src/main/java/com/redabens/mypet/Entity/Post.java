package com.redabens.mypet.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "posts", schema = "public", catalog = "mypet")
@Data
@NoArgsConstructor

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "type")
    private String type;
    @Column(name = "age")
    private int age;
    @Column(name = "duration")
    private int duration;
    @Column(name = "description")
    private String description;
    @Column(name = "city")
    private String city;
    @Column(name = "price")
    private Float price;
    @Column(name = "status")
    private Boolean status;
//    @Column(name = "photo")
//    private String photo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER)
    private List<Comment> comments;

}
