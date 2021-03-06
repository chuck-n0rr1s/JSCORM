package com.arcusys.learn.persistence.liferay.service.persistence;

import com.arcusys.learn.persistence.liferay.NoSuchLFFileStorageException;
import com.arcusys.learn.persistence.liferay.model.LFFileStorage;
import com.arcusys.learn.persistence.liferay.model.impl.LFFileStorageImpl;
import com.arcusys.learn.persistence.liferay.model.impl.LFFileStorageModelImpl;
import com.arcusys.learn.persistence.liferay.service.persistence.LFFileStoragePersistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the l f file storage service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LFFileStoragePersistence
 * @see LFFileStorageUtil
 * @generated
 */
public class LFFileStoragePersistenceImpl extends BasePersistenceImpl<LFFileStorage>
    implements LFFileStoragePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link LFFileStorageUtil} to access the l f file storage persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = LFFileStorageImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LFFileStorageModelImpl.ENTITY_CACHE_ENABLED,
            LFFileStorageModelImpl.FINDER_CACHE_ENABLED,
            LFFileStorageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LFFileStorageModelImpl.ENTITY_CACHE_ENABLED,
            LFFileStorageModelImpl.FINDER_CACHE_ENABLED,
            LFFileStorageImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LFFileStorageModelImpl.ENTITY_CACHE_ENABLED,
            LFFileStorageModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FILENAME = new FinderPath(LFFileStorageModelImpl.ENTITY_CACHE_ENABLED,
            LFFileStorageModelImpl.FINDER_CACHE_ENABLED,
            LFFileStorageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByFilename",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FILENAME =
        new FinderPath(LFFileStorageModelImpl.ENTITY_CACHE_ENABLED,
            LFFileStorageModelImpl.FINDER_CACHE_ENABLED,
            LFFileStorageImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByFilename", new String[] { String.class.getName() },
            LFFileStorageModelImpl.FILENAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FILENAME = new FinderPath(LFFileStorageModelImpl.ENTITY_CACHE_ENABLED,
            LFFileStorageModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFilename",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_FILENAME_FILENAME_1 = "lfFileStorage.filename IS NULL";
    private static final String _FINDER_COLUMN_FILENAME_FILENAME_NULL = "lfFileStorage.filename IS NULL";
    private static final String _FINDER_COLUMN_FILENAME_FILENAME_2 = "lfFileStorage.filename = ?";
    private static final String _FINDER_COLUMN_FILENAME_FILENAME_NULL_2 = "lfFileStorage.filename IS NULL ";
    private static final String _FINDER_COLUMN_FILENAME_FILENAME_3 = "(lfFileStorage.filename IS NULL OR lfFileStorage.filename = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_DIRECTORY =
        new FinderPath(LFFileStorageModelImpl.ENTITY_CACHE_ENABLED,
            LFFileStorageModelImpl.FINDER_CACHE_ENABLED,
            LFFileStorageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByDirectory",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_DIRECTORY =
        new FinderPath(LFFileStorageModelImpl.ENTITY_CACHE_ENABLED,
            LFFileStorageModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByDirectory",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_DIRECTORY_FILENAME_1 = "lfFileStorage.filename LIKE NULL";
    private static final String _FINDER_COLUMN_DIRECTORY_FILENAME_NULL = "lfFileStorage.filename IS NULL";
    private static final String _FINDER_COLUMN_DIRECTORY_FILENAME_2 = "lfFileStorage.filename LIKE ?";
    private static final String _FINDER_COLUMN_DIRECTORY_FILENAME_NULL_2 = "lfFileStorage.filename IS NULL ";
    private static final String _FINDER_COLUMN_DIRECTORY_FILENAME_3 = "(lfFileStorage.filename IS NULL OR lfFileStorage.filename LIKE '')";
    private static final String _SQL_SELECT_LFFILESTORAGE = "SELECT lfFileStorage FROM LFFileStorage lfFileStorage";
    private static final String _SQL_SELECT_LFFILESTORAGE_WHERE = "SELECT lfFileStorage FROM LFFileStorage lfFileStorage WHERE ";
    private static final String _SQL_COUNT_LFFILESTORAGE = "SELECT COUNT(lfFileStorage) FROM LFFileStorage lfFileStorage";
    private static final String _SQL_COUNT_LFFILESTORAGE_WHERE = "SELECT COUNT(lfFileStorage) FROM LFFileStorage lfFileStorage WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "lfFileStorage.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LFFileStorage exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LFFileStorage exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(LFFileStoragePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static LFFileStorage _nullLFFileStorage = new LFFileStorageImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<LFFileStorage> toCacheModel() {
                return _nullLFFileStorageCacheModel;
            }
        };

    private static CacheModel<LFFileStorage> _nullLFFileStorageCacheModel = new CacheModel<LFFileStorage>() {
            @Override
            public LFFileStorage toEntityModel() {
                return _nullLFFileStorage;
            }
        };

    public LFFileStoragePersistenceImpl() {
        setModelClass(LFFileStorage.class);
    }

    /**
     * Returns all the l f file storages where filename = &#63;.
     *
     * @param filename the filename
     * @return the matching l f file storages
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFFileStorage> findByFilename(String filename)
        throws SystemException {
        return findByFilename(filename, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the l f file storages where filename = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFFileStorageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param filename the filename
     * @param start the lower bound of the range of l f file storages
     * @param end the upper bound of the range of l f file storages (not inclusive)
     * @return the range of matching l f file storages
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFFileStorage> findByFilename(String filename, int start,
        int end) throws SystemException {
        return findByFilename(filename, start, end, null);
    }

    /**
     * Returns an ordered range of all the l f file storages where filename = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFFileStorageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param filename the filename
     * @param start the lower bound of the range of l f file storages
     * @param end the upper bound of the range of l f file storages (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching l f file storages
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFFileStorage> findByFilename(String filename, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FILENAME;
            finderArgs = new Object[] { filename };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FILENAME;
            finderArgs = new Object[] { filename, start, end, orderByComparator };
        }

        List<LFFileStorage> list = (List<LFFileStorage>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (LFFileStorage lfFileStorage : list) {
                if (!Validator.equals(filename, lfFileStorage.getFilename())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_LFFILESTORAGE_WHERE);

            boolean bindFilename = false;

            if (filename == null) {
                query.append(_FINDER_COLUMN_FILENAME_FILENAME_1);
            } else if (filename.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FILENAME_FILENAME_3);
            } else {
                bindFilename = true;

                if (filename.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_FILENAME_FILENAME_3);
                } else {
                    query.append(_FINDER_COLUMN_FILENAME_FILENAME_2);
                }
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(LFFileStorageModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindFilename) {
                    if (filename != null) {
                        qPos.add(filename);
                    }
                }

                if (!pagination) {
                    list = (List<LFFileStorage>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LFFileStorage>(list);
                } else {
                    list = (List<LFFileStorage>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first l f file storage in the ordered set where filename = &#63;.
     *
     * @param filename the filename
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching l f file storage
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFFileStorageException if a matching l f file storage could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFFileStorage findByFilename_First(String filename,
        OrderByComparator orderByComparator)
        throws NoSuchLFFileStorageException, SystemException {
        LFFileStorage lfFileStorage = fetchByFilename_First(filename,
                orderByComparator);

        if (lfFileStorage != null) {
            return lfFileStorage;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("filename=");
        msg.append(filename);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLFFileStorageException(msg.toString());
    }

    /**
     * Returns the first l f file storage in the ordered set where filename = &#63;.
     *
     * @param filename the filename
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching l f file storage, or <code>null</code> if a matching l f file storage could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFFileStorage fetchByFilename_First(String filename,
        OrderByComparator orderByComparator) throws SystemException {
        List<LFFileStorage> list = findByFilename(filename, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last l f file storage in the ordered set where filename = &#63;.
     *
     * @param filename the filename
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching l f file storage
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFFileStorageException if a matching l f file storage could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFFileStorage findByFilename_Last(String filename,
        OrderByComparator orderByComparator)
        throws NoSuchLFFileStorageException, SystemException {
        LFFileStorage lfFileStorage = fetchByFilename_Last(filename,
                orderByComparator);

        if (lfFileStorage != null) {
            return lfFileStorage;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("filename=");
        msg.append(filename);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLFFileStorageException(msg.toString());
    }

    /**
     * Returns the last l f file storage in the ordered set where filename = &#63;.
     *
     * @param filename the filename
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching l f file storage, or <code>null</code> if a matching l f file storage could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFFileStorage fetchByFilename_Last(String filename,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByFilename(filename);

        if (count == 0) {
            return null;
        }

        List<LFFileStorage> list = findByFilename(filename, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the l f file storages before and after the current l f file storage in the ordered set where filename = &#63;.
     *
     * @param id the primary key of the current l f file storage
     * @param filename the filename
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next l f file storage
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFFileStorageException if a l f file storage with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFFileStorage[] findByFilename_PrevAndNext(long id, String filename,
        OrderByComparator orderByComparator)
        throws NoSuchLFFileStorageException, SystemException {
        LFFileStorage lfFileStorage = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            LFFileStorage[] array = new LFFileStorageImpl[3];

            array[0] = getByFilename_PrevAndNext(session, lfFileStorage,
                    filename, orderByComparator, true);

            array[1] = lfFileStorage;

            array[2] = getByFilename_PrevAndNext(session, lfFileStorage,
                    filename, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected LFFileStorage getByFilename_PrevAndNext(Session session,
        LFFileStorage lfFileStorage, String filename,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_LFFILESTORAGE_WHERE);

        boolean bindFilename = false;

        if (filename == null) {
            query.append(_FINDER_COLUMN_FILENAME_FILENAME_1);
        } else if (filename.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_FILENAME_FILENAME_3);
        } else {
            bindFilename = true;

            if (filename.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FILENAME_FILENAME_3);
            } else {
                query.append(_FINDER_COLUMN_FILENAME_FILENAME_2);
            }
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(LFFileStorageModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindFilename) {
            if (filename != null) {
                qPos.add(filename);
            }
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(lfFileStorage);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<LFFileStorage> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the l f file storages where filename = &#63; from the database.
     *
     * @param filename the filename
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByFilename(String filename) throws SystemException {
        for (LFFileStorage lfFileStorage : findByFilename(filename,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(lfFileStorage);
        }
    }

    /**
     * Returns the number of l f file storages where filename = &#63;.
     *
     * @param filename the filename
     * @return the number of matching l f file storages
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByFilename(String filename) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_FILENAME;

        Object[] finderArgs = new Object[] { filename };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_LFFILESTORAGE_WHERE);

            boolean bindFilename = false;

            if (filename == null) {
                query.append(_FINDER_COLUMN_FILENAME_FILENAME_1);
            } else if (filename.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FILENAME_FILENAME_3);
            } else {
                bindFilename = true;

                if (filename.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_FILENAME_FILENAME_3);
                } else {
                    query.append(_FINDER_COLUMN_FILENAME_FILENAME_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindFilename) {
                    if (filename != null) {
                        qPos.add(filename);
                    }
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the l f file storages where filename LIKE &#63;.
     *
     * @param filename the filename
     * @return the matching l f file storages
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFFileStorage> findByDirectory(String filename)
        throws SystemException {
        return findByDirectory(filename, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the l f file storages where filename LIKE &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFFileStorageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param filename the filename
     * @param start the lower bound of the range of l f file storages
     * @param end the upper bound of the range of l f file storages (not inclusive)
     * @return the range of matching l f file storages
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFFileStorage> findByDirectory(String filename, int start,
        int end) throws SystemException {
        return findByDirectory(filename, start, end, null);
    }

    /**
     * Returns an ordered range of all the l f file storages where filename LIKE &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFFileStorageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param filename the filename
     * @param start the lower bound of the range of l f file storages
     * @param end the upper bound of the range of l f file storages (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching l f file storages
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFFileStorage> findByDirectory(String filename, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_DIRECTORY;
        finderArgs = new Object[] { filename, start, end, orderByComparator };

        List<LFFileStorage> list = (List<LFFileStorage>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (LFFileStorage lfFileStorage : list) {
                if (!StringUtil.wildcardMatches(lfFileStorage.getFilename(),
                            filename, CharPool.UNDERLINE, CharPool.PERCENT,
                            CharPool.BACK_SLASH, true)) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_LFFILESTORAGE_WHERE);

            boolean bindFilename = false;

            if (filename == null) {
                query.append(_FINDER_COLUMN_DIRECTORY_FILENAME_1);
            } else if (filename.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_DIRECTORY_FILENAME_3);
            } else {
                bindFilename = true;

                if (filename.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_DIRECTORY_FILENAME_3);
                } else {
                    query.append(_FINDER_COLUMN_DIRECTORY_FILENAME_2);
                }
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(LFFileStorageModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindFilename) {
                    if (filename != null) {
                        qPos.add(filename);
                    }
                }

                if (!pagination) {
                    list = (List<LFFileStorage>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LFFileStorage>(list);
                } else {
                    list = (List<LFFileStorage>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the first l f file storage in the ordered set where filename LIKE &#63;.
     *
     * @param filename the filename
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching l f file storage
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFFileStorageException if a matching l f file storage could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFFileStorage findByDirectory_First(String filename,
        OrderByComparator orderByComparator)
        throws NoSuchLFFileStorageException, SystemException {
        LFFileStorage lfFileStorage = fetchByDirectory_First(filename,
                orderByComparator);

        if (lfFileStorage != null) {
            return lfFileStorage;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("filename=");
        msg.append(filename);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLFFileStorageException(msg.toString());
    }

    /**
     * Returns the first l f file storage in the ordered set where filename LIKE &#63;.
     *
     * @param filename the filename
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching l f file storage, or <code>null</code> if a matching l f file storage could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFFileStorage fetchByDirectory_First(String filename,
        OrderByComparator orderByComparator) throws SystemException {
        List<LFFileStorage> list = findByDirectory(filename, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last l f file storage in the ordered set where filename LIKE &#63;.
     *
     * @param filename the filename
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching l f file storage
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFFileStorageException if a matching l f file storage could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFFileStorage findByDirectory_Last(String filename,
        OrderByComparator orderByComparator)
        throws NoSuchLFFileStorageException, SystemException {
        LFFileStorage lfFileStorage = fetchByDirectory_Last(filename,
                orderByComparator);

        if (lfFileStorage != null) {
            return lfFileStorage;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("filename=");
        msg.append(filename);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLFFileStorageException(msg.toString());
    }

    /**
     * Returns the last l f file storage in the ordered set where filename LIKE &#63;.
     *
     * @param filename the filename
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching l f file storage, or <code>null</code> if a matching l f file storage could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFFileStorage fetchByDirectory_Last(String filename,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByDirectory(filename);

        if (count == 0) {
            return null;
        }

        List<LFFileStorage> list = findByDirectory(filename, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the l f file storages before and after the current l f file storage in the ordered set where filename LIKE &#63;.
     *
     * @param id the primary key of the current l f file storage
     * @param filename the filename
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next l f file storage
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFFileStorageException if a l f file storage with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFFileStorage[] findByDirectory_PrevAndNext(long id,
        String filename, OrderByComparator orderByComparator)
        throws NoSuchLFFileStorageException, SystemException {
        LFFileStorage lfFileStorage = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            LFFileStorage[] array = new LFFileStorageImpl[3];

            array[0] = getByDirectory_PrevAndNext(session, lfFileStorage,
                    filename, orderByComparator, true);

            array[1] = lfFileStorage;

            array[2] = getByDirectory_PrevAndNext(session, lfFileStorage,
                    filename, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected LFFileStorage getByDirectory_PrevAndNext(Session session,
        LFFileStorage lfFileStorage, String filename,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_LFFILESTORAGE_WHERE);

        boolean bindFilename = false;

        if (filename == null) {
            query.append(_FINDER_COLUMN_DIRECTORY_FILENAME_1);
        } else if (filename.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_DIRECTORY_FILENAME_3);
        } else {
            bindFilename = true;

            if (filename.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_DIRECTORY_FILENAME_3);
            } else {
                query.append(_FINDER_COLUMN_DIRECTORY_FILENAME_2);
            }
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(LFFileStorageModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindFilename) {
            if (filename != null) {
                qPos.add(filename);
            }
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(lfFileStorage);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<LFFileStorage> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the l f file storages where filename LIKE &#63; from the database.
     *
     * @param filename the filename
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByDirectory(String filename) throws SystemException {
        for (LFFileStorage lfFileStorage : findByDirectory(filename,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(lfFileStorage);
        }
    }

    /**
     * Returns the number of l f file storages where filename LIKE &#63;.
     *
     * @param filename the filename
     * @return the number of matching l f file storages
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByDirectory(String filename) throws SystemException {
        FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_DIRECTORY;

        Object[] finderArgs = new Object[] { filename };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_LFFILESTORAGE_WHERE);

            boolean bindFilename = false;

            if (filename == null) {
                query.append(_FINDER_COLUMN_DIRECTORY_FILENAME_1);
            } else if (filename.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_DIRECTORY_FILENAME_3);
            } else {
                bindFilename = true;

                if (filename.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_DIRECTORY_FILENAME_3);
                } else {
                    query.append(_FINDER_COLUMN_DIRECTORY_FILENAME_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindFilename) {
                    if (filename != null) {
                        qPos.add(filename);
                    }
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Caches the l f file storage in the entity cache if it is enabled.
     *
     * @param lfFileStorage the l f file storage
     */
    @Override
    public void cacheResult(LFFileStorage lfFileStorage) {
        EntityCacheUtil.putResult(LFFileStorageModelImpl.ENTITY_CACHE_ENABLED,
            LFFileStorageImpl.class, lfFileStorage.getPrimaryKey(),
            lfFileStorage);

        lfFileStorage.resetOriginalValues();
    }

    /**
     * Caches the l f file storages in the entity cache if it is enabled.
     *
     * @param lfFileStorages the l f file storages
     */
    @Override
    public void cacheResult(List<LFFileStorage> lfFileStorages) {
        for (LFFileStorage lfFileStorage : lfFileStorages) {
            if (EntityCacheUtil.getResult(
                        LFFileStorageModelImpl.ENTITY_CACHE_ENABLED,
                        LFFileStorageImpl.class, lfFileStorage.getPrimaryKey()) == null) {
                cacheResult(lfFileStorage);
            } else {
                lfFileStorage.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all l f file storages.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(LFFileStorageImpl.class.getName());
        }

        EntityCacheUtil.clearCache(LFFileStorageImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the l f file storage.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(LFFileStorage lfFileStorage) {
        EntityCacheUtil.removeResult(LFFileStorageModelImpl.ENTITY_CACHE_ENABLED,
            LFFileStorageImpl.class, lfFileStorage.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<LFFileStorage> lfFileStorages) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (LFFileStorage lfFileStorage : lfFileStorages) {
            EntityCacheUtil.removeResult(LFFileStorageModelImpl.ENTITY_CACHE_ENABLED,
                LFFileStorageImpl.class, lfFileStorage.getPrimaryKey());
        }
    }

    /**
     * Creates a new l f file storage with the primary key. Does not add the l f file storage to the database.
     *
     * @param id the primary key for the new l f file storage
     * @return the new l f file storage
     */
    @Override
    public LFFileStorage create(long id) {
        LFFileStorage lfFileStorage = new LFFileStorageImpl();

        lfFileStorage.setNew(true);
        lfFileStorage.setPrimaryKey(id);

        return lfFileStorage;
    }

    /**
     * Removes the l f file storage with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the l f file storage
     * @return the l f file storage that was removed
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFFileStorageException if a l f file storage with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFFileStorage remove(long id)
        throws NoSuchLFFileStorageException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the l f file storage with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the l f file storage
     * @return the l f file storage that was removed
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFFileStorageException if a l f file storage with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFFileStorage remove(Serializable primaryKey)
        throws NoSuchLFFileStorageException, SystemException {
        Session session = null;

        try {
            session = openSession();

            LFFileStorage lfFileStorage = (LFFileStorage) session.get(LFFileStorageImpl.class,
                    primaryKey);

            if (lfFileStorage == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchLFFileStorageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(lfFileStorage);
        } catch (NoSuchLFFileStorageException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected LFFileStorage removeImpl(LFFileStorage lfFileStorage)
        throws SystemException {
        lfFileStorage = toUnwrappedModel(lfFileStorage);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(lfFileStorage)) {
                lfFileStorage = (LFFileStorage) session.get(LFFileStorageImpl.class,
                        lfFileStorage.getPrimaryKeyObj());
            }

            if (lfFileStorage != null) {
                session.delete(lfFileStorage);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (lfFileStorage != null) {
            clearCache(lfFileStorage);
        }

        return lfFileStorage;
    }

    @Override
    public LFFileStorage updateImpl(
        com.arcusys.learn.persistence.liferay.model.LFFileStorage lfFileStorage)
        throws SystemException {
        lfFileStorage = toUnwrappedModel(lfFileStorage);

        boolean isNew = lfFileStorage.isNew();

        LFFileStorageModelImpl lfFileStorageModelImpl = (LFFileStorageModelImpl) lfFileStorage;

        Session session = null;

        try {
            session = openSession();

            if (lfFileStorage.isNew()) {
                session.save(lfFileStorage);

                lfFileStorage.setNew(false);
            } else {
                session.merge(lfFileStorage);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !LFFileStorageModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((lfFileStorageModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FILENAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        lfFileStorageModelImpl.getOriginalFilename()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FILENAME, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FILENAME,
                    args);

                args = new Object[] { lfFileStorageModelImpl.getFilename() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FILENAME, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FILENAME,
                    args);
            }
        }

        EntityCacheUtil.putResult(LFFileStorageModelImpl.ENTITY_CACHE_ENABLED,
            LFFileStorageImpl.class, lfFileStorage.getPrimaryKey(),
            lfFileStorage);

        return lfFileStorage;
    }

    protected LFFileStorage toUnwrappedModel(LFFileStorage lfFileStorage) {
        if (lfFileStorage instanceof LFFileStorageImpl) {
            return lfFileStorage;
        }

        LFFileStorageImpl lfFileStorageImpl = new LFFileStorageImpl();

        lfFileStorageImpl.setNew(lfFileStorage.isNew());
        lfFileStorageImpl.setPrimaryKey(lfFileStorage.getPrimaryKey());

        lfFileStorageImpl.setId(lfFileStorage.getId());
        lfFileStorageImpl.setFilename(lfFileStorage.getFilename());
        lfFileStorageImpl.setContent(lfFileStorage.getContent());

        return lfFileStorageImpl;
    }

    /**
     * Returns the l f file storage with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the l f file storage
     * @return the l f file storage
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFFileStorageException if a l f file storage with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFFileStorage findByPrimaryKey(Serializable primaryKey)
        throws NoSuchLFFileStorageException, SystemException {
        LFFileStorage lfFileStorage = fetchByPrimaryKey(primaryKey);

        if (lfFileStorage == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchLFFileStorageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return lfFileStorage;
    }

    /**
     * Returns the l f file storage with the primary key or throws a {@link com.arcusys.learn.persistence.liferay.NoSuchLFFileStorageException} if it could not be found.
     *
     * @param id the primary key of the l f file storage
     * @return the l f file storage
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFFileStorageException if a l f file storage with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFFileStorage findByPrimaryKey(long id)
        throws NoSuchLFFileStorageException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the l f file storage with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the l f file storage
     * @return the l f file storage, or <code>null</code> if a l f file storage with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFFileStorage fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        LFFileStorage lfFileStorage = (LFFileStorage) EntityCacheUtil.getResult(LFFileStorageModelImpl.ENTITY_CACHE_ENABLED,
                LFFileStorageImpl.class, primaryKey);

        if (lfFileStorage == _nullLFFileStorage) {
            return null;
        }

        if (lfFileStorage == null) {
            Session session = null;

            try {
                session = openSession();

                lfFileStorage = (LFFileStorage) session.get(LFFileStorageImpl.class,
                        primaryKey);

                if (lfFileStorage != null) {
                    cacheResult(lfFileStorage);
                } else {
                    EntityCacheUtil.putResult(LFFileStorageModelImpl.ENTITY_CACHE_ENABLED,
                        LFFileStorageImpl.class, primaryKey, _nullLFFileStorage);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(LFFileStorageModelImpl.ENTITY_CACHE_ENABLED,
                    LFFileStorageImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return lfFileStorage;
    }

    /**
     * Returns the l f file storage with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the l f file storage
     * @return the l f file storage, or <code>null</code> if a l f file storage with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFFileStorage fetchByPrimaryKey(long id) throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the l f file storages.
     *
     * @return the l f file storages
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFFileStorage> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the l f file storages.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFFileStorageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of l f file storages
     * @param end the upper bound of the range of l f file storages (not inclusive)
     * @return the range of l f file storages
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFFileStorage> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the l f file storages.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFFileStorageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of l f file storages
     * @param end the upper bound of the range of l f file storages (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of l f file storages
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFFileStorage> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<LFFileStorage> list = (List<LFFileStorage>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_LFFILESTORAGE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_LFFILESTORAGE;

                if (pagination) {
                    sql = sql.concat(LFFileStorageModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<LFFileStorage>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LFFileStorage>(list);
                } else {
                    list = (List<LFFileStorage>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the l f file storages from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (LFFileStorage lfFileStorage : findAll()) {
            remove(lfFileStorage);
        }
    }

    /**
     * Returns the number of l f file storages.
     *
     * @return the number of l f file storages
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_LFFILESTORAGE);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    @Override
    protected Set<String> getBadColumnNames() {
        return _badColumnNames;
    }

    /**
     * Initializes the l f file storage persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.arcusys.learn.persistence.liferay.model.LFFileStorage")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<LFFileStorage>> listenersList = new ArrayList<ModelListener<LFFileStorage>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<LFFileStorage>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(LFFileStorageImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
