package com.arcusys.learn.persistence.liferay.model.impl;

import com.arcusys.learn.persistence.liferay.model.LFCertificateCourse;
import com.arcusys.learn.persistence.liferay.service.LFCertificateCourseLocalServiceUtil;

import com.liferay.portal.kernel.exception.SystemException;

/**
 * The extended model base implementation for the LFCertificateCourse service. Represents a row in the &quot;Learn_LFCertificateCourse&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LFCertificateCourseImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LFCertificateCourseImpl
 * @see com.arcusys.learn.persistence.liferay.model.LFCertificateCourse
 * @generated
 */
public abstract class LFCertificateCourseBaseImpl
    extends LFCertificateCourseModelImpl implements LFCertificateCourse {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a l f certificate course model instance should use the {@link LFCertificateCourse} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            LFCertificateCourseLocalServiceUtil.addLFCertificateCourse(this);
        } else {
            LFCertificateCourseLocalServiceUtil.updateLFCertificateCourse(this);
        }
    }
}