package recipes.module;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "process", columnDefinition = "text")
    private String process;
    @Column(name = "catalogy")
    private String catalogy;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "recipe")
    private List<Image> images = new ArrayList<>();
    private Long previewImageId;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    private LocalDateTime dataOfCreated;
    @Column(name = "bytes", columnDefinition = "longblob")
    private byte[] bytes;

    public Recipe(Long id, String title, String description, String process, String catalogy, List<Image> images, Long previewImageId, User user, LocalDateTime dataOfCreated, byte[] bytes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.process = process;
        this.catalogy = catalogy;
        this.images = images;
        this.previewImageId = previewImageId;
        this.user = user;
        this.dataOfCreated = dataOfCreated;
        this.bytes = bytes;
    }

    public Recipe() {
    }

    @PrePersist
    private void init() {
        dataOfCreated = LocalDateTime.now();
    }

    public void addImageToRecipe(Image image) {
        image.setRecipe(this);
        images.add(image);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Recipe;
    }

}
