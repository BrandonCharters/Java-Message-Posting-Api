# Message Post API

Simple Java API (Google Cloud Datastore) as well as Frontend for posting messages. Endpoints are as follows:
- Create Post
- View Post/Posts
- Edit Posts (If you are the user who created it)

# Endpoints

`POST /posts` → Create a post

`GET /posts` → Get all posts

`GET /posts?id=<id>` → Get post by ID

`PUT /posts?id=<id>` → Update a post

Requires `X-User-Email` header for create/update.

# Run Locally

Use command:
`mvn clean package appengine:run`

Then open browser at:
http://localhost:8080/index.html

# Deploy App

Use command:
`mvn clean package appengine:deploy`

Then open in browser:
https://crystalloids-candidates.ew.r.appspot.com/index.html

# Author

Brandon Charters
