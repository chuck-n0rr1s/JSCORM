package com.arcusys.learn.persistence.liferay.service.persistence;

import com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the l f sequencing permissions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LFSequencingPermissionsPersistenceImpl
 * @see LFSequencingPermissionsUtil
 * @generated
 */
public interface LFSequencingPermissionsPersistence extends BasePersistence<LFSequencingPermissions> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link LFSequencingPermissionsUtil} to access the l f sequencing permissions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the l f sequencing permissionses where sequencingID = &#63;.
    *
    * @param sequencingID the sequencing i d
    * @return the matching l f sequencing permissionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions> findBySequencingID(
        java.lang.Integer sequencingID)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the l f sequencing permissionses where sequencingID = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFSequencingPermissionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sequencingID the sequencing i d
    * @param start the lower bound of the range of l f sequencing permissionses
    * @param end the upper bound of the range of l f sequencing permissionses (not inclusive)
    * @return the range of matching l f sequencing permissionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions> findBySequencingID(
        java.lang.Integer sequencingID, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the l f sequencing permissionses where sequencingID = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFSequencingPermissionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sequencingID the sequencing i d
    * @param start the lower bound of the range of l f sequencing permissionses
    * @param end the upper bound of the range of l f sequencing permissionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching l f sequencing permissionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions> findBySequencingID(
        java.lang.Integer sequencingID, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first l f sequencing permissions in the ordered set where sequencingID = &#63;.
    *
    * @param sequencingID the sequencing i d
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching l f sequencing permissions
    * @throws com.arcusys.learn.persistence.liferay.NoSuchLFSequencingPermissionsException if a matching l f sequencing permissions could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions findBySequencingID_First(
        java.lang.Integer sequencingID,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.arcusys.learn.persistence.liferay.NoSuchLFSequencingPermissionsException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first l f sequencing permissions in the ordered set where sequencingID = &#63;.
    *
    * @param sequencingID the sequencing i d
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching l f sequencing permissions, or <code>null</code> if a matching l f sequencing permissions could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions fetchBySequencingID_First(
        java.lang.Integer sequencingID,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last l f sequencing permissions in the ordered set where sequencingID = &#63;.
    *
    * @param sequencingID the sequencing i d
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching l f sequencing permissions
    * @throws com.arcusys.learn.persistence.liferay.NoSuchLFSequencingPermissionsException if a matching l f sequencing permissions could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions findBySequencingID_Last(
        java.lang.Integer sequencingID,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.arcusys.learn.persistence.liferay.NoSuchLFSequencingPermissionsException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last l f sequencing permissions in the ordered set where sequencingID = &#63;.
    *
    * @param sequencingID the sequencing i d
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching l f sequencing permissions, or <code>null</code> if a matching l f sequencing permissions could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions fetchBySequencingID_Last(
        java.lang.Integer sequencingID,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the l f sequencing permissionses before and after the current l f sequencing permissions in the ordered set where sequencingID = &#63;.
    *
    * @param id the primary key of the current l f sequencing permissions
    * @param sequencingID the sequencing i d
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next l f sequencing permissions
    * @throws com.arcusys.learn.persistence.liferay.NoSuchLFSequencingPermissionsException if a l f sequencing permissions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions[] findBySequencingID_PrevAndNext(
        long id, java.lang.Integer sequencingID,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.arcusys.learn.persistence.liferay.NoSuchLFSequencingPermissionsException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the l f sequencing permissionses where sequencingID = &#63; from the database.
    *
    * @param sequencingID the sequencing i d
    * @throws SystemException if a system exception occurred
    */
    public void removeBySequencingID(java.lang.Integer sequencingID)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of l f sequencing permissionses where sequencingID = &#63;.
    *
    * @param sequencingID the sequencing i d
    * @return the number of matching l f sequencing permissionses
    * @throws SystemException if a system exception occurred
    */
    public int countBySequencingID(java.lang.Integer sequencingID)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the l f sequencing permissions in the entity cache if it is enabled.
    *
    * @param lfSequencingPermissions the l f sequencing permissions
    */
    public void cacheResult(
        com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions lfSequencingPermissions);

    /**
    * Caches the l f sequencing permissionses in the entity cache if it is enabled.
    *
    * @param lfSequencingPermissionses the l f sequencing permissionses
    */
    public void cacheResult(
        java.util.List<com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions> lfSequencingPermissionses);

    /**
    * Creates a new l f sequencing permissions with the primary key. Does not add the l f sequencing permissions to the database.
    *
    * @param id the primary key for the new l f sequencing permissions
    * @return the new l f sequencing permissions
    */
    public com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions create(
        long id);

    /**
    * Removes the l f sequencing permissions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the l f sequencing permissions
    * @return the l f sequencing permissions that was removed
    * @throws com.arcusys.learn.persistence.liferay.NoSuchLFSequencingPermissionsException if a l f sequencing permissions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions remove(
        long id)
        throws com.arcusys.learn.persistence.liferay.NoSuchLFSequencingPermissionsException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions updateImpl(
        com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions lfSequencingPermissions)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the l f sequencing permissions with the primary key or throws a {@link com.arcusys.learn.persistence.liferay.NoSuchLFSequencingPermissionsException} if it could not be found.
    *
    * @param id the primary key of the l f sequencing permissions
    * @return the l f sequencing permissions
    * @throws com.arcusys.learn.persistence.liferay.NoSuchLFSequencingPermissionsException if a l f sequencing permissions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions findByPrimaryKey(
        long id)
        throws com.arcusys.learn.persistence.liferay.NoSuchLFSequencingPermissionsException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the l f sequencing permissions with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the l f sequencing permissions
    * @return the l f sequencing permissions, or <code>null</code> if a l f sequencing permissions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the l f sequencing permissionses.
    *
    * @return the l f sequencing permissionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the l f sequencing permissionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFSequencingPermissionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of l f sequencing permissionses
    * @param end the upper bound of the range of l f sequencing permissionses (not inclusive)
    * @return the range of l f sequencing permissionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the l f sequencing permissionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFSequencingPermissionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of l f sequencing permissionses
    * @param end the upper bound of the range of l f sequencing permissionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of l f sequencing permissionses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.arcusys.learn.persistence.liferay.model.LFSequencingPermissions> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the l f sequencing permissionses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of l f sequencing permissionses.
    *
    * @return the number of l f sequencing permissionses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
