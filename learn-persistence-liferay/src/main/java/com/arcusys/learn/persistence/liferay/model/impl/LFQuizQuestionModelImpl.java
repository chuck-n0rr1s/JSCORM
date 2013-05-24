package com.arcusys.learn.persistence.liferay.model.impl;

import com.arcusys.learn.persistence.liferay.model.LFQuizQuestion;
import com.arcusys.learn.persistence.liferay.model.LFQuizQuestionModel;

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
* The base model implementation for the LFQuizQuestion service. Represents a row in the &quot;Learn_LFQuizQuestion&quot; database table, with each column mapped to a property of this class.
*
* <p>
    * This implementation and its corresponding interface {@link com.arcusys.learn.persistence.liferay.model.LFQuizQuestionModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LFQuizQuestionImpl}.
    * </p>
*
* @author Brian Wing Shun Chan
* @see LFQuizQuestionImpl
* @see com.arcusys.learn.persistence.liferay.model.LFQuizQuestion
* @see com.arcusys.learn.persistence.liferay.model.LFQuizQuestionModel
* @generated
*/
public class LFQuizQuestionModelImpl extends BaseModelImpl<LFQuizQuestion>
    implements LFQuizQuestionModel {
    /*
    * NOTE FOR DEVELOPERS:
    *
    * Never modify or reference this class directly. All methods that expect a l f quiz question model instance should use the {@link com.arcusys.learn.persistence.liferay.model.LFQuizQuestion} interface instead.
    */
    public static final String TABLE_NAME = "Learn_LFQuizQuestion";
    public static final Object[][] TABLE_COLUMNS = {
            { "id_", Types.BIGINT },
            { "quizId", Types.INTEGER },
            { "categoryId", Types.INTEGER },
            { "questionId", Types.INTEGER },
            { "questionType", Types.CLOB },
            { "title", Types.CLOB },
            { "url", Types.CLOB },
            { "plainText", Types.CLOB },
            { "arrangementIndex", Types.INTEGER }
        };
    public static final String TABLE_SQL_CREATE = "create table Learn_LFQuizQuestion (id_ LONG not null primary key,quizId INTEGER,categoryId INTEGER null,questionId INTEGER,questionType TEXT null,title TEXT null,url TEXT null,plainText TEXT null,arrangementIndex INTEGER)";
    public static final String TABLE_SQL_DROP = "drop table Learn_LFQuizQuestion";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.com.arcusys.learn.persistence.liferay.model.LFQuizQuestion"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.com.arcusys.learn.persistence.liferay.model.LFQuizQuestion"),
            true);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.com.arcusys.learn.persistence.liferay.model.LFQuizQuestion"),
            true);
    public static long CATEGORYID_COLUMN_BITMASK = 1L;
    public static long QUIZID_COLUMN_BITMASK = 2L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
                "lock.expiration.time.com.arcusys.learn.persistence.liferay.model.LFQuizQuestion"));
    private static ClassLoader _classLoader = LFQuizQuestion.class.getClassLoader();
    private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
            LFQuizQuestion.class
        };
    private long _id;
    private Integer _quizId;
    private Integer _originalQuizId;
    private boolean _setOriginalQuizId;
    private Integer _categoryId;
    private Integer _originalCategoryId;
    private boolean _setOriginalCategoryId;
    private Integer _questionId;
    private String _questionType;
    private String _title;
    private String _url;
    private String _plainText;
    private Integer _arrangementIndex;
    private long _columnBitmask;
    private LFQuizQuestion _escapedModelProxy;

    public LFQuizQuestionModelImpl() {
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
        return LFQuizQuestion.class;
    }

    public String getModelClassName() {
        return LFQuizQuestion.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("quizId", getQuizId());
        attributes.put("categoryId", getCategoryId());
        attributes.put("questionId", getQuestionId());
        attributes.put("questionType", getQuestionType());
        attributes.put("title", getTitle());
        attributes.put("url", getUrl());
        attributes.put("plainText", getPlainText());
        attributes.put("arrangementIndex", getArrangementIndex());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Integer quizId = (Integer) attributes.get("quizId");

        if (quizId != null) {
            setQuizId(quizId);
        }

        Integer categoryId = (Integer) attributes.get("categoryId");

        if (categoryId != null) {
            setCategoryId(categoryId);
        }

        Integer questionId = (Integer) attributes.get("questionId");

        if (questionId != null) {
            setQuestionId(questionId);
        }

        String questionType = (String) attributes.get("questionType");

        if (questionType != null) {
            setQuestionType(questionType);
        }

        String title = (String) attributes.get("title");

        if (title != null) {
            setTitle(title);
        }

        String url = (String) attributes.get("url");

        if (url != null) {
            setUrl(url);
        }

        String plainText = (String) attributes.get("plainText");

        if (plainText != null) {
            setPlainText(plainText);
        }

        Integer arrangementIndex = (Integer) attributes.get("arrangementIndex");

        if (arrangementIndex != null) {
            setArrangementIndex(arrangementIndex);
        }
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public Integer getQuizId() {
        return _quizId;
    }

    public void setQuizId(Integer quizId) {
        _columnBitmask |= QUIZID_COLUMN_BITMASK;

        if (!_setOriginalQuizId) {
            _setOriginalQuizId = true;

            _originalQuizId = _quizId;
        }

        _quizId = quizId;
    }

    public Integer getOriginalQuizId() {
        return _originalQuizId;
    }

    public Integer getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        _columnBitmask |= CATEGORYID_COLUMN_BITMASK;

        if (!_setOriginalCategoryId) {
            _setOriginalCategoryId = true;

            _originalCategoryId = _categoryId;
        }

        _categoryId = categoryId;
    }

    public Integer getOriginalCategoryId() {
        return _originalCategoryId;
    }

    public Integer getQuestionId() {
        return _questionId;
    }

    public void setQuestionId(Integer questionId) {
        _questionId = questionId;
    }

    public String getQuestionType() {
        if (_questionType == null) {
            return StringPool.BLANK;
        } else {
            return _questionType;
        }
    }

    public void setQuestionType(String questionType) {
        _questionType = questionType;
    }

    public String getTitle() {
        if (_title == null) {
            return StringPool.BLANK;
        } else {
            return _title;
        }
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getUrl() {
        if (_url == null) {
            return StringPool.BLANK;
        } else {
            return _url;
        }
    }

    public void setUrl(String url) {
        _url = url;
    }

    public String getPlainText() {
        if (_plainText == null) {
            return StringPool.BLANK;
        } else {
            return _plainText;
        }
    }

    public void setPlainText(String plainText) {
        _plainText = plainText;
    }

    public Integer getArrangementIndex() {
        return _arrangementIndex;
    }

    public void setArrangementIndex(Integer arrangementIndex) {
        _arrangementIndex = arrangementIndex;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
            LFQuizQuestion.class.getName(), getPrimaryKey());
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        ExpandoBridge expandoBridge = getExpandoBridge();

        expandoBridge.setAttributes(serviceContext);
    }

    @Override
    public LFQuizQuestion toEscapedModel() {
        if (_escapedModelProxy == null) {
            _escapedModelProxy = (LFQuizQuestion) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelProxyInterfaces,
                    new AutoEscapeBeanHandler(this));
        }

        return _escapedModelProxy;
    }

    @Override
    public Object clone() {
        LFQuizQuestionImpl lfQuizQuestionImpl = new LFQuizQuestionImpl();

        lfQuizQuestionImpl.setId(getId());
        lfQuizQuestionImpl.setQuizId(getQuizId());
        lfQuizQuestionImpl.setCategoryId(getCategoryId());
        lfQuizQuestionImpl.setQuestionId(getQuestionId());
        lfQuizQuestionImpl.setQuestionType(getQuestionType());
        lfQuizQuestionImpl.setTitle(getTitle());
        lfQuizQuestionImpl.setUrl(getUrl());
        lfQuizQuestionImpl.setPlainText(getPlainText());
        lfQuizQuestionImpl.setArrangementIndex(getArrangementIndex());

        lfQuizQuestionImpl.resetOriginalValues();

        return lfQuizQuestionImpl;
    }

    public int compareTo(LFQuizQuestion lfQuizQuestion) {
        long primaryKey = lfQuizQuestion.getPrimaryKey();

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

        LFQuizQuestion lfQuizQuestion = null;

        try {
            lfQuizQuestion = (LFQuizQuestion) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = lfQuizQuestion.getPrimaryKey();

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
        LFQuizQuestionModelImpl lfQuizQuestionModelImpl = this;

        lfQuizQuestionModelImpl._originalQuizId = lfQuizQuestionModelImpl._quizId;

        lfQuizQuestionModelImpl._setOriginalQuizId = false;

        lfQuizQuestionModelImpl._originalCategoryId = lfQuizQuestionModelImpl._categoryId;

        lfQuizQuestionModelImpl._setOriginalCategoryId = false;

        lfQuizQuestionModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<LFQuizQuestion> toCacheModel() {
        LFQuizQuestionCacheModel lfQuizQuestionCacheModel = new LFQuizQuestionCacheModel();

        lfQuizQuestionCacheModel.id = getId();

        lfQuizQuestionCacheModel.quizId = getQuizId();

        lfQuizQuestionCacheModel.categoryId = getCategoryId();

        lfQuizQuestionCacheModel.questionId = getQuestionId();

        lfQuizQuestionCacheModel.questionType = getQuestionType();

        String questionType = lfQuizQuestionCacheModel.questionType;

        if ((questionType != null) && (questionType.length() == 0)) {
            lfQuizQuestionCacheModel.questionType = null;
        }

        lfQuizQuestionCacheModel.title = getTitle();

        String title = lfQuizQuestionCacheModel.title;

        if ((title != null) && (title.length() == 0)) {
            lfQuizQuestionCacheModel.title = null;
        }

        lfQuizQuestionCacheModel.url = getUrl();

        String url = lfQuizQuestionCacheModel.url;

        if ((url != null) && (url.length() == 0)) {
            lfQuizQuestionCacheModel.url = null;
        }

        lfQuizQuestionCacheModel.plainText = getPlainText();

        String plainText = lfQuizQuestionCacheModel.plainText;

        if ((plainText != null) && (plainText.length() == 0)) {
            lfQuizQuestionCacheModel.plainText = null;
        }

        lfQuizQuestionCacheModel.arrangementIndex = getArrangementIndex();

        return lfQuizQuestionCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(19);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", quizId=");
        sb.append(getQuizId());
        sb.append(", categoryId=");
        sb.append(getCategoryId());
        sb.append(", questionId=");
        sb.append(getQuestionId());
        sb.append(", questionType=");
        sb.append(getQuestionType());
        sb.append(", title=");
        sb.append(getTitle());
        sb.append(", url=");
        sb.append(getUrl());
        sb.append(", plainText=");
        sb.append(getPlainText());
        sb.append(", arrangementIndex=");
        sb.append(getArrangementIndex());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(31);

        sb.append("<model><model-name>");
        sb.append("com.arcusys.learn.persistence.liferay.model.LFQuizQuestion");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>quizId</column-name><column-value><![CDATA[");
        sb.append(getQuizId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryId</column-name><column-value><![CDATA[");
        sb.append(getCategoryId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>questionId</column-name><column-value><![CDATA[");
        sb.append(getQuestionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>questionType</column-name><column-value><![CDATA[");
        sb.append(getQuestionType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>title</column-name><column-value><![CDATA[");
        sb.append(getTitle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>url</column-name><column-value><![CDATA[");
        sb.append(getUrl());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>plainText</column-name><column-value><![CDATA[");
        sb.append(getPlainText());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>arrangementIndex</column-name><column-value><![CDATA[");
        sb.append(getArrangementIndex());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
