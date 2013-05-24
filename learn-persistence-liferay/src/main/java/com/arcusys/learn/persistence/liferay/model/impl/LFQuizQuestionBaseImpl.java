package com.arcusys.learn.persistence.liferay.model.impl;

import com.arcusys.learn.persistence.liferay.model.LFQuizQuestion;
import com.arcusys.learn.persistence.liferay.service.LFQuizQuestionLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the LFQuizQuestion service. Represents a row in the &quot;Learn_LFQuizQuestion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LFQuizQuestionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LFQuizQuestionImpl
 * @see com.arcusys.learn.persistence.liferay.model.LFQuizQuestion
 * @generated
 */
public abstract class LFQuizQuestionBaseImpl extends LFQuizQuestionModelImpl
    implements LFQuizQuestion {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a l f quiz question model instance should use the {@link LFQuizQuestion} interface instead.
     */
    public void persist() throws SystemException {
        if (this.isNew()) {
            LFQuizQuestionLocalServiceUtil.addLFQuizQuestion(this);
        } else {
            LFQuizQuestionLocalServiceUtil.updateLFQuizQuestion(this);
        }
    }
}
