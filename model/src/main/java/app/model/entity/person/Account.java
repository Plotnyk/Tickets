package app.model.entity.person;

import app.model.entity.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity that encapsulates user of the application
 * @author jeka */
@Table(name = "ACCOUNT")
@Entity
public class Account extends AbstractEntity {
}
