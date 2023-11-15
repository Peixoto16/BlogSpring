package com.spring.blog.service;

import com.spring.blog.model.Post;

import java.util.List;

public interface PostService {

    List<Post> findAll();
    Post findById(long id);
    Post save(Post post);
    String delete(Long id);
    Post updatePost(Long id, Post updatedPost);


}
