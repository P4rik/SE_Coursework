package recipes.module;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "recipes")
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "process")
    private String process;
    @Column(name = "author")
    private String author;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "recipe")
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;
    private LocalDateTime dataOfCreated;

    @PrePersist
    private  void init(){
        dataOfCreated = LocalDateTime.now();
    }

    public  void  addImageToRecipe (Image image){
        image.setRecipe(this);
        images.add(image);
    }
}
