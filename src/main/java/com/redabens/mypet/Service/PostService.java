package com.redabens.mypet.Service;

import com.redabens.mypet.Dto.PostDto;
import com.redabens.mypet.Entity.Post;
import com.redabens.mypet.Repository.PostRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserService userService;

    public List<Post> getAll() {
        return postRepo.findAll();
    }

    public Post getPostById(int id) {
        return postRepo.findById(id).get();
    }

    public Post savePost(PostDto postDto) {
        Post post = new Post();
//        BeanUtils.copyProperties(postDto, post);
        post.setTitle(postDto.getTitle());
        post.setType(postDto.getType());
        post.setAge(postDto.getAge());
        post.setDuration(postDto.getDuration());
        post.setDescription(postDto.getDescription());
        post.setCity(postDto.getCity());
        post.setPrice(postDto.getPrice());
        post.setUser(userService.getUserById(postDto.getUserId()));
        return postRepo.save(post);
    }

    public Post updatePost(PostDto postDto, int id) {
        Post post = postRepo.findById(id).get();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setPrice(postDto.getPrice());
        post.setStatus(postDto.getStatus());
        post.setCity(postDto.getCity());
        post.setAge(postDto.getAge());
        post.setType(postDto.getType());
        post.setDuration(postDto.getDuration());
        return postRepo.save(post);
    }

    public void deletePost(int id) {
        postRepo.deleteById(id);
    }

    public void adopt(int id, int userId) {
        Post post = postRepo.findById(id).get();
        post.setStatus(userId);
        postRepo.save(post);
    }

}
