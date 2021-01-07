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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import project.model.File;
import project.model.FileDto;
import project.model.Folder;
import project.model.FolderDto;
import project.model.FolderFileDto;
import project.model.dao.FileDao;
import project.model.dao.FolderDao;

@RestController
@RequestMapping("/folders")
public class FileManagerController {

	@Autowired
	private FolderDao folderDao;

	@Autowired
	private FileDao fileDao;

	@GetMapping
	@CrossOrigin
	public FolderFileDto get() {
		ArrayList<Folder> folders = new ArrayList<Folder>();
		ArrayList<File> files = new ArrayList<File>();
		FolderFileDto foldersFilesDtos = null;

		List<Folder> folderList = folderDao.getFolders();

		List<File> fileList = fileDao.getFiles();
		if (!fileList.isEmpty() && !folderList.isEmpty()) {
			folders.addAll(folderList);
			files.addAll(fileList);
			foldersFilesDtos = new FolderFileDto(folders, files);
		} else if (!folderList.isEmpty()) {
			folders.addAll(folderList);
			foldersFilesDtos = new FolderFileDto(folders);
		} else {

		}
		
		return foldersFilesDtos;
	}

	@GetMapping("/{id}")
	@CrossOrigin
	public FolderFileDto get(@PathVariable Integer id) {
		ArrayList<Folder> folders = new ArrayList<Folder>();
		ArrayList<File> files = new ArrayList<File>();
		FolderFileDto foldersFilesDtos = new FolderFileDto();

		if (folderDao.getFolder(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found");
		}

		List<Folder> folderList = new ArrayList<Folder>();
		try {
			folderList.addAll(folderDao.getFolders(id));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found");
		}

		List<File> fileList = fileDao.getFiles(id);
		if (!fileList.isEmpty() && !folderList.isEmpty()) {
			folders.addAll(folderList);
			files.addAll(fileList);
			foldersFilesDtos = new FolderFileDto(folders, files);
		} else if (!folderList.isEmpty()) {
			folders.addAll(folderList);
			foldersFilesDtos = new FolderFileDto(folders);
		} else if (!fileList.isEmpty()) {
			files.addAll(fileList);
			foldersFilesDtos = new FolderFileDto(files, true);
		}

		if (foldersFilesDtos.getFolders().isEmpty() && foldersFilesDtos.getFiles().isEmpty()) {
			List<Folder> parentFolders;
			try {
				parentFolders = folderDao.getParentFolder(id);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found");
			}

			if (parentFolders.get(0).getParentFolder() != null) {
				Integer parentFolderId = parentFolders.get(0).getParentFolder() == null ? null
						: parentFolders.get(0).getParentFolder().getId();
				String parentFolderName = parentFolders.get(0).getName();

				foldersFilesDtos = new FolderFileDto(parentFolderId, parentFolderName);
			} else {
				String parentFolderName = parentFolders.get(0).getName();
				foldersFilesDtos = new FolderFileDto(parentFolderName);
			}
		}

		return foldersFilesDtos;
	}

//	@PostMapping
//	@CrossOrigin
//	@ResponseStatus(HttpStatus.CREATED)
//	public FolderDto createFolder(@RequestBody FolderDto newFolder) {
//		if (!StringUtils.hasText(newFolder.getName()))
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Folder name is required");
//
//		Folder folder = new Folder();
//		folder.setName(newFolder.getName());
//
//		folder = folderDao.createFolder(folder);
//		return new FolderDto(folder);
//	}

	@PostMapping(value = { "", "/{id}" })
	@CrossOrigin
	@ResponseStatus(HttpStatus.CREATED)
	public FolderDto createFolder(@PathVariable(required = false) Integer id, @RequestBody FolderDto newFolder) {
		if (!StringUtils.hasText(newFolder.getName()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Folder name is required");

		Folder folder = new Folder();
		folder.setName(newFolder.getName());

		if (id != null) {
			Folder parentFolder = folderDao.getFolder(id);
			folder.setParentFolder(parentFolder);
		}

		folder = folderDao.createFolder(folder);
		return new FolderDto(folder);
	}

//	@PostMapping("/files")
//	@ResponseStatus(HttpStatus.CREATED)
//	public FileDto uploadFile(@RequestBody MultipartFile uploadedFile) throws Exception {
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

	@PostMapping(value = { "/files", "/{id}/files" })
	@ResponseStatus(HttpStatus.CREATED)
	public FileDto uploadFile(@PathVariable(required = false) Integer id, @RequestBody MultipartFile uploadedFile)
			throws Exception {
		if (id != null && folderDao.getFolder(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found");
		}

		String fileName = StringUtils.cleanPath(uploadedFile.getOriginalFilename());

		File file = new File();
		file.setName(fileName);
		file.setSize(uploadedFile.getSize() + " Byte(s)");
		file.setType(uploadedFile.getContentType());

		try {
			if (fileName.contains("..")) {
				throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
			}

			file.setData(uploadedFile.getBytes());
		} catch (IOException e) {
			throw new Exception("Unable to store file" + fileName + ": " + e);
		}

		if (id != null) {
			Folder parentFolder = folderDao.getFolder(id);
			file.setParentFolder(parentFolder);
		}

		List<File> fileVersions;
		if (file.getParentFolder() == null) {
			fileVersions = fileDao.getFiles(file.getName());
		} else {
			fileVersions = fileDao.getFiles(file.getParentFolder().getId(), file.getName());
		}

		if (fileVersions.isEmpty()) {
			file.setVersion(1);
		} else {
			file.setVersion(fileVersions.get(fileVersions.size() - 1).getVersion() + 1);
		}

		file = fileDao.createFile(file);
		return new FileDto(file);
	}

	@GetMapping(value = { "/files/{name}", "/files/{name}/version/{version}", "{id}/files/{name}",
			"{id}/files/{name}/version/{version}" })
	public ResponseEntity<Resource> downloadFile(@PathVariable(required = false) Integer id, @PathVariable String name,
			@PathVariable(required = false) Integer version) {
		List<File> file = new ArrayList<File>();
		if (version != null) {
			file = fileDao.getFile(name, version);
		} else {
			file = fileDao.getFile(name);
		}

		if (id != null) {
			Folder parentFolder = folderDao.getFolder(id);

			if (version != null) {
				file = fileDao.getFile(parentFolder.getId(), name, version);
			} else {
				file = fileDao.getFile(parentFolder.getId(), name);
			}
		}

		if (file.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.get(0).getType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.get(0).getName() + "\"")
				.body(new ByteArrayResource(file.get(0).getData()));
	}

//	@GetMapping("/files/{name}/version/{version}")
//	public ResponseEntity<Resource> downloadFile(@PathVariable String name, @PathVariable Integer version) {
//		List<File> file = new ArrayList<File>();
//		file = fileDao.getFile(name, version);
//
//		if (file.isEmpty())
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
//
//		return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.get(0).getType()))
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.get(0).getName() + "\"")
//				.body(new ByteArrayResource(file.get(0).getData()));
//	}

//	@GetMapping(value = { "{id}/files/{name}", "{id}/files/{name}/version/{version}" })
//	public ResponseEntity<Resource> downloadFile(@PathVariable Integer id, @PathVariable String name,
//			@PathVariable(required = false) Integer version) {
//		Folder parentFolder = folderDao.getFolder(id);
//
//		List<File> file = new ArrayList<File>();
//		if (version != null) {
//			file = fileDao.getFile(parentFolder.getId(), name, version);
//		} else {
//			file = fileDao.getFile(parentFolder.getId(), name);
//		}
//
//		if (file.isEmpty())
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
//
//		return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.get(0).getType()))
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.get(0).getName() + "\"")
//				.body(new ByteArrayResource(file.get(0).getData()));
//	}

//	@GetMapping("{id}/files/{name}/version/{version}")
//	public ResponseEntity<Resource> downloadFile(@PathVariable Integer id, @PathVariable String name,
//			@PathVariable Integer version) {
//		Folder parentFolder = folderDao.getFolder(id);
//
//		List<File> file = new ArrayList<File>();
//		file = fileDao.getFile(parentFolder.getId(), name, version);
//
//		if (file.isEmpty())
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
//
//		return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.get(0).getType()))
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.get(0).getName() + "\"")
//				.body(new ByteArrayResource(file.get(0).getData()));
//	}

	@DeleteMapping("/{id}")
	@CrossOrigin
	public String removeFolder(@PathVariable Integer id) {
		Folder folder = folderDao.getFolder(id);

		if (folder == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Folder not found");
		}

		Integer[] removeCount = new Integer[2];
		removeCount[0] = folderCount(folder, 1);
		removeCount[1] = fileCount(folder, 0);

		folderDao.removeFolder(folder);

		return "Total folders deleted: " + removeCount[0] + ". Total files deleted: " + removeCount[1] + ".";
	}

	@DeleteMapping("/files/{fileId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CrossOrigin
	public void removeFile(@PathVariable Integer fileId) {
		File file = fileDao.getFile(fileId);
		List<File> files;

		if (file.getParentFolder() == null) {
			files = fileDao.getFiles(file.getName());
		} else {
			files = fileDao.getFiles(file.getParentFolder().getId(), file.getName());
		}

		if (files == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
		}

		for (File fileToRemove : files) {
			fileDao.removeFile(fileToRemove);
		}
	}

	public Integer folderCount(Folder folder, Integer folderCount) {
		List<Folder> folders = folderDao.getFolders(folder.getId());

		if (folders.size() == 0) {
			return folderCount;
		}

		for (Folder currentFolder : folders) {
			folderCount = folderCount(currentFolder, folderCount + 1);
		}
		return folderCount;
	}

	public Integer fileCount(Folder folder, Integer fileCount) {
		List<Folder> folders = folderDao.getFolders(folder.getId());
		List<File> files = fileDao.getAllFiles(folder.getId());

		for (File file : files) {
			fileCount++;
		}

		if (folders.size() == 0) {
			return fileCount;
		}

		for (Folder currentFolder : folders) {
			fileCount = fileCount(currentFolder, fileCount);
		}
		return fileCount;
	}

}
