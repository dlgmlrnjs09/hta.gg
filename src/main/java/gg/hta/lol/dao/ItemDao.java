package gg.hta.lol.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gg.hta.lol.vo.ItemVo;

@Repository
public class ItemDao {
	@Autowired private SqlSession sqlSession;
	public final String NAMESPACE="gg.hta.lol.mapper.ItemMapper";
	public int item_insert(ItemVo vo) {
		return sqlSession.insert(NAMESPACE+".item_insert",vo);
	}
	public ItemVo item_list(int inum) {
		return sqlSession.selectOne(NAMESPACE+".item_select",inum);
	}
	public List<ItemVo> item_alllist(){
	
		return sqlSession.selectList(NAMESPACE+".item_alllist");
	}
}
