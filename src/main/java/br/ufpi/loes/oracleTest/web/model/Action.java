package br.ufpi.loes.oracleTest.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Action implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String sActionType;
	private String sContent;
	private Long sPosX;
	private Long sPosY;
	private String sTag;
	private String sTagIndex;
	private Long sTime;
	private String sUrl;
	private String sContentText;
	private String sClass;
	private String sId;
	private String sName;
	@Lob
	@Column(length = 1000)
	private String sXPath;
	private String sUserAgent;
	private String sClient;
	private Long sVersion;
	private String sUserName;
	private String sRole;
	private String sJhm;
	private String sActionJhm;
	private String sSectionJhm;
	private Boolean sDeleted;
	private String createdAt;
	private String updateAt;
	private Long sOracleElements;
	private Long sOracleVisibleElements;
	private String sOracleUrl;
	
	public Action() {
	}
	
	/**
	 * @param id
	 * @param sActionType
	 * @param sContent
	 * @param sPosX
	 * @param sPosY
	 * @param sTag
	 * @param sTagIndex
	 * @param sTime
	 * @param sUrl
	 * @param sContentText
	 * @param sClass
	 * @param sId
	 * @param sName
	 * @param sXPath
	 * @param sUserAgent
	 * @param sClient
	 * @param sVersion
	 * @param sUserName
	 * @param sRole
	 * @param sJhm
	 * @param sActionJhm
	 * @param sSectionJhm
	 * @param sDeleted
	 * @param createdAt
	 * @param updateAt
	 */
	public Action(Long id, String sActionType, String sContent, Long sPosX,
			Long sPosY, String sTag, String sTagIndex, Long sTime,
			String sUrl, String sContentText, String sClass, String sId,
			String sName, String sXPath, String sUserAgent, String sClient,
			Long sVersion, String sUserName, String sRole, String sJhm,
			String sActionJhm, String sSectionJhm, Boolean sDeleted,
			String createdAt, String updateAt, Long sOracleElements, Long sOracleVisibleElements, String sOracleUrl) {
		this.id = id;
		this.sActionType = sActionType;
		this.sContent = sContent;
		this.sPosX = sPosX;
		this.sPosY = sPosY;
		this.sTag = sTag;
		this.sTagIndex = sTagIndex;
		this.sTime = sTime;
		this.sUrl = sUrl;
		this.sContentText = sContentText;
		this.sClass = sClass;
		this.sId = sId;
		this.sName = sName;
		this.sXPath = sXPath;
		this.sUserAgent = sUserAgent;
		this.sClient = sClient;
		this.sVersion = sVersion;
		this.sUserName = sUserName;
		this.sRole = sRole;
		this.sJhm = sJhm;
		this.sActionJhm = sActionJhm;
		this.sSectionJhm = sSectionJhm;
		this.sDeleted = sDeleted;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
		this.sOracleElements = sOracleElements;
		this.sOracleVisibleElements = sOracleVisibleElements;
		this.sOracleUrl = sOracleUrl;
	}

	public Action(Action action){
		this.id = action.id;
		this.sActionType = action.sActionType;
		this.sContent = action.sContent;
		this.sPosX = action.sPosX;
		this.sPosY = action.sPosY;
		this.sTag = action.sTag;
		this.sTagIndex = action.sTagIndex;
		this.sTime = action.sTime;
		this.sUrl = action.sUrl;
		this.sContentText = action.sContentText;
		this.sClass = action.sClass;
		this.sId = action.sId;
		this.sName = action.sName;
		this.sXPath = action.sXPath;
		this.sUserAgent = action.sUserAgent;
		this.sClient = action.sClient;
		this.sVersion = action.sVersion;
		this.sUserName = action.sUserName;
		this.sRole = action.sRole;
		this.sJhm = action.sJhm;
		this.sActionJhm = action.sActionJhm;
		this.sSectionJhm = action.sSectionJhm;
		this.sDeleted = action.sDeleted;
		this.sOracleElements = action.sOracleElements;
		this.sOracleVisibleElements = action.sOracleVisibleElements;
		this.sOracleUrl = action.sOracleUrl;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getsActionType() {
		return sActionType;
	}
	public void setsActionType(String sActionType) {
		this.sActionType = sActionType;
	}
	public String getsContent() {
		return sContent;
	}
	public void setsContent(String sContent) {
		this.sContent = sContent;
	}
	public Long getsPosX() {
		return sPosX;
	}
	public void setsPosX(Long sPosX) {
		this.sPosX = sPosX;
	}
	public Long getsPosY() {
		return sPosY;
	}
	public void setsPosY(Long sPosY) {
		this.sPosY = sPosY;
	}
	public String getsTag() {
		return sTag;
	}
	public void setsTag(String sTag) {
		this.sTag = sTag;
	}
	public String getsTagIndex() {
		return sTagIndex;
	}
	public void setsTagIndex(String sTagIndex) {
		this.sTagIndex = sTagIndex;
	}
	public Long getsTime() {
		return sTime;
	}
	public void setsTime(Long sTime) {
		this.sTime = sTime;
	}
	public String getsUrl() {
		return sUrl;
	}
	public void setsUrl(String sUrl) {
		this.sUrl = sUrl;
	}
	public String getsContentText() {
		return sContentText;
	}
	public void setsContentText(String sContentText) {
		this.sContentText = sContentText;
	}
	public String getsClass() {
		return sClass;
	}
	public void setsClass(String sClass) {
		this.sClass = sClass;
	}
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsXPath() {
		return sXPath;
	}
	public void setsXPath(String sXPath) {
		this.sXPath = sXPath;
	}
	public String getsUserAgent() {
		return sUserAgent;
	}
	public void setsUserAgent(String sUserAgent) {
		this.sUserAgent = sUserAgent;
	}
	public String getsClient() {
		return sClient;
	}
	public void setsClient(String sClient) {
		this.sClient = sClient;
	}
	public Long getsVersion() {
		return sVersion;
	}
	public void setsVersion(Long sVersion) {
		this.sVersion = sVersion;
	}
	public String getsUserName() {
		return sUserName;
	}
	public void setsUserName(String sUserName) {
		this.sUserName = sUserName;
	}
	public String getsRole() {
		return sRole;
	}
	public void setsRole(String sRole) {
		this.sRole = sRole;
	}
	public String getsJhm() {
		return sJhm;
	}
	public void setsJhm(String sJhm) {
		this.sJhm = sJhm;
	}
	public String getsActionJhm() {
		return sActionJhm;
	}
	public void setsActionJhm(String sActionJhm) {
		this.sActionJhm = sActionJhm;
	}
	public String getsSectionJhm() {
		return sSectionJhm;
	}
	public void setsSectionJhm(String sSectionJhm) {
		this.sSectionJhm = sSectionJhm;
	}
	public Boolean getsDeleted() {
		return sDeleted;
	}
	public void setsDeleted(Boolean sDeleted) {
		this.sDeleted = sDeleted;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}
	public Long getsOracleElements() {
		return sOracleElements;
	}
	public void setsOracleElements(Long sOracleElements) {
		this.sOracleElements = sOracleElements;
	}
	public Long getsOracleVisibleElements() {
		return sOracleVisibleElements;
	}
	public void setsOracleVisibleElements(Long sOracleVisibleElements) {
		this.sOracleVisibleElements = sOracleVisibleElements;
	}

	@Override
	public String toString() {
		return "Action [id=" + id + ", sActionType=" + sActionType
				+ ", sContent=" + sContent + ", sPosX=" + sPosX + ", sPosY="
				+ sPosY + ", sTag=" + sTag + ", sTagIndex=" + sTagIndex
				+ ", sTime=" + sTime + ", sUrl=" + sUrl + ", sContentText="
				+ sContentText + ", sClass=" + sClass + ", sId=" + sId
				+ ", sName=" + sName + ", sXPath=" + sXPath + ", sUserAgent="
				+ sUserAgent + ", sClient=" + sClient + ", sVersion=" + sVersion
				+ ", sUserName=" + sUserName + ", sRole=" + sRole + ", sJhm="
				+ sJhm + ", sActionJhm=" + sActionJhm + ", sSectionJhm="
				+ sSectionJhm + ", sDeleted=" + sDeleted + ", createdAt="
				+ createdAt + ", updateAt=" + updateAt + ", sOracleElements="
				+ sOracleElements + ", sOracleVisibleElements="
				+ sOracleVisibleElements + "]";
	}

	
}
