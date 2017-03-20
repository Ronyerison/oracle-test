package br.ufpi.loes.oracleTest.core.graph;

public class Event {
	private String eventType;
	private String element;
	private Integer count;
	private String url;
	
	public Event() {
	}
	
	/**
	 * @param id
	 * @param eventType
	 * @param element
	 * @param count
	 * @param url
	 */
	public Event(String eventType, String element, Integer count,
			String url) {
		this.eventType = eventType;
		this.element = element;
		this.count = count;
		this.url = url;
	}

	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getElement() {
		return element;
	}
	public void setElement(String element) {
		this.element = element;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		result = prime * result
				+ ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Event other = (Event) obj;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element))
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [eventType=" + eventType + ", element=" + element
				+ ", count=" + count + ", url=" + url + "]";
	}

	
}
