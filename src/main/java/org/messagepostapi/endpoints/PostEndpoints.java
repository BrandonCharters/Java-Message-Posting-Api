package org.messagepostapi.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.messagepostapi.models.PostModel;
import org.messagepostapi.services.PostService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

// curl -X POST http://localhost:8080/posts -H "Content-Type: application/json" -H "X-User-Email: brandoncharters38@gmail.com" -d "{\"subject\":\"Hello World\",\"body\":\"My first post!\"}"

@WebServlet(name = "PostEndpoints", urlPatterns = {"/posts"})
public class PostEndpoints extends HttpServlet {
    private final PostService service = new PostService();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getHeader("X-User-Email");
        PostModel post = mapper.readValue(req.getReader(), PostModel.class);
        service.create(post, email);
        resp.setContentType("application/json");
        mapper.writeValue(resp.getWriter(), post);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        resp.setContentType("application/json");

        if (id != null) {
            PostModel post = service.getById(id);
            if (post == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\": \"Post not found\"}");
                return;
            }
            mapper.writeValue(resp.getWriter(), post);
        } else {
            List<PostModel> posts = service.getAll();
            mapper.writeValue(resp.getWriter(), posts);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String email = req.getHeader("X-User-Email");
        PostModel post = mapper.readValue(req.getReader(), PostModel.class);
        post.setId(id);

        try {
            service.update(post, email);
            resp.setContentType("application/json");
            mapper.writeValue(resp.getWriter(), post);
        } catch (SecurityException e) {
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
