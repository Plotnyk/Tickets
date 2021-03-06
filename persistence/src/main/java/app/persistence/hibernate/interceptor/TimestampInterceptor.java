package app.persistence.hibernate.interceptor;


import app.model.entity.base.AbstractEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Initialize mandatory timestamp fields for the entities
 *
 * @author Plotnyk
 */
public class TimestampInterceptor extends EmptyInterceptor {
    private static final long serialVersionUID = 6825201844366406253L;

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        int idx = ArrayUtils.indexOf(propertyNames, AbstractEntity.FIELD_CREATED_AT);
        if (idx >= 0) {
            state[idx] = LocalDateTime.now();
            return true;
        }
        return false;
    }
}
