package com.ninehcom.userinfo.service;

import com.ninehcom.common.util.*;
import com.ninehcom.userinfo.entity.Item;
import com.ninehcom.userinfo.entity.UserInfo;
import com.ninehcom.userinfo.enums.ItemType;
import com.ninehcom.userinfo.mapper.ItemMapper;
import com.ninehcom.userinfo.mapper.UserInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ItemService {
	
	@Resource
	private UserInfoService userInfoService;

	@Autowired
	private UserInfoMapper userInfoMapper;

    @Autowired
    private ItemMapper itemMapper;
	

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	final Logger useSupplementaryCardLogger = LoggerFactory.getLogger("com.ninehcom.userinfo.controller.ItemController.useSupplementaryCard");
	
	/**
	 * 添加物品
	 * @param item
	 * @return
	 *
	 */
	@Transactional(propagation= Propagation.REQUIRED)
	public Item addNewItem( Item item) throws Exception {
//		Long userId = userService.getUserIdByToken(token);
//		if (null == userId) {
//			throw new NotLoginException("用户未登录");
//		}
//		item.setUserId(userId);
		if (ItemType.GuessCardTimeLimit.getKey().equals(item.getItemTypeKey())) {
			if (item.getRelatedId()==null||item.getRelatedId().isEmpty()||item.getRelatedId().trim().isEmpty()) {
				throw new Exception("relatedId 不能为空!");
			}
			if (null == item.getInvalidAt()) {
				throw new Exception("invalidAt 不能为空!");
			}
		}

		item.setItemId(IdUtil.nextId());
		item.setCreatedAt(new Date());
		item.setUpdatedAt(new Date());
        itemMapper.insertItem(item);

//		itemDao.insertItem(item);
		return item;
	}

	/**
	 * 用户物品列表
	 * @param userId
	 * @return
	 */
	public List<Item> userItemList(String userId){
//		List<Item> userItemList = itemDao.userItemList(userId);
		List<Item> userItemList = itemMapper.userItemList(userId);

//		List<Item> supplementaryList = new ArrayList<Item>();
//		 for (int i = userItemList.size()-1;i>=0;i--) {
//			 Item item = userItemList.get(i);
//			 if (ItemType.SupplementaryCard.getKey().equals(item.getItemTypeKey())) {
//				 supplementaryList.add(item);
//				 userItemList.remove(item);
//			 }
//		 }
//		 if (supplementaryList.size()>0) {
//			 Item supplementary =  supplementaryList.get(0);
//			 supplementary.setCount(supplementaryList.size());
//			 userItemList.add(0, supplementary);
//		 }
		 //UserInfo user = userInfoMapper.selectUserInfoById(userId) ;
//		 Item bindBageCard = user._getBadgeCard();
//		 for (Item item : userItemList) {
//			 item.setItemType(ItemType.getItemTypeByKey(item.getItemTypeKey()));
//			 if (null != bindBageCard) {
//				 if (item.getItemId().equals(bindBageCard.getItemId())) {
//					 item.setIsBind(1);
//				 }
//			 }
//		 }
		return userItemList;
	}
	
	public Integer supplementaryCardCount(Long userId){
		Integer count = itemMapper.supplementaryCardCount(userId,ItemType.SupplementaryCard.getKey());
		return count;
	}

	/**
	 * 消费补签卡
	 * @param nhId  返回ResultV2
	 * @return
	 */
	public ResultV2 useSupplementary(String nhId){
		String serialNumber = System.currentTimeMillis()+Base62Utils.getRamdomIntString(3);

		UserInfo user = userInfoMapper.selectUserInfoById(nhId) ;
		//User user = userDao.getUserByNhId(nhId);
		if (null == user) {
			return ResultV2.Fail(-2, "用户不存在");
		}

		List<Item> userSupplementaryList = itemMapper.userSupplementaryList(nhId,ItemType.SupplementaryCard.getKey());
		if (userSupplementaryList.size()==0) {
			useSupplementaryCardLogger.info("serialNumber: {} log ececute 您还没有补签卡",serialNumber);
			return ResultV2.Fail(-1, "您还没有补签卡");
		}


		//item.setRelatedId(relatedId);
		//itemDao.updateItemRelatedId(item);
		Item item = userSupplementaryList.get(0);
		itemMapper.deleteItem(item.getItemId());
		return ResultV2.Success(item);

	}
	/**
	 * 消费补签卡  返回Result
	 * @param nhId
	 * @return
	 */
	public Result useSupplementary1(String nhId){
		String serialNumber = System.currentTimeMillis()+Base62Utils.getRamdomIntString(3);

		UserInfo user = userInfoMapper.selectUserInfoById(nhId) ;
		//User user = userDao.getUserByNhId(nhId);
		if (null == user) {
			return Result.Fail(-2, "用户不存在");
		}

        List<Item> userSupplementaryList = itemMapper.userSupplementaryList(nhId,ItemType.SupplementaryCard.getKey());
        if (userSupplementaryList.size()==0) {
			useSupplementaryCardLogger.info("serialNumber: {} log ececute 您还没有补签卡",serialNumber);
			return Result.Fail(-1, "您还没有补签卡");
		}


		//item.setRelatedId(relatedId);
		//itemDao.updateItemRelatedId(item);
		Item item = userSupplementaryList.get(0);
		itemMapper.deleteItem(item.getItemId());
		return Result.Success(item);

	}

	
	public void doSomeThing(){
		logger.info("--------clearInvalidBadgeCard has been called-------");
	}
	
	
}
