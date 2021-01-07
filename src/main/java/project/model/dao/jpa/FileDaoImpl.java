package project.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.model.File;
import project.model.dao.FileDao;

@Repository
public class FileDaoImpl implements FileDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<File> getFiles() {
		return entityManager.createQuery(
				"select f from File f where f.parentFolder is null and f.version = (select max(version) from File f2 where f2.name = f.name ) ",
				File.class).getResultList();
	}

	@Override
	public List<File> getFiles(Integer parentFolderId) {
		return entityManager.createQuery(
				"select f from File f where f.parentFolder.id = :parentFolderId and f.version = (select max(version) from File f2 where f2.name = f.name) ",
				File.class).setParameter("parentFolderId", parentFolderId).getResultList();
	}

	@Override
	public List<File> getAllFiles(Integer parentFolderId) {
		return entityManager.createQuery("select f from File f where f.parentFolder.id = :parentFolderId ", File.class)
				.setParameter("parentFolderId", parentFolderId).getResultList();
	}

	@Override
	public List<File> getFiles(String name) {
		return entityManager.createQuery("from File where parentFolder is null and name = :name", File.class)
				.setParameter("name", name).getResultList();
	}

	@Override
	public List<File> getFile(String name) {
		return entityManager
				.createQuery("from File where parentFolder is null and name = :name ORDER BY version DESC", File.class)
				.setParameter("name", name).getResultList();
	}

	@Override
	public List<File> getFile(Integer parentFolderId, String name) {
		return entityManager
				.createQuery("from File where parentFolder.id = :parentFolderId and name = :name ORDER BY version DESC",
						File.class)
				.setParameter("parentFolderId", parentFolderId).setParameter("name", name).getResultList();
	}

	@Override
	public List<File> getFile(Integer parentFolderId, String name, Integer version) {
		return entityManager
				.createQuery(
						"from File where parentFolder.id = :parentFolderId and name = :name and version = :version",
						File.class)
				.setParameter("parentFolderId", parentFolderId).setParameter("name", name)
				.setParameter("version", version).getResultList();
	}

	@Override
	public List<File> getFile(String name, Integer version) {
		return entityManager
				.createQuery("from File where parentFolder.id is null and name = :name and version = :version",
						File.class)
				.setParameter("name", name).setParameter("version", version).getResultList();
	}

	@Override
	@Transactional
	public File createFile(File file) {
		return entityManager.merge(file);
	}

	@Override
	@Transactional
	public void removeFile(File file) {
		entityManager.remove(file);
	}

	@Override
	public File getFile(Integer id) {
		return entityManager.find(File.class, id);
	}

	@Override
	public List<File> getFiles(Integer parentFolderId, String name) {
		return entityManager
				.createQuery("from File f where f.parentFolder.id = :parentFolderId and f.name = :name", File.class)
				.setParameter("parentFolderId", parentFolderId).setParameter("name", name).getResultList();
	}

}
