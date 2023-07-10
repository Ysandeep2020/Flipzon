package com.flipzon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flipzon.model.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

}
