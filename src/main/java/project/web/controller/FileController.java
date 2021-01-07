package project.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import project.model.File;
import project.model.FileDto;
import project.model.Folder;
import project.model.dao.FileDao;
import project.model.dao.FolderDao;

@RestController
@RequestMapping("/files")
public class FileController {

	@Autowired
	private FileDao fileDao;

	@Autowired
	private FolderDao folderDao;

//	@GetMapping
//	public List<FileDto> list() {
//		List<File> files = fileDao.getFiles();
//		List<FileDto> filesDtos = new ArrayList<FileDto>();
//		for (File file : files)
//			filesDtos.add(new FileDto(file));
//		return filesDtos;
//	}
//
//	@GetMapping("/{name}")
//	public ResponseEntity<Resource> get(@PathVariable String name, @RequestBody FileDto fileDto) {
////		if (!StringUtils.hasText(fileDto.getName()))
////			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File name is required");
//
//		if (fileDto.getParentFolderId() != null) {
//			Folder parentFolder = folderDao.getFolder(fileDto.getParentFolderId());
//			fileDto.setParentFolderId(parentFolder.getId());
//		}
//
//		List<File> file = new ArrayList<File>();
//		// fileDao.getFile(fileDto.getParentFolderId(), name);
//		if (fileDto.getParentFolderId() == null) {
//			file = fileDao.getFile(name);
//		} else {
//			file = fileDao.getFile(fileDto.getParentFolderId(), name);
//		}
//
//		if (file.isEmpty())
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
//
//		return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.get(0).getType()))
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.get(0).getName() + "\"")
//				.body(new ByteArrayResource(file.get(0).getData()));
//	}
//
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public FileDto create(@RequestBody MultipartFile uploadedFile) throws Exception {
//		FileDto fileDto = new FileDto();
//		fileDto.setParentFolderId(2);
//
//		String fileName = StringUtils.cleanPath(uploadedFile.getOriginalFilename());
//
//		File file = new File();
//		file.setName(fileName);
//		file.setSize(uploadedFile.getSize() + " Byte(s)");
//		file.setType(uploadedFile.getContentType());
//
//		try {
//			if (fileName.contains("..")) {
//				throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
//			}
//
//			file.setData(uploadedFile.getBytes());
//		} catch (IOException e) {
//			throw new Exception("Unable to store file" + fileName + ": " + e);
//		}
//
//		if (fileDto.getParentFolderId() != null) {
//			Folder parentFolder = folderDao.getFolder(fileDto.getParentFolderId());
//			file.setParentFolder(parentFolder);
//		}
//
//		List<File> fileVersions;
//		if (file.getParentFolder() == null) {
//			fileVersions = fileDao.getFiles(file.getName());
//		} else {
//			fileVersions = fileDao.getFiles(file.getParentFolder().getId(), file.getName());
//		}
//
//		if (fileVersions.isEmpty()) {
//			file.setVersion(1);
//		} else {
//			file.setVersion(fileVersions.get(fileVersions.size() - 1).getVersion() + 1);
//		}
//
//		file = fileDao.createFile(file);
//		return new FileDto(file);
//	}
//
//	@DeleteMapping("/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void remove(@PathVariable Integer id) {
//		File file = fileDao.getFile(id);
//		List<File> files;
//
//		if (file.getParentFolder() == null) {
//			files = fileDao.getFiles(file.getName());
//		} else {
//			files = fileDao.getFiles(file.getParentFolder().getId(), file.getName());
//		}
//
//		if (files == null) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
//		}
//
//		for (File fileToRemove : files) {
//			fileDao.removeFile(fileToRemove);
//		}
//	}
}
