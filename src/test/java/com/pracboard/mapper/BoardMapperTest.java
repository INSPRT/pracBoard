package com.pracboard.mapper;

import com.pracboard.domain.BoardDto;
import com.pracboard.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BoardMapperTest {
    @Autowired
    BoardMapper boardMapper;
    @Autowired
    BoardService boardService;

    @Test
    public void select() throws Exception {
        BoardDto boardDto = boardMapper.select(1452);
        System.out.println("boardDto=" + boardDto);
        assertEquals(1452, (int) boardDto.getBno());
    }

    @Test
    public void delete() throws Exception{
        Map map = new HashMap();
        map.put("bno", 1670);
        map.put("writer", "asdf");
        int result = boardService.remove(map);
        assertEquals(1, result);
    }

}