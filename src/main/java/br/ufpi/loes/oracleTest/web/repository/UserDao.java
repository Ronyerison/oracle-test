/**
 * 
 */
package br.ufpi.loes.oracleTest.web.repository;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.ufpi.loes.oracleTest.web.model.User;

@RequestScoped
public class UserDao extends GenericDao<User> {
	
	/**
	 * @deprecated CDI eyes only
	 */
	protected UserDao() {
		this(null);
	}

	@Inject
	public UserDao(EntityManager entityManager) {
		super(entityManager);
	}

	public List<User> findAll(){
		try {
			return entityManager.createQuery("Select u from User a", User.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public User find(String login, String password) {
//		GenerateHashPasswordUtil generateHashPasswordUtil = new GenerateHashPasswordUtil();
		try {
			User user = entityManager
				.createQuery("select u from User u where u.login = :login and u.password = :password", User.class)
					.setParameter("login", login)
					.setParameter("password", password)
					.getSingleResult();
			return user;
		} catch (NoResultException e) {
			return null;
		}
	}

	public User find(String login) {
		User user = entityManager
				.createQuery("select u from User u where u.login = :login", User.class)
					.setParameter("login", login)
					.getSingleResult();
		return user;
	}
	
	public boolean containsUserWithLogin(String login) {
		Long count = entityManager
				.createQuery("select count(u) from User u where u.login = :login", Long.class)
				.setParameter("login", login)
				.getSingleResult();
		return count > 0;
	}
	
	public List<User> findUserName(String name) {	 
		List<User> users = entityManager
				.createQuery("select u from User u where u.name like :name", User.class)
					.setParameter("name", "%" + name + "%").getResultList();
		return users;
	}
	
	public User findEmail(String email){
		try {
			return entityManager.createQuery("select u from User u where u.email = :email", User.class)
					.setParameter("email", email)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public User findCodeRecovery(String code){
		try {
			return entityManager.createQuery("select u from User u where u.recoveryCode = :recoverycode", User.class)
					.setParameter("recoverycode", code)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void clearAllCodeRecovery(){
			
	}

	public boolean containsUserWithEMail(String email) {
		Long count = entityManager
				.createQuery("select count(u) from User u where u.email = :email", Long.class)
				.setParameter("email", email)
				.getSingleResult();
		return count > 0;
	}

}