package com.pracboard.controller;

import com.pracboard.domain.BoardDto;
import com.pracboard.domain.PageHandler;
import com.pracboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;


    @GetMapping("/write")
    public String write(Model m){
        return "boardWrite";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto){

        try {
            boardService.write(boardDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model m, RedirectAttributes rattr, HttpSession session){

        String reporter = "asdf";
        String msg = "DEL_OK";
        String msg2 = "DEL_ERR";
        try {
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            Map map = new HashMap();
            map.put("bno", bno);
            map.put("reporter", reporter);

            int rowCnt = boardService.remove(map);

            if(rowCnt==1){
                rattr.addAttribute("msg", msg);
                return "redirect:/board/list?page="+page+"&pageSize="+pageSize;
            }else{
                throw new Exception("remove error");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rattr.addAttribute("msg", msg2);
            return "redirect:/board/list?page="+page+"&pageSize="+pageSize;
        }
    }

    @GetMapping("/list")
    public String list(Integer page, Integer pageSize, Model m, HttpServletRequest request) {

        if(page==null){
            page = 1;
        }
        if(pageSize==null){
            pageSize = 10;
        }

        try {
            int totalCnt = boardService.getCount();
            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);

            Map map = new HashMap();
            map.put("offset", (page-1)*pageSize);
            map.put("pageSize", pageSize);

            List<BoardDto> list = boardService.getPage(map);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model m) {
        try {
            BoardDto boardDto = boardService.read(bno);
            m.addAttribute("boardDto", boardDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "boardDetail";
    }


    @GetMapping("/")
    public String home(HttpServletRequest request) {

        return "index";
    }

    @GetMapping("/modify")
    public String modify(Integer bno, Integer page, Integer pageSize, Model m){
        try {
            BoardDto boardDto = boardService.read(bno);
            m.addAttribute("boardDto", boardDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "boardModify";
    }

    @PostMapping("/modify")
    public String modify(BoardDto boardDto){

        System.out.println(boardDto);
        try {
            boardService.modify(boardDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/board/list";
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id")!=null;
    }
}

