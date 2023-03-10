package com.redabens.mypet.Controller;

import com.redabens.mypet.Dto.PostDto;
import com.redabens.mypet.Entity.Post;
import com.redabens.mypet.Response.Response;
import com.redabens.mypet.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;

//    @GetMapping("/test")
//    public String test(){
//        return "test";
//    }

    @GetMapping("/all")
    public Response getAll() {
        Response response = new Response();
        Map map = new HashMap<>();
        List<Post> posts = postService.getAll();
        if (posts != null) {
            posts = posts.stream().map(post -> {
                post.getComments().forEach(c -> {
                    //disable post for comment in post and disable user for comment in post
                    c.setPost(null);
                    c.getUser().setPosts(null);
                    c.getUser().setComments(null);
                });
                //disable posts and commets for user in post
                post.getUser().setPosts(null);
                post.getUser().setComments(null);
                return post;
            }).toList();
            map.put("posts", posts);
            response.setData(map);
            response.setMessage("success");
            response.setStatus(200);
        }else{
            response.setMessage("No posts found");
            response.setStatus(404);
        }
        return response;
    }

    @GetMapping("/{id}")
    public Response getPostById(@PathVariable int id) {
        Response response = new Response();
        Map map = new HashMap<>();
        Post post = postService.getPostById(id);
        if (post != null) {
            post.getComments().forEach(c -> {
                c.setPost(null);
                c.getUser().setPosts(null);
                c.getUser().setComments(null);
            });
            post.getUser().setPosts(null);
            post.getUser().setComments(null);
            post.getUser().setPosts(null);
            map.put("post", post);
            response.setData(map);
            response.setMessage("success");
            response.setStatus(200);
        }else{
            response.setMessage("No post found");
            response.setStatus(404);
            }
        return response;
    }

    @PostMapping
    public Response add(@RequestBody PostDto postDto) {
        Response response = new Response();
        Map map = new HashMap<>();
        postService.savePost(postDto);
        map.put("post", postDto);
        response.setData(map);
        response.setMessage("success : post added");
        response.setStatus(200);
        return response;
    }

    @PutMapping("/{id}")
    public Response update(@RequestBody PostDto postDto, @PathVariable int id) {
        Response response = new Response();
        Map map = new HashMap<>();
        postService.updatePost(postDto, id);
        map.put("post", postDto);
        response.setData(map);
        response.setMessage("success : post updated");
        response.setStatus(200);
        return response;
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable int id) {
        Response response = new Response();
        postService.deletePost(id);
        response.setMessage("success : post deleted");
        response.setStatus(200);
        return response;
    }

    @PutMapping("/adopt/{id}/{userId}")
    public Response adopt(@PathVariable int id , @PathVariable int userId) {
        Response response = new Response();
        postService.adopt(id,userId);
        response.setMessage("success : pet adopted");
        response.setStatus(200);
        return response;
    }

}
