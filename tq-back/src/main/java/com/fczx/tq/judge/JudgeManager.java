package com.fczx.tq.judge;

import com.fczx.tq.judge.codesandbox.model.JudgeInfo;
import com.fczx.tq.judge.strategy.DefaultJudgeStrategy;
import com.fczx.tq.judge.strategy.JavaLanguageJudgeStrategy;
import com.fczx.tq.judge.strategy.JudgeContext;
import com.fczx.tq.judge.strategy.JudgeStrategy;
import com.fczx.tq.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化调用）
 */
@Service
public class JudgeManager {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}