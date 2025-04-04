const baseUrl = '/posts';

async function createPost() {
  const email = document.getElementById('createEmail').value;
  const subject = document.getElementById('createSubject').value;
  const body = document.getElementById('createBody').value;

  const res = await fetch(baseUrl, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'X-User-Email': email
    },
    body: JSON.stringify({ subject, body })
  });

  const data = await res.json();
  alert('Post created');
  console.log(data);
}

async function getAllPosts() {
  const res = await fetch(baseUrl);
  const posts = await res.json();
  const container = document.getElementById('allPosts');
  container.innerHTML = '';

  posts.forEach(post => {
    const div = document.createElement('div');
    div.className = 'post';
    div.innerHTML = `
      <div><strong>ID:</strong> ${post.id}</div>
      <div><strong>Email:</strong> ${post.authorEmail}</div>
      <div><strong>Subject:</strong> ${post.subject}</div>
      <div><strong>Body:</strong> ${post.body}</div>
    `;
    container.appendChild(div);
  });
}

async function getPostById() {
  const id = document.getElementById('getId').value;
  const res = await fetch(`${baseUrl}?id=${id}`);
  const out = document.getElementById('singlePost');

  if (res.status === 404) {
    out.textContent = 'Post not found';
    return;
  }

  const post = await res.json();
  out.textContent = JSON.stringify(post, null, 2);
}

async function updatePost() {
  const id = document.getElementById('updateId').value;
  const email = document.getElementById('updateEmail').value;
  const subject = document.getElementById('updateSubject').value;
  const body = document.getElementById('updateBody').value;

  const res = await fetch(`${baseUrl}?id=${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      'X-User-Email': email
    },
    body: JSON.stringify({ subject, body })
  });

  if (res.status === 403) {
    alert('You don\'t have permission to update this.');
    return;
  }

  const updated = await res.json();
  alert('Post updated');
  console.log(updated);
}
