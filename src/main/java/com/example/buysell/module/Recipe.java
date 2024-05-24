package com.example.buysell.module;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Recipe {
    private Long id;
    private String title;
    private String description;
    private String recipe;
    private String author;
}
