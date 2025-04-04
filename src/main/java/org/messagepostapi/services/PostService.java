package org.messagepostapi.services;

import org.messagepostapi.dao.PostDao;
import org.messagepostapi.models.PostModel;

import java.util.*;

public class PostService {
    private final PostDao dao = new PostDao();

    public List<PostModel> getAll() {
        return dao.getAll();
    }

    public PostModel getById(String id) {
        return dao.getById(id);
    }

    public void create(PostModel post, String userEmail) {
        post.setId(UUID.randomUUID().toString());
        post.setAuthorEmail(userEmail);
        post.setCreationDate(new Date());
        post.setLastModifiedDate(new Date());
        dao.save(post);
    }

    public void update(PostModel post, String userEmail) {
        PostModel existing = dao.getById(post.getId());
        if (existing == null) throw new IllegalArgumentException("Post not found");
        if (!existing.getAuthorEmail().equals(userEmail))
            throw new SecurityException("Not allowed to update this post");
        post.setAuthorEmail(existing.getAuthorEmail());
        post.setCreationDate(existing.getCreationDate());
        post.setLastModifiedDate(new Date());
        dao.save(post);
    }
}
