package org.ithub.mediastorageservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class MediaBatchOperationRequestDTO {
    private List<Long> mediaIds;
    private String operation; // например "delete", "addToAlbum", "removeFromAlbum"
    private Long albumId; // используется для операций с альбомами
}