package project.model;

public class FileDto {

	private Integer id;
	private String name;
	private String type;
	private String size;
	private Integer version;

//	private byte[] data;

//	private Integer parentFolderId;
//	private String parentFolderName;

	public FileDto() {
	}

	public FileDto(File file) {
		id = file.getId();
		name = file.getName();
		type = file.getType();
		size = file.getSize();
		version = file.getVersion();
//		data = file.getData();

//		if (file.getParentFolder() != null) {
//			parentFolderId = file.getParentFolder().getId();
//			parentFolderName = file.getParentFolder().getName();
//		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getVersion() {
		return version;
	}

//	public byte[] getData() {
//		return data;
//	}
//
//	public void setData(byte[] data) {
//		this.data = data;
//	}

	public void setVersion(Integer version) {
		this.version = version;
	}

//	public Integer getParentFolderId() {
//		return parentFolderId;
//	}
//
//	public void setParentFolderId(Integer parentFolderId) {
//		this.parentFolderId = parentFolderId;
//	}
//
//	public String getParentFolderName() {
//		return parentFolderName;
//	}
//
//	public void setParentFolderName(String parentFolderName) {
//		this.parentFolderName = parentFolderName;
//	}

}
