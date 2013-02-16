package Poc.TestSqliteJdbc.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Poc.TestSqliteJdbc.entity.Translation;


@Repository("translationDao")
@Transactional
public class TranslationDao {

private static final Logger log = LoggerFactory.getLogger(TranslationDao.class);
	
	private SessionFactory sessionFactory;
	
	@Transactional(readOnly = true)
	public Translation findById(String phrase1, String language1, String phrase2, String language2) {

		log.debug("get translation begin");

		Session s = sessionFactory.getCurrentSession();
		Criteria c = s.createCriteria(Translation.class);
		
		c.add(Restrictions.eq("phrase1", phrase1));
		c.add(Restrictions.eq("language1", language1));
		c.add(Restrictions.eq("phrase2", phrase2));
		c.add(Restrictions.eq("language2", language2));

		log.debug("getNaziv end");

		Translation t = (Translation) c.uniqueResult();
		log.debug(t.toString());
		return t;

	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Translation> findAll() {
		
		Criteria c = null;		
		try {
			log.debug("findAll begin");

			Session s = sessionFactory.getCurrentSession();
			c = s.createCriteria(Translation.class);
			c.addOrder(Order.asc("phrase1"));

			return c.list();
		} finally {
			log.debug("found: " + c.list().size() + " translations.");
			log.debug("findAll end");
		}
	}
	

	public SessionFactory getSessionFactory() {

		try {
			log.debug("getSessionFactory begin");
			return sessionFactory;
		} finally {
			log.debug("getSessionFactory end");
		}

	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		try {
			log.debug("setSessionFactory begin");
			this.sessionFactory = sessionFactory;
		} finally {
			log.debug("setSessionFactory end");
		}
	}
}
