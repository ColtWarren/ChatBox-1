const messagesDiv = document.getElementById('messages');
const messageInput = document.getElementById('messageInput');
const usernameInput = document.getElementById('username');
const sendBtn = document.getElementById('sendBtn');
const roomSelect = document.getElementById('roomSelect');

async function fetchRooms() {
    try {
        const res = await fetch('/api/rooms');
        const rooms = await res.json();

        roomSelect.innerHTML = '';

        rooms.forEach(room => {
            const opt = document.createElement('option');
            opt.value = room.id;
            opt.textContent = room.name;
            roomSelect.appendChild(opt);
        });

        // default to first room if nothing selected
        if (rooms.length > 0 && !roomSelect.value) {
            roomSelect.value = rooms[0].id;
        }

        // load messages for the selected room
        await fetchMessages();
    } catch (err) {
        console.error('Error fetching rooms', err);
    }
}

async function fetchMessages() {
    try {
        const roomId = roomSelect.value;
        const url = roomId ? `/api/messages?roomId=${roomId}` : '/api/messages';

        const response = await fetch(url);
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
    const roomId = roomSelect.value ? parseInt(roomSelect.value, 10) : null;

    if (!content) return;

    try {
        await fetch('/api/messages', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, content, roomId })
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

roomSelect.addEventListener('change', fetchMessages);

// Initial load
fetchRooms();

// Poll every 2 seconds
setInterval(fetchMessages, 2000);