package com.weekie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class News {
    private Long newsId;
    private String title;
    private String description;
    private String publishedAt;
    private String detail;
}
