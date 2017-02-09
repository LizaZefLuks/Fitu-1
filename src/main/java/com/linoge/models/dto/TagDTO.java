package com.linoge.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Timo on 30.12.2016.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {
    public Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String title;
}
