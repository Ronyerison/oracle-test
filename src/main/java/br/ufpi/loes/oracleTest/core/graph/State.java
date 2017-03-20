package br.ufpi.loes.oracleTest.core.graph;

public class State {
	
	private String currentUrl;
	private Integer countVisibleElements;
	private Boolean root;
	
	public State() {
	}
	
	
	public State(String currentUrl, Integer countVisibleElements,
			Boolean root) {
		super();
		this.currentUrl = currentUrl;
		this.countVisibleElements = countVisibleElements;
		this.root = root;
	}
	
	

	public State(String currentUrl, Integer countVisibleElements) {
		super();
		this.currentUrl = currentUrl;
		this.countVisibleElements = countVisibleElements;
		this.root = false;
	}


	public String getCurrentUrl() {
		return currentUrl;
	}
	
	public void setCurrentUrl(String currentUrl) {
		this.currentUrl = currentUrl;
	}
	
	public Integer getCountVisibleElements() {
		return countVisibleElements;
	}
	
	public void setCountVisibleElements(Integer countVisibleElements) {
		this.countVisibleElements = countVisibleElements;
	}

	public Boolean getRoot() {
		return root;
	}

	public void setRoot(Boolean root) {
		this.root = root;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((currentUrl == null) ? 0 : currentUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (currentUrl == null) {
			if (other.currentUrl != null)
				return false;
		} else if (!currentUrl.equals(other.currentUrl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "State [currentUrl=" + currentUrl + ", countVisibleElements="
				+ countVisibleElements + ", root=" + root + "]";
	}

}
