const messagesDiv = document.getElementById('messages');
const messageInput = document.getElementById('messageInput');
const usernameInput = document.getElementById('username');
const sendBtn = document.getElementById('sendBtn');

async function fetchMessages() {
    try {
        const response = await fetch('/api/messages');
        const messages = await response.json();
        renderMessages(messages);
    } catch (error) {
        console.error('Error fetching messages', error);
    }
}

function renderMessages(messages) {
    messagesDiv.innerHTML = '';

    messages.forEach(msg => {
        const msgDiv = document.createElement('div');
        msgDiv.classList.add('message');

        const usernameSpan = document.createElement('span');
        usernameSpan.classList.add('message-username');
        usernameSpan.textContent = msg.username + ':';

        const contentSpan = document.createElement('span');
        contentSpan.textContent = ' ' + msg.content;

        const timeSpan = document.createElement('span');
        timeSpan.classList.add('message-time');
        if (msg.createdAt) {
            timeSpan.textContent =
                ' (' + msg.createdAt.replace('T', ' ').substring(0, 16) + ')';
        }

        msgDiv.appendChild(usernameSpan);
        msgDiv.appendChild(contentSpan);
        msgDiv.appendChild(timeSpan);
        messagesDiv.appendChild(msgDiv);
    });

    messagesDiv.scrollTop = messagesDiv.scrollHeight;
}

async function sendMessage() {
    const username = usernameInput.value.trim() || 'Anonymous';
    const content = messageInput.value.trim();

    if (!content) return;

    try {
        await fetch('/api/messages', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, content })
        });
        messageInput.value = '';
        await fetchMessages();
    } catch (error) {
        console.error('Error sending message', error);
    }
}

sendBtn.addEventListener('click', sendMessage);

messageInput.addEventListener('keyup', (e) => {
    if (e.key === 'Enter') {
        sendMessage();
    }
});

// Initial load
fetchMessages();

// Poll every 2 seconds
setInterval(fetchMessages, 2000);