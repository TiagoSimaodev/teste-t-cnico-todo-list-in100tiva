// Configuração da API
const API_BASE_URL = 'https://teste-tecnico-in100tiva-production.up.railway.app/api/tasks';

// Elementos DOM
const taskForm = document.getElementById('taskForm');
const editForm = document.getElementById('editForm');
const taskList = document.getElementById('taskList');
const loading = document.getElementById('loading');
const emptyState = document.getElementById('emptyState');
const editModal = document.getElementById('editModal');
const closeModal = document.querySelector('.close');

// Variáveis globais
let editingTaskId = null;

// Inicialização da aplicação
document.addEventListener('DOMContentLoaded', () => {
    loadTasks();

    // Event listeners
    taskForm.addEventListener('submit', handleAddTask);
    editForm.addEventListener('submit', handleEditTask);
    closeModal.addEventListener('click', closeEditModal);

    // Fechar modal ao clicar fora
    window.addEventListener('click', (e) => {
        if (e.target === editModal) {
            closeEditModal();
        }
    });
});

// Funções da API
async function apiRequest(url, options = {}) {
    try {
        const response = await fetch(url, {
            headers: {
                'Content-Type': 'application/json',
                ...options.headers
            },
            ...options
        });

        if (!response.ok) {
            throw new Error(`Erro na API: ${response.status} ${response.statusText}`);
        }

        // Para DELETE, não há corpo de resposta
        if (options.method === 'DELETE') {
            return null;
        }

        return await response.json();
    } catch (error) {
        console.error('Erro na requisição:', error);
        showError(`Erro ao conectar com o servidor: ${error.message}`);
        throw error;
    }
}

async function loadTasks() {
    try {
        showLoading(true);
        const tasks = await apiRequest(API_BASE_URL);
        renderTasks(tasks);
    } catch (error) {
        // Erro já tratado na apiRequest
    } finally {
        showLoading(false);
    }
}

async function addTask(taskData) {
    const newTask = await apiRequest(API_BASE_URL, {
        method: 'POST',
        body: JSON.stringify(taskData)
    });
    return newTask;
}

async function updateTask(id, taskData) {
    const updatedTask = await apiRequest(`${API_BASE_URL}/${id}`, {
        method: 'PUT',
        body: JSON.stringify(taskData)
    });
    return updatedTask;
}

async function deleteTask(id) {
    await apiRequest(`${API_BASE_URL}/${id}`, {
        method: 'DELETE'
    });
}

// Funções de manipulação de DOM
function showLoading(show) {
    loading.style.display = show ? 'block' : 'none';
}

function showError(message) {
    // Criar um toast de erro simples
    const toast = document.createElement('div');
    toast.className = 'error-toast';
    toast.textContent = message;
    toast.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        background: #e53e3e;
        color: white;
        padding: 12px 20px;
        border-radius: 8px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
        z-index: 1001;
        animation: fadeIn 0.3s ease-out;
    `;
    document.body.appendChild(toast);

    setTimeout(() => {
        toast.remove();
    }, 5000);
}

function renderTasks(tasks) {
    taskList.innerHTML = '';

    if (tasks.length === 0) {
        emptyState.style.display = 'block';
        return;
    }

    emptyState.style.display = 'none';

    tasks.forEach(task => {
        const taskElement = createTaskElement(task);
        taskList.appendChild(taskElement);
    });
}

function createTaskElement(task) {
    const taskDiv = document.createElement('div');
    taskDiv.className = `task-item ${task.completed ? 'completed' : ''}`;
    taskDiv.dataset.id = task.id;

    taskDiv.innerHTML = `
        <div class="task-title">${escapeHtml(task.title)}</div>
        <div class="task-description">${escapeHtml(task.description || '')}</div>
        <div class="task-meta">
            <div class="task-status">
                <label class="checkbox-label">
                    <input type="checkbox" ${task.completed ? 'checked' : ''} onchange="toggleTask(${task.id}, this.checked)">
                    ${task.completed ? 'Concluída' : 'Pendente'}
                </label>
            </div>
            <div class="task-actions">
                <button class="btn btn-secondary" onclick="openEditModal(${task.id})">Editar</button>
                <button class="btn btn-danger" onclick="confirmDelete(${task.id})">Excluir</button>
            </div>
        </div>
    `;

    return taskDiv;
}

function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

// Handlers de eventos
async function handleAddTask(e) {
    e.preventDefault();

    const title = document.getElementById('title').value.trim();
    const description = document.getElementById('description').value.trim();

    if (!title) {
        showError('O título da tarefa é obrigatório');
        return;
    }

    try {
        const taskData = {
            title,
            description: description || null,
            completed: false
        };

        await addTask(taskData);
        taskForm.reset();
        await loadTasks(); // Recarregar lista
    } catch (error) {
        // Erro já tratado
    }
}

async function handleEditTask(e) {
    e.preventDefault();

    const title = document.getElementById('editTitle').value.trim();
    const description = document.getElementById('editDescription').value.trim();
    const completed = document.getElementById('editCompleted').checked;

    if (!title) {
        showError('O título da tarefa é obrigatório');
        return;
    }

    try {
        const taskData = {
            title,
            description: description || null,
            completed
        };

        await updateTask(editingTaskId, taskData);
        closeEditModal();
        await loadTasks(); // Recarregar lista
    } catch (error) {
        // Erro já tratado
    }
}

async function toggleTask(id, completed) {
    try {
        // Buscar tarefa atual para manter outros dados
        const currentTasks = await apiRequest(API_BASE_URL);
        const task = currentTasks.find(t => t.id === id);

        if (task) {
            const taskData = {
                title: task.title,
                description: task.description,
                completed
            };

            await updateTask(id, taskData);
            await loadTasks(); // Recarregar lista
        }
    } catch (error) {
        // Erro já tratado
    }
}

async function confirmDelete(id) {
    if (confirm('Tem certeza que deseja excluir esta tarefa?')) {
        try {
            await deleteTask(id);
            await loadTasks(); // Recarregar lista
        } catch (error) {
            // Erro já tratado
        }
    }
}

async function openEditModal(id) {
    try {
        const tasks = await apiRequest(API_BASE_URL);
        const task = tasks.find(t => t.id === id);

        if (task) {
            editingTaskId = id;
            document.getElementById('editId').value = task.id;
            document.getElementById('editTitle').value = task.title;
            document.getElementById('editDescription').value = task.description || '';
            document.getElementById('editCompleted').checked = task.completed;

            editModal.style.display = 'block';
        }
    } catch (error) {
        // Erro já tratado
    }
}

function closeEditModal() {
    editModal.style.display = 'none';
    editForm.reset();
    editingTaskId = null;
}