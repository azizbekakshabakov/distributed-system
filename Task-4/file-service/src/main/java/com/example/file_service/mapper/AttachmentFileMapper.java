package com.example.file_service.mapper;

import com.example.file_service.dto.AttachmentFileDto;
import com.example.file_service.model.AttachmentFile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttachmentFileMapper {
    AttachmentFileDto toDto(AttachmentFile attachmentFile);
    AttachmentFile toEntity(AttachmentFileDto attachmentFileDto);

    List<AttachmentFileDto> toDtoList(List<AttachmentFile> attachmentFiles);
}
