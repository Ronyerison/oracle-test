/**
 * 
 */
package br.ufpi.loes.oracleTest.web.repository;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ufpi.loes.oracleTest.web.model.StatisticApplication;

/**
 * @author Rony
 *
 */
@RequestScoped
public class StatisticApplicationDao extends GenericDao<StatisticApplication> {

	protected StatisticApplicationDao() {
		this(null);
	}

	@Inject
	public StatisticApplicationDao(EntityManager entityManager) {
		super(entityManager);
	}

	public StatisticApplication saveStatistic(StatisticApplication statistic) {
		StatisticApplication statisticApplication = getStatisticBySaveDate(statistic);
		if (statisticApplication != null) {
			statisticApplication
					.setActionsNumber(statisticApplication.getActionsNumber() + statistic.getActionsNumber());
			return update(statisticApplication);
		} else {
			return update(statistic);
		}
	}

	public StatisticApplication getStatisticBySaveDate(StatisticApplication statistic) {
		TypedQuery<StatisticApplication> query = entityManager.createQuery(
				"Select s from StatisticApplication s where s.saveDate = :saveDate AND s.application = :application",
				StatisticApplication.class);
		query.setParameter("saveDate", statistic.getSaveDate());
		query.setParameter("application", statistic.getApplication());

		return query.getSingleResult();
	}

	public List<StatisticApplication> getStatisticByApplication(Long idApplication) {
		try {
			TypedQuery<StatisticApplication> query = entityManager.createQuery(
					"Select s from StatisticApplication s where s.application.id = :application",
					StatisticApplication.class);
			query.setParameter("application", idApplication);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
