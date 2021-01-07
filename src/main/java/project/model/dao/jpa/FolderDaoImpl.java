package project.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.model.Folder;
import project.model.dao.FolderDao;

@Repository
public class FolderDaoImpl implements FolderDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Folder> getFolders() {
		return entityManager.createQuery("from Folder where parentFolder is null", Folder.class).getResultList();
	}

	@Override
	public List<Folder> getFolders(Integer parentFolderId) {
		return entityManager.createQuery("from Folder where parentFolder.id = :parentFolderId", Folder.class)
				.setParameter("parentFolderId", parentFolderId).getResultList();
	}
	
	@Override
	public List<Folder> getParentFolder(Integer parentFolderId) {
		return entityManager.createQuery("from Folder where id = :parentFolderId", Folder.class)
				.setParameter("parentFolderId", parentFolderId).getResultList();
	}

	@Override
	@Transactional
	public Folder createFolder(Folder folder) {
		return entityManager.merge(folder);
	}

	@Override
	@Transactional
	public void removeFolder(Folder folder) {
//		Integer[] count = { 0, 0 };

//		count[0] = folderCount(folder, 1);
		entityManager.remove(folder);

//		return count;
	}

	@Override
	public Folder getFolder(Integer id) {
		return entityManager.find(Folder.class, id);
	}

}
