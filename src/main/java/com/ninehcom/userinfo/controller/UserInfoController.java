package com.ninehcom.userinfo.controller;

import com.ninehcom.common.util.RequestUtils;
import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.entity.LogInfo;
import com.ninehcom.userinfo.entity.UserInfo;
import com.ninehcom.userinfo.service.EditconfigService;
import com.ninehcom.userinfo.service.UserInfoService;
import com.wordnik.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * UserInfo 的控制器，用于显示同时查询2个数据库的结果 * @author shenjizhe
 *
 * @version 1.0.0
 */
@Api(basePath = "/users", value = "发送验证码", description = "验证码", produces = "application/json")
@RestController
@RequestMapping(value = "/users")
public class UserInfoController {

    @Autowired
    UserInfoService userService;

    public UserInfoController() {

    }

    /**
     * 发送验证码
     */
    @ApiOperation(value = "发送验证码", notes = "发送验证码到指定手机号", position = 1)
    @ApiResponses(value = {
        @ApiResponse(code = 10000, message = "发送失败，短信运营商内部错误"),
        @ApiResponse(code = 10001, message = "sn无效"),
        @ApiResponse(code = 10002, message = "已达单日单号码可发送总条数上限"),
        @ApiResponse(code = 10003, message = "已达单日单平台单号码可发送总条数上限"),
        @ApiResponse(code = 10004, message = "60秒后后可再次对此手机号发送短信")
    })
    @RequestMapping(value = "/sendcode/{mobileNum}", method = RequestMethod.POST)
    @ResponseBody
    public Result sendcode(@ApiParam(value = "电话号码", defaultValue = "15801563000")
            @PathVariable("mobileNum") String mobileNum,@RequestHeader(value = "appId") String appId) throws Exception {
        return userService.sendCheckCode(mobileNum, appId);
    }

    /**
     * 检验验证码 .
     */
    @ApiOperation(value = "检验验证码", notes = "校验验证码", position = 2)
    @RequestMapping(value = "/validatecode/{mobileNum}/{checkCode}", method = RequestMethod.POST)
    @ResponseBody
    public Result checkCode(
            @ApiParam(value = "电话号码", defaultValue = "15801563000")
            @PathVariable("mobileNum") String mobileNum,
            @ApiParam(value = "短信校验码", defaultValue = "602549")
            @PathVariable("checkCode") String checkCode,@RequestHeader(value = "appId") String appId) throws Exception {
        return userService.checkCode(mobileNum, checkCode, appId);
    }

    /**
     * 检查手机号是否可用
     */
    @ApiOperation(value = "检查手机号", notes = "检查手机号是否可用", position = 3)
    @RequestMapping(value = "/validatemobile/{mobileNum}", method = RequestMethod.POST)
    @ResponseBody
    public Result validateMobile(
            @ApiParam(value = "电话号码", defaultValue = "15801563000")
            @PathVariable("mobileNum") String mobileNum,@RequestHeader(value = "appId") String appId) throws Exception {
        return userService.validateMobile(mobileNum);
    }

    /**
     * 检查token
     */
    @ApiOperation(value = "检查token", notes = "检查token是否可用", position = 4)
    @ApiResponses(value = {})
    @RequestMapping(value = "/validatetoken", method = RequestMethod.POST)
    @ResponseBody
    public Result validateToken(
            @ApiParam(value = "token", defaultValue = "ade7d1e2-10ab-4481-867b-2e9b78e2061f")
            @RequestHeader("token") String token,@RequestHeader(value = "appId") String appId) throws Exception {
        return userService.validateToken(token);
    }

    /**
     * 新用户的注册功能
     */
    @ApiOperation(value = "手机注册", notes = "新用户的注册", position = 4)
    @ApiResponse(code = 20001, message = "用户数据插入数据库失败")
    @RequestMapping(value = "/register/{mobileNum}/{password}/{checkCode}", method = RequestMethod.POST)
    @ResponseBody
    public Result register(
            HttpServletRequest request,
            @RequestHeader("systemtypeid") String systemtypeid,
            @RequestHeader("equipmentnum") String equipmentnum,
            @PathVariable("mobileNum") String mobileNum,
            @PathVariable("password") String password,
            @PathVariable("checkCode") String checkCode,
            @RequestHeader(value = "appId") String appId
    ) throws Exception {
        String ip = RequestUtils.getIpAddr(request);
        return userService.register(mobileNum, password, checkCode, appId,
                new LogInfo(ip, systemtypeid, equipmentnum));
    }

    /**
     * 国安俱乐部用户中心启动 用户登录
     */
    @ApiOperation(value = "用户登录", notes = "用户登录", position = 5)
    @ApiResponse(code = 20001, message = "用户数据插入数据库失败")
    @RequestMapping(value = "/login/{mobileNum}/{password}", method = RequestMethod.POST)
    @ResponseBody
    public Result login(
            @RequestHeader("systemtypeid") String systemtypeid,
            @RequestHeader("equipmentnum") String equipmentnum,
            @PathVariable("mobileNum") String mobileNum,
            @PathVariable("password") String password,
            HttpServletRequest request,@RequestHeader(value = "appId") String appId) throws Exception {
        String ip = RequestUtils.getIpAddr(request);
        return userService.login(mobileNum, password, appId,
                new LogInfo(ip, systemtypeid, equipmentnum));
    }

    /**
     * 换绑定手机号
     */
    @ApiOperation(value = "换绑定手机号", notes = "换绑定手机号", position = 6)
    @RequestMapping(value = "/resetmobile/{mobileNum}/{password}/{checkCode}", method = RequestMethod.POST)
    @ResponseBody
    public Result resetMobile(
            @RequestHeader("token") String token,
            @PathVariable("mobileNum") String mobileNum,
            @PathVariable("password") String password,
            @PathVariable("checkCode") String checkCode,@RequestHeader(value = "appId") String appId) throws Exception {
        return userService.resetMobile(token, mobileNum, password, checkCode);
    }

    /**
     * 用户重置密码
     */
    @ApiOperation(value = "用户重置密码", notes = "用户重置密码", position = 7)
    @RequestMapping(value = "/resetpassword/{mobileNum}/{password}/{checkCode}", method = RequestMethod.POST)
    @ResponseBody
    public Result resetPassword(
            @RequestHeader("systemtypeid") String systemtypeid,
            @RequestHeader("equipmentnum") String equipmentnum,
            @PathVariable("mobileNum") String mobileNum,
            @PathVariable("password") String password,
            @PathVariable("checkCode") String checkCode,
            HttpServletRequest request,@RequestHeader(value = "appId") String appId) throws Exception {
        String ip = RequestUtils.getIpAddr(request);
        return userService.resetPassword(mobileNum, password, checkCode, appId,
                new LogInfo(ip, systemtypeid, equipmentnum));
    }

    /**
     * 用户修改密码
     */
    @ApiOperation(value = "用户修改密码", notes = "用户修改密码", position = 8)
    @RequestMapping(value = "/modifypassword/{oldpassword}/{newpassword}", method = RequestMethod.POST)
    @ResponseBody
    public Result modifyPassword(
            @RequestHeader("token") String token,
            @PathVariable("oldpassword") String oldpassword,
            @PathVariable("newpassword") String newpassword,@RequestHeader(value = "appId") String appId) throws Exception {
        return userService.modifyPassword(token, oldpassword, newpassword);
    }

    /**
     * 根据token取得用户信息
     */
    @ApiOperation(value = "根据token取得用户信息", notes = "根据token取得用户信息", position = 9)
    @RequestMapping(value = "/getuserbytoken", method = RequestMethod.POST)
    @ResponseBody
    public Result getUserbytoken(
            @RequestHeader("token") String token,@RequestHeader(value = "appId") String appId) throws Exception {
        return userService.getUserbytoken(token);
    }

    @ApiOperation(value = "取得用户聚合信息", notes = "根据用户ID取得用户聚合信息", position = 10)
    @ApiResponse(code = 20007, message = "用户不存在")
    @RequestMapping(value = "/getuserbyid/{UsreId}", method = RequestMethod.POST)
    @ResponseBody
    public Result selectUserInfoWrapperById(
            @PathVariable("UsreId") String UsreId,@RequestHeader(value = "appId") String appId) throws Exception {
        return userService.selectUserInfoWrapperById(UsreId);
    }

    @ApiOperation(value = "换取用户ID", notes = "换取用户ID", position = 10)
    @ApiResponse(code = 20007, message = "用户不存在")
    @RequestMapping(value = "/user-swap-id/{nhid}", method = RequestMethod.GET)
    @ResponseBody
    public Result selectUserIdFromNHID(@PathVariable("nhid") String nhid,@RequestHeader(value = "appId") String appId
    ) {
        return userService.selectUserIdFromNHID(nhid);
    }

    @ApiOperation(value = "换取用户NHID", notes = "换取用户NHID", position = 10)
    @ApiResponse(code = 20007, message = "用户不存在")
    @RequestMapping(value = "/user-swap-nhid/{user_id}", method = RequestMethod.GET)
    @ResponseBody
    public Result selectNHIdFromUserId(@PathVariable("user_id") Long user_id,@RequestHeader(value = "appId") String appId
    ) {
        return userService.selectNHIdFromUserId(user_id);
    }

    @ApiOperation(value = "取得用户聚合信息(批量接口)", notes = "根据用户ID取得用户聚合信息", position = 10)
    @RequestMapping(value = "/getuserbyids", method = RequestMethod.POST)
    @ResponseBody
    public Result selectUserInfoWrapperById(
            @RequestBody List<String> UsreIds,@RequestHeader(value = "appId") String appId) throws Exception {
        return userService.selectUserInfoWrapperByIds(UsreIds);
    }

    /**
     * 三方登录
     */
    @ApiOperation(value = "三方登录", notes = "三方登录", position = 11)
    @ApiResponse(code = 20001, message = "用户数据插入数据库失败")
    @RequestMapping(value = "/authorizedlogin/{authorizedtypeid}/{openId}", method = RequestMethod.POST)
    @ResponseBody
    public Result threePartLogin(
            @RequestHeader("systemtypeid") String systemtypeid,
            @RequestHeader("equipmentnum") String equipmentnum,
            @PathVariable("openId") String openId,
            @PathVariable("authorizedtypeid") int authorizedtypeid,
            HttpServletRequest request,@RequestHeader(value = "appId") String appId) throws Exception {
        String ip = RequestUtils.getIpAddr(request);
        return userService.threePartLogin(openId, authorizedtypeid, appId,
                new LogInfo(ip, systemtypeid, equipmentnum));
    }

    /**
     * 三方登录
     */
    @ApiOperation(value = "三方登录v2", notes = "三方登录v2", position = 11)
    @ApiResponse(code = 20001, message = "用户数据插入数据库失败")
    @RequestMapping(value = "/authorizedlogin/v2/{authorizedtypeid}/{openid}/{unionid}/{accesstoken}/{refreshtoken}", method = RequestMethod.POST)
    @ResponseBody
    public Result threePartLoginv2(
            @RequestHeader("systemtypeid") String systemtypeid,
            @RequestHeader("equipmentnum") String equipmentnum,
            @PathVariable("openid") String openId,
            @PathVariable("unionid") String unionId,
            @PathVariable("accesstoken") String accessToken,
            @PathVariable("refreshtoken") String refreshToken,
            @PathVariable("authorizedtypeid") int authorizedtypeid,
            HttpServletRequest request,@RequestHeader(value = "appId") String appId) throws Exception {
        String ip = RequestUtils.getIpAddr(request);
        return userService.threePartLoginv2(openId, unionId, accessToken, refreshToken, authorizedtypeid, appId,
                new LogInfo(ip, systemtypeid, equipmentnum));
    }

    /**
     * 绑定新手机号
     */
    @ApiOperation(value = "绑定新手机号", notes = "绑定新手机号", position = 12)
    @RequestMapping(value = "/binduser/{mobileNum}/{password}/{checkCode}", method = RequestMethod.POST)
    @ResponseBody
    public Result binduser(
            @RequestHeader("token") String token,
            @PathVariable("mobileNum") String mobileNum,
            @PathVariable("password") String password,
            @PathVariable("checkCode") String checkCode,@RequestHeader(value = "appId") String appId) throws Exception {
        return userService.binduser(token, mobileNum, password, checkCode);
    }

    /**
     * token 登录
     */
    @ApiOperation(value = "token 登录", notes = "token 登录", position = 13)
    @RequestMapping(value = "/tokenlogin", method = RequestMethod.POST)
    @ResponseBody
    public Result tokenlogin(
            @RequestHeader("token") String token,@RequestHeader(value = "appId") String appId) throws Exception {
        return userService.tokenlogin(token);
    }

    /**
     * 登出
     */
    @ApiOperation(value = "登出", notes = "登出", position = 14)
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Result logout(
            @RequestHeader("token") String token,@RequestHeader(value = "appId") String appId) throws Exception {
        return userService.logout(token);
    }

    /**
     * 完善个人信息
     */
    @ApiOperation(value = "完善个人信息", notes = "完善个人信息", position = 15)
    @ApiResponse(code = 20002, message = "用户数据更新数据库失败")
    @RequestMapping(value = "/completeinfo", method = RequestMethod.PUT)
    @ResponseBody
    public Result completeinfo(
            @RequestHeader("token") String token,
            @RequestBody UserInfo user,@RequestHeader(value = "appId") String appId
    ) throws Exception {
        return userService.completeinfo(token, user);
    }

    /**
     * 检查用户权限
     */
    @ApiOperation(value = "检查用户权限", notes = "检查用户权限", position = 16)
    @RequestMapping(value = "/{user_id}/validate-privilege/{privilege_key}", method = RequestMethod.GET)
    @ResponseBody
    public Result validatePrivilege(
            @ApiParam(value = "用户NHID", defaultValue = "")
            @PathVariable("user_id") String user_id,
            @ApiParam(value = "权限名称", defaultValue = "FORUM_TOP")
            @PathVariable("privilege_key") String privilege_key,@RequestHeader(value = "appId") String appId
    ) {
        return userService.validatePrivilege(user_id, privilege_key);
    }

    @ApiOperation(value = "validate token", notes = "validate token", position = 4)
    @ApiResponses(value = {})
    @RequestMapping(value = "/validate-token", method = RequestMethod.POST)
    @ResponseBody
    public UserInfo validateToken(
            @ApiParam(value = "token", defaultValue = "ade7d1e2-10ab-4481-867b-2e9b78e2061f")
            @RequestHeader("token") String token, HttpServletResponse response,@RequestHeader(value = "appId") String appId) throws Exception {

        UserInfo userinfo = userService.getUserInfoByToken(token);
        if (userinfo == null) {
            response.setStatus(403);
        }
        return userinfo;
    }

    /**
     * 根据昵称模糊查询用户
     */
//    @ApiOperation(value = "根据昵称模糊查询用户", notes = "根据昵称模糊查询用户", position = 16)
//    @RequestMapping(value = "/userLike", method = RequestMethod.GET)
//    @ResponseBody
//    public Result selectUserLike(
//            @ApiParam(value = "用户昵称")
//            @RequestParam("nickname") String nickname,@RequestHeader(value = "appId") String appId
//    ) {
//        return userService.selectUserLike(nickname);
//    }
}
