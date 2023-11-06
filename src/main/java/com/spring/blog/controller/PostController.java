package com.spring.blog.controller;

import com.spring.blog.model.Post;
import com.spring.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PostController {


    @Autowired
    PostService postService;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getPosts(){
        ModelAndView mv = new ModelAndView("posts");
        List<Post> posts = postService.findAll();
        mv.addObject("posts", posts);
        return mv;
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetail(@PathVariable(name = "id") Long id){
        ModelAndView mv = new ModelAndView("postDetail");
        Post post = postService.findById(id);
        mv.addObject("post", post);
        return mv;
    }

    @RequestMapping(value="/novopost", method=RequestMethod.GET)
    public String getPostForm(){
        return "newPost";
    }

    @RequestMapping(value="/novopost", method=RequestMethod.POST)
    public String savePost(@Valid Post post, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
            return "redirect:/novopost";
        }
        post.setData(LocalDate.now());
        postService.save(post);
        return "redirect:/posts";
    }

    @RequestMapping(value="/deletepost/{id}", method=RequestMethod.POST)
    public String deletePost(@PathVariable Long id, RedirectAttributes attributes) {
        Post post = postService.findById(id);

        if (post != null) {
            postService.delete(id);
            attributes.addFlashAttribute("mensagem", "Post excluído com sucesso.");
        } else {
            attributes.addFlashAttribute("mensagem", "Post não encontrado.");
        }

        return "redirect:/posts";
    }




}
