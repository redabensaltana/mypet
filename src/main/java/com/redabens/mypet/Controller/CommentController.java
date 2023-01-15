package com.redabens.mypet.Controller;


import com.redabens.mypet.Dto.CommentDto;
//import com.redabens.mypet.Entity.Comment;
import com.redabens.mypet.Response.Response;
import com.redabens.mypet.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public Response add(@RequestBody CommentDto commentDto) {
        Response response = new Response();
        Map map = new HashMap<>();
        commentService.saveComment(commentDto);
        map.put("comment", commentDto);
        response.setData(map);
        response.setMessage("success : comment added");
        response.setStatus(200);
        return response;
    }

    @PutMapping("/{id}")
    public Response update(@RequestBody CommentDto commentDto, @PathVariable int id) {
        Response response = new Response();
        Map map = new HashMap<>();
        commentService.updateComment(commentDto, id);
        map.put("comment", commentDto);
        response.setData(map);
        response.setMessage("success : comment updated");
        response.setStatus(200);
        return response;
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable int id) {
        Response response = new Response();
        commentService.deleteComment(id);
        response.setMessage("success : comment deleted");
        response.setStatus(200);
        return response;
    }



}
