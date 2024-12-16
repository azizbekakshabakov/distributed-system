package com.example.file_service.repository;


import com.example.file_service.model.AttachmentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentFileRepository extends JpaRepository<AttachmentFile, Long> {
    AttachmentFile findByFileName(String fileName);
}
