//package com.ninehcom.userinfo.mapper;
//
//import com.ninehcom.common.util.DateUtils;
//import com.ninehcom.common.util.IdUtil;
//import com.ninehcom.userinfo.entity.Item;
//import com.ninehcom.userinfo.enums.ItemType;
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class ItemDao {
//
//	@Autowired
//	protected SqlSession sqlSession;
//
//	public Integer insertItem(Item item){
//		item.setItemId(IdUtil.nextId());
//		item.setCreatedAt(DateUtils.now());
//		item.setUpdatedAt(DateUtils.now());
//		return this.sqlSession.insert("insertItem",item);
//	}
//
//	public Item getItem(Long id){
//		return this.sqlSession.selectOne("getItem", id);
//	}
//
//	public List<Item> userItemList(String userId){
//		return sqlSession.selectList("userItemList", userId);
//	}
//
//	public List<Item> userSupplementaryList(String userId){
//		Map<String, Object> paramMap = new HashMap();
//        paramMap.put("userId",userId);
//        paramMap.put("itemTypeKey", ItemType.SupplementaryCard.getKey());
//		return sqlSession.selectList("userSupplementaryList", paramMap);
//	}
//
//	public Integer deleteItem(Long itemId){
//		return sqlSession.update("deleteItem",itemId);
//	}
//
//	public Integer useBadgeCard(Item item){
//		return sqlSession.update("useBadgeCard",item);
//	}
//
//	public List<Item> userBadgeList(Long userId){
//		Map<String, Object> paramMap = new HashMap();
//        paramMap.put("userId",userId);
//        paramMap.put("now",DateUtils.now());
//        paramMap.put("badgeCardKeyList", ItemType.badgeCardKeyList());
//        return sqlSession.selectList("userBadgeList", paramMap);
//	}
//
//	public List<Item> invalidBadgeCardList(){
//		Map<String, Object> paramMap = new HashMap();
//        paramMap.put("now",DateUtils.now());
//        paramMap.put("badgeCardKeyList", ItemType.badgeCardKeyList());
//        return sqlSession.selectList("invalidBadgeCardList", paramMap);
//	}
//
//	public List<Item> willInvalidBadgeCardList(){
//		Map<String, Object> paramMap = new HashMap();
//		Calendar calendar3 = Calendar.getInstance();
//		calendar3.add(Calendar.DATE, 3);
//		Calendar calendar4 = Calendar.getInstance();
//		calendar4.add(Calendar.DATE, 4);
//        paramMap.put("date3",calendar3.getTime());
//        paramMap.put("date4",calendar4.getTime());
//        paramMap.put("badgeCardKeyList", ItemType.badgeCardKeyList());
//        return sqlSession.selectList("willInvalidBadgeCardList", paramMap);
//	}
//
//	public Integer supplementaryCardCount(Long userId){
//		Map<String, Object> paramMap = new HashMap();
//        paramMap.put("userId",userId);
//        paramMap.put("itemTypeKey", ItemType.SupplementaryCard.getKey());
//		return sqlSession.selectOne("supplementaryCardCount",paramMap);
//	}
//
//	public Item getOneScientificGuessCard(Long userId, String relatedId){
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("userId",userId);
//        paramMap.put("relatedId",relatedId);
//        paramMap.put("GuessCardTimeLimit", ItemType.GuessCardTimeLimit.getKey());
//        paramMap.put("GuessCardForEver", ItemType.GuessCardForEver.getKey());
//		return sqlSession.selectOne("getOneScientificGuessCard",paramMap);
//	}
//
//	public Integer updateItemRelatedId(Item item){
//		return sqlSession.update("updateItemRelatedId",item);
//	}
//
//	public Integer recoveryItem(Long itemId){
//		return sqlSession.update("recoveryItem",itemId);
//	}
//
//	public List<Item> getDeletedGuessCardListByGuessId(String guessId){
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("relatedId",guessId);
//        paramMap.put("GuessCardTimeLimit", ItemType.GuessCardTimeLimit.getKey());
//        paramMap.put("GuessCardForEver", ItemType.GuessCardForEver.getKey());
//		return sqlSession.selectList("getDeletedGuessCardListByGuessId",paramMap);
//	}
//
//
//
//}
