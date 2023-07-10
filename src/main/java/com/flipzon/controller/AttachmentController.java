package com.flipzon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.flipzon.model.Attachment;
import com.flipzon.service.AttachmentService;

@RestController
public class AttachmentController {
	@Autowired
	private AttachmentService attachmentService;

	@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String uploadFile(@RequestPart MultipartFile file) {
		return attachmentService.uploadFile(file);
	}

	@GetMapping("/download/{pk}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long pk) {
		Attachment attachemt = attachmentService.downloadFile(pk);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachemt.getType()))
 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachemt.getName() + "\"")
				.body(new ByteArrayResource(attachemt.getData()));

	}
}
