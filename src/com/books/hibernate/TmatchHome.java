package com.books.hibernate;

// Generated 14 janv. 2015 16:30:53 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.books.model.Tmatch;

/**
 * Home object for domain model class Tmatch.
 * @see com.books.hibernate.Tmatch
 * @author Hibernate Tools
 */
public class TmatchHome {

	private static final Log log = LogFactory.getLog(TmatchHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Tmatch transientInstance) {
		log.debug("persisting Tmatch instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tmatch instance) {
		log.debug("attaching dirty Tmatch instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tmatch instance) {
		log.debug("attaching clean Tmatch instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tmatch persistentInstance) {
		log.debug("deleting Tmatch instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tmatch merge(Tmatch detachedInstance) {
		log.debug("merging Tmatch instance");
		try {
			Tmatch result = (Tmatch) sessionFactory.getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Tmatch findById(java.lang.Integer id) {
		log.debug("getting Tmatch instance with id: " + id);
		try {
			Tmatch instance = (Tmatch) sessionFactory.getCurrentSession().get(
					"com.books.hibernate.Tmatch", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tmatch instance) {
		log.debug("finding Tmatch instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("com.books.hibernate.Tmatch")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
