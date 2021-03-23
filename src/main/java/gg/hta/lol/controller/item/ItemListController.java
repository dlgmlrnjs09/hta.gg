package gg.hta.lol.controller.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gg.hta.lol.service.ItemService;
import gg.hta.lol.vo.ItemVo;

@RestController
public class ItemListController {
	@Autowired ItemService service;
	
	@RequestMapping(value="/itemlist",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE} )
	public List<ItemVo> Item_list(){
		List<ItemVo> list = service.item_alllist();
		return list;
	}
	@RequestMapping(value="/iteminfo",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ItemVo item_info(int inum) {
	
		ItemVo info=service.item_select(inum);
		return info;
	}
	@RequestMapping(value="/select/iteminfo",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ItemVo item_info2(int inum) {
	
		ItemVo info=service.item_select(inum);
		return info;
}
}
	