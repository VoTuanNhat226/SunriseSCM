package com.vtn.repository.implement;

import com.vtn.pojo.Unit;
import com.vtn.repository.UnitRepository;
import com.vtn.util.Pagination;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@Transactional
public class UnitRepositoryImplement implements UnitRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(factory.getObject()).getCurrentSession();
    }

    @Override
    public Unit findById(Long id) {
        Session session = this.getCurrentSession();

        return session.get(Unit.class, id);
    }

    @Override
    public void save(Unit unit) {
        Session session = this.getCurrentSession();
        session.persist(unit);
    }

    @Override
    public void update(Unit unit) {
        Session session = this.getCurrentSession();
        session.merge(unit);
    }

    @Override
    public void delete(Long id) {
        Session session = this.getCurrentSession();
        Unit unit = session.get(Unit.class, id);
        session.delete(unit);
    }

    @Override
    public Long count() {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Unit> root = criteria.from(Unit.class);

        criteria.select(builder.count(root));
        Query<Long> query = session.createQuery(criteria);

        return query.getSingleResult();
    }

    @Override
    public List<Unit> findAllWithFilter(Map<String, String> params) {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Unit> criteria = builder.createQuery(Unit.class);
        Root<Unit> root = criteria.from(Unit.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("active"), true));

        if (params != null && !params.isEmpty()) {
            String name = params.get("name");
            if (name != null && !name.isEmpty()) {
                predicates.add(builder.like(root.get("name"), String.format("%%%s%%", name)));
            }

            String abbreviation = params.get("abbreviation");
            if (abbreviation != null && !abbreviation.isEmpty()) {
                predicates.add(builder.like(root.get("abbreviation"), String.format("%%%s%%", abbreviation)));
            }
        }

        criteria.select(root).where(predicates.toArray(Predicate[]::new)).orderBy(builder.desc(root.get("id")));
        Query<Unit> query = session.createQuery(criteria);
        Pagination.paginator(query, params);

        return query.getResultList();
    }
}
