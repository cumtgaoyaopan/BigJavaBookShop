package org.fkit.bookstore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.fkit.bookstore.domain.Book;
import org.fkit.bookstore.domain.Detail;
import org.fkit.bookstore.service.BookstoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DetailController {
	/**
	 * 自动注入BookstoreService
	 */
	@Autowired
	@Qualifier("bookstoreService")
	private BookstoreService bookstoreService;
	/**
	 * 添加订单细节
	 */
	@RequestMapping(value="/addDetail")	
	public ModelAndView insertDetail(String loginname, 
			String bookname, 
			String author,
			Double price, 
			Integer count,
			ModelAndView mv,
			HttpSession session){
		 bookstoreService.insertDetail(loginname, bookname, author, price, count);
		mv.setViewName("success");
		return mv;
	}
	/**
	 * 处理/cart请求
	 */
	@RequestMapping(value="/cart")
	public String cart(Model model){
		//获得所有图书集合
		List<Detail> detail_list = bookstoreService.getAllDetailByLoginname();
		//将图书集合添加到model当中
		model.addAttribute("detail_list", detail_list);
		//跳转到novel页面
		return "cart";
	}
	/**
	 * 处理/cart请求
	 */
	@RequestMapping(value="/clear")
	public String clear(Model model){
		bookstoreService.clearCart();
		//获得所有图书集合
		List<Detail> detail_list = bookstoreService.getAllDetailByLoginname();
		//将图书集合添加到model当中
		model.addAttribute("detail_list", detail_list);
		//跳转到novel页面
		return "cart";
	}
}
