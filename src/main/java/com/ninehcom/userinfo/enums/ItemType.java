package com.ninehcom.userinfo.enums;

import com.ninehcom.common.util.Constant;

import java.util.ArrayList;
import java.util.List;


public enum ItemType {
	
	SupplementaryCard("SupplementaryCard",
			"补签卡",
			"http://static-csl.9h-sports.com/prop/2016/qiandaoka.png",
			"",
			"补签卡--补签卡描述",0),
	
	//30天徽章卡
	BadgeCardGuoAn("BadgeCardGuoAn",
			"北京国安乐视徽章卡 30天",
			"http://static-csl.9h-sports.com/prop/2016/guoan.png",
			"http://static-csl.9h-sports.com/prop/2016/90/guoan.png",
			"北京国安乐视徽章卡 30天--描述", Constant.day_30),
	BadgeCardShenHua("BadgeCardShenHua",
			"上海绿地申花徽章卡 30天",			
			"http://static-csl.9h-sports.com/prop/2016/shenhua.png",
			"http://static-csl.9h-sports.com/prop/2016/90/shenhua.png",
			"上海绿地申花徽章卡 30天--描述",Constant.day_30),
	BadgeCardLiFan("BadgeCardLiFan",
			"重庆力帆徽章卡 30天",
			"http://static-csl.9h-sports.com/prop/2016/chongqinglifan.png",
			"http://static-csl.9h-sports.com/prop/2016/90/lifan.png",
			"重庆力帆徽章卡 30天--描述",Constant.day_30),
	BadgeCardFuLi("BadgeCardFuLi",
			"广州富力徽章卡 30天",
			"http://static-csl.9h-sports.com/prop/2016/guozhoufuli.png",
			"http://static-csl.9h-sports.com/prop/2016/90/fuli.png",
			"广州富力徽章卡 30天--描述",Constant.day_30),
	BadgeCardHengDa("BadgeCardHengDa",
			"广州恒大淘宝徽章卡 30天",
			"http://static-csl.9h-sports.com/prop/2016/guozhouhengda.png",
			"http://static-csl.9h-sports.com/prop/2016/90/guangzhouhengdataobao.png",
			"广州恒大淘宝徽章卡 30天--描述",Constant.day_30),
	BadgeCardHuaXiaXingFu("BadgeCardHuaXiaXingFu",
			"河北华夏幸福徽章卡 30天",
			"http://static-csl.9h-sports.com/prop/2016/hebei.png",
			"http://static-csl.9h-sports.com/prop/2016/90/huaxiaxingfu.png",
			"河北华夏幸福徽章卡 30天--描述",Constant.day_30),
	BadgeCardJianYe("BadgeCardJianYe",
			"河南建业徽章卡 30天",
			"http://static-csl.9h-sports.com/prop/2016/henanjianye.png",
			"http://static-csl.9h-sports.com/prop/2016/90/jianye.png",
			"河南建业徽章卡 30天--描述",Constant.day_30),
	BadgeCardSuNing("BadgeCardSuNing",
			"江苏苏宁易购徽章卡 30天",
			"http://static-csl.9h-sports.com/prop/2016/jiangsusuning.png",
			"http://static-csl.9h-sports.com/prop/2016/90/jiangsu.png",
			"江苏苏宁易购徽章卡 30天--描述",Constant.day_30),
	BadgeCardHengYuan("BadgeCardHengYuan",
			"辽宁沈阳宏远徽章卡 30天",
			"http://static-csl.9h-sports.com/prop/2016/liaoninghongyuan.png",
			"http://static-csl.9h-sports.com/prop/2016/90/hongyuan.png",
			"辽宁沈阳宏远徽章卡 30天--描述",Constant.day_30),
	BadgeCardLvCheng("BadgeCardLvCheng",
			"杭州绿城徽章卡 30天",
			"http://static-csl.9h-sports.com/prop/2016/lvcheng.png",
			"http://static-csl.9h-sports.com/prop/2016/90/lvcheng.png",
			"杭州绿城徽章卡 30天--描述",Constant.day_30),
	BadgeCardLuNeng("BadgeCardLuNeng",
			"山东鲁能泰山徽章卡 30天",
			"http://static-csl.9h-sports.com/prop/2016/shandongluneng.png",
			"http://static-csl.9h-sports.com/prop/2016/90/luneng.png",
			"山东鲁能泰山徽章卡 30天--描述",Constant.day_30),
	BadgeCardShangGang("BadgeCardShangGang",
			"上海上港徽章卡 30天",
			"http://static-csl.9h-sports.com/prop/2016/shanggang.png",
			"http://static-csl.9h-sports.com/prop/2016/90/shanghaishanggang.png",
			"上海上港徽章卡 30天--描述",Constant.day_30),
	BadgeCardTaiDa("BadgeCardTaiDa",
			"天津泰达亿利徽章卡 30天",
			"http://static-csl.9h-sports.com/prop/2016/taida.png",
			"http://static-csl.9h-sports.com/prop/2016/90/teda.png",
			"天津泰达亿利徽章卡 30天--描述",Constant.day_30),
	BadgeCardYaTai("BadgeCardYaTai",
			"长春亚泰徽章卡 30天",
			"http://static-csl.9h-sports.com/prop/2016/yatai.png",
			"http://static-csl.9h-sports.com/prop/2016/90/yatai.png",
			"长春亚泰徽章卡 30天--描述",Constant.day_30),
	BadgeCardYanBian("BadgeCardYanBian",
			"延边长白山徽章卡 30天",
			"http://static-csl.9h-sports.com/prop/2016/yanbian.png",
			"http://static-csl.9h-sports.com/prop/2016/90/yanbian.jpg",
			"延边长白山徽章卡 30天--描述",Constant.day_30),
	BadgeCardYongChang("BadgeCardYongChang",
			"石家庄永昌徽章卡 30天",
			"http://static-csl.9h-sports.com/prop/2016/yongchang.png",
			"http://static-csl.9h-sports.com/prop/2016/90/yongchang.png",
			"石家庄永昌徽章卡 30天--描述",Constant.day_30),
	
