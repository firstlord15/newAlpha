package org.ithub.mediastorageservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MediaAlbumCreateRequestDTO {
    @NotBlank(message = "Album name is required")
    private String name;

    private String description;

    private Boolean isPublic = false;
}
