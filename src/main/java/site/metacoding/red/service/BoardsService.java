package site.metacoding.red.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.util.Script;
import site.metacoding.red.web.dto.boards.request.SaveDto;
import site.metacoding.red.web.dto.boards.request.WriteDto;
import site.metacoding.red.web.dto.boards.response.MainDto;
import site.metacoding.red.web.dto.boards.response.PagingDto;

@RequiredArgsConstructor	
@Service
public class BoardsService {
	
	private final BoardsDao boardsDao;
	
	public PagingDto 게시글목록보기(Integer page, String keyword) { //PagingDto 로 받아보기
		if (page == null)
			page = 0;
		int startNum = page * 4;
		
		List<MainDto> boardsList = boardsDao.findAll(startNum, keyword);
		PagingDto pagingDto = boardsDao.paging(page, keyword);
		
		pagingDto.blockPoint(keyword);
		
		pagingDto.setMainDto(boardsList);

		return pagingDto;
	}
	
	public Boards 게시글상세보기(Integer id) {
		return boardsDao.findById(id);
	}
	
	public void 게시글쓰기(WriteDto writeDto, Users userPrincipal) {
		boardsDao.insert(writeDto.toEntity(userPrincipal.getId()));
	}

	
	public void 게시글수정하기(Integer id, SaveDto updateDto) {
		Boards boardsPS = boardsDao.findById(id); // 바꿀 데이터 갖고오기
		
		boardsPS.update(updateDto); // 데이터 바꿔치기
		
		boardsDao.update(boardsPS); // 데이터 바꿔쳐진 상태에서 쿼리 실행
	}
	public void 게시글삭제하기(Integer id) {
		Boards boardsPS = boardsDao.findById(id); //삭제할 데이터가 있는지 보기
		
		if(boardsPS == null)
			Script.back("삭제할 게시글이 없습니다");
		
		boardsDao.deleteById(id);
	}

	
	
}
