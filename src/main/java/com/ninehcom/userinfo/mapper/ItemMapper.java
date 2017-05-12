package com.ninehcom.userinfo.mapper;

import com.ninehcom.userinfo.entity.Item;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
public interface ItemMapper {
    int insertItem(Item item);
    Item getItem(Long id);
    List<Item> userItemList(String userId);
    List<Item> userSupplementaryList(String userId,String itemTypeKey);
    Integer deleteItem(Long itemId);
    Integer useBadgeCard(Item item);
    List<Item> userBadgeList(Long userId, Date now,List<String> badgeCardKeyList);
    List<Item> invalidBadgeCardList(Date now,List<String> badgeCardKeyList);
    List<Item> willInvalidBadgeCardList(Date date,Date date2,List<String> badgeCardKeyList);
    Integer supplementaryCardCount(Long userId,String itemTypeKey);
    Item getOneScientificGuessCard(Long userId, String relatedId,String GuessCardTimeLimit,String GuessCardForEver);
    Integer updateItemRelatedId(Item item);
    Integer recoveryItem(Long itemId);
    List<Item> getDeletedGuessCardListByGuessId(String guessId,String GuessCardTimeLimit,String GuessCardForEver);

}
