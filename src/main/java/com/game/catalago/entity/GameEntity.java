package com.game.catalago.entity;

import com.game.catalago.dto.GameDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_game")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;
    private String description;   // **antes era descripition**

    public GameEntity() { }

    public GameEntity(Long id, String image, String name, String description) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public void updatedDTO(GameDTO dto) {
        this.image = dto.image();
        this.name = dto.name();
        this.description = dto.description();  // DTO deve ter description()
    }

    // getters e setters renomeados:

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {   // antes getDescripition()
        return description;
    }

    public void setDescription(String description) {  // antes setDescripition()
        this.description = description;
    }
}
