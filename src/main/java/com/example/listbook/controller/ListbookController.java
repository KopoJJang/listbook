package com.example.listbook.controller;


import com.example.listbook.dto.ListbookDTO;
import com.example.listbook.dto.PageRequestDTO;
import com.example.listbook.service.ListbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/listbook")
@Log4j2
@RequiredArgsConstructor

public class ListbookController {

    private final ListbookService service;


    @GetMapping("/")
    public String index(){
        return "redirect:/listbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("방명록 목록 화면 : " + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
    }

    // 등록 화면을 보여줌
    @GetMapping("/register")
    public void register(){
        log.info("show register");
    }


    // 등록 처리 후에 목록페이지를 이동
    @PostMapping("/register")
    public String registerPost(ListbookDTO dto, RedirectAttributes redirectAttributes){
        log.info("등록처리 후 목록페이지 리다이렉트" + dto);
        Long bno = service.register(dto);
        redirectAttributes.addFlashAttribute("msg", bno);
        return "redirect:/listbook/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(long bno, @ModelAttribute("requestDTO")PageRequestDTO requestDTO, Model model){
        log.info("bno: " + bno);
        ListbookDTO dto = service.read(bno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes redirectAttributes){
        log.info("bno: " + bno );
        service.remove(bno);
        redirectAttributes.addFlashAttribute("msg", bno);
        return "redirect:/listbook/list";
    }

    @PostMapping("/modify")
    public String modify(ListbookDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        service.modify(dto);
        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("bno", dto.getBno());
        return "redirect:/listbook/read";
    }
}
