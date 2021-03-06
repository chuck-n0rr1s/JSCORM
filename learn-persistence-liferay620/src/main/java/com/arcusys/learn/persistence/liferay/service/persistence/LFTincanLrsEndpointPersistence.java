package com.arcusys.learn.persistence.liferay.service.persistence;

import com.arcusys.learn.persistence.liferay.model.LFTincanLrsEndpoint;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the l f tincan lrs endpoint service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LFTincanLrsEndpointPersistenceImpl
 * @see LFTincanLrsEndpointUtil
 * @generated
 */
public interface LFTincanLrsEndpointPersistence extends BasePersistence<LFTincanLrsEndpoint> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link LFTincanLrsEndpointUtil} to access the l f tincan lrs endpoint persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the l f tincan lrs endpoint in the entity cache if it is enabled.
    *
    * @param lfTincanLrsEndpoint the l f tincan lrs endpoint
    */
    public void cacheResult(
        com.arcusys.learn.persistence.liferay.model.LFTincanLrsEndpoint lfTincanLrsEndpoint);

    /**
    * Caches the l f tincan lrs endpoints in the entity cache if it is enabled.
    *
    * @param lfTincanLrsEndpoints the l f tincan lrs endpoints
    */
    public void cacheResult(
        java.util.List<com.arcusys.learn.persistence.liferay.model.LFTincanLrsEndpoint> lfTincanLrsEndpoints);

    /**
    * Creates a new l f tincan lrs endpoint with the primary key. Does not add the l f tincan lrs endpoint to the database.
    *
    * @param id the primary key for the new l f tincan lrs endpoint
    * @return the new l f tincan lrs endpoint
    */
    public com.arcusys.learn.persistence.liferay.model.LFTincanLrsEndpoint create(
        long id);

    /**
    * Removes the l f tincan lrs endpoint with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the l f tincan lrs endpoint
    * @return the l f tincan lrs endpoint that was removed
    * @throws com.arcusys.learn.persistence.liferay.NoSuchLFTincanLrsEndpointException if a l f tincan lrs endpoint with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFTincanLrsEndpoint remove(
        long id)
        throws com.arcusys.learn.persistence.liferay.NoSuchLFTincanLrsEndpointException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.arcusys.learn.persistence.liferay.model.LFTincanLrsEndpoint updateImpl(
        com.arcusys.learn.persistence.liferay.model.LFTincanLrsEndpoint lfTincanLrsEndpoint)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the l f tincan lrs endpoint with the primary key or throws a {@link com.arcusys.learn.persistence.liferay.NoSuchLFTincanLrsEndpointException} if it could not be found.
    *
    * @param id the primary key of the l f tincan lrs endpoint
    * @return the l f tincan lrs endpoint
    * @throws com.arcusys.learn.persistence.liferay.NoSuchLFTincanLrsEndpointException if a l f tincan lrs endpoint with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFTincanLrsEndpoint findByPrimaryKey(
        long id)
        throws com.arcusys.learn.persistence.liferay.NoSuchLFTincanLrsEndpointException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the l f tincan lrs endpoint with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the l f tincan lrs endpoint
    * @return the l f tincan lrs endpoint, or <code>null</code> if a l f tincan lrs endpoint with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFTincanLrsEndpoint fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the l f tincan lrs endpoints.
    *
    * @return the l f tincan lrs endpoints
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.arcusys.learn.persistence.liferay.model.LFTincanLrsEndpoint> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the l f tincan lrs endpoints.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFTincanLrsEndpointModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of l f tincan lrs endpoints
    * @param end the upper bound of the range of l f tincan lrs endpoints (not inclusive)
    * @return the range of l f tincan lrs endpoints
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.arcusys.learn.persistence.liferay.model.LFTincanLrsEndpoint> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the l f tincan lrs endpoints.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFTincanLrsEndpointModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of l f tincan lrs endpoints
    * @param end the upper bound of the range of l f tincan lrs endpoints (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of l f tincan lrs endpoints
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.arcusys.learn.persistence.liferay.model.LFTincanLrsEndpoint> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the l f tincan lrs endpoints from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of l f tincan lrs endpoints.
    *
    * @return the number of l f tincan lrs endpoints
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
