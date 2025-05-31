package dto;

import java.util.List;

	
	public class LaboratorioDTO {
	    private Long id; // Ajustado para Long para corresponder ao banco
	    private String name; // Renomeado de 'nome' para 'name' por consistência com o frontend
	    private String status;
	    private List<Long> softwaresInstaladosIds;
	    
	    public LaboratorioDTO(Long id, String name, String status, List<Long> softwaresInstaladosIds) {
			super();
			this.id = id;
			this.name = name;
			this.status = status;
			this.softwaresInstaladosIds = softwaresInstaladosIds;
		}
	    
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public List<Long> getSoftwaresIds() {
			return softwaresInstaladosIds;
		}

		public void setSoftwaresIds(List<Long> softwaresInstaladosIds) {
			this.softwaresInstaladosIds = softwaresInstaladosIds;
		}

		
	    
}
