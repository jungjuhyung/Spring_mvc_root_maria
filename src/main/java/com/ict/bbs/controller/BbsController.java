package com.ict.bbs.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.log.UserDataHelper.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.bbs.dao.BbsVO;
import com.ict.bbs.dao.CommentVO;
import com.ict.bbs.service.BbsService;
import com.ict.common.Paging;

@Controller
public class BbsController {
	@Autowired
	private BbsService bbsService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	// 페이징 기법에 필요한 정보를 저장할 클래스
	@Autowired
	private Paging paging;
	// 게시판 리스트로 이동
	@RequestMapping("bbs_list.do")
	public ModelAndView getBbsList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("bbs/list");
		/*
		페이징 기법 이전 리스트 보이기
		List<BbsVO> list = bbsService.getBbsList();
		if (list != null) {
			mv.addObject("list", list);
			return mv;
		}
		return new ModelAndView("bbs/error");
		 */
		
		// 페이징 기법 사용
		int count = bbsService.getTotalCount();
		paging.setTotalRecord(count);
		
		// 전체 페이지의 수를 구하기
		if (paging.getTotalRecord() <= paging.getNumPerPage() ) {
			// 전체 db수가 지정한 페이지당 수보다 작으면 무조건 1페이지만 설정
			paging.setTotalPage(1);
		}else {
			paging.setTotalPage(paging.getTotalRecord() /paging.getNumPerPage());
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage()+1);
			}
		}
		// 현재 페이지 구하기 => begin, end 구한다.
		String cPage = request.getParameter("cPage");
		if (cPage == null) {
			// 맨처음에 오면 cPage가 없으므로 null이다. 그래서 첫페이지 처리해준다.
			paging.setNowPage(1);
		}else {
			paging.setNowPage(Integer.parseInt(cPage));
		}
		// 오라클 : begin, end 사용
		// 마리아DB : limit, offset 사용
		
		// offset = limit * (현재페이지 -1 )
		// 우리는 limit = NumPerPage
		paging.setOffset(paging.getNumPerPage()*(paging.getNowPage()-1));
		
		
		// 시작 블록과 끝블록 구하기
		paging.setBeginBlock(
				(int)(((paging.getNowPage() -1) / paging.getPagePerBlock()) * paging.getPagePerBlock() +1)
		);
		paging.setEndBlock(paging.getBeginBlock() + paging.getPagePerBlock() -1);
		
		// 주의 사항
		// endBlock과 totalpage가 다르면 endBlock이 크면 endBlock을 totalPage로 지정한다.
		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		
		List<BbsVO> bbs_list = bbsService.getBbsList(paging.getOffset(), paging.getNumPerPage());
		mv.addObject("bbs_list", bbs_list);
		mv.addObject("paging", paging);
		return mv;
	}
	
	// 게시판 글쓰기 이동
	@GetMapping("bbs_write.do")
	public ModelAndView getBbsWrite() {
		ModelAndView mv = new ModelAndView("bbs/write");
		return mv;
	}
	
	// 게시판 글쓰기 저장
	@PostMapping("bbs_write_ok.do")
	public ModelAndView getBbsWriteOK(HttpServletRequest request, BbsVO bvo) {
		try {
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile file = bvo.getFile();
			if (file.isEmpty()) {
				bvo.setF_name("");
			}
			else {
				UUID uuid = UUID.randomUUID();
				String f_name = uuid.toString() + "_" + file.getOriginalFilename();
				bvo.setF_name(f_name);
				
				byte[] in = file.getBytes();
				File out = new File(path, f_name);
				FileCopyUtils.copy(in, out);
				}
			
			String dpwd =passwordEncoder.encode(bvo.getPwd());
			bvo.setPwd(dpwd);
			
			int res = bbsService.getBbsInsert(bvo);
			if (res > 0) {
				return new ModelAndView("redirect:bbs_list.do");					
			}
		} catch (Exception e) {
			System.out.println(e);
		}			
		return new ModelAndView("bbs/error");					
	}
	
	// 게시판 상세보기
	@GetMapping("bbs_detail.do")
	public ModelAndView getBbsDetail(String b_idx, String cPage) {
		System.out.println(b_idx+"= 테스트");
		ModelAndView mv = new ModelAndView("bbs/detail");
		// 조회수 증가
		int result = bbsService.getHitUpdate(b_idx);
		//상세보기
		BbsVO bvo = bbsService.getBbsDetail(b_idx);
		
		if (result > 0 && bvo != null) {
			// 댓글 가져오기
			List<CommentVO> comm_list = bbsService.getCommentList(b_idx);
			System.out.println(comm_list);
			mv.addObject("comm_list", comm_list);
			mv.addObject("bvo", bvo);
			mv.addObject("cPage", cPage);
			return mv;
		}
		return new ModelAndView("bbs/error");
	}
	
	//댓글 입력
	@PostMapping("comment_insert.do")
	public ModelAndView getCommentInsert(CommentVO cvo, @ModelAttribute("b_idx") String b_idx,
			@ModelAttribute("cPage") String cPage) {
		ModelAndView mv = new ModelAndView("redirect:bbs_detail.do");
		int result = bbsService.getCommentInsert(cvo);
		return mv;
	}
	
	// 댓글 삭제
	@PostMapping("comment_delete.do")
	public ModelAndView getCommentDelete(String c_idx, @ModelAttribute("b_idx") String b_idx, 
			@ModelAttribute("cPage") String cPage) {
		ModelAndView mv = new ModelAndView("redirect:bbs_detail.do");
		int result = bbsService.getCommentDelete(c_idx);
		return mv;
		
	}
	
	// 게시글 삭제 이동
	@PostMapping("bbs_delete.do")
	public ModelAndView getBbsDelete(@ModelAttribute("cPage") String cPage,
			@ModelAttribute("b_idx") String b_idx) {
		return new ModelAndView("bbs/delete");
	}
	// 게시글 삭제(무결성 제약조건에 의해 오류)
	@PostMapping("bbs_delete_ok.do")
	public ModelAndView getBbsDeleteOK(@RequestParam("pwd") String pwd,
			@ModelAttribute("cPage") String cPage, @ModelAttribute("b_idx") String b_idx) {
		ModelAndView mv = new ModelAndView();	
		// 비밀번호 체크
		BbsVO  bvo = bbsService.getBbsDetail(b_idx);
		String dpwd = bvo.getPwd();
		
		if (! passwordEncoder.matches(pwd, dpwd)) {
			mv.setViewName("bbs/delete");
			mv.addObject("pwdchk", "fail");
			return mv;
		}else {
			// 원글 삭제(그냥 삭제하면 외래키가 있기 때문에 때문에 오류 발생)
			// 그래서 실제로는 update명령어로 active를 1로 바꾸는 처리한다(실제로는 삭제안됨)
			int res = bbsService.getBbsDelete(b_idx);
			if (res > 0) {
				mv.setViewName("redirect:bbs_list.do");
				return mv;
			}
		}
		return new ModelAndView("bbs/error");
	}
	
}
