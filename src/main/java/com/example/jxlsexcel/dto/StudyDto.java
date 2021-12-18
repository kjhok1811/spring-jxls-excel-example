package com.example.jxlsexcel.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StudyDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public StudyDto(Long id, String title, String content, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}
