package com.redabens.mypet.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @NoArgsConstructor
    @Getter
    @Setter

public class CommentDto {
    private String content;
    private int postId;
    private int userId;
}
