import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusOfEpicTest {
    TaskManager taskManager = Managers.getDefault();

    Epic epic1 = new Epic(0, "Epic#1", "First epic", TaskStatus.NEW, new ArrayList<>());
    int epic1Id = taskManager.addEpic(epic1);

    @Test
    public void statusEmptyEpic() {
        assertEquals(TaskStatus.NEW, epic1.getStatus(), "Статусы не совпадают");
    }

    @Test
    public void statusNewEpic() {
        Subtask subtask1 = new Subtask(0, "Subtask#1", "1.1 Subtask", TaskStatus.NEW, epic1Id);
        Subtask subtask2 = new Subtask(0, "Subtask#2", "1.2 Subtask", TaskStatus.NEW, epic1Id);
        int subtask1Id = taskManager.addSubtask(subtask1);
        int subtask2Id = taskManager.addSubtask(subtask2);

        assertEquals(TaskStatus.NEW, epic1.getStatus(), "Статусы не совпадают");
    }

    @Test
    public void statusDoneEpic() {
        Subtask subtask1 = new Subtask(0, "Subtask#1", "1.1 Subtask", TaskStatus.DONE, epic1Id);
        Subtask subtask2 = new Subtask(0, "Subtask#2", "1.2 Subtask", TaskStatus.DONE, epic1Id);
        int subtask1Id = taskManager.addSubtask(subtask1);
        int subtask2Id = taskManager.addSubtask(subtask2);

        assertEquals(TaskStatus.DONE, epic1.getStatus(), "Статусы не совпадают");
    }

    @Test
    public void statusNewDoneEpic() {
        Subtask subtask1 = new Subtask(0, "Subtask#1", "1.1 Subtask", TaskStatus.NEW, epic1Id);
        Subtask subtask2 = new Subtask(0, "Subtask#2", "1.2 Subtask", TaskStatus.DONE, epic1Id);
        int subtask1Id = taskManager.addSubtask(subtask1);
        int subtask2Id = taskManager.addSubtask(subtask2);

        assertEquals(TaskStatus.IN_PROGRESS, epic1.getStatus(), "Статусы не совпадают");
    }

    @Test
    public void statusInProgressEpic() {
        Subtask subtask1 = new Subtask(0, "Subtask#1", "1.1 Subtask", TaskStatus.IN_PROGRESS, epic1Id);
        Subtask subtask2 = new Subtask(0, "Subtask#2", "1.2 Subtask", TaskStatus.IN_PROGRESS, epic1Id);
        int subtask1Id = taskManager.addSubtask(subtask1);
        int subtask2Id = taskManager.addSubtask(subtask2);

        assertEquals(TaskStatus.IN_PROGRESS, epic1.getStatus(), "Статусы не совпадают");
    }
}
