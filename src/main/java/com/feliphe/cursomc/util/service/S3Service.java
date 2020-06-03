package com.feliphe.cursomc.util.service;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.feliphe.cursomc.exception.FileException;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3Client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);
	
	public URI uploadFile(MultipartFile multiPartFile) {
		
		try {
			String fileName = multiPartFile.getOriginalFilename();
			InputStream is = multiPartFile.getInputStream();
			String contentType = multiPartFile.getContentType();
			return uploadFile(fileName, is, contentType);
		} catch (Exception e) {
			throw new FileException("Erro de IO: " + e.getMessage());
		}
		
	}

	public URI uploadFile(String fileName, InputStream is, String contentType) {
	
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			LOG.info("Iniciando Upload");
			s3Client.putObject(bucketName, fileName, is, meta);
			LOG.info("Updload finalizado");
			return s3Client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Error ao converter URL para URI");
		} 
	}
		
}
