package site.metacoding.red.domain.boards;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.red.web.dto.boards.request.SaveDto;

@Getter
@Setter
@NoArgsConstructor
public class Boards {
	private Integer id;
	private String title;
	private String content;
	private Integer usersId;
	private Timestamp createdAt; // At 시분초 다 표현 , Dt 년월일
	
	public Boards(String title, String content, Integer usersId) {
		this.title = title;
		this.content = content;
		this.usersId = usersId;
	}
	
	public void update(SaveDto update) {
		this.title = update.getTitle();
		this.content = update.getContent();
	}
} 
