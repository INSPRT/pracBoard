package com.pracboard.service;

import com.pracboard.domain.BoardDto;
import com.pracboard.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardMapper boardMapper;

    @Override
    public int getCount() throws Exception {
        return boardMapper.count();
    }

    @Override
    public int remove(Map map) throws Exception {
        return boardMapper.delete(map);
    }

    @Override
    public int write(BoardDto boardDto) throws Exception {
        return boardMapper.insert(boardDto);
    }

    @Override
    public List<BoardDto> getList() throws Exception {
        return boardMapper.selectAll();
    }

    @Override
    public BoardDto read(Integer bno) throws Exception {
        BoardDto boardDto = boardMapper.select(bno);
        boardMapper.increaseViewCnt(bno);

        return boardDto;
    }

    @Override
    public List<BoardDto> getPage(Map map) throws Exception {
        return boardMapper.selectPage(map);
    }

    @Override
    public int modify(BoardDto boardDto) throws Exception {
        return boardMapper.update(boardDto);
    }

//    @Override
//    public int getSearchResultCnt(SearchCondition sc) throws Exception {
//        return boardDao.searchResultCnt(sc);
//    }
//
//    @Override
//    public List<BoardDto> getSearchResultPage(SearchCondition sc) throws Exception {
//        return boardDao.searchSelectPage(sc);
//    }
}