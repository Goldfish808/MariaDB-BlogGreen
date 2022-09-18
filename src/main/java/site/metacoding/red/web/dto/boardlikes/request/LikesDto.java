package site.metacoding.red.web.dto.boardlikes.request;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikesDto {
	private Integer usersId;
	private Integer boardsId;
}
