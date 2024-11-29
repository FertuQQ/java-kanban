import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        System.out.println("Создаем две задачи, а также эпик с двумя подзадачами и эпик с одной подзадачей");
        TaskManager taskManager = new TaskManager();

        Task test1 = new Task(0, "Task#1", "First test", TaskStatus.NEW);
        Task test2 = new Task(0, "Task#2", "Second test", TaskStatus.NEW);
        int task1Id = taskManager.addTask(test1);
        int task2Id = taskManager.addTask(test2);

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

        System.out.println("Распечатываем списки эпиков, задач и подзадач");
        for (Task task : taskManager.getTasks()) {
            System.out.println(task);
        }
        for (Task task : taskManager.getEpics()) {
            System.out.println(task);
        }
        for (Task task : taskManager.getSubtasks()) {
            System.out.println(task);
        }

        System.out.println("Изменяем статусы созданных объектов, распечатываем их");
        test1.setStatus(TaskStatus.IN_PROGRESS);
        test2.setStatus(TaskStatus.DONE);
        subtask2.setStatus(TaskStatus.IN_PROGRESS);
        subtask3.setStatus(TaskStatus.DONE);
        for (Task task : taskManager.getTasks()) {
            System.out.println(task);
        }
        for (Task task : taskManager.getEpics()) {
            System.out.println(task);
        }
        for (Task task : taskManager.getSubtasks()) {
            System.out.println(task);
        }


        System.out.println("Удаляем одну из задач и один из эпиков");
        taskManager.deleteTaskById(task1Id);
        taskManager.deleteEpicById(epic1Id);
        for (Task task : taskManager.getTasks()) {
            System.out.println(task);
        }
        for (Task task : taskManager.getEpics()) {
            System.out.println(task);
        }
        for (Task task : taskManager.getSubtasks()) {
            System.out.println(task);
        }
    }
}
