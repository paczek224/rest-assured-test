package com.example.models;

import com.example.commons.utils.DataGeneratorUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Integer id;
    @Builder.Default
    private Integer userId = 1;
    @Builder.Default
    private String title = DataGeneratorUtils.randomAlphanumeric(25);
    @Builder.Default
    private String body = DataGeneratorUtils.randomAlphanumeric(25);
}
