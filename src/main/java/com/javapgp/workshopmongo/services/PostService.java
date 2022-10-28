package com.javapgp.workshopmongo.services;

import com.javapgp.workshopmongo.domain.Post;
import com.javapgp.workshopmongo.exception.ObjectNotFoundException;
import com.javapgp.workshopmongo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository PostRepository;

    public Post findById(String id){
        Post Post = PostRepository.findById(id).orElse(null);
        if (Post == null){
            throw new ObjectNotFoundException("Objeto não encontrado!");
        }
        return Post;
    }

    public List<Post> findByTitle(String text){
        return PostRepository.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate){
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return PostRepository.fullSearch(text, minDate, maxDate);
    }

}
