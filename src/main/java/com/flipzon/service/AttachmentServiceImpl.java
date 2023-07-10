package com.flipzon.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.flipzon.model.Attachment;
import com.flipzon.repository.AttachmentRepository;

@Service
public class AttachmentServiceImpl implements AttachmentService {
	@Autowired
	private AttachmentRepository attRepo;

	@Override
	public String uploadFile(MultipartFile file) {
		Attachment attachment = null;
		Attachment att = new Attachment();
		try {
			att.setData(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		att.setName(file.getOriginalFilename());
		att.setType(file.getContentType());

		// save file
		attachment = attRepo.save(att);

		// save image in c drive
		String uploadDir = "C:\\images";
		try {
			FileOutputStream fos = new FileOutputStream(uploadDir + File.separator + att.getName());
			fos.write(att.getData());
			fos.flush();
			fos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		// save images into cloud
		

		return attachment.getName() + " Added with : " + attachment.getPk();
	}

	@Override
	public Attachment downloadFile(Long pk) {
		return attRepo.findById(pk).orElseThrow(() -> new RuntimeException("File Not found With " + pk));
	}

}
