package site.metacoding.red.web.dto.users.request;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.users.Users;

@Getter
@Setter
public class SaveDto {
	private String password;
	private String email;
}
