package com.redabens.mypet.Repository;

import com.redabens.mypet.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository <Post, Integer> {
}

