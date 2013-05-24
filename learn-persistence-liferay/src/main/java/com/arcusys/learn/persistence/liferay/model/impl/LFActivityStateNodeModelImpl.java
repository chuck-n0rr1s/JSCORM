package com.arcusys.learn.persistence.liferay.model.impl;

import com.arcusys.learn.persistence.liferay.model.LFActivityStateNode;
import com.arcusys.learn.persistence.liferay.model.LFActivityStateNodeModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
* The base model implementation for the LFActivityStateNode service. Represents a row in the &quot;Learn_LFActivityStateNode&quot; database table, with each column mapped to a property of this class.
*
* <p>
    * This implementation and its corresponding interface {@link com.arcusys.learn.persistence.liferay.model.LFActivityStateNodeModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LFActivityStateNodeImpl}.
    * </p>
*
* @author Brian Wing Shun Chan
* @see LFActivityStateNodeImpl
* @see com.arcusys.learn.persistence.liferay.model.LFActivityStateNode
* @see com.arcusys.learn.persistence.liferay.model.LFActivityStateNodeModel
* @generated
*/
public class LFActivityStateNodeModelImpl extends BaseModelImpl<LFActivityStateNode>
    implements LFActivityStateNodeModel {
    /*
    * NOTE FOR DEVELOPERS:
    *
    * Never modify or reference this class directly. All methods that expect a l f activity state node model instance should use the {@link com.arcusys.learn.persistence.liferay.model.LFActivityStateNode} interface instead.
    */
    public static final String TABLE_NAME = "Learn_LFActivityStateNode";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", Types.BIGINT },
            { "parentID", Types.INTEGER },
            { "treeID", Types.INTEGER },
            { "availableChildrenIDs", Types.CLOB }
        };
    public static final String TABLE_SQL_CREATE = "create table Learn_LFActivityStateNode (id_ LONG not null primary key,parentID INTEGER null,treeID INTEGER,availableChildrenIDs TEXT null)";
    public static final String TABLE_SQL_DROP = "drop table Learn_LFActivityStateNode";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.arcusys.learn.persistence.liferay.model.LFActivityStateNode"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.arcusys.learn.persistence.liferay.model.LFActivityStateNode"),
            true);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.com.arcusys.learn.persistence.liferay.model.LFActivityStateNode"),
            true);
    public static long PARENTID_COLUMN_BITMASK = 1L;
    public static long TREEID_COLUMN_BITMASK = 2L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
                "lock.expiration.time.com.arcusys.learn.persistence.liferay.model.LFActivityStateNode"));
    private static ClassLoader _classLoader = LFActivityStateNode.class.getClassLoader();
    private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
            LFActivityStateNode.class
        };
    private long _id;
    private Integer _parentID;
    private Integer _originalParentID;
    private boolean _setOriginalParentID;
    private Integer _treeID;
    private Integer _originalTreeID;
    private boolean _setOriginalTreeID;
    private String _availableChildrenIDs;
    private long _columnBitmask;
    private LFActivityStateNode _escapedModelProxy;

    public LFActivityStateNodeModelImpl() {
    }

    public long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_id);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Class<?> getModelClass() {
        return LFActivityStateNode.class;
    }

    public String getModelClassName() {
        return LFActivityStateNode.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("parentID", getParentID());
        attributes.put("treeID", getTreeID());
        attributes.put("availableChildrenIDs", getAvailableChildrenIDs());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Integer parentID = (Integer) attributes.get("parentID");

        if (parentID != null) {
            setParentID(parentID);
        }

        Integer treeID = (Integer) attributes.get("treeID");

        if (treeID != null) {
            setTreeID(treeID);
        }

        String availableChildrenIDs = (String) attributes.get(
                "availableChildrenIDs");

        if (availableChildrenIDs != null) {
            setAvailableChildrenIDs(availableChildrenIDs);
        }
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public Integer getParentID() {
        return _parentID;
    }

    public void setParentID(Integer parentID) {
        _columnBitmask |= PARENTID_COLUMN_BITMASK;

        if (!_setOriginalParentID) {
            _setOriginalParentID = true;

            _originalParentID = _parentID;
        }

        _parentID = parentID;
    }

    public Integer getOriginalParentID() {
        return _originalParentID;
    }

    public Integer getTreeID() {
        return _treeID;
    }

    public void setTreeID(Integer treeID) {
        _columnBitmask |= TREEID_COLUMN_BITMASK;

        if (!_setOriginalTreeID) {
            _setOriginalTreeID = true;

            _originalTreeID = _treeID;
        }

        _treeID = treeID;
    }

    public Integer getOriginalTreeID() {
        return _originalTreeID;
    }

    public String getAvailableChildrenIDs() {
        if (_availableChildrenIDs == null) {
            return StringPool.BLANK;
        } else {
            return _availableChildrenIDs;
        }
    }

    public void setAvailableChildrenIDs(String availableChildrenIDs) {
        _availableChildrenIDs = availableChildrenIDs;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
            LFActivityStateNode.class.getName(), getPrimaryKey());
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        ExpandoBridge expandoBridge = getExpandoBridge();

        expandoBridge.setAttributes(serviceContext);
    }

    @Override
    public LFActivityStateNode toEscapedModel() {
        if (_escapedModelProxy == null) {
            _escapedModelProxy = (LFActivityStateNode) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelProxyInterfaces,
                    new AutoEscapeBeanHandler(this));
        }

        return _escapedModelProxy;
    }

    @Override
    public Object clone() {
        LFActivityStateNodeImpl lfActivityStateNodeImpl = new LFActivityStateNodeImpl();

        lfActivityStateNodeImpl.setId(getId());
        lfActivityStateNodeImpl.setParentID(getParentID());
        lfActivityStateNodeImpl.setTreeID(getTreeID());
        lfActivityStateNodeImpl.setAvailableChildrenIDs(getAvailableChildrenIDs());

        lfActivityStateNodeImpl.resetOriginalValues();

        return lfActivityStateNodeImpl;
    }

    public int compareTo(LFActivityStateNode lfActivityStateNode) {
        long primaryKey = lfActivityStateNode.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        LFActivityStateNode lfActivityStateNode = null;

        try {
            lfActivityStateNode = (LFActivityStateNode) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = lfActivityStateNode.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public void resetOriginalValues() {
        LFActivityStateNodeModelImpl lfActivityStateNodeModelImpl = this;

        lfActivityStateNodeModelImpl._originalParentID = lfActivityStateNodeModelImpl._parentID;

        lfActivityStateNodeModelImpl._setOriginalParentID = false;

        lfActivityStateNodeModelImpl._originalTreeID = lfActivityStateNodeModelImpl._treeID;

        lfActivityStateNodeModelImpl._setOriginalTreeID = false;

        lfActivityStateNodeModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<LFActivityStateNode> toCacheModel() {
        LFActivityStateNodeCacheModel lfActivityStateNodeCacheModel = new LFActivityStateNodeCacheModel();

        lfActivityStateNodeCacheModel.id = getId();

        lfActivityStateNodeCacheModel.parentID = getParentID();

        lfActivityStateNodeCacheModel.treeID = getTreeID();

        lfActivityStateNodeCacheModel.availableChildrenIDs = getAvailableChildrenIDs();

        String availableChildrenIDs = lfActivityStateNodeCacheModel.availableChildrenIDs;

        if ((availableChildrenIDs != null) &&
                (availableChildrenIDs.length() == 0)) {
            lfActivityStateNodeCacheModel.availableChildrenIDs = null;
        }

        return lfActivityStateNodeCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(9);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", parentID=");
        sb.append(getParentID());
        sb.append(", treeID=");
        sb.append(getTreeID());
        sb.append(", availableChildrenIDs=");
        sb.append(getAvailableChildrenIDs());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(16);

        sb.append("<model><model-name>");
        sb.append(
            "com.arcusys.learn.persistence.liferay.model.LFActivityStateNode");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentID</column-name><column-value><![CDATA[");
        sb.append(getParentID());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>treeID</column-name><column-value><![CDATA[");
        sb.append(getTreeID());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>availableChildrenIDs</column-name><column-value><![CDATA[");
        sb.append(getAvailableChildrenIDs());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