	//60天徽章卡
	BadgeCardGuoAn60("BadgeCardGuoAn60",
			"北京国安乐视徽章卡 60天",
			"http://static-csl.9h-sports.com/prop/2016/guoan.png",
			"http://static-csl.9h-sports.com/prop/2016/90/guoan.png",
			"北京国安乐视徽章卡 60天--描述",Constant.day_60),
	BadgeCardShenHua60("BadgeCardShenHua60",
			"上海绿地申花徽章卡 60天",
			"http://static-csl.9h-sports.com/prop/2016/shenhua.png",
			"http://static-csl.9h-sports.com/prop/2016/90/shenhua.png",
			"上海绿地申花徽章卡 60天--描述",Constant.day_60),
	BadgeCardLiFan60("BadgeCardLiFan60",
			"重庆力帆徽章卡 60天",
			"http://static-csl.9h-sports.com/prop/2016/chongqinglifan.png",
			"http://static-csl.9h-sports.com/prop/2016/90/lifan.png",
			"重庆力帆徽章卡 60天--描述",Constant.day_60),
	BadgeCardFuLi60("BadgeCardFuLi60",
			"广州富力徽章卡 60天",
			"http://static-csl.9h-sports.com/prop/2016/guozhoufuli.png",
			"http://static-csl.9h-sports.com/prop/2016/90/fuli.png",
			"广州富力徽章卡 60天--描述",Constant.day_60),
	BadgeCardHengDa60("BadgeCardHengDa60",
			"广州恒大淘宝徽章卡 60天",
			"http://static-csl.9h-sports.com/prop/2016/guozhouhengda.png",
			"http://static-csl.9h-sports.com/prop/2016/90/guangzhouhengdataobao.png",
			"广州恒大淘宝徽章卡 60天--描述",Constant.day_60),
	BadgeCardHuaXiaXingFu60("BadgeCardHuaXiaXingFu60",
			"河北华夏幸福徽章卡 60天",
			"http://static-csl.9h-sports.com/prop/2016/hebei.png",
			"http://static-csl.9h-sports.com/prop/2016/90/huaxiaxingfu.png",
			"河北华夏幸福徽章卡 60天--描述",Constant.day_60),
	BadgeCardJianYe60("BadgeCardJianYe60",
			"河南建业徽章卡 60天",
			"http://static-csl.9h-sports.com/prop/2016/henanjianye.png",
			"http://static-csl.9h-sports.com/prop/2016/90/jianye.png",
			"河南建业徽章卡 60天--描述",Constant.day_60),
	BadgeCardSuNing60("BadgeCardSuNing60",
			"江苏苏宁易购徽章卡 60天",
			"http://static-csl.9h-sports.com/prop/2016/jiangsusuning.png",
			"http://static-csl.9h-sports.com/prop/2016/90/jiangsu.png",
			"江苏苏宁易购徽章卡 60天--描述",Constant.day_60),
	BadgeCardHengYuan60("BadgeCardHengYuan60",
			"辽宁沈阳宏远徽章卡 60天",
			"http://static-csl.9h-sports.com/prop/2016/liaoninghongyuan.png",
			"http://static-csl.9h-sports.com/prop/2016/90/hongyuan.png",
			"辽宁沈阳宏远徽章卡 60天--描述",Constant.day_60),
	BadgeCardLvCheng60("BadgeCardLvCheng60",
			"杭州绿城徽章卡 60天",
			"http://static-csl.9h-sports.com/prop/2016/lvcheng.png",
			"http://static-csl.9h-sports.com/prop/2016/90/lvcheng.png",
			"杭州绿城徽章卡 60天--描述",Constant.day_60),
	BadgeCardLuNeng60("BadgeCardLuNeng60",
			"山东鲁能泰山徽章卡 60天",
			"http://static-csl.9h-sports.com/prop/2016/shandongluneng.png",
			"http://static-csl.9h-sports.com/prop/2016/90/luneng.png",
			"山东鲁能泰山徽章卡 60天--描述",Constant.day_60),
	BadgeCardShangGang60("BadgeCardShangGang60",
			"上海上港徽章卡 60天",
			"http://static-csl.9h-sports.com/prop/2016/shanggang.png",
			"http://static-csl.9h-sports.com/prop/2016/90/shanghaishanggang.png",
			"上海上港徽章卡 60天--描述",Constant.day_60),
	BadgeCardTaiDa60("BadgeCardTaiDa60",
			"天津泰达亿利徽章卡 60天",
			"http://static-csl.9h-sports.com/prop/2016/taida.png",
			"http://static-csl.9h-sports.com/prop/2016/90/teda.png",
			"天津泰达亿利徽章卡 60天--描述",Constant.day_60),
	BadgeCardYaTai60("BadgeCardYaTai60",
			"长春亚泰徽章卡 60天",
			"http://static-csl.9h-sports.com/prop/2016/yatai.png",
			"http://static-csl.9h-sports.com/prop/2016/90/yatai.png",
			"长春亚泰徽章卡 60天--描述",Constant.day_60),
	BadgeCardYanBian60("BadgeCardYanBian60",
			"延边长白山徽章卡 60天",
			"http://static-csl.9h-sports.com/prop/2016/yanbian.png",
			"http://static-csl.9h-sports.com/prop/2016/90/yanbian.jpg",
			"延边长白山徽章卡 60天--描述",Constant.day_60),
	BadgeCardYongChang60("BadgeCardYongChang60",
			"石家庄永昌徽章卡 60天",
			"http://static-csl.9h-sports.com/prop/2016/yongchang.png",
			"http://static-csl.9h-sports.com/prop/2016/90/yongchang.png",
			"石家庄永昌徽章卡 60天--描述",Constant.day_60),
	
