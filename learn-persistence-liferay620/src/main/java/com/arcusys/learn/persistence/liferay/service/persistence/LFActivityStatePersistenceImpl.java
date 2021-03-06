package com.arcusys.learn.persistence.liferay.service.persistence;

import com.arcusys.learn.persistence.liferay.NoSuchLFActivityStateException;
import com.arcusys.learn.persistence.liferay.model.LFActivityState;
import com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateImpl;
import com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl;
import com.arcusys.learn.persistence.liferay.service.persistence.LFActivityStatePersistence;

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
import com.liferay.portal.kernel.util.ArrayUtil;
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
 * The persistence implementation for the l f activity state service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LFActivityStatePersistence
 * @see LFActivityStateUtil
 * @generated
 */
public class LFActivityStatePersistenceImpl extends BasePersistenceImpl<LFActivityState>
    implements LFActivityStatePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link LFActivityStateUtil} to access the l f activity state persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = LFActivityStateImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateModelImpl.FINDER_CACHE_ENABLED,
            LFActivityStateImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateModelImpl.FINDER_CACHE_ENABLED,
            LFActivityStateImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITYSTATENODEIDACTIVITYID =
        new FinderPath(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateModelImpl.FINDER_CACHE_ENABLED,
            LFActivityStateImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByActivityStateNodeIDActivityID",
            new String[] {
                Integer.class.getName(), String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYSTATENODEIDACTIVITYID =
        new FinderPath(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateModelImpl.FINDER_CACHE_ENABLED,
            LFActivityStateImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByActivityStateNodeIDActivityID",
            new String[] { Integer.class.getName(), String.class.getName() },
            LFActivityStateModelImpl.ACTIVITYSTATENODEID_COLUMN_BITMASK |
            LFActivityStateModelImpl.ACTIVITYID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVITYSTATENODEIDACTIVITYID =
        new FinderPath(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByActivityStateNodeIDActivityID",
            new String[] { Integer.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_ACTIVITYSTATENODEIDACTIVITYID =
        new FinderPath(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "countByActivityStateNodeIDActivityID",
            new String[] { Integer.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_NULL =
        "lfActivityState.activityStateNodeID IS NULL";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_2 =
        "lfActivityState.activityStateNodeID = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_NULL_2 =
        "lfActivityState.activityStateNodeID IS NULL  AND ";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_5 =
        "(" +
        removeConjunction(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_2) +
        ")";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_5_NULL =
        "(" +
        _removeConjunction(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_NULL_2) +
        ")";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_1 =
        "lfActivityState.activityID IS NULL";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_NULL =
        "lfActivityState.activityID IS NULL";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_2 =
        "lfActivityState.activityID = ?";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_NULL_2 =
        "lfActivityState.activityID IS NULL ";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_3 =
        "(lfActivityState.activityID IS NULL OR lfActivityState.activityID = '')";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_4 =
        "(" +
        removeConjunction(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_1) +
        ")";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_5 =
        "(" +
        removeConjunction(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_2) +
        ")";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_5_NULL =
        "(" +
        _removeConjunction(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_NULL_2) +
        ")";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_6 =
        "(" +
        removeConjunction(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_3) +
        ")";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIDACTSTATENODEACTSTATETREE =
        new FinderPath(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateModelImpl.FINDER_CACHE_ENABLED,
            LFActivityStateImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByActIDActStateNodeActStateTree",
            new String[] {
                String.class.getName(), Integer.class.getName(),
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDACTSTATENODEACTSTATETREE =
        new FinderPath(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateModelImpl.FINDER_CACHE_ENABLED,
            LFActivityStateImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByActIDActStateNodeActStateTree",
            new String[] {
                String.class.getName(), Integer.class.getName(),
                Integer.class.getName()
            },
            LFActivityStateModelImpl.ACTIVITYID_COLUMN_BITMASK |
            LFActivityStateModelImpl.ACTIVITYSTATENODEID_COLUMN_BITMASK |
            LFActivityStateModelImpl.ACTIVITYSTATETREEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIDACTSTATENODEACTSTATETREE =
        new FinderPath(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByActIDActStateNodeActStateTree",
            new String[] {
                String.class.getName(), Integer.class.getName(),
                Integer.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_ACTIDACTSTATENODEACTSTATETREE =
        new FinderPath(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "countByActIDActStateNodeActStateTree",
            new String[] {
                String.class.getName(), Integer.class.getName(),
                Integer.class.getName()
            });
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_1 =
        "lfActivityState.activityID IS NULL AND ";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_NULL =
        "lfActivityState.activityID IS NULL";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_2 =
        "lfActivityState.activityID = ? AND ";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_NULL_2 =
        "lfActivityState.activityID IS NULL  AND ";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_3 =
        "(lfActivityState.activityID IS NULL OR lfActivityState.activityID = '') AND ";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_4 =
        "(" +
        removeConjunction(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_1) +
        ")";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_5 =
        "(" +
        removeConjunction(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_2) +
        ")";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_5_NULL =
        "(" +
        _removeConjunction(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_NULL_2) +
        ")";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_6 =
        "(" +
        removeConjunction(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_3) +
        ")";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_NULL =
        "lfActivityState.activityStateNodeID IS NULL";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_2 =
        "lfActivityState.activityStateNodeID = ? AND ";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_NULL_2 =
        "lfActivityState.activityStateNodeID IS NULL  AND ";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_5 =
        "(" +
        removeConjunction(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_2) +
        ")";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_5_NULL =
        "(" +
        _removeConjunction(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_NULL_2) +
        ")";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_NULL =
        "lfActivityState.activityStateTreeID IS NULL";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_2 =
        "lfActivityState.activityStateTreeID = ?";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_NULL_2 =
        "lfActivityState.activityStateTreeID IS NULL ";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_5 =
        "(" +
        removeConjunction(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_2) +
        ")";
    private static final String _FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_5_NULL =
        "(" +
        _removeConjunction(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_NULL_2) +
        ")";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITYSTATENODEID =
        new FinderPath(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateModelImpl.FINDER_CACHE_ENABLED,
            LFActivityStateImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByActivityStateNodeID",
            new String[] {
                Integer.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYSTATENODEID =
        new FinderPath(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateModelImpl.FINDER_CACHE_ENABLED,
            LFActivityStateImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByActivityStateNodeID",
            new String[] { Integer.class.getName() },
            LFActivityStateModelImpl.ACTIVITYSTATENODEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVITYSTATENODEID = new FinderPath(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByActivityStateNodeID",
            new String[] { Integer.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_ACTIVITYSTATENODEID =
        new FinderPath(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "countByActivityStateNodeID",
            new String[] { Integer.class.getName() });
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_NULL =
        "lfActivityState.activityStateNodeID IS NULL";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_2 =
        "lfActivityState.activityStateNodeID = ?";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_NULL_2 =
        "lfActivityState.activityStateNodeID IS NULL ";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_5 =
        "(" +
        removeConjunction(_FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_2) +
        ")";
    private static final String _FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_5_NULL =
        "(" +
        _removeConjunction(_FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_NULL_2) +
        ")";
    private static final String _SQL_SELECT_LFACTIVITYSTATE = "SELECT lfActivityState FROM LFActivityState lfActivityState";
    private static final String _SQL_SELECT_LFACTIVITYSTATE_WHERE = "SELECT lfActivityState FROM LFActivityState lfActivityState WHERE ";
    private static final String _SQL_COUNT_LFACTIVITYSTATE = "SELECT COUNT(lfActivityState) FROM LFActivityState lfActivityState";
    private static final String _SQL_COUNT_LFACTIVITYSTATE_WHERE = "SELECT COUNT(lfActivityState) FROM LFActivityState lfActivityState WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "lfActivityState.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LFActivityState exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LFActivityState exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(LFActivityStatePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id", "active"
            });
    private static LFActivityState _nullLFActivityState = new LFActivityStateImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<LFActivityState> toCacheModel() {
                return _nullLFActivityStateCacheModel;
            }
        };

    private static CacheModel<LFActivityState> _nullLFActivityStateCacheModel = new CacheModel<LFActivityState>() {
            @Override
            public LFActivityState toEntityModel() {
                return _nullLFActivityState;
            }
        };

    public LFActivityStatePersistenceImpl() {
        setModelClass(LFActivityState.class);
    }

    /**
     * Returns all the l f activity states where activityStateNodeID = &#63; and activityID = &#63;.
     *
     * @param activityStateNodeID the activity state node i d
     * @param activityID the activity i d
     * @return the matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActivityStateNodeIDActivityID(
        Integer activityStateNodeID, String activityID)
        throws SystemException {
        return findByActivityStateNodeIDActivityID(activityStateNodeID,
            activityID, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the l f activity states where activityStateNodeID = &#63; and activityID = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param activityStateNodeID the activity state node i d
     * @param activityID the activity i d
     * @param start the lower bound of the range of l f activity states
     * @param end the upper bound of the range of l f activity states (not inclusive)
     * @return the range of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActivityStateNodeIDActivityID(
        Integer activityStateNodeID, String activityID, int start, int end)
        throws SystemException {
        return findByActivityStateNodeIDActivityID(activityStateNodeID,
            activityID, start, end, null);
    }

    /**
     * Returns an ordered range of all the l f activity states where activityStateNodeID = &#63; and activityID = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param activityStateNodeID the activity state node i d
     * @param activityID the activity i d
     * @param start the lower bound of the range of l f activity states
     * @param end the upper bound of the range of l f activity states (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActivityStateNodeIDActivityID(
        Integer activityStateNodeID, String activityID, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYSTATENODEIDACTIVITYID;
            finderArgs = new Object[] { activityStateNodeID, activityID };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITYSTATENODEIDACTIVITYID;
            finderArgs = new Object[] {
                    activityStateNodeID, activityID,
                    
                    start, end, orderByComparator
                };
        }

        List<LFActivityState> list = (List<LFActivityState>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (LFActivityState lfActivityState : list) {
                if (!Validator.equals(activityStateNodeID,
                            lfActivityState.getActivityStateNodeID()) ||
                        !Validator.equals(activityID,
                            lfActivityState.getActivityID())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_LFACTIVITYSTATE_WHERE);

            if (activityStateNodeID == null) {
                query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_NULL_2);
            } else {
                query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_2);
            }

            boolean bindActivityID = false;

            if (activityID == null) {
                query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_1);
            } else if (activityID.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_3);
            } else {
                bindActivityID = true;

                if (activityID.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_3);
                } else {
                    query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_2);
                }
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(LFActivityStateModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (activityStateNodeID != null) {
                    qPos.add(activityStateNodeID.intValue());
                }

                if (bindActivityID) {
                    if (activityID != null) {
                        qPos.add(activityID);
                    }
                }

                if (!pagination) {
                    list = (List<LFActivityState>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LFActivityState>(list);
                } else {
                    list = (List<LFActivityState>) QueryUtil.list(q,
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
     * Returns the first l f activity state in the ordered set where activityStateNodeID = &#63; and activityID = &#63;.
     *
     * @param activityStateNodeID the activity state node i d
     * @param activityID the activity i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching l f activity state
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFActivityStateException if a matching l f activity state could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState findByActivityStateNodeIDActivityID_First(
        Integer activityStateNodeID, String activityID,
        OrderByComparator orderByComparator)
        throws NoSuchLFActivityStateException, SystemException {
        LFActivityState lfActivityState = fetchByActivityStateNodeIDActivityID_First(activityStateNodeID,
                activityID, orderByComparator);

        if (lfActivityState != null) {
            return lfActivityState;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("activityStateNodeID=");
        msg.append(activityStateNodeID);

        msg.append(", activityID=");
        msg.append(activityID);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLFActivityStateException(msg.toString());
    }

    /**
     * Returns the first l f activity state in the ordered set where activityStateNodeID = &#63; and activityID = &#63;.
     *
     * @param activityStateNodeID the activity state node i d
     * @param activityID the activity i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching l f activity state, or <code>null</code> if a matching l f activity state could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState fetchByActivityStateNodeIDActivityID_First(
        Integer activityStateNodeID, String activityID,
        OrderByComparator orderByComparator) throws SystemException {
        List<LFActivityState> list = findByActivityStateNodeIDActivityID(activityStateNodeID,
                activityID, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last l f activity state in the ordered set where activityStateNodeID = &#63; and activityID = &#63;.
     *
     * @param activityStateNodeID the activity state node i d
     * @param activityID the activity i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching l f activity state
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFActivityStateException if a matching l f activity state could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState findByActivityStateNodeIDActivityID_Last(
        Integer activityStateNodeID, String activityID,
        OrderByComparator orderByComparator)
        throws NoSuchLFActivityStateException, SystemException {
        LFActivityState lfActivityState = fetchByActivityStateNodeIDActivityID_Last(activityStateNodeID,
                activityID, orderByComparator);

        if (lfActivityState != null) {
            return lfActivityState;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("activityStateNodeID=");
        msg.append(activityStateNodeID);

        msg.append(", activityID=");
        msg.append(activityID);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLFActivityStateException(msg.toString());
    }

    /**
     * Returns the last l f activity state in the ordered set where activityStateNodeID = &#63; and activityID = &#63;.
     *
     * @param activityStateNodeID the activity state node i d
     * @param activityID the activity i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching l f activity state, or <code>null</code> if a matching l f activity state could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState fetchByActivityStateNodeIDActivityID_Last(
        Integer activityStateNodeID, String activityID,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByActivityStateNodeIDActivityID(activityStateNodeID,
                activityID);

        if (count == 0) {
            return null;
        }

        List<LFActivityState> list = findByActivityStateNodeIDActivityID(activityStateNodeID,
                activityID, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the l f activity states before and after the current l f activity state in the ordered set where activityStateNodeID = &#63; and activityID = &#63;.
     *
     * @param id the primary key of the current l f activity state
     * @param activityStateNodeID the activity state node i d
     * @param activityID the activity i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next l f activity state
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFActivityStateException if a l f activity state with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState[] findByActivityStateNodeIDActivityID_PrevAndNext(
        long id, Integer activityStateNodeID, String activityID,
        OrderByComparator orderByComparator)
        throws NoSuchLFActivityStateException, SystemException {
        LFActivityState lfActivityState = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            LFActivityState[] array = new LFActivityStateImpl[3];

            array[0] = getByActivityStateNodeIDActivityID_PrevAndNext(session,
                    lfActivityState, activityStateNodeID, activityID,
                    orderByComparator, true);

            array[1] = lfActivityState;

            array[2] = getByActivityStateNodeIDActivityID_PrevAndNext(session,
                    lfActivityState, activityStateNodeID, activityID,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected LFActivityState getByActivityStateNodeIDActivityID_PrevAndNext(
        Session session, LFActivityState lfActivityState,
        Integer activityStateNodeID, String activityID,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_LFACTIVITYSTATE_WHERE);

        if (activityStateNodeID == null) {
            query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_NULL_2);
        } else {
            query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_2);
        }

        boolean bindActivityID = false;

        if (activityID == null) {
            query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_1);
        } else if (activityID.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_3);
        } else {
            bindActivityID = true;

            if (activityID.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_3);
            } else {
                query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_2);
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
            query.append(LFActivityStateModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (activityStateNodeID != null) {
            qPos.add(activityStateNodeID.intValue());
        }

        if (bindActivityID) {
            if (activityID != null) {
                qPos.add(activityID);
            }
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(lfActivityState);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<LFActivityState> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the l f activity states where activityStateNodeID = any &#63; and activityID = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param activityStateNodeIDs the activity state node i ds
     * @param activityID the activity i d
     * @return the matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActivityStateNodeIDActivityID(
        Integer[] activityStateNodeIDs, String activityID)
        throws SystemException {
        return findByActivityStateNodeIDActivityID(activityStateNodeIDs,
            activityID, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the l f activity states where activityStateNodeID = any &#63; and activityID = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param activityStateNodeIDs the activity state node i ds
     * @param activityID the activity i d
     * @param start the lower bound of the range of l f activity states
     * @param end the upper bound of the range of l f activity states (not inclusive)
     * @return the range of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActivityStateNodeIDActivityID(
        Integer[] activityStateNodeIDs, String activityID, int start, int end)
        throws SystemException {
        return findByActivityStateNodeIDActivityID(activityStateNodeIDs,
            activityID, start, end, null);
    }

    /**
     * Returns an ordered range of all the l f activity states where activityStateNodeID = any &#63; and activityID = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param activityStateNodeIDs the activity state node i ds
     * @param activityID the activity i d
     * @param start the lower bound of the range of l f activity states
     * @param end the upper bound of the range of l f activity states (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActivityStateNodeIDActivityID(
        Integer[] activityStateNodeIDs, String activityID, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        if ((activityStateNodeIDs != null) &&
                (activityStateNodeIDs.length == 1)) {
            return findByActivityStateNodeIDActivityID(activityStateNodeIDs[0],
                activityID, start, end, orderByComparator);
        }

        boolean pagination = true;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderArgs = new Object[] {
                    StringUtil.merge(activityStateNodeIDs), activityID
                };
        } else {
            finderArgs = new Object[] {
                    StringUtil.merge(activityStateNodeIDs), activityID,
                    
                    start, end, orderByComparator
                };
        }

        List<LFActivityState> list = (List<LFActivityState>) FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITYSTATENODEIDACTIVITYID,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (LFActivityState lfActivityState : list) {
                if (!ArrayUtil.contains(activityStateNodeIDs,
                            lfActivityState.getActivityStateNodeID()) ||
                        !Validator.equals(activityID,
                            lfActivityState.getActivityID())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = new StringBundler();

            query.append(_SQL_SELECT_LFACTIVITYSTATE_WHERE);

            boolean conjunctionable = false;

            if ((activityStateNodeIDs != null) &&
                    (activityStateNodeIDs.length > 0)) {
                if (conjunctionable) {
                    query.append(WHERE_AND);
                }

                query.append(StringPool.OPEN_PARENTHESIS);

                for (int i = 0; i < activityStateNodeIDs.length; i++) {
                    if (activityStateNodeIDs[i] == null) {
                        query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_NULL);
                    } else {
                        query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_5);
                    }

                    if ((i + 1) < activityStateNodeIDs.length) {
                        query.append(WHERE_OR);
                    }
                }

                query.append(StringPool.CLOSE_PARENTHESIS);

                conjunctionable = true;
            }

            if (conjunctionable) {
                query.append(WHERE_AND);
            }

            if (activityID == null) {
                query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_4);
            } else {
                if (activityID.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_6);
                } else {
                    query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_5);
                }
            }

            conjunctionable = true;

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(LFActivityStateModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (activityStateNodeIDs != null) {
                    for (Integer activityStateNodeID : activityStateNodeIDs) {
                        if (activityStateNodeID != null) {
                            qPos.add(activityStateNodeID);
                        }
                    }
                }

                if (activityID != null) {
                    qPos.add(activityID);
                }

                if (!pagination) {
                    list = (List<LFActivityState>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LFActivityState>(list);
                } else {
                    list = (List<LFActivityState>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITYSTATENODEIDACTIVITYID,
                    finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITYSTATENODEIDACTIVITYID,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the l f activity states where activityStateNodeID = &#63; and activityID = &#63; from the database.
     *
     * @param activityStateNodeID the activity state node i d
     * @param activityID the activity i d
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActivityStateNodeIDActivityID(
        Integer activityStateNodeID, String activityID)
        throws SystemException {
        for (LFActivityState lfActivityState : findByActivityStateNodeIDActivityID(
                activityStateNodeID, activityID, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(lfActivityState);
        }
    }

    /**
     * Returns the number of l f activity states where activityStateNodeID = &#63; and activityID = &#63;.
     *
     * @param activityStateNodeID the activity state node i d
     * @param activityID the activity i d
     * @return the number of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActivityStateNodeIDActivityID(
        Integer activityStateNodeID, String activityID)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVITYSTATENODEIDACTIVITYID;

        Object[] finderArgs = new Object[] { activityStateNodeID, activityID };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_LFACTIVITYSTATE_WHERE);

            if (activityStateNodeID == null) {
                query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_NULL_2);
            } else {
                query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_2);
            }

            boolean bindActivityID = false;

            if (activityID == null) {
                query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_1);
            } else if (activityID.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_3);
            } else {
                bindActivityID = true;

                if (activityID.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_3);
                } else {
                    query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (activityStateNodeID != null) {
                    qPos.add(activityStateNodeID.intValue());
                }

                if (bindActivityID) {
                    if (activityID != null) {
                        qPos.add(activityID);
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
     * Returns the number of l f activity states where activityStateNodeID = any &#63; and activityID = &#63;.
     *
     * @param activityStateNodeIDs the activity state node i ds
     * @param activityID the activity i d
     * @return the number of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActivityStateNodeIDActivityID(
        Integer[] activityStateNodeIDs, String activityID)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                StringUtil.merge(activityStateNodeIDs), activityID
            };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ACTIVITYSTATENODEIDACTIVITYID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler();

            query.append(_SQL_COUNT_LFACTIVITYSTATE_WHERE);

            boolean conjunctionable = false;

            if ((activityStateNodeIDs != null) &&
                    (activityStateNodeIDs.length > 0)) {
                if (conjunctionable) {
                    query.append(WHERE_AND);
                }

                query.append(StringPool.OPEN_PARENTHESIS);

                for (int i = 0; i < activityStateNodeIDs.length; i++) {
                    if (activityStateNodeIDs[i] == null) {
                        query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_NULL);
                    } else {
                        query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYSTATENODEID_5);
                    }

                    if ((i + 1) < activityStateNodeIDs.length) {
                        query.append(WHERE_OR);
                    }
                }

                query.append(StringPool.CLOSE_PARENTHESIS);

                conjunctionable = true;
            }

            if (conjunctionable) {
                query.append(WHERE_AND);
            }

            if (activityID == null) {
                query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_4);
            } else {
                if (activityID.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_6);
                } else {
                    query.append(_FINDER_COLUMN_ACTIVITYSTATENODEIDACTIVITYID_ACTIVITYID_5);
                }
            }

            conjunctionable = true;

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (activityStateNodeIDs != null) {
                    for (Integer activityStateNodeID : activityStateNodeIDs) {
                        if (activityStateNodeID != null) {
                            qPos.add(activityStateNodeID);
                        }
                    }
                }

                if (activityID != null) {
                    qPos.add(activityID);
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ACTIVITYSTATENODEIDACTIVITYID,
                    finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ACTIVITYSTATENODEIDACTIVITYID,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the l f activity states where activityID = &#63; and activityStateNodeID = &#63; and activityStateTreeID = &#63;.
     *
     * @param activityID the activity i d
     * @param activityStateNodeID the activity state node i d
     * @param activityStateTreeID the activity state tree i d
     * @return the matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActIDActStateNodeActStateTree(
        String activityID, Integer activityStateNodeID,
        Integer activityStateTreeID) throws SystemException {
        return findByActIDActStateNodeActStateTree(activityID,
            activityStateNodeID, activityStateTreeID, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the l f activity states where activityID = &#63; and activityStateNodeID = &#63; and activityStateTreeID = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param activityID the activity i d
     * @param activityStateNodeID the activity state node i d
     * @param activityStateTreeID the activity state tree i d
     * @param start the lower bound of the range of l f activity states
     * @param end the upper bound of the range of l f activity states (not inclusive)
     * @return the range of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActIDActStateNodeActStateTree(
        String activityID, Integer activityStateNodeID,
        Integer activityStateTreeID, int start, int end)
        throws SystemException {
        return findByActIDActStateNodeActStateTree(activityID,
            activityStateNodeID, activityStateTreeID, start, end, null);
    }

    /**
     * Returns an ordered range of all the l f activity states where activityID = &#63; and activityStateNodeID = &#63; and activityStateTreeID = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param activityID the activity i d
     * @param activityStateNodeID the activity state node i d
     * @param activityStateTreeID the activity state tree i d
     * @param start the lower bound of the range of l f activity states
     * @param end the upper bound of the range of l f activity states (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActIDActStateNodeActStateTree(
        String activityID, Integer activityStateNodeID,
        Integer activityStateTreeID, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDACTSTATENODEACTSTATETREE;
            finderArgs = new Object[] {
                    activityID, activityStateNodeID, activityStateTreeID
                };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIDACTSTATENODEACTSTATETREE;
            finderArgs = new Object[] {
                    activityID, activityStateNodeID, activityStateTreeID,
                    
                    start, end, orderByComparator
                };
        }

        List<LFActivityState> list = (List<LFActivityState>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (LFActivityState lfActivityState : list) {
                if (!Validator.equals(activityID,
                            lfActivityState.getActivityID()) ||
                        !Validator.equals(activityStateNodeID,
                            lfActivityState.getActivityStateNodeID()) ||
                        !Validator.equals(activityStateTreeID,
                            lfActivityState.getActivityStateTreeID())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(5 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(5);
            }

            query.append(_SQL_SELECT_LFACTIVITYSTATE_WHERE);

            boolean bindActivityID = false;

            if (activityID == null) {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_1);
            } else if (activityID.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_3);
            } else {
                bindActivityID = true;

                if (activityID.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_3);
                } else {
                    query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_2);
                }
            }

            if (activityStateNodeID == null) {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_NULL_2);
            } else {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_2);
            }

            if (activityStateTreeID == null) {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_NULL_2);
            } else {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(LFActivityStateModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindActivityID) {
                    if (activityID != null) {
                        qPos.add(activityID);
                    }
                }

                if (activityStateNodeID != null) {
                    qPos.add(activityStateNodeID.intValue());
                }

                if (activityStateTreeID != null) {
                    qPos.add(activityStateTreeID.intValue());
                }

                if (!pagination) {
                    list = (List<LFActivityState>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LFActivityState>(list);
                } else {
                    list = (List<LFActivityState>) QueryUtil.list(q,
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
     * Returns the first l f activity state in the ordered set where activityID = &#63; and activityStateNodeID = &#63; and activityStateTreeID = &#63;.
     *
     * @param activityID the activity i d
     * @param activityStateNodeID the activity state node i d
     * @param activityStateTreeID the activity state tree i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching l f activity state
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFActivityStateException if a matching l f activity state could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState findByActIDActStateNodeActStateTree_First(
        String activityID, Integer activityStateNodeID,
        Integer activityStateTreeID, OrderByComparator orderByComparator)
        throws NoSuchLFActivityStateException, SystemException {
        LFActivityState lfActivityState = fetchByActIDActStateNodeActStateTree_First(activityID,
                activityStateNodeID, activityStateTreeID, orderByComparator);

        if (lfActivityState != null) {
            return lfActivityState;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("activityID=");
        msg.append(activityID);

        msg.append(", activityStateNodeID=");
        msg.append(activityStateNodeID);

        msg.append(", activityStateTreeID=");
        msg.append(activityStateTreeID);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLFActivityStateException(msg.toString());
    }

    /**
     * Returns the first l f activity state in the ordered set where activityID = &#63; and activityStateNodeID = &#63; and activityStateTreeID = &#63;.
     *
     * @param activityID the activity i d
     * @param activityStateNodeID the activity state node i d
     * @param activityStateTreeID the activity state tree i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching l f activity state, or <code>null</code> if a matching l f activity state could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState fetchByActIDActStateNodeActStateTree_First(
        String activityID, Integer activityStateNodeID,
        Integer activityStateTreeID, OrderByComparator orderByComparator)
        throws SystemException {
        List<LFActivityState> list = findByActIDActStateNodeActStateTree(activityID,
                activityStateNodeID, activityStateTreeID, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last l f activity state in the ordered set where activityID = &#63; and activityStateNodeID = &#63; and activityStateTreeID = &#63;.
     *
     * @param activityID the activity i d
     * @param activityStateNodeID the activity state node i d
     * @param activityStateTreeID the activity state tree i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching l f activity state
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFActivityStateException if a matching l f activity state could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState findByActIDActStateNodeActStateTree_Last(
        String activityID, Integer activityStateNodeID,
        Integer activityStateTreeID, OrderByComparator orderByComparator)
        throws NoSuchLFActivityStateException, SystemException {
        LFActivityState lfActivityState = fetchByActIDActStateNodeActStateTree_Last(activityID,
                activityStateNodeID, activityStateTreeID, orderByComparator);

        if (lfActivityState != null) {
            return lfActivityState;
        }

        StringBundler msg = new StringBundler(8);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("activityID=");
        msg.append(activityID);

        msg.append(", activityStateNodeID=");
        msg.append(activityStateNodeID);

        msg.append(", activityStateTreeID=");
        msg.append(activityStateTreeID);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLFActivityStateException(msg.toString());
    }

    /**
     * Returns the last l f activity state in the ordered set where activityID = &#63; and activityStateNodeID = &#63; and activityStateTreeID = &#63;.
     *
     * @param activityID the activity i d
     * @param activityStateNodeID the activity state node i d
     * @param activityStateTreeID the activity state tree i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching l f activity state, or <code>null</code> if a matching l f activity state could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState fetchByActIDActStateNodeActStateTree_Last(
        String activityID, Integer activityStateNodeID,
        Integer activityStateTreeID, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByActIDActStateNodeActStateTree(activityID,
                activityStateNodeID, activityStateTreeID);

        if (count == 0) {
            return null;
        }

        List<LFActivityState> list = findByActIDActStateNodeActStateTree(activityID,
                activityStateNodeID, activityStateTreeID, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the l f activity states before and after the current l f activity state in the ordered set where activityID = &#63; and activityStateNodeID = &#63; and activityStateTreeID = &#63;.
     *
     * @param id the primary key of the current l f activity state
     * @param activityID the activity i d
     * @param activityStateNodeID the activity state node i d
     * @param activityStateTreeID the activity state tree i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next l f activity state
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFActivityStateException if a l f activity state with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState[] findByActIDActStateNodeActStateTree_PrevAndNext(
        long id, String activityID, Integer activityStateNodeID,
        Integer activityStateTreeID, OrderByComparator orderByComparator)
        throws NoSuchLFActivityStateException, SystemException {
        LFActivityState lfActivityState = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            LFActivityState[] array = new LFActivityStateImpl[3];

            array[0] = getByActIDActStateNodeActStateTree_PrevAndNext(session,
                    lfActivityState, activityID, activityStateNodeID,
                    activityStateTreeID, orderByComparator, true);

            array[1] = lfActivityState;

            array[2] = getByActIDActStateNodeActStateTree_PrevAndNext(session,
                    lfActivityState, activityID, activityStateNodeID,
                    activityStateTreeID, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected LFActivityState getByActIDActStateNodeActStateTree_PrevAndNext(
        Session session, LFActivityState lfActivityState, String activityID,
        Integer activityStateNodeID, Integer activityStateTreeID,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_LFACTIVITYSTATE_WHERE);

        boolean bindActivityID = false;

        if (activityID == null) {
            query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_1);
        } else if (activityID.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_3);
        } else {
            bindActivityID = true;

            if (activityID.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_3);
            } else {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_2);
            }
        }

        if (activityStateNodeID == null) {
            query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_NULL_2);
        } else {
            query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_2);
        }

        if (activityStateTreeID == null) {
            query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_NULL_2);
        } else {
            query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_2);
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
            query.append(LFActivityStateModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindActivityID) {
            if (activityID != null) {
                qPos.add(activityID);
            }
        }

        if (activityStateNodeID != null) {
            qPos.add(activityStateNodeID.intValue());
        }

        if (activityStateTreeID != null) {
            qPos.add(activityStateTreeID.intValue());
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(lfActivityState);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<LFActivityState> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the l f activity states where activityID = &#63; and activityStateNodeID = any &#63; and activityStateTreeID = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param activityID the activity i d
     * @param activityStateNodeIDs the activity state node i ds
     * @param activityStateTreeID the activity state tree i d
     * @return the matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActIDActStateNodeActStateTree(
        String activityID, Integer[] activityStateNodeIDs,
        Integer activityStateTreeID) throws SystemException {
        return findByActIDActStateNodeActStateTree(activityID,
            activityStateNodeIDs, activityStateTreeID, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the l f activity states where activityID = &#63; and activityStateNodeID = any &#63; and activityStateTreeID = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param activityID the activity i d
     * @param activityStateNodeIDs the activity state node i ds
     * @param activityStateTreeID the activity state tree i d
     * @param start the lower bound of the range of l f activity states
     * @param end the upper bound of the range of l f activity states (not inclusive)
     * @return the range of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActIDActStateNodeActStateTree(
        String activityID, Integer[] activityStateNodeIDs,
        Integer activityStateTreeID, int start, int end)
        throws SystemException {
        return findByActIDActStateNodeActStateTree(activityID,
            activityStateNodeIDs, activityStateTreeID, start, end, null);
    }

    /**
     * Returns an ordered range of all the l f activity states where activityID = &#63; and activityStateNodeID = any &#63; and activityStateTreeID = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param activityID the activity i d
     * @param activityStateNodeIDs the activity state node i ds
     * @param activityStateTreeID the activity state tree i d
     * @param start the lower bound of the range of l f activity states
     * @param end the upper bound of the range of l f activity states (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActIDActStateNodeActStateTree(
        String activityID, Integer[] activityStateNodeIDs,
        Integer activityStateTreeID, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        if ((activityStateNodeIDs != null) &&
                (activityStateNodeIDs.length == 1)) {
            return findByActIDActStateNodeActStateTree(activityID,
                activityStateNodeIDs[0], activityStateTreeID, start, end,
                orderByComparator);
        }

        boolean pagination = true;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderArgs = new Object[] {
                    activityID, StringUtil.merge(activityStateNodeIDs),
                    activityStateTreeID
                };
        } else {
            finderArgs = new Object[] {
                    activityID, StringUtil.merge(activityStateNodeIDs),
                    activityStateTreeID,
                    
                    start, end, orderByComparator
                };
        }

        List<LFActivityState> list = (List<LFActivityState>) FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIDACTSTATENODEACTSTATETREE,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (LFActivityState lfActivityState : list) {
                if (!Validator.equals(activityID,
                            lfActivityState.getActivityID()) ||
                        !ArrayUtil.contains(activityStateNodeIDs,
                            lfActivityState.getActivityStateNodeID()) ||
                        !Validator.equals(activityStateTreeID,
                            lfActivityState.getActivityStateTreeID())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = new StringBundler();

            query.append(_SQL_SELECT_LFACTIVITYSTATE_WHERE);

            boolean conjunctionable = false;

            if (conjunctionable) {
                query.append(WHERE_AND);
            }

            if (activityID == null) {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_4);
            } else {
                if (activityID.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_6);
                } else {
                    query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_5);
                }
            }

            conjunctionable = true;

            if ((activityStateNodeIDs != null) &&
                    (activityStateNodeIDs.length > 0)) {
                if (conjunctionable) {
                    query.append(WHERE_AND);
                }

                query.append(StringPool.OPEN_PARENTHESIS);

                for (int i = 0; i < activityStateNodeIDs.length; i++) {
                    if (activityStateNodeIDs[i] == null) {
                        query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_NULL);
                    } else {
                        query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_5);
                    }

                    if ((i + 1) < activityStateNodeIDs.length) {
                        query.append(WHERE_OR);
                    }
                }

                query.append(StringPool.CLOSE_PARENTHESIS);

                conjunctionable = true;
            }

            if (conjunctionable) {
                query.append(WHERE_AND);
            }

            if (activityStateTreeID == null) {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_NULL);
            } else {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_5);
            }

            conjunctionable = true;

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(LFActivityStateModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (activityID != null) {
                    qPos.add(activityID);
                }

                if (activityStateNodeIDs != null) {
                    for (Integer activityStateNodeID : activityStateNodeIDs) {
                        if (activityStateNodeID != null) {
                            qPos.add(activityStateNodeID);
                        }
                    }
                }

                if (activityStateTreeID != null) {
                    qPos.add(activityStateTreeID.intValue());
                }

                if (!pagination) {
                    list = (List<LFActivityState>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LFActivityState>(list);
                } else {
                    list = (List<LFActivityState>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIDACTSTATENODEACTSTATETREE,
                    finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIDACTSTATENODEACTSTATETREE,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the l f activity states where activityID = &#63; and activityStateNodeID = &#63; and activityStateTreeID = &#63; from the database.
     *
     * @param activityID the activity i d
     * @param activityStateNodeID the activity state node i d
     * @param activityStateTreeID the activity state tree i d
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActIDActStateNodeActStateTree(String activityID,
        Integer activityStateNodeID, Integer activityStateTreeID)
        throws SystemException {
        for (LFActivityState lfActivityState : findByActIDActStateNodeActStateTree(
                activityID, activityStateNodeID, activityStateTreeID,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(lfActivityState);
        }
    }

    /**
     * Returns the number of l f activity states where activityID = &#63; and activityStateNodeID = &#63; and activityStateTreeID = &#63;.
     *
     * @param activityID the activity i d
     * @param activityStateNodeID the activity state node i d
     * @param activityStateTreeID the activity state tree i d
     * @return the number of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActIDActStateNodeActStateTree(String activityID,
        Integer activityStateNodeID, Integer activityStateTreeID)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIDACTSTATENODEACTSTATETREE;

        Object[] finderArgs = new Object[] {
                activityID, activityStateNodeID, activityStateTreeID
            };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_COUNT_LFACTIVITYSTATE_WHERE);

            boolean bindActivityID = false;

            if (activityID == null) {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_1);
            } else if (activityID.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_3);
            } else {
                bindActivityID = true;

                if (activityID.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_3);
                } else {
                    query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_2);
                }
            }

            if (activityStateNodeID == null) {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_NULL_2);
            } else {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_2);
            }

            if (activityStateTreeID == null) {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_NULL_2);
            } else {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindActivityID) {
                    if (activityID != null) {
                        qPos.add(activityID);
                    }
                }

                if (activityStateNodeID != null) {
                    qPos.add(activityStateNodeID.intValue());
                }

                if (activityStateTreeID != null) {
                    qPos.add(activityStateTreeID.intValue());
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
     * Returns the number of l f activity states where activityID = &#63; and activityStateNodeID = any &#63; and activityStateTreeID = &#63;.
     *
     * @param activityID the activity i d
     * @param activityStateNodeIDs the activity state node i ds
     * @param activityStateTreeID the activity state tree i d
     * @return the number of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActIDActStateNodeActStateTree(String activityID,
        Integer[] activityStateNodeIDs, Integer activityStateTreeID)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                activityID, StringUtil.merge(activityStateNodeIDs),
                activityStateTreeID
            };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ACTIDACTSTATENODEACTSTATETREE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler();

            query.append(_SQL_COUNT_LFACTIVITYSTATE_WHERE);

            boolean conjunctionable = false;

            if (conjunctionable) {
                query.append(WHERE_AND);
            }

            if (activityID == null) {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_4);
            } else {
                if (activityID.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_6);
                } else {
                    query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYID_5);
                }
            }

            conjunctionable = true;

            if ((activityStateNodeIDs != null) &&
                    (activityStateNodeIDs.length > 0)) {
                if (conjunctionable) {
                    query.append(WHERE_AND);
                }

                query.append(StringPool.OPEN_PARENTHESIS);

                for (int i = 0; i < activityStateNodeIDs.length; i++) {
                    if (activityStateNodeIDs[i] == null) {
                        query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_NULL);
                    } else {
                        query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATENODEID_5);
                    }

                    if ((i + 1) < activityStateNodeIDs.length) {
                        query.append(WHERE_OR);
                    }
                }

                query.append(StringPool.CLOSE_PARENTHESIS);

                conjunctionable = true;
            }

            if (conjunctionable) {
                query.append(WHERE_AND);
            }

            if (activityStateTreeID == null) {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_NULL);
            } else {
                query.append(_FINDER_COLUMN_ACTIDACTSTATENODEACTSTATETREE_ACTIVITYSTATETREEID_5);
            }

            conjunctionable = true;

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (activityID != null) {
                    qPos.add(activityID);
                }

                if (activityStateNodeIDs != null) {
                    for (Integer activityStateNodeID : activityStateNodeIDs) {
                        if (activityStateNodeID != null) {
                            qPos.add(activityStateNodeID);
                        }
                    }
                }

                if (activityStateTreeID != null) {
                    qPos.add(activityStateTreeID.intValue());
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ACTIDACTSTATENODEACTSTATETREE,
                    finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ACTIDACTSTATENODEACTSTATETREE,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the l f activity states where activityStateNodeID = &#63;.
     *
     * @param activityStateNodeID the activity state node i d
     * @return the matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActivityStateNodeID(
        Integer activityStateNodeID) throws SystemException {
        return findByActivityStateNodeID(activityStateNodeID,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the l f activity states where activityStateNodeID = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param activityStateNodeID the activity state node i d
     * @param start the lower bound of the range of l f activity states
     * @param end the upper bound of the range of l f activity states (not inclusive)
     * @return the range of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActivityStateNodeID(
        Integer activityStateNodeID, int start, int end)
        throws SystemException {
        return findByActivityStateNodeID(activityStateNodeID, start, end, null);
    }

    /**
     * Returns an ordered range of all the l f activity states where activityStateNodeID = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param activityStateNodeID the activity state node i d
     * @param start the lower bound of the range of l f activity states
     * @param end the upper bound of the range of l f activity states (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActivityStateNodeID(
        Integer activityStateNodeID, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYSTATENODEID;
            finderArgs = new Object[] { activityStateNodeID };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITYSTATENODEID;
            finderArgs = new Object[] {
                    activityStateNodeID,
                    
                    start, end, orderByComparator
                };
        }

        List<LFActivityState> list = (List<LFActivityState>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (LFActivityState lfActivityState : list) {
                if (!Validator.equals(activityStateNodeID,
                            lfActivityState.getActivityStateNodeID())) {
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

            query.append(_SQL_SELECT_LFACTIVITYSTATE_WHERE);

            if (activityStateNodeID == null) {
                query.append(_FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_NULL_2);
            } else {
                query.append(_FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(LFActivityStateModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (activityStateNodeID != null) {
                    qPos.add(activityStateNodeID.intValue());
                }

                if (!pagination) {
                    list = (List<LFActivityState>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LFActivityState>(list);
                } else {
                    list = (List<LFActivityState>) QueryUtil.list(q,
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
     * Returns the first l f activity state in the ordered set where activityStateNodeID = &#63;.
     *
     * @param activityStateNodeID the activity state node i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching l f activity state
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFActivityStateException if a matching l f activity state could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState findByActivityStateNodeID_First(
        Integer activityStateNodeID, OrderByComparator orderByComparator)
        throws NoSuchLFActivityStateException, SystemException {
        LFActivityState lfActivityState = fetchByActivityStateNodeID_First(activityStateNodeID,
                orderByComparator);

        if (lfActivityState != null) {
            return lfActivityState;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("activityStateNodeID=");
        msg.append(activityStateNodeID);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLFActivityStateException(msg.toString());
    }

    /**
     * Returns the first l f activity state in the ordered set where activityStateNodeID = &#63;.
     *
     * @param activityStateNodeID the activity state node i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching l f activity state, or <code>null</code> if a matching l f activity state could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState fetchByActivityStateNodeID_First(
        Integer activityStateNodeID, OrderByComparator orderByComparator)
        throws SystemException {
        List<LFActivityState> list = findByActivityStateNodeID(activityStateNodeID,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last l f activity state in the ordered set where activityStateNodeID = &#63;.
     *
     * @param activityStateNodeID the activity state node i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching l f activity state
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFActivityStateException if a matching l f activity state could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState findByActivityStateNodeID_Last(
        Integer activityStateNodeID, OrderByComparator orderByComparator)
        throws NoSuchLFActivityStateException, SystemException {
        LFActivityState lfActivityState = fetchByActivityStateNodeID_Last(activityStateNodeID,
                orderByComparator);

        if (lfActivityState != null) {
            return lfActivityState;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("activityStateNodeID=");
        msg.append(activityStateNodeID);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchLFActivityStateException(msg.toString());
    }

    /**
     * Returns the last l f activity state in the ordered set where activityStateNodeID = &#63;.
     *
     * @param activityStateNodeID the activity state node i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching l f activity state, or <code>null</code> if a matching l f activity state could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState fetchByActivityStateNodeID_Last(
        Integer activityStateNodeID, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByActivityStateNodeID(activityStateNodeID);

        if (count == 0) {
            return null;
        }

        List<LFActivityState> list = findByActivityStateNodeID(activityStateNodeID,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the l f activity states before and after the current l f activity state in the ordered set where activityStateNodeID = &#63;.
     *
     * @param id the primary key of the current l f activity state
     * @param activityStateNodeID the activity state node i d
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next l f activity state
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFActivityStateException if a l f activity state with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState[] findByActivityStateNodeID_PrevAndNext(long id,
        Integer activityStateNodeID, OrderByComparator orderByComparator)
        throws NoSuchLFActivityStateException, SystemException {
        LFActivityState lfActivityState = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            LFActivityState[] array = new LFActivityStateImpl[3];

            array[0] = getByActivityStateNodeID_PrevAndNext(session,
                    lfActivityState, activityStateNodeID, orderByComparator,
                    true);

            array[1] = lfActivityState;

            array[2] = getByActivityStateNodeID_PrevAndNext(session,
                    lfActivityState, activityStateNodeID, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected LFActivityState getByActivityStateNodeID_PrevAndNext(
        Session session, LFActivityState lfActivityState,
        Integer activityStateNodeID, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_LFACTIVITYSTATE_WHERE);

        if (activityStateNodeID == null) {
            query.append(_FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_NULL_2);
        } else {
            query.append(_FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_2);
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
            query.append(LFActivityStateModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (activityStateNodeID != null) {
            qPos.add(activityStateNodeID.intValue());
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(lfActivityState);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<LFActivityState> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the l f activity states where activityStateNodeID = any &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param activityStateNodeIDs the activity state node i ds
     * @return the matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActivityStateNodeID(
        Integer[] activityStateNodeIDs) throws SystemException {
        return findByActivityStateNodeID(activityStateNodeIDs,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the l f activity states where activityStateNodeID = any &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param activityStateNodeIDs the activity state node i ds
     * @param start the lower bound of the range of l f activity states
     * @param end the upper bound of the range of l f activity states (not inclusive)
     * @return the range of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActivityStateNodeID(
        Integer[] activityStateNodeIDs, int start, int end)
        throws SystemException {
        return findByActivityStateNodeID(activityStateNodeIDs, start, end, null);
    }

    /**
     * Returns an ordered range of all the l f activity states where activityStateNodeID = any &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param activityStateNodeIDs the activity state node i ds
     * @param start the lower bound of the range of l f activity states
     * @param end the upper bound of the range of l f activity states (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findByActivityStateNodeID(
        Integer[] activityStateNodeIDs, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        if ((activityStateNodeIDs != null) &&
                (activityStateNodeIDs.length == 1)) {
            return findByActivityStateNodeID(activityStateNodeIDs[0], start,
                end, orderByComparator);
        }

        boolean pagination = true;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderArgs = new Object[] { StringUtil.merge(activityStateNodeIDs) };
        } else {
            finderArgs = new Object[] {
                    StringUtil.merge(activityStateNodeIDs),
                    
                    start, end, orderByComparator
                };
        }

        List<LFActivityState> list = (List<LFActivityState>) FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITYSTATENODEID,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (LFActivityState lfActivityState : list) {
                if (!ArrayUtil.contains(activityStateNodeIDs,
                            lfActivityState.getActivityStateNodeID())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = new StringBundler();

            query.append(_SQL_SELECT_LFACTIVITYSTATE_WHERE);

            boolean conjunctionable = false;

            if ((activityStateNodeIDs != null) &&
                    (activityStateNodeIDs.length > 0)) {
                if (conjunctionable) {
                    query.append(WHERE_AND);
                }

                query.append(StringPool.OPEN_PARENTHESIS);

                for (int i = 0; i < activityStateNodeIDs.length; i++) {
                    if (activityStateNodeIDs[i] == null) {
                        query.append(_FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_NULL);
                    } else {
                        query.append(_FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_5);
                    }

                    if ((i + 1) < activityStateNodeIDs.length) {
                        query.append(WHERE_OR);
                    }
                }

                query.append(StringPool.CLOSE_PARENTHESIS);

                conjunctionable = true;
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(LFActivityStateModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (activityStateNodeIDs != null) {
                    for (Integer activityStateNodeID : activityStateNodeIDs) {
                        if (activityStateNodeID != null) {
                            qPos.add(activityStateNodeID);
                        }
                    }
                }

                if (!pagination) {
                    list = (List<LFActivityState>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LFActivityState>(list);
                } else {
                    list = (List<LFActivityState>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITYSTATENODEID,
                    finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVITYSTATENODEID,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the l f activity states where activityStateNodeID = &#63; from the database.
     *
     * @param activityStateNodeID the activity state node i d
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByActivityStateNodeID(Integer activityStateNodeID)
        throws SystemException {
        for (LFActivityState lfActivityState : findByActivityStateNodeID(
                activityStateNodeID, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(lfActivityState);
        }
    }

    /**
     * Returns the number of l f activity states where activityStateNodeID = &#63;.
     *
     * @param activityStateNodeID the activity state node i d
     * @return the number of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActivityStateNodeID(Integer activityStateNodeID)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ACTIVITYSTATENODEID;

        Object[] finderArgs = new Object[] { activityStateNodeID };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_LFACTIVITYSTATE_WHERE);

            if (activityStateNodeID == null) {
                query.append(_FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_NULL_2);
            } else {
                query.append(_FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (activityStateNodeID != null) {
                    qPos.add(activityStateNodeID.intValue());
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
     * Returns the number of l f activity states where activityStateNodeID = any &#63;.
     *
     * @param activityStateNodeIDs the activity state node i ds
     * @return the number of matching l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByActivityStateNodeID(Integer[] activityStateNodeIDs)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                StringUtil.merge(activityStateNodeIDs)
            };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ACTIVITYSTATENODEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler();

            query.append(_SQL_COUNT_LFACTIVITYSTATE_WHERE);

            boolean conjunctionable = false;

            if ((activityStateNodeIDs != null) &&
                    (activityStateNodeIDs.length > 0)) {
                if (conjunctionable) {
                    query.append(WHERE_AND);
                }

                query.append(StringPool.OPEN_PARENTHESIS);

                for (int i = 0; i < activityStateNodeIDs.length; i++) {
                    if (activityStateNodeIDs[i] == null) {
                        query.append(_FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_NULL);
                    } else {
                        query.append(_FINDER_COLUMN_ACTIVITYSTATENODEID_ACTIVITYSTATENODEID_5);
                    }

                    if ((i + 1) < activityStateNodeIDs.length) {
                        query.append(WHERE_OR);
                    }
                }

                query.append(StringPool.CLOSE_PARENTHESIS);

                conjunctionable = true;
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (activityStateNodeIDs != null) {
                    for (Integer activityStateNodeID : activityStateNodeIDs) {
                        if (activityStateNodeID != null) {
                            qPos.add(activityStateNodeID);
                        }
                    }
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ACTIVITYSTATENODEID,
                    finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_ACTIVITYSTATENODEID,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Caches the l f activity state in the entity cache if it is enabled.
     *
     * @param lfActivityState the l f activity state
     */
    @Override
    public void cacheResult(LFActivityState lfActivityState) {
        EntityCacheUtil.putResult(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateImpl.class, lfActivityState.getPrimaryKey(),
            lfActivityState);

        lfActivityState.resetOriginalValues();
    }

    /**
     * Caches the l f activity states in the entity cache if it is enabled.
     *
     * @param lfActivityStates the l f activity states
     */
    @Override
    public void cacheResult(List<LFActivityState> lfActivityStates) {
        for (LFActivityState lfActivityState : lfActivityStates) {
            if (EntityCacheUtil.getResult(
                        LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
                        LFActivityStateImpl.class,
                        lfActivityState.getPrimaryKey()) == null) {
                cacheResult(lfActivityState);
            } else {
                lfActivityState.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all l f activity states.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(LFActivityStateImpl.class.getName());
        }

        EntityCacheUtil.clearCache(LFActivityStateImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the l f activity state.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(LFActivityState lfActivityState) {
        EntityCacheUtil.removeResult(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateImpl.class, lfActivityState.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<LFActivityState> lfActivityStates) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (LFActivityState lfActivityState : lfActivityStates) {
            EntityCacheUtil.removeResult(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
                LFActivityStateImpl.class, lfActivityState.getPrimaryKey());
        }
    }

    /**
     * Creates a new l f activity state with the primary key. Does not add the l f activity state to the database.
     *
     * @param id the primary key for the new l f activity state
     * @return the new l f activity state
     */
    @Override
    public LFActivityState create(long id) {
        LFActivityState lfActivityState = new LFActivityStateImpl();

        lfActivityState.setNew(true);
        lfActivityState.setPrimaryKey(id);

        return lfActivityState;
    }

    /**
     * Removes the l f activity state with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the l f activity state
     * @return the l f activity state that was removed
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFActivityStateException if a l f activity state with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState remove(long id)
        throws NoSuchLFActivityStateException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the l f activity state with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the l f activity state
     * @return the l f activity state that was removed
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFActivityStateException if a l f activity state with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState remove(Serializable primaryKey)
        throws NoSuchLFActivityStateException, SystemException {
        Session session = null;

        try {
            session = openSession();

            LFActivityState lfActivityState = (LFActivityState) session.get(LFActivityStateImpl.class,
                    primaryKey);

            if (lfActivityState == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchLFActivityStateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(lfActivityState);
        } catch (NoSuchLFActivityStateException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected LFActivityState removeImpl(LFActivityState lfActivityState)
        throws SystemException {
        lfActivityState = toUnwrappedModel(lfActivityState);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(lfActivityState)) {
                lfActivityState = (LFActivityState) session.get(LFActivityStateImpl.class,
                        lfActivityState.getPrimaryKeyObj());
            }

            if (lfActivityState != null) {
                session.delete(lfActivityState);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (lfActivityState != null) {
            clearCache(lfActivityState);
        }

        return lfActivityState;
    }

    @Override
    public LFActivityState updateImpl(
        com.arcusys.learn.persistence.liferay.model.LFActivityState lfActivityState)
        throws SystemException {
        lfActivityState = toUnwrappedModel(lfActivityState);

        boolean isNew = lfActivityState.isNew();

        LFActivityStateModelImpl lfActivityStateModelImpl = (LFActivityStateModelImpl) lfActivityState;

        Session session = null;

        try {
            session = openSession();

            if (lfActivityState.isNew()) {
                session.save(lfActivityState);

                lfActivityState.setNew(false);
            } else {
                session.merge(lfActivityState);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !LFActivityStateModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((lfActivityStateModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYSTATENODEIDACTIVITYID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        lfActivityStateModelImpl.getOriginalActivityStateNodeID(),
                        lfActivityStateModelImpl.getOriginalActivityID()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVITYSTATENODEIDACTIVITYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYSTATENODEIDACTIVITYID,
                    args);

                args = new Object[] {
                        lfActivityStateModelImpl.getActivityStateNodeID(),
                        lfActivityStateModelImpl.getActivityID()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVITYSTATENODEIDACTIVITYID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYSTATENODEIDACTIVITYID,
                    args);
            }

            if ((lfActivityStateModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDACTSTATENODEACTSTATETREE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        lfActivityStateModelImpl.getOriginalActivityID(),
                        lfActivityStateModelImpl.getOriginalActivityStateNodeID(),
                        lfActivityStateModelImpl.getOriginalActivityStateTreeID()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIDACTSTATENODEACTSTATETREE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDACTSTATENODEACTSTATETREE,
                    args);

                args = new Object[] {
                        lfActivityStateModelImpl.getActivityID(),
                        lfActivityStateModelImpl.getActivityStateNodeID(),
                        lfActivityStateModelImpl.getActivityStateTreeID()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIDACTSTATENODEACTSTATETREE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIDACTSTATENODEACTSTATETREE,
                    args);
            }

            if ((lfActivityStateModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYSTATENODEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        lfActivityStateModelImpl.getOriginalActivityStateNodeID()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVITYSTATENODEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYSTATENODEID,
                    args);

                args = new Object[] {
                        lfActivityStateModelImpl.getActivityStateNodeID()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVITYSTATENODEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVITYSTATENODEID,
                    args);
            }
        }

        EntityCacheUtil.putResult(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
            LFActivityStateImpl.class, lfActivityState.getPrimaryKey(),
            lfActivityState);

        return lfActivityState;
    }

    protected LFActivityState toUnwrappedModel(LFActivityState lfActivityState) {
        if (lfActivityState instanceof LFActivityStateImpl) {
            return lfActivityState;
        }

        LFActivityStateImpl lfActivityStateImpl = new LFActivityStateImpl();

        lfActivityStateImpl.setNew(lfActivityState.isNew());
        lfActivityStateImpl.setPrimaryKey(lfActivityState.getPrimaryKey());

        lfActivityStateImpl.setId(lfActivityState.getId());
        lfActivityStateImpl.setPackageID(lfActivityState.getPackageID());
        lfActivityStateImpl.setActivityID(lfActivityState.getActivityID());
        lfActivityStateImpl.setActive(lfActivityState.getActive());
        lfActivityStateImpl.setSuspended(lfActivityState.getSuspended());
        lfActivityStateImpl.setAttemptCompleted(lfActivityState.getAttemptCompleted());
        lfActivityStateImpl.setAttemptCompletionAmount(lfActivityState.getAttemptCompletionAmount());
        lfActivityStateImpl.setAttemptAbsoluteDuration(lfActivityState.getAttemptAbsoluteDuration());
        lfActivityStateImpl.setAttemptExperiencedDuration(lfActivityState.getAttemptExperiencedDuration());
        lfActivityStateImpl.setActivityAbsoluteDuration(lfActivityState.getActivityAbsoluteDuration());
        lfActivityStateImpl.setActivityExperiencedDuration(lfActivityState.getActivityExperiencedDuration());
        lfActivityStateImpl.setAttemptCount(lfActivityState.getAttemptCount());
        lfActivityStateImpl.setActivityStateNodeID(lfActivityState.getActivityStateNodeID());
        lfActivityStateImpl.setActivityStateTreeID(lfActivityState.getActivityStateTreeID());

        return lfActivityStateImpl;
    }

    /**
     * Returns the l f activity state with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the l f activity state
     * @return the l f activity state
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFActivityStateException if a l f activity state with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState findByPrimaryKey(Serializable primaryKey)
        throws NoSuchLFActivityStateException, SystemException {
        LFActivityState lfActivityState = fetchByPrimaryKey(primaryKey);

        if (lfActivityState == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchLFActivityStateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return lfActivityState;
    }

    /**
     * Returns the l f activity state with the primary key or throws a {@link com.arcusys.learn.persistence.liferay.NoSuchLFActivityStateException} if it could not be found.
     *
     * @param id the primary key of the l f activity state
     * @return the l f activity state
     * @throws com.arcusys.learn.persistence.liferay.NoSuchLFActivityStateException if a l f activity state with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState findByPrimaryKey(long id)
        throws NoSuchLFActivityStateException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the l f activity state with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the l f activity state
     * @return the l f activity state, or <code>null</code> if a l f activity state with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        LFActivityState lfActivityState = (LFActivityState) EntityCacheUtil.getResult(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
                LFActivityStateImpl.class, primaryKey);

        if (lfActivityState == _nullLFActivityState) {
            return null;
        }

        if (lfActivityState == null) {
            Session session = null;

            try {
                session = openSession();

                lfActivityState = (LFActivityState) session.get(LFActivityStateImpl.class,
                        primaryKey);

                if (lfActivityState != null) {
                    cacheResult(lfActivityState);
                } else {
                    EntityCacheUtil.putResult(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
                        LFActivityStateImpl.class, primaryKey,
                        _nullLFActivityState);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(LFActivityStateModelImpl.ENTITY_CACHE_ENABLED,
                    LFActivityStateImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return lfActivityState;
    }

    /**
     * Returns the l f activity state with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the l f activity state
     * @return the l f activity state, or <code>null</code> if a l f activity state with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LFActivityState fetchByPrimaryKey(long id) throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the l f activity states.
     *
     * @return the l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the l f activity states.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of l f activity states
     * @param end the upper bound of the range of l f activity states (not inclusive)
     * @return the range of l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the l f activity states.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.arcusys.learn.persistence.liferay.model.impl.LFActivityStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of l f activity states
     * @param end the upper bound of the range of l f activity states (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of l f activity states
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LFActivityState> findAll(int start, int end,
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

        List<LFActivityState> list = (List<LFActivityState>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_LFACTIVITYSTATE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_LFACTIVITYSTATE;

                if (pagination) {
                    sql = sql.concat(LFActivityStateModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<LFActivityState>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LFActivityState>(list);
                } else {
                    list = (List<LFActivityState>) QueryUtil.list(q,
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
     * Removes all the l f activity states from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (LFActivityState lfActivityState : findAll()) {
            remove(lfActivityState);
        }
    }

    /**
     * Returns the number of l f activity states.
     *
     * @return the number of l f activity states
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

                Query q = session.createQuery(_SQL_COUNT_LFACTIVITYSTATE);

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
     * Initializes the l f activity state persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.arcusys.learn.persistence.liferay.model.LFActivityState")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<LFActivityState>> listenersList = new ArrayList<ModelListener<LFActivityState>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<LFActivityState>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(LFActivityStateImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    private static String _removeConjunction(String sql) {
        int pos = sql.indexOf(" AND ");

        if (pos != -1) {
            sql = sql.substring(0, pos);
        }

        return sql;
    }
}
