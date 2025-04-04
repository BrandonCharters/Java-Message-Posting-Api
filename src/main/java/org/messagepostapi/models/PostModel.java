package org.messagepostapi.models;

import java.util.Date;

public class PostModel {
    private String id;
    private String authorEmail;
    private Date creationDate;
    private Date lastModifiedDate;
    private String subject;
    private String body;

    public PostModel() {}

    public String getId() { return id; }
    public String getAuthorEmail() { return authorEmail; }
    public Date getCreationDate() { return creationDate; }
    public Date getLastModifiedDate() { return lastModifiedDate; }
    public String getSubject() { return subject; }
    public String getBody() { return body; }

    public void setId(String id) { this.id = id; }
    public void setAuthorEmail(String authorEmail) { this.authorEmail = authorEmail; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }
    public void setLastModifiedDate(Date lastModifiedDate) { this.lastModifiedDate = lastModifiedDate; }
    public void setSubject(String subject) { this.subject = subject; }
    public void setBody(String body) { this.body = body; }
}
