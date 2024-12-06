import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;


public class HistoryManagerTest {
    TaskManager taskManager = Managers.getDefault();
    HistoryManager historyManager = Managers.getDefaultHistory();
    ArrayList<Task> emptyList = new ArrayList<>();

    @Test
    void emptyHistory() {
        assertEquals(emptyList, taskManager.getHistory(), "История не пуста");
    }

    @Test
    void historyVersionTest() {
        Task task1 = new Task(0, "Task#1", "First test", TaskStatus.NEW);
        int task1Id = taskManager.addTask(task1);

        historyManager.add(task1);
        List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История пустая");
        assertEquals(1, history.size(), "История пустая");
    }
}
