package site.metacoding.red.web.dto.boards.response;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import site.metacoding.red.domain.boards.BoardsDao;

@Getter
@Setter
public class PagingDto {
	public static final int ROW =5; // 페이징에 필요한 상수 지정해서 사용하기.
	private final Integer blockCount=5; // 상수 한페이지에 페이지 넘수 개수(5) 1-5, 6-10
	private Integer currentBlock; // 변수
	private Integer startPageNum; // 1 -> 6 -> 11
	private Integer lastPageNum; // 5-> 10 -> 15

	private Integer totalCount;
	private Integer totalPage;
	private Integer currentPage;

	// getter가 만들어지면 isLast/isFirst() 로 만들어짐 -> el 표현식에서는 last/first로 찾아짐
	private boolean isFirst;
	private boolean isLast;
	
	private Integer page;
	private String keyword;
	
	private List<MainDto> mainDto;
	
	public void blockPoint(String keyword) {
		this.keyword = keyword;
		
		this.currentBlock = currentPage / blockCount;
		this.startPageNum = 1 + blockCount * currentBlock; // 1 + 5 * 0 = 1 첫 Current 시작 : 1 / 6 / 11 / 16
		this.lastPageNum = 5 + blockCount * currentBlock; // 5 + 5 * 0 = 5 첫 Currnet 끝 : 5 / 10 / 15 / 20
		
		if (totalPage < lastPageNum) {
			this.lastPageNum = totalPage;
		}
	}
	
	
	
	
}
