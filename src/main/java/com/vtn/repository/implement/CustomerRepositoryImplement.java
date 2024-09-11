package com.vtn.repository.implement;

import com.vtn.pojo.Customer;
import com.vtn.pojo.User;
import com.vtn.repository.CustomerRepository;
import com.vtn.util.Pagination;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
@Transactional
public class CustomerRepositoryImplement implements CustomerRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    private Session getCurrentSession() {
        return Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
    }

    @Override
    public Customer findById(Long id) {
        Session session = this.getCurrentSession();

        return session.get(Customer.class, id);
    }

    @Override
    public Customer findByUser(@NotNull User user) {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
        Root<Customer> root = criteria.from(Customer.class);

        try {
            criteria.select(root).where(builder.equal(root.get("user").get("id"), user.getId()));
            Query<Customer> query = session.createQuery(criteria);

            return query.getSingleResult();
        } catch (NoResultException e) {
            LoggerFactory.getLogger(UserRepositoryImplement.class).error("An error occurred while getting supplier by user", e);
            return null;
        }
    }

    @Override
    public void save(Customer customer) {
        Session session = this.getCurrentSession();
        session.persist(customer);
    }

    @Override
    public void update(Customer customer) {
        Session session = this.getCurrentSession();
        session.merge(customer);
    }

    @Override
    public void delete(Long id) {
        Session session = this.getCurrentSession();
        Customer customer = session.get(Customer.class, id);
        session.delete(customer);
    }

    @Override
    public Long count() {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Customer> root = criteria.from(Customer.class);

        criteria.select(builder.count(root));
        Query<Long> query = session.createQuery(criteria);

        return query.getSingleResult();
    }

    @Override
    public List<Customer> findAllWithFilter(Map<String, String> params) {
        Session session = this.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
        Root<Customer> root = criteria.from(Customer.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("active"), true));

        if (params != null && !params.isEmpty()) {
            String name = params.get("name");
            if (name != null && !name.isEmpty()) {
                List<Predicate> namePredicates = new ArrayList<>();

                Arrays.asList("lastName", "middleName", "firstName").forEach(key -> {
                    namePredicates.add(builder.like(root.get(key), String.format("%%%s%%", name)));
                });

                predicates.add(builder.or(namePredicates.toArray(Predicate[]::new)));
            }
        }

        criteria.select(root).where(predicates.toArray(Predicate[]::new)).orderBy(builder.desc(root.get("id")));
        Query<Customer> query = session.createQuery(criteria);
        Pagination.paginator(query, params);

        return query.getResultList();
    }
}
