package org.ithub.mediastorageservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class MediaSearchRequestDTO {
    private Set<String> tags;
    private String mediaType;
    private String filename;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private String uploadedBy;
}