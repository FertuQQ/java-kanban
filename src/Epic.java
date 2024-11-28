import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtasksId = new ArrayList<>();

    public Epic(int id, String name, String description, TaskStatus status, ArrayList<Integer> subtasksId) {
        super(id, name, description, status);
        this.subtasksId = subtasksId;
    }

    public ArrayList<Integer> getSubtasksId() {
        return subtasksId;
    }

    public void setSubtasksId(ArrayList<Integer> subtasksId) {
        this.subtasksId = subtasksId;
    }


    @Override
    public String toString() {
        return "Epic{" +
                "epicId=" + id +
                ", epicName='" + name + '\'' +
                ", epicDescription='" + description + '\'' +
                ", epicStatus=" + status + '\'' +
                ", subtasksId=" + subtasksId +
                '}';
    }
}
