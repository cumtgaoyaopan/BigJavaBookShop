package org.fkit.bookstore.dao;

import static org.fkit.bookstore.util.common.BookstoreConstants.DETAILTABLE;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.fkit.bookstore.domain.Book;
import org.fkit.bookstore.domain.Detail;

public interface DetailDao {
	//添加到购物车
	@Insert("insert into tb_detail(loginname,bookname,author,price,count) values(#{loginname},#{bookname},#{author},#{price},#{count})")
	void insertDetail(
		@Param("loginname") String loginname,
		@Param("bookname") String bookname,
		@Param("author") String author,
		@Param("price") Double price,
		@Param("count") Integer count
		);
	
	/**
	 * 查询所有订单细节
	 * @return 订单细节对象集合
	 */
	@Select(" select * from "+ DETAILTABLE )
	List<Detail> findAllDetailByLoginname();
	//清空购物车
	@Delete("delete from "+ DETAILTABLE)
	void clearCart();
	//删除商品
	@Delete("delete from "+ DETAILTABLE+" where ID=#{ID}")
	void removeCart(Detail detail);
}
