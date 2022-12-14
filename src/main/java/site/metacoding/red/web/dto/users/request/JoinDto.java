package site.metacoding.red.web.dto.users.request;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.users.Users;

@Getter
@Setter
public class JoinDto {
	private String username;
	private String password;
	private String email;
	
	public Users toEntity() {
		Users user = new Users(this.username, this.password, this.email);
		return user;
	}
}
