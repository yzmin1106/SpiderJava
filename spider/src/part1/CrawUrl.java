package part1;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;

public class CrawUrl implements Serializable{
	
	public CrawUrl()
	{
		
	}

	/**
	 * 原始URL的值，主机部分是域名
	 */
	private String OriUrl;
	/**
	 * URL的值，主机部分是IP，为了防止重复主机的出现
	 */
	private String url;
	/**
	 * URL NUM
	 */
	private int urlNo;
	/**
	 * 获取URL返回的结果码
	 */
	private int statusCode;
	/**
	 * 此URL被其他文章引用的次数
	 */
	private int hitNum;
	/**
	 * 此URL对应文章的汉字编码
	 */
	private String charSet;
	/**
	 * 文章摘要
	 */
	private String abstractText;
	/**
	 * 作者
	 */
    private String author;
    /**
     * 文章的权重(包含导向词的信息)
     */
	private int weight;
	/**
	 * 文章的描述
	 */
	private String description;
	/**
	 * 文章大小
	 */
	private int fileSize;
	/**
	 * 最后修改时间
	 */
	private Timestamp lastUpdateTimestamp;
	/**
	 * 过期时间
	 */
	private Date timeToLive;
	/**
	 * 文章名称
	 */
	private String title;
	/**
	 * 文章类型
	 */
	private String type;
	/**
	 * 引用的链接
	 */
	private String [] urlRefrences;
	/**
	 * 爬取的层次，从种子开始，依次为第0层，第1层...
	 */
	private int layer;
	public String getOriUrl() {
		return OriUrl;
	}
	public void setOriUrl(String oriUrl) {
		OriUrl = oriUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getUrlNo() {
		return urlNo;
	}
	public void setUrlNo(int urlNo) {
		this.urlNo = urlNo;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public int getHitNum() {
		return hitNum;
	}
	public void setHitNum(int hitNum) {
		this.hitNum = hitNum;
	}
	public String getCharSet() {
		return charSet;
	}
	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
	public String getAbstractText() {
		return abstractText;
	}
	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public Timestamp getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}
	public void setLastUpdateTimestamp(Timestamp lastUpdateTimestamp) {
		this.lastUpdateTimestamp = lastUpdateTimestamp;
	}
	public Date getTimeToLive() {
		return timeToLive;
	}
	public void setTimeToLive(Date timeToLive) {
		this.timeToLive = timeToLive;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String[] getUrlRefrences() {
		return urlRefrences;
	}
	public void setUrlRefrences(String[] urlRefrences) {
		this.urlRefrences = urlRefrences;
	}
	public int getLayer() {
		return layer;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	
}
