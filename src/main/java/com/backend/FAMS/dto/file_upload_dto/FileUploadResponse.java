package com.backend.FAMS.dto.file_upload_dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponse {
    private String fileName;
    private String downloadUri;
    private String deleteUri;
    private long size;
}
