package project.model;

import java.util.ArrayList;
import java.util.List;

public class FolderFileDto {

//	private Integer folderId;
//	private String folderName;
//
//	private Integer fileId;
//	private String fileName;
//	private String fileType;
//	private String fileSize;
//	private Integer fileVersion;

	private List<FolderDto> folders;
	private List<FileDto> files;

	private Integer parentFolderId;
	private String folderName;

	public FolderFileDto() {
		folders = new ArrayList<FolderDto>();
		files = new ArrayList<FileDto>();
	}

	public FolderFileDto(ArrayList<Folder> argsFolders) {
		folders = new ArrayList<FolderDto>();
		files = new ArrayList<FileDto>();

		for (Folder folder : argsFolders) {
			folders.add(new FolderDto(folder));
		}

		if (argsFolders.get(0).getParentFolder() != null) {
			parentFolderId = argsFolders.get(0).getParentFolder().getParentFolder() == null ? null
					: argsFolders.get(0).getParentFolder().getParentFolder().getId();
			folderName = argsFolders.get(0).getParentFolder().getName();
		}
	}

	public FolderFileDto(ArrayList<File> argsFiles, boolean t) {
		files = new ArrayList<FileDto>();
		folders = new ArrayList<FolderDto>();
		for (File file : argsFiles) {
			files.add(new FileDto(file));
		}

		if (argsFiles.get(0).getParentFolder() != null) {
			parentFolderId = argsFiles.get(0).getParentFolder().getParentFolder() == null ? null
					: argsFiles.get(0).getParentFolder().getParentFolder().getId();
			folderName = argsFiles.get(0).getParentFolder().getName();
		}
	}

	public FolderFileDto(ArrayList<Folder> argsFolders, ArrayList<File> argsFiles) {
		folders = new ArrayList<FolderDto>();
		for (Folder folder : argsFolders) {
			folders.add(new FolderDto(folder));
		}

//		folderDto.addAll(folders);

		if (argsFolders.get(0).getParentFolder() != null) {
			parentFolderId = argsFolders.get(0).getParentFolder().getParentFolder() == null ? null
					: argsFolders.get(0).getParentFolder().getParentFolder().getId();
			folderName = argsFolders.get(0).getParentFolder().getName();
		}

		files = new ArrayList<FileDto>();
		for (File file : argsFiles) {
			files.add(new FileDto(file));
		}

		if (argsFiles.get(0).getParentFolder() != null) {
			parentFolderId = argsFiles.get(0).getParentFolder().getParentFolder() == null ? null
					: argsFiles.get(0).getParentFolder().getParentFolder().getId();
			folderName = argsFiles.get(0).getParentFolder().getName();
		}
	}

//	public FolderFileDto(Folder folder) {
//		folderId = folder.getId();
//		folderName = folder.getName();
//
//		if (folder.getParentFolder() != null) {
//			parentFolderId = folder.getParentFolder().getParentFolder() == null ? null
//					: folder.getParentFolder().getParentFolder().getId();
//			parentFolderName = folder.getParentFolder().getName();
//		}
//	}

	public FolderFileDto(Integer parentFolderId, String parentFolderName) {
		files = new ArrayList<FileDto>();
		folders = new ArrayList<FolderDto>();
		this.parentFolderId = parentFolderId;
		this.folderName = parentFolderName;
	}

	public FolderFileDto(String parentFolderName) {
		files = new ArrayList<FileDto>();
		folders = new ArrayList<FolderDto>();
		this.folderName = parentFolderName;
	}

//	public FolderFileDto(File file) {
//		fileId = file.getId();
//		fileName = file.getName();
//		fileType = file.getType();
//		fileSize = file.getSize();
//		fileVersion = file.getVersion();
////		data = file.getData();
//
//		if (file.getParentFolder() != null) {
//			parentFolderId = file.getParentFolder().getParentFolder() == null ? null
//					: file.getParentFolder().getParentFolder().getId();
//			parentFolderName = file.getParentFolder().getName();
//		}
//	}

	public FolderFileDto(Folder folder, File file) {
//		folderId = folder.getId();
//		folderName = folder.getName();
//
//		fileId = file.getId();
//		fileName = file.getName();
//		fileType = file.getType();
//		fileSize = file.getSize();
//		fileVersion = file.getVersion();
////		data = file.getData();
//
//		if (file.getParentFolder() != null) {
//			parentFolderId = file.getParentFolder().getParentFolder() == null ? null
//					: folder.getParentFolder().getParentFolder().getId();
//			parentFolderName = file.getParentFolder().getName();
//		}
	}

//	public Integer getFolderId() {
//		return folderId;
//	}
//
//	public void setFolderId(Integer folderId) {
//		this.folderId = folderId;
//	}
//
//	public String getFolderName() {
//		return folderName;
//	}
//
//	public void setFolderName(String folderName) {
//		this.folderName = folderName;
//	}

	public Integer getParentFolderId() {
		return parentFolderId;
	}

	public List<FolderDto> getFolders() {
		return folders;
	}

	public void setFolders(List<FolderDto> folders) {
		this.folders = folders;
	}

	public List<FileDto> getFiles() {
		return files;
	}

	public void setFiles(List<FileDto> files) {
		this.files = files;
	}

	public void setParentFolderId(Integer parentFolderId) {
		this.parentFolderId = parentFolderId;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

//	public Integer getFileId() {
//		return fileId;
//	}
//
//	public void setFileId(Integer fileId) {
//		this.fileId = fileId;
//	}
//
//	public String getFileName() {
//		return fileName;
//	}
//
//	public void setFileName(String fileName) {
//		this.fileName = fileName;
//	}
//
//	public String getFileType() {
//		return fileType;
//	}
//
//	public void setFileType(String fileType) {
//		this.fileType = fileType;
//	}
//
//	public String getFileSize() {
//		return fileSize;
//	}
//
//	public void setFileSize(String fileSize) {
//		this.fileSize = fileSize;
//	}
//
//	public Integer getFileVersion() {
//		return fileVersion;
//	}
//
//	public void setFileVersion(Integer fileVersion) {
//		this.fileVersion = fileVersion;
//	}
}
