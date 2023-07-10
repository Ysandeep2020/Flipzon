package com.flipzon.service;

import org.springframework.web.multipart.MultipartFile;

import com.flipzon.model.Attachment;

public interface AttachmentService {

	String uploadFile(MultipartFile file);

	Attachment downloadFile(Long pk);

}
