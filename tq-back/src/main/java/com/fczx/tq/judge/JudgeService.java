package com.fczx.tq.judge;

import com.fczx.tq.model.entity.QuestionSubmit;
import com.fczx.tq.model.vo.QuestionVO;

/**
 * 判题
 */

public interface JudgeService {
    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
