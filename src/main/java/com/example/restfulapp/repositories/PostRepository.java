package com.example.restfulapp.repositories;

import com.example.restfulapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostName(String postName);
    Optional<Post> findByPostNameAndOwner(String postName, String username);

    boolean existsByPostName(String postName);
    boolean existsByPostNameAndOwner(String postName, String username);
}
