package com.redabens.mypet.Repository;

import com.redabens.mypet.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository <Comment, Integer> {
}

