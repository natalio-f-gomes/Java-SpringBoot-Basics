// js/profile.js
document.addEventListener('DOMContentLoaded', async () => {
    if (!localStorage.getItem('token')) {
        window.location.href = 'login.html';
        return;
    }

    try {
        const response = await makeRequest('/accounts/profile');
        if (response.ok) {
            const data = await response.json();
            displayProfile(data);
        } else {
            window.location.href = 'login.html';
        }
    } catch (error) {
        console.error('Error fetching profile:', error);
    }

    const logoutBtn = document.getElementById('logoutBtn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', () => {
            localStorage.removeItem('token');
            localStorage.removeItem('user');
            window.location.href = 'login.html';
        });
    }
});

function displayProfile(user) {
    document.getElementById('username').textContent = user.username || 'No username found';
    document.getElementById('email').textContent = user.email || 'No email found';
}
