import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private int id = 0;

    private int nextId() {
        id++;
        return id;
    }

    private void refreshEpicStatus(Epic epic) {
        ArrayList<Integer> emptyList = new ArrayList<>();
        if (epic.getSubtasksId().equals(emptyList)) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }

        boolean isNew = false;
        boolean isInProgress = false;
        boolean isDone = false;
        for (Integer id : epic.getSubtasksId()) {
            if (subtasks.get(id).getStatus() == TaskStatus.NEW) {
                isNew = true;
            }
            if (subtasks.get(id).getStatus() == TaskStatus.IN_PROGRESS) {
                isInProgress = true;
            }
            if (subtasks.get(id).getStatus() == TaskStatus.DONE) {
                isDone = true;
            }
        }

        if ((isNew) && (!isDone) && (!isInProgress)) {
            epic.setStatus(TaskStatus.NEW);
        } else if ((!isNew) && (isDone) && (!isInProgress)) {
            epic.setStatus(TaskStatus.DONE);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    public Integer addTask(Task task) {
        final int taskId = nextId();
        task.setId(taskId);
        tasks.put(taskId, task);
        return taskId;
    }

    public Integer addEpic(Epic epic) {
        final int epicId = nextId();
        epic.setId(epicId);
        epics.put(epicId, epic);
        return epicId;
    }

    public Integer addSubtask(Subtask subtask) {
        Epic epic = epics.get(subtask.getEpicId());
        if (epic == null) {
            return null;
        }

        final int subtaskId = nextId();
        subtask.setId(subtaskId);
        subtasks.put(subtaskId, subtask);
        epic.getSubtasksId().add(subtaskId);
        refreshEpicStatus(epic);
        return id;
    }

    public void updateTask(Task task) {
        final Task currentTask = tasks.get(task.getId());
        if (currentTask == null) {
            return;
        }
        tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        final Task currentEpic = epics.get(epic.getId());
        if (currentEpic == null) {
            return;
        }
        epics.put(epic.getId(), epic);
    }

    public void updateSubtask(Subtask subtask) {
        final Task currentSubtask = subtasks.get(subtask.getId());
        if (currentSubtask == null) {
            return;
        }
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());
        refreshEpicStatus(epic);
    }

    public Task getTaskById(Integer id) {
        return tasks.get(id);
    }

    public Task getEpicById(Integer id) {
        return epics.get(id);
    }

    public Task getSubtaskById(Integer id) {
        return subtasks.get(id);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Task> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Task> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public ArrayList<Subtask> displayEpicSubtasks(Epic epic) {
        ArrayList<Subtask> epicSubtasks = new ArrayList<>();
        for (Integer subtaskId : epic.getSubtasksId()) {
            epicSubtasks.add(subtasks.get(subtaskId));
        }
        return epicSubtasks;
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtasksId().clear();
            refreshEpicStatus(epic);
        }
        subtasks.clear();
    }

    public void deleteAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    public void deleteTaskById(Integer id) {
        tasks.remove(id);
    }

    public void deleteEpicById(Integer id) {
        epics.remove(id);
    }

    public void deleteSubtaskById(Integer id) {
        Epic epic = epics.get(subtasks.get(id).getEpicId());
        epic.getSubtasksId().remove(id);
        refreshEpicStatus(epic);
        subtasks.remove(id);
    }
}