	//180天徽章卡
	BadgeCardGuoAn180("BadgeCardGuoAn180",
			"北京国安乐视徽章卡 180天",
			"http://static-csl.9h-sports.com/prop/2016/guoan.png",
			"http://static-csl.9h-sports.com/prop/2016/90/guoan.png",
			"北京国安乐视徽章卡 180天--描述",Constant.day_180),
	BadgeCardShenHua180("BadgeCardShenHua180",
			"上海绿地申花徽章卡 180天",
			"http://static-csl.9h-sports.com/prop/2016/shenhua.png",
			"http://static-csl.9h-sports.com/prop/2016/90/shenhua.png",
			"上海绿地申花徽章卡 180天--描述",Constant.day_180),
	BadgeCardLiFan180("BadgeCardLiFan180",
			"重庆力帆徽章卡 180天",
			"http://static-csl.9h-sports.com/prop/2016/chongqinglifan.png",
			"http://static-csl.9h-sports.com/prop/2016/90/lifan.png",
			"重庆力帆徽章卡 180天--描述",Constant.day_180),
	BadgeCardFuLi180("BadgeCardFuLi180",
			"广州富力徽章卡 180天",
			"http://static-csl.9h-sports.com/prop/2016/guozhoufuli.png",
			"http://static-csl.9h-sports.com/prop/2016/90/fuli.png",
			"广州富力徽章卡 180天--描述",Constant.day_180),
	BadgeCardHengDa180("BadgeCardHengDa180",
			"广州恒大淘宝徽章卡 180天",
			"http://static-csl.9h-sports.com/prop/2016/guozhouhengda.png",
			"http://static-csl.9h-sports.com/prop/2016/90/guangzhouhengdataobao.png",
			"广州恒大淘宝徽章卡 180天--描述",Constant.day_180),
	BadgeCardHuaXiaXingFu180("BadgeCardHuaXiaXingFu180",
			"河北华夏幸福徽章卡 180天",
			"http://static-csl.9h-sports.com/prop/2016/hebei.png",
			"http://static-csl.9h-sports.com/prop/2016/90/huaxiaxingfu.png",
			"河北华夏幸福徽章卡 180天--描述",Constant.day_180),
	BadgeCardJianYe180("BadgeCardJianYe180",
			"河南建业徽章卡 180天",
			"http://static-csl.9h-sports.com/prop/2016/henanjianye.png",
			"http://static-csl.9h-sports.com/prop/2016/90/jianye.png",
			"河南建业徽章卡 180天--描述",Constant.day_180),
	BadgeCardSuNing180("BadgeCardSuNing180",
			"江苏苏宁易购徽章卡 180天",
			"http://static-csl.9h-sports.com/prop/2016/jiangsusuning.png",
			"http://static-csl.9h-sports.com/prop/2016/90/jiangsu.png",
			"江苏苏宁易购徽章卡 180天--描述",Constant.day_180),
	BadgeCardHengYuan180("BadgeCardHengYuan180",
			"辽宁沈阳宏远徽章卡 180天",
			"http://static-csl.9h-sports.com/prop/2016/liaoninghongyuan.png",
			"http://static-csl.9h-sports.com/prop/2016/90/hongyuan.png",
			"辽宁沈阳宏远徽章卡 180天--描述",Constant.day_180),
	BadgeCardLvCheng180("BadgeCardLvCheng180",
			"杭州绿城徽章卡 180天",
			"http://static-csl.9h-sports.com/prop/2016/lvcheng.png",
			"http://static-csl.9h-sports.com/prop/2016/90/lvcheng.png",
			"杭州绿城徽章卡 180天--描述",Constant.day_180),
	BadgeCardLuNeng180("BadgeCardLuNeng180",
			"山东鲁能泰山徽章卡 180天",
			"http://static-csl.9h-sports.com/prop/2016/shandongluneng.png",
			"http://static-csl.9h-sports.com/prop/2016/90/luneng.png",
			"山东鲁能泰山徽章卡 180天--描述",Constant.day_180),
	BadgeCardShangGang180("BadgeCardShangGang180",
			"上海上港徽章卡 180天",
			"http://static-csl.9h-sports.com/prop/2016/shanggang.png",
			"http://static-csl.9h-sports.com/prop/2016/90/shanghaishanggang.png",
			"上海上港徽章卡 180天--描述",Constant.day_180),
	BadgeCardTaiDa180("BadgeCardTaiDa180",
			"天津泰达亿利徽章卡 180天",
			"http://static-csl.9h-sports.com/prop/2016/taida.png",
			"http://static-csl.9h-sports.com/prop/2016/90/teda.png",
			"天津泰达亿利徽章卡 180天--描述",Constant.day_180),
	BadgeCardYaTai180("BadgeCardYaTai180",
			"长春亚泰徽章卡 180天",
			"http://static-csl.9h-sports.com/prop/2016/yatai.png",
			"http://static-csl.9h-sports.com/prop/2016/90/yatai.png",
			"长春亚泰徽章卡 180天--描述",Constant.day_180),
	BadgeCardYanBian180("BadgeCardYanBian180",
			"延边长白山徽章卡 180天",
			"http://static-csl.9h-sports.com/prop/2016/yanbian.png",
			"http://static-csl.9h-sports.com/prop/2016/90/yanbian.jpg",
			"延边长白山徽章卡 180天--描述",Constant.day_180),
	BadgeCardYongChang180("BadgeCardYongChang180",
			"石家庄永昌徽章卡 180天",
			"http://static-csl.9h-sports.com/prop/2016/yongchang.png",
			"http://static-csl.9h-sports.com/prop/2016/90/yongchang.png",
			"石家庄永昌徽章卡 180天--描述",Constant.day_180),
	
	
	//365天徽章卡
	BadgeCardGuoAn365("BadgeCardGuoAn365",
			"北京国安乐视徽章卡 365天",
			"http://static-csl.9h-sports.com/prop/2016/guoan.png",
			"http://static-csl.9h-sports.com/prop/2016/90/guoan.png",
			"北京国安乐视徽章卡 365天--描述",Constant.day_365),
	BadgeCardShenHua365("BadgeCardShenHua365",
			"上海绿地申花徽章卡 365天",
			"http://static-csl.9h-sports.com/prop/2016/shenhua.png",
			"http://static-csl.9h-sports.com/prop/2016/90/shenhua.png",
			"上海绿地申花徽章卡 365天--描述",Constant.day_365),
	BadgeCardLiFan365("BadgeCardLiFan365",
			"重庆力帆徽章卡 365天",
			"http://static-csl.9h-sports.com/prop/2016/chongqinglifan.png",
			"http://static-csl.9h-sports.com/prop/2016/90/lifan.png",
			"重庆力帆徽章卡 365天--描述",Constant.day_365),
	BadgeCardFuLi365("BadgeCardFuLi365",
			"广州富力徽章卡 365天",
			"http://static-csl.9h-sports.com/prop/2016/guozhoufuli.png",
			"http://static-csl.9h-sports.com/prop/2016/90/fuli.png",
			"广州富力徽章卡 365天--描述",Constant.day_365),
	BadgeCardHengDa365("BadgeCardHengDa365",
			"广州恒大淘宝徽章卡 365天",
			"http://static-csl.9h-sports.com/prop/2016/guozhouhengda.png",
			"http://static-csl.9h-sports.com/prop/2016/90/guangzhouhengdataobao.png",
			"广州恒大淘宝徽章卡 365天--描述",Constant.day_365),
	BadgeCardHuaXiaXingFu365("BadgeCardHuaXiaXingFu365",
			"河北华夏幸福徽章卡 365天",
			"http://static-csl.9h-sports.com/prop/2016/hebei.png",
			"http://static-csl.9h-sports.com/prop/2016/90/huaxiaxingfu.png",
			"河北华夏幸福徽章卡 365天--描述",Constant.day_365),
	BadgeCardJianYe365("BadgeCardJianYe365",
			"河南建业徽章卡 365天",
			"http://static-csl.9h-sports.com/prop/2016/henanjianye.png",
			"http://static-csl.9h-sports.com/prop/2016/90/jianye.png",
			"河南建业徽章卡 365天--描述",Constant.day_365),
	BadgeCardSuNing365("BadgeCardSuNing365",
			"江苏苏宁易购徽章卡 365天",
			"http://static-csl.9h-sports.com/prop/2016/jiangsusuning.png",
			"http://static-csl.9h-sports.com/prop/2016/90/jiangsu.png",
			"江苏苏宁易购徽章卡 365天--描述",Constant.day_365),
	BadgeCardHengYuan365("BadgeCardHengYuan365",
			"辽宁沈阳宏远徽章卡 365天",
			"http://static-csl.9h-sports.com/prop/2016/liaoninghongyuan.png",
			"http://static-csl.9h-sports.com/prop/2016/90/hongyuan.png",
			"辽宁沈阳宏远徽章卡 365天--描述",Constant.day_365),
	BadgeCardLvCheng365("BadgeCardLvCheng365",
			"杭州绿城徽章卡 365天",
			"http://static-csl.9h-sports.com/prop/2016/lvcheng.png",
			"http://static-csl.9h-sports.com/prop/2016/90/lvcheng.png",
			"杭州绿城徽章卡 365天--描述",Constant.day_365),
	BadgeCardLuNeng365("BadgeCardLuNeng365",
			"山东鲁能泰山徽章卡 365天",
			"http://static-csl.9h-sports.com/prop/2016/shandongluneng.png",
			"http://static-csl.9h-sports.com/prop/2016/90/luneng.png",
			"山东鲁能泰山徽章卡 365天--描述",Constant.day_365),
	BadgeCardShangGang365("BadgeCardShangGang365",
			"上海上港徽章卡 365天",
			"http://static-csl.9h-sports.com/prop/2016/shanggang.png",
			"http://static-csl.9h-sports.com/prop/2016/90/shanghaishanggang.png",
			"上海上港徽章卡 365天--描述",Constant.day_365),
	BadgeCardTaiDa365("BadgeCardTaiDa365",
			"天津泰达亿利徽章卡 365天",
			"http://static-csl.9h-sports.com/prop/2016/taida.png",
			"http://static-csl.9h-sports.com/prop/2016/90/teda.png",
			"天津泰达亿利徽章卡 365天--描述",Constant.day_365),
	BadgeCardYaTai365("BadgeCardYaTai365",
			"长春亚泰徽章卡 365天",
			"http://static-csl.9h-sports.com/prop/2016/yatai.png",
			"http://static-csl.9h-sports.com/prop/2016/90/yatai.png",
			"长春亚泰徽章卡 365天--描述",Constant.day_365),
	BadgeCardYanBian365("BadgeCardYanBian365",
			"延边长白山徽章卡 365天",
			"http://static-csl.9h-sports.com/prop/2016/yanbian.png",
			"http://static-csl.9h-sports.com/prop/2016/90/yanbian.jpg",
			"延边长白山徽章卡 365天--描述",Constant.day_365),
	BadgeCardYongChang365("BadgeCardYongChang365",
			"石家庄永昌徽章卡 365天",
			"http://static-csl.9h-sports.com/prop/2016/yongchang.png",
			"http://static-csl.9h-sports.com/prop/2016/90/yongchang.png",
			"石家庄永昌徽章卡 365天--描述",Constant.day_365),
	GuessCardForEver("GuessCardForEver",
			"机会牌 永久",
			"http://pic43.nipic.com/20140711/13013376_134545145337_2.jpg",
			"http://pic43.nipic.com/20140711/13013376_134545145337_2.jpg",
			"机会牌 永久--描述",Constant.year_100),
	GuessCardTimeLimit("GuessCardTimeLimit",
			"机会牌 30天",
			"http://pic43.nipic.com/20140711/13013376_134545145337_2.jpg",
			"http://pic43.nipic.com/20140711/13013376_134545145337_2.jpg",
			"机会牌 30天--描述",Constant.day_30),
	;	
	
	
	ItemType(String key , String name, String icon, String logo, String description, Integer effectiveSecond){
		this.key = key;
		this.description = description;
		this.name = name;
		this.icon = icon;
		this.effectiveSecond = effectiveSecond;
		this.logo = logo;
		
	}
	
	private String key;
	
	private String icon;
	
	private String description;
	
	private String name;
	
	private Integer effectiveSecond;
	
	private String logo;
	


	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static List<String> badgeCardKeyList(){
		List list = new ArrayList();
		ItemType[] allTypes = ItemType.values();
		for (ItemType itemType : allTypes) {
			if (itemType.getKey().startsWith("BadgeCard")) {
				list.add(itemType.getKey());
			}
		}
		return list;
	}
	
	public static ItemType getItemTypeByKey(String key) {
		for (ItemType itemType : ItemType.values()) {
			if (itemType.getKey().equals(key)) {
				return itemType;
			}
		}
		return null;
	}

	public Integer getEffectiveSecond() {
		return effectiveSecond;
	}

	public void setEffectiveSecond(Integer effectiveSecond) {
		this.effectiveSecond = effectiveSecond;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
			
}
