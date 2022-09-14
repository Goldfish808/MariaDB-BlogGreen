package site.metacoding.red.domain.boards;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.metacoding.red.web.dto.boards.request.SaveDto;
import site.metacoding.red.web.dto.boards.response.MainDto;
import site.metacoding.red.web.dto.boards.response.PagingDto;

public interface BoardsDao {
	public void insert(Boards boards); //1번 ~ 
	public List<MainDto> findAll(@Param("startNum")Integer startNum, @Param("keyword") String keyword);
	public PagingDto paging(@Param("page") Integer page, @Param("keyword") String keyword);
	public Boards findById(Integer id);
	public void update(Boards boards);
	public void deleteById(Integer id);
	
	public void updateUsersId(Integer usersId);
}
