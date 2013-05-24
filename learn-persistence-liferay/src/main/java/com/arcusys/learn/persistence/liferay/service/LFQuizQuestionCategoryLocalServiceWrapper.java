package com.arcusys.learn.persistence.liferay.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link LFQuizQuestionCategoryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LFQuizQuestionCategoryLocalService
 * @generated
 */
public class LFQuizQuestionCategoryLocalServiceWrapper
    implements LFQuizQuestionCategoryLocalService,
        ServiceWrapper<LFQuizQuestionCategoryLocalService> {
    private LFQuizQuestionCategoryLocalService _lfQuizQuestionCategoryLocalService;

    public LFQuizQuestionCategoryLocalServiceWrapper(
        LFQuizQuestionCategoryLocalService lfQuizQuestionCategoryLocalService) {
        _lfQuizQuestionCategoryLocalService = lfQuizQuestionCategoryLocalService;
    }

    /**
    * Adds the l f quiz question category to the database. Also notifies the appropriate model listeners.
    *
    * @param lfQuizQuestionCategory the l f quiz question category
    * @return the l f quiz question category that was added
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory addLFQuizQuestionCategory(
        com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory lfQuizQuestionCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.addLFQuizQuestionCategory(lfQuizQuestionCategory);
    }

    /**
    * Creates a new l f quiz question category with the primary key. Does not add the l f quiz question category to the database.
    *
    * @param id the primary key for the new l f quiz question category
    * @return the new l f quiz question category
    */
    public com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory createLFQuizQuestionCategory(
        long id) {
        return _lfQuizQuestionCategoryLocalService.createLFQuizQuestionCategory(id);
    }

    /**
    * Deletes the l f quiz question category with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the l f quiz question category
    * @return the l f quiz question category that was removed
    * @throws PortalException if a l f quiz question category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory deleteLFQuizQuestionCategory(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.deleteLFQuizQuestionCategory(id);
    }

    /**
    * Deletes the l f quiz question category from the database. Also notifies the appropriate model listeners.
    *
    * @param lfQuizQuestionCategory the l f quiz question category
    * @return the l f quiz question category that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory deleteLFQuizQuestionCategory(
        com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory lfQuizQuestionCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.deleteLFQuizQuestionCategory(lfQuizQuestionCategory);
    }

    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _lfQuizQuestionCategoryLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory fetchLFQuizQuestionCategory(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.fetchLFQuizQuestionCategory(id);
    }

    /**
    * Returns the l f quiz question category with the primary key.
    *
    * @param id the primary key of the l f quiz question category
    * @return the l f quiz question category
    * @throws PortalException if a l f quiz question category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory getLFQuizQuestionCategory(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.getLFQuizQuestionCategory(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the l f quiz question categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of l f quiz question categories
    * @param end the upper bound of the range of l f quiz question categories (not inclusive)
    * @return the range of l f quiz question categories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory> getLFQuizQuestionCategories(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.getLFQuizQuestionCategories(start,
            end);
    }

    /**
    * Returns the number of l f quiz question categories.
    *
    * @return the number of l f quiz question categories
    * @throws SystemException if a system exception occurred
    */
    public int getLFQuizQuestionCategoriesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.getLFQuizQuestionCategoriesCount();
    }

    /**
    * Updates the l f quiz question category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param lfQuizQuestionCategory the l f quiz question category
    * @return the l f quiz question category that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory updateLFQuizQuestionCategory(
        com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory lfQuizQuestionCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.updateLFQuizQuestionCategory(lfQuizQuestionCategory);
    }

    /**
    * Updates the l f quiz question category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param lfQuizQuestionCategory the l f quiz question category
    * @param merge whether to merge the l f quiz question category with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the l f quiz question category that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory updateLFQuizQuestionCategory(
        com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory lfQuizQuestionCategory,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.updateLFQuizQuestionCategory(lfQuizQuestionCategory,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _lfQuizQuestionCategoryLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _lfQuizQuestionCategoryLocalService.setBeanIdentifier(beanIdentifier);
    }

    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _lfQuizQuestionCategoryLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    public com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory createLFQuizQuestionCategory()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.createLFQuizQuestionCategory();
    }

    public java.util.List<com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory> findByQuizIdAndParentId(
        java.lang.Integer quizId, java.lang.Integer parentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.findByQuizIdAndParentId(quizId,
            parentId);
    }

    public java.util.List<com.arcusys.learn.persistence.liferay.model.LFQuizQuestionCategory> findByQuizId(
        java.lang.Integer quizId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _lfQuizQuestionCategoryLocalService.findByQuizId(quizId);
    }

    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        _lfQuizQuestionCategoryLocalService.removeAll();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public LFQuizQuestionCategoryLocalService getWrappedLFQuizQuestionCategoryLocalService() {
        return _lfQuizQuestionCategoryLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedLFQuizQuestionCategoryLocalService(
        LFQuizQuestionCategoryLocalService lfQuizQuestionCategoryLocalService) {
        _lfQuizQuestionCategoryLocalService = lfQuizQuestionCategoryLocalService;
    }

    public LFQuizQuestionCategoryLocalService getWrappedService() {
        return _lfQuizQuestionCategoryLocalService;
    }

    public void setWrappedService(
        LFQuizQuestionCategoryLocalService lfQuizQuestionCategoryLocalService) {
        _lfQuizQuestionCategoryLocalService = lfQuizQuestionCategoryLocalService;
    }
}
