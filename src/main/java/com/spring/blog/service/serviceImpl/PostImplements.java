package com.spring.blog.service.serviceImpl;

import com.spring.blog.model.Post;
import com.spring.blog.repository.PostRepository;
import com.spring.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostImplements implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> findAll() {

        return postRepository.findAll();
    }

    @Override
    public Post findById(long id) {

        return postRepository.findById(id).get();
    }

    @Override
    public Post save(Post post) {

        return postRepository.save(post);
    }

}
