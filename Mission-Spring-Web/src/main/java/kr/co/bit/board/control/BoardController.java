package kr.co.bit.board.control;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.bit.board.service.BoardService;
import kr.co.bit.board.vo.BoardVO;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping("/list.do")
	public ModelAndView list() {
		List<BoardVO> boardList = service.selectAllBoard();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/list");
		mav.addObject("boardList", boardList);
		
		return mav;
	}
	
	@RequestMapping(value="/write.do", method=RequestMethod.GET)
	public String writeForm(
//			HttpServletRequest request,
			Model model) {
		
//		request.setAttribute("boardVO", new BoardVO());
		model.addAttribute("boardVO", new BoardVO());
		
		
		return "board/write";
	}

	@RequestMapping(value="/write.do", method=RequestMethod.POST)
	public String write(@Valid BoardVO boardVO, BindingResult result) {

		if(result.hasErrors()) {
			return "board/write";
		}
		
//		System.out.println(boardVO);	
		service.insertBoard(boardVO);
		
		return "redirect:/board/list.do"; 
		// 얘는 알아서 맞춰 준다 
	}
	
	// /board/detail.do?no=15
	@RequestMapping("/detail.do")
	public void detail(@RequestParam("no") int no) {
		BoardVO board = service.selectBoardByNo(no);
		System.out.println(board);
	}
	
	// /board/15/detail.do
	@RequestMapping("/{no}/detail.do")
	public void detail01(@PathVariable int no) {
		
		BoardVO board = service.selectBoardByNo(no);
		System.out.println(board);
	}
	
	
	
	
	
}
