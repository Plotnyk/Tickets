package main.java.app.model.entity.base;


import main.java.app.model.entity.person.Account;

import java.time.LocalDateTime;

/**
 * Base class for all business entities
 * @author jeka
 *
 */
public abstract class AbstractEntity {
    /**
     * Unique entity identifier
     */
    private int id;
    /**
    * Timestamp of entity creation
    *
    * */
    private LocalDateTime createAt;
    /**
     * Timestamp of entity last modification
     * */
    private LocalDateTime modifiedAt;
    /**
     * Person who create specific entity
     * */
    private Account createBy;
    /**
     * Last person who modified entity
     * */
    private Account modifiedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Account getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Account createBy) {
        this.createBy = createBy;
    }

    public Account getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Account modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        AbstractEntity other = (AbstractEntity) obj;
        if(id != other.id)
            return false;
        return true;
    }
}