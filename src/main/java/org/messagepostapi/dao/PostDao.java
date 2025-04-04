package org.messagepostapi.dao;

import com.google.cloud.datastore.*;
import org.messagepostapi.models.PostModel;

import java.util.*;

public class PostDao {
    Datastore datastore = DatastoreOptions.newBuilder()
            .setProjectId("crystalloids-candidates")
            .build()
            .getService();
    private final KeyFactory keyFactory = datastore.newKeyFactory().setKind("Post");

    public PostModel getById(String id) {
        Key key = keyFactory.newKey(id);
        Entity entity = datastore.get(key);
        return entity != null ? entityToPost(entity) : null;
    }

    public List<PostModel> getAll() {
        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Post").build();
        QueryResults<Entity> results = datastore.run(query);
        List<PostModel> posts = new ArrayList<>();
        results.forEachRemaining(entity -> posts.add(entityToPost(entity)));
        return posts;
    }

    public void save(PostModel post) {
        Key key = keyFactory.newKey(post.getId());
        Entity entity = Entity.newBuilder(key)
                .set("authorEmail", post.getAuthorEmail())
                .set("creationDate", post.getCreationDate().getTime())
                .set("lastModifiedDate", post.getLastModifiedDate().getTime())
                .set("subject", post.getSubject())
                .set("body", post.getBody())
                .build();
        datastore.put(entity);
    }

    private PostModel entityToPost(Entity entity) {
        PostModel post = new PostModel();
        post.setId(entity.getKey().getName());
        post.setAuthorEmail(entity.getString("authorEmail"));
        post.setCreationDate(new Date(entity.getLong("creationDate")));
        post.setLastModifiedDate(new Date(entity.getLong("lastModifiedDate")));
        post.setSubject(entity.getString("subject"));
        post.setBody(entity.getString("body"));
        return post;
    }
}
