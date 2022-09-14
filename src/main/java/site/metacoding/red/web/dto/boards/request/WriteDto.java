package site.metacoding.red.web.dto.boards.request;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;

@Getter
@Setter
public class WriteDto {
	private String title;
	private String content;	
	
	public Boards toEntity(Integer usersId) { //Dto 가  엔티티로 변하는 메서드
		Boards boards = new Boards(this.title, this.content, usersId);
		return boards;
	}
}