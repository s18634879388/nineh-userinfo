package com.ninehcom.userinfo.service;

import com.ninehcom.common.enums.ErrorCode;
import com.ninehcom.common.util.Result;
import com.ninehcom.userinfo.entity.Feedback;
import com.ninehcom.userinfo.entity.UserInfo;
import com.ninehcom.userinfo.mapper.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

/**
 * Feedback的Service
 *
 * @author shenjizhe
 * @version 1.0.0
 */
@Service
public class FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;
    @Autowired
    private UserInfoService userInfoService;

    public Result getAllFeedback() {
        try {
            ArrayList<Feedback> feedbackList = feedbackMapper.selectAllFeedback(-1);
            return Result.Success(feedbackList);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.GetFeedbackFail, ex);
        }
    }

    public Result getAllFeedbackUnReaded() {
        try {
            ArrayList<Feedback> feedbackList = feedbackMapper.selectAllFeedback(0);
            return Result.Success(feedbackList);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.GetFeedbackFail, ex);
        }
    }

    public Result sendFeedback(String token, String content) {
        Result result;
        try {
            result = userInfoService.getUserbytoken(token);
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.SendFeedbackFail, ex);
        }
        if (result.isSuccess()) {
            try {
                UserInfo user = (UserInfo) result.getTag();
                String userId = user.getId();
                int i = feedbackMapper.insertFeedback(userId, content);
                if (i == 1) {
                    return Result.Success();
                } else {
                    return Result.Fail(ErrorCode.SendFeedbackFail);
                }
            } catch (Exception ex) {
                return Result.Fail(ErrorCode.SendFeedbackFail, ex);
            }
        }
        return result;
    }

    public Result reviewFeedBack(String Id, int feedbackType, String record) {
        try {
            int i = feedbackMapper.updateFeedback(Id, feedbackType, record);
            if (i == 1) {
                return Result.Success();
            } else {
                return Result.Fail(ErrorCode.ReviewFeedbackFail);
            }
        } catch (Exception ex) {
            return Result.Fail(ErrorCode.ReviewFeedbackFail, ex);
        }
    }
}
