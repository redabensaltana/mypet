package com.redabens.mypet.Dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    private String title;
    private String type;
    private int age;
    private int duration;
    private String description;
    private String city;
    private Float price;
    private int status;
    private int userId;
//    private String photo;
}


