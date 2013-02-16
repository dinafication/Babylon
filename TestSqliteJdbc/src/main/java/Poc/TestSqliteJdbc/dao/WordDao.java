package Poc.TestSqliteJdbc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Poc.TestSqliteJdbc.entity.Word;


@Repository("wordDao")
@Transactional
public class WordDao {
	
	private static final Logger log = LoggerFactory.getLogger(WordDao.class);
	
	private SessionFactory sessionFactory;
	
	@Transactional(readOnly = true)
	public Word findById(String phrase, String language) {

		log.debug("getNaziv begin");

		Session s = sessionFactory.getCurrentSession();
		Criteria c = s.createCriteria(Word.class);
		c.add(Restrictions.eq("phrase", phrase));
		c.add(Restrictions.eq("language", language));

		log.debug("getNaziv end");

		return ((Word) c.uniqueResult());

	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Word> findAll() {
		try {
			log.debug("findAll begin");

			Session s = sessionFactory.getCurrentSession();
			Criteria c = s.createCriteria(Word.class);
			c.addOrder(Order.asc("phrase"));

			return c.list();
		} finally {
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
