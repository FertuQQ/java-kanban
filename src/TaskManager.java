import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    List<Task> getHistory();

    Integer addTask(Task task);
    Integer addEpic(Epic epic);
    Integer addSubtask(Subtask subtask);

    void updateTask(Task task);
    void updateEpic(Epic epic);
    void updateSubtask(Subtask subtask);

    Task getTaskById(Integer id);
    Task getEpicById(Integer id);
    Task getSubtaskById(Integer id);

    ArrayList<Task> getTasks();
    ArrayList<Task> getEpics();
    ArrayList<Task> getSubtasks();
    ArrayList<Subtask> getEpicSubtasks(Epic epic);

    void deleteAllTasks();
    void deleteAllSubtasks();
    void deleteAllEpics();

    void deleteTaskById(Integer id);
    void deleteEpicById(Integer id);
    void deleteSubtaskById(Integer id);
}
