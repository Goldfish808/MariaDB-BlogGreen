package site.metacoding.red.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.users.request.JoinDto;
import site.metacoding.red.web.dto.users.request.LoginDto;
import site.metacoding.red.web.dto.users.request.SaveDto;

@RequiredArgsConstructor
@Service
public class UsersService {
	private final UsersDao usersDao;
	private final BoardsDao boardsDao;
	
	// DB에 연결될 필요가 없어서 서비스에서 열외, 트랜잭션이 필요없음 public void 로그아웃() {}	
	 public void 회원가입(JoinDto joinDto) { // username, password, email (id, createdAt)
	      // 1. 디티오를 엔티티로 변경하는 코
	      Users usersPS = joinDto.toEntity();
	      // 2. 엔티티로 디비 수행
	      usersDao.insert(usersPS);
	   }
	 
	   public Users 로그인(LoginDto loginDto) { // username, password
	      Users usersPS = usersDao.login(loginDto);
	      // if로 usersPS의 password와 디티오 password 비교
	      if(usersPS == null)
	    	  return null;
	      
	      if(usersPS.getPassword().equals(loginDto.getPassword())) {
	      }
	      return usersPS;
	   }
	   
	   public Users 회원수정(Integer id, SaveDto saveDto) { // id, 디티오(password, email)
	      // 1. 영속화
	      Users usersPS = usersDao.findById(id);
	      System.out.println("실행됨"+usersPS.getUsername());
	      // 2. 영속화된 객체 변경
	      usersPS.update(saveDto);
	      
	      // 3. 디비 수행
	      usersDao.update(usersPS);
	      
	      return usersPS;
	   }
	   
	   @Transactional(rollbackFor = RuntimeException.class)
	   public void 회원탈퇴(Integer id) {
	      usersDao.deleteById(id);
	      boardsDao.updateUsersId(id);
	      
	      
	     // boardsDao.해당회원이적은글을 모두 찾아서 usersId를 null로 업데이트();
	   } // users - delete, boards - update
	   
	   public boolean 네임중복확인(String username) {
	      Users usersPS = usersDao.findByUsername(username);
	      if(usersPS == null) { //있으면,
	    	  return false;
	      }else
	    	  return true;
	   }
	   
	   public Users 회원정보보기(Integer id) {
	      Users usersPS = usersDao.findById(id);
	      return usersPS;
	   }

}
