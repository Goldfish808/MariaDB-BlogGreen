package site.metacoding.red.domain.boardlikes;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardLikes {
	private Integer id;
	private Integer usersId;
	private Integer boardsId;
	private Timestamp createdAt;
}
