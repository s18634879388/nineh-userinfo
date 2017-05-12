package com.ninehcom.userinfo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninehcom.common.util.Base62Utils;
import com.ninehcom.common.util.ResultV2;
import com.ninehcom.userinfo.entity.Item;
import com.ninehcom.userinfo.entity.UserInfo;
import com.ninehcom.userinfo.mapper.UserInfoMapper;
import com.ninehcom.userinfo.service.ItemService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

@Controller
public class ItemController {
	
	@Resource
	private ItemService itemService;

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	final Logger itemAddLogger =
			//new LogHelper("com.ninehcom.user.controller.ItemController.addNewItem","/data/logs/csl-user-item-add.log",true);
			LoggerFactory.getLogger("com.ninehcom.userinfo.controller.ItemController.addNewItem");
	
	final Logger useSupplementaryCardLogger =
			//new LogHelper("com.ninehcom.user.controller.ItemController.useSupplementaryCard","/data/logs/csl-user-useSupplementaryCard.log",true);
			LoggerFactory.getLogger("com.ninehcom.userinfo.controller.ItemController.useSupplementaryCard");
	
	@ApiOperation(value = "给用户添加物品")
	@RequestMapping(value="/users/items/add",method=RequestMethod.POST)
	@ResponseBody

	public ResultV2 addNewItem(@RequestBody  Item item,@RequestHeader(value = "appId") String appId) throws Exception{
		ArrayList<Item> itemList = new ArrayList();
		String param = new ObjectMapper().writeValueAsString(item);
		String serialNumber = System.currentTimeMillis()+Base62Utils.getRamdomIntString(3);
		itemAddLogger.info("serialNumber: {} log json body: {}",serialNumber,param);
		itemAddLogger.info("serialNumber: {} ,parse json is:  getNhId : {} ,item.getNhId() : {} ,item.getItemTypeKey(): {} ,item.getCount(): {} item.getUserId()L {}",serialNumber,item.getUserId(),item.getNhId(),item.getItemTypeKey(),item.getCount(),item.getUserId());
		try{
			if (item.getNhId()!=null&&!item.getNhId().isEmpty()&&!item.getNhId().trim().isEmpty()) {
				//selectUserInfoById方法传入的id是nhid，sql语句查询的是id
				UserInfo user = userInfoMapper.selectUserInfoById(item.getNhId());
				if (null == user) {
					itemAddLogger.error("serialNumber: {} log exceptions can not find user by nhId {}",serialNumber,item.getNhId());
					throw new Exception("can not find user by nhId: "+item.getNhId());
				}
				item.setUserId(user.getId());
			} else if (item.getNhId()!=null&&!item.getNhId().isEmpty()&&!item.getNhId().trim().isEmpty()) {
				//selectNHIdFromUserId方法传入的是nhid，查询出的是id
				String nhId = userInfoMapper.selectNHIdFromUserId(item.getUserId());
				item.setNhId(nhId);
				itemAddLogger.info("serialNumber: {} log selectNHIdFromUserId  {} by userId {}",serialNumber,item.getNhId(), item.getUserId());
				if (item.getNhId()!=null&&!item.getNhId().isEmpty()&&!item.getNhId().trim().isEmpty()) {
					itemAddLogger.error("serialNumber: {} log exceptions can not selectNHIdFromUserId by userId {}",serialNumber,item.getUserId());
					throw new Exception("can not selectNHIdFromUserId by userId: "+item.getUserId());
				}

//				UserInfo user = userInfoMapper.selectUserInfoById(item.getNhId());
//				if (null == user) {
//					itemAddLogger.error("serialNumber: {} log exceptions can not find user by nhId {}",serialNumber,item.getNhId());
//					throw new Exception("can not find user by nhId: "+item.getNhId());
//				}
				item.setUserId(nhId);
			} else {
				itemAddLogger.error("serialNumber: {} log exceptions param  userId  or nhId must have one",serialNumber);
				throw new Exception("param  userId  or nhId must have one");
			}
			for (int i = 0 ; i<item.getCount();i++) {			  
				  item =itemService.addNewItem(item);
				  itemList.add(item.clone());
			}
		}catch (Exception ex){
			itemAddLogger.error("serialNumber: {} log exceptions {}",serialNumber,ex.getMessage());
		}
		
		String result = new ObjectMapper().writeValueAsString(itemList);  
		itemAddLogger.info("serialNumber: {} log result body: {}",serialNumber,result);
		return ResultV2.Success( itemList);
	}
	
	@ApiOperation(value = "用户所有物品")
	@RequestMapping(value="/users/items/list/{user_id}",method=RequestMethod.GET)
	@ResponseBody
	public ResultV2 userItemList(@PathVariable(value="user_id") String userId,@RequestHeader(value = "appId") String appId){
		ArrayList<Item> userItemList = (ArrayList<Item>) itemService.userItemList(userId);
		return ResultV2.Success(userItemList);
	}


	@ApiOperation(value = "测试使用补签卡")
	@RequestMapping(value="/users/items/test-use-supplementary/{userId}",method=RequestMethod.POST)
	@ResponseBody
	public ResultV2 testUseSupplementary(@PathVariable String userId,@RequestHeader(value = "appId") String appId) {
		return itemService.useSupplementary(userId);
	}
	

}
