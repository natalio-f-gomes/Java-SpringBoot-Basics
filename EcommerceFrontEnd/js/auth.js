// js/auth.js
document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');

    if (loginForm) {
        registerForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            const username = document.getElementById('username').value;
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            const role = document.getElementById('role').value;  // Ensure role is captured here
        
            try {
                const response = await makeRequest('/accounts/register', 'POST', {
                    username,
                    email,
                    password,
                    role  // Include role in the request payload
                });
                const data = await response.json();
        
                if (response.ok) {
                    window.location.href = 'login.html';
                } else {
                    showError(data.error || 'Registration failed');
                }
            } catch (error) {
                showError('Network error occurred');
                console.error('Error:', error);
            }
        });
        
        
    }

    if (registerForm) {
        registerForm.addEventListener('submit', async (e) => {
            e.preventDefault();
            const username = document.getElementById('username').value;
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            const role = document.getElementById('role').value;

            try {
                const response = await makeRequest('/accounts/register', 'POST', {
                    username,
                    email,
                    password,
                    role
                });
                const data = await response.json();

                if (response.ok) {
                    window.location.href = 'login.html';
                } else {
                    showError(data.error || 'Registration failed');
                }
            } catch (error) {
                showError('Network error occurred');
                console.error('Error:', error);
            }
        });
    }
});