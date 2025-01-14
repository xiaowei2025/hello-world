document.getElementById('fetchUsers').addEventListener('click', fetchGitHubUsers);

function fetchGitHubUsers() {
    fetch('https://api.github.com/users')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            displayUsers(data);
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}

function displayUsers(users) {
    const userContainer = document.getElementById('userContainer');
    userContainer.innerHTML = ''; // 清空容器

    users.forEach(user => {
        const userCard = document.createElement('div');
        userCard.classList.add('user-card');

        const userImage = document.createElement('img');
        userImage.src = user.avatar_url;
        userImage.alt = user.login;

        const userName = document.createElement('h3');
        userName.textContent = user.login;

        const userLink = document.createElement('a');
        userLink.href = user.html_url;
        userLink.textContent = 'View Profile';
        userLink.target = '_blank';

        userCard.appendChild(userImage);
        userCard.appendChild(userName);
        userCard.appendChild(userLink);

        userContainer.appendChild(userCard);
    });
}
