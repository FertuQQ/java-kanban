import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;


class TaskManagerTest {


    TaskManager taskManager = Managers.getDefault();
    ArrayList<Task> emptyList = new ArrayList<>();

    Task task1 = new Task(0, "Task#1", "First test", TaskStatus.NEW);
    Task task2 = new Task(0, "Task#2", "Second test", TaskStatus.NEW);
    int task1Id = taskManager.addTask(task1);
    int task2Id = taskManager.addTask(task2);

    Epic epic1 = new Epic(0, "Epic#1", "First epic", TaskStatus.NEW, new ArrayList<>());
    Epic epic2 = new Epic(0, "Epic#2", "Second epic", TaskStatus.NEW, new ArrayList<>());
    int epic1Id = taskManager.addEpic(epic1);
    int epic2Id = taskManager.addEpic(epic2);

    Subtask subtask1 = new Subtask(0, "Subtask#1", "1.1 Subtask", TaskStatus.NEW, epic1Id);
    Subtask subtask2 = new Subtask(0, "Subtask#2", "1.2 Subtask", TaskStatus.NEW, epic1Id);
    Subtask subtask3 = new Subtask(0, "Subtask#3", "2.1 Subtask", TaskStatus.NEW, epic2Id);
    int subtask1Id = taskManager.addSubtask(subtask1);
    int subtask2Id = taskManager.addSubtask(subtask2);
    int subtask3Id = taskManager.addSubtask(subtask3);

    @Test
    void getTaskById() {
        Task getTask1 = taskManager.getTaskById(task1Id);
        assertNotNull(getTask1, "Задачи не существует");
        assertEquals(task1, getTask1, "Задачи не совпадают");
    }

    @Test
    void getSubtaskById() {
        Task getSubtask1 = taskManager.getSubtaskById(subtask1Id);
        assertNotNull(getSubtask1, "Задачи не существует");
        assertEquals(subtask1, getSubtask1, "Задачи не совпадают");
    }

    @Test
    void getEpicById() {
        Task getEpic1 = taskManager.getEpicById(epic1Id);
        assertNotNull(getEpic1, "Задачи не существует");
        assertEquals(epic1, getEpic1, "Задачи не совпадают");
    }

    @Test
    void updateTask() {
        taskManager.updateTask(task1);
        Task update = taskManager.getTaskById(task1Id);

        assertEquals(task1, update, "Одинаковые задачи не совпадают");

        taskManager.updateTask(new Task(task1Id, "update", "desc", TaskStatus.NEW));
        update = taskManager.getTaskById(task1Id);

        assertNotEquals(task1, update, "Разные задачи совпали");
    }

    @Test
    void updateEpic() {
        taskManager.updateEpic(epic1);
        Task update = taskManager.getEpicById(epic1Id);

        assertEquals(epic1, update, "Одинаковые эпики не совпадают");

        taskManager.updateEpic(new Epic(epic1Id, "update", "desc", TaskStatus.NEW, new ArrayList<>()));
        update = taskManager.getEpicById(epic1Id);

        assertNotEquals(epic1, update, "Разные эпики совпали");
    }

    @Test
    void updateSubtask() {
        taskManager.updateSubtask(subtask1);
        Task update = taskManager.getSubtaskById(subtask1Id);

        assertEquals(subtask1, update, "Одинаковые подзадачи не совпадают");

        taskManager.updateSubtask(new Subtask(subtask1Id, "update", "desc", TaskStatus.NEW, epic1Id));
        update = taskManager.getSubtaskById(subtask1Id);

        assertNotEquals(subtask1, update, "Разные подзадачи совпали");
    }



    @Test
    void getAllTasks() {
        List<Task> tasks = taskManager.getTasks();
        assertNotNull(tasks, "Задачи не возвращены");
        assertEquals(2, tasks.size(), "Неверное количество задач");
        assertEquals(task1, tasks.getFirst(), "Задачи не совпадают");
    }

    @Test
    void getEpics() {
        List<Task> epics = taskManager.getEpics();
        assertNotNull(epics, "Эпики не возвращены");
        assertEquals(2, epics.size(), "Неверное количество эпиков");
        assertEquals(epic1, epics.getFirst(), "Эпики не совпадают");
    }

    @Test
    void getSubtasks() {
        List<Task> subtasks = taskManager.getSubtasks();
        assertNotNull(subtasks, "Подзадачи не возвращены");
        assertEquals(3, subtasks.size(), "Неверное количество подзадач");
        assertEquals(subtask1, subtasks.getFirst(), "Подзадачи не совпадают");
    }

    @Test
    void deleteTaskById() {
        taskManager.deleteTaskById(task1Id);
        List<Task> tasks = taskManager.getTasks();
        assertNotNull(tasks, "Удалились все задачи");
        assertEquals(1, tasks.size(), "Осталось неверное количество задач");
    }

    @Test
    void deleteSubtaskById() {
        taskManager.deleteSubtaskById(subtask1Id);
        List<Task> subtasks = taskManager.getSubtasks();
        assertNotNull(subtasks, "Удалились все подзадачи");
        assertEquals(2, subtasks.size(), "Осталось неверное количество подзадач");
    }

    @Test
    void deleteEpicById() {
        taskManager.deleteEpicById(epic1Id);
        List<Task> epics = taskManager.getEpics();
        assertNotNull(epics, "Удалились все эпики");
        assertEquals(1, epics.size(), "Осталось неверное количество эпиков");
    }

    @Test
    void deleteAllTasks() {
        taskManager.deleteAllTasks();
        assertEquals(0, taskManager.getTasks().size(), "Удалены не все задачи");
    }

    @Test
    void deleteAllSubtasks() {
        taskManager.deleteAllSubtasks();
        assertEquals(0, taskManager.getSubtasks().size(), "Удалены не все подзадачи");
    }

    @Test
    void deleteAllEpics() {
        taskManager.deleteAllEpics();
        assertEquals(0, taskManager.getEpics().size(), "Удалены не все эпики");
    }

}