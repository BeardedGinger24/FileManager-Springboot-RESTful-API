package project.model;

public class FolderDto {
	
    private Integer id;
    private String name;
//    private Integer parentFolderId;
//    private String parentFolderName;

    public FolderDto()
    {
    }

    public FolderDto( Folder folder )
    {
        id = folder.getId();
        name = folder.getName();
        
//        if( folder.getParentFolder() != null )
//        {
//        	parentFolderId = folder.getParentFolder().getId();
//        	parentFolderName = folder.getParentFolder().getName();
//        }
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
