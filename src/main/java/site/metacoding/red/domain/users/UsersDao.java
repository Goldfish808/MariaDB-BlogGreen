package site.metacoding.red.domain.users;

import java.util.List;

import site.metacoding.red.domain.boards.Boards;

public interface UsersDao {
	public void insert(Users users); //1번 ~ 
	public List<Users> findAll();
	public Users findById(Integer id);
	public void update(Users users);
	public void deleteById(Integer id);
	public Users findByUsername(String username);
}
