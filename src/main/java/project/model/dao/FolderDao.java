package project.model.dao;

import java.util.List;

import project.model.Folder;

public interface FolderDao {

	List<Folder> getFolders();

	List<Folder> getFolders(Integer parentFolderId);

	Folder getFolder(Integer id);

	Folder createFolder(Folder folder);

	void removeFolder(Folder folder);

	List<Folder> getParentFolder(Integer parentFolderId);

}
