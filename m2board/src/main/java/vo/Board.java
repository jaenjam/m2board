package vo;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private String createDate;
	private int boardRead;
	private int boardNice;
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getBoardRead() {
		return boardRead+1;
	}
	public void setBoardRead(int boardRead) {
		this.boardRead = boardRead;
	}
	public int getBoardNice() {
		return boardNice+1;
	}
	public void setBoardNice(int boardNice) {
		this.boardNice = boardNice;
	}
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", boardContent=" + boardContent + ", createDate=" + createDate + ", boardRead=" + boardRead
				+ ", boardNice=" + boardNice + "]";
	}
	
	
}
