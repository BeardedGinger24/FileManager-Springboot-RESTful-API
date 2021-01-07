package project.model.dao;

import java.util.List;

import project.model.File;

public interface FileDao {

	List<File> getFiles();

	List<File> getFiles(Integer parentFolderId);

	List<File> getFiles(Integer parentFolderId, String name);

	File getFile(Integer id);

	List<File> getFile(Integer parentFolderId, String name);

	List<File> getFile(Integer parentFolderId, String name, Integer version);

	File createFile(File file);

	void removeFile(File file);

	List<File> getFiles(String name);

	List<File> getFile(String name);

	List<File> getFile(String name, Integer version);

	List<File> getAllFiles(Integer parentFolderId);

}
