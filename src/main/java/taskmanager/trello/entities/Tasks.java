package taskmanager.trello.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_tasks")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @Column(length = 2)
    private int status;

    private String statusMeaning;

    @ManyToOne
    private Folders folder;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusMeaning() {
        if (this.status == 0){
            return "TO DO";
        }else if (this.status == 1){
            return "IN TEST";
        }else if (this.status == 2){
            return "DONE";
        }else{
            return "FAILED";
        }
    }

    public Folders getFolder() {
        return folder;
    }
}
