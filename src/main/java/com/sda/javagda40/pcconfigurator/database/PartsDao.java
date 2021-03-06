package com.sda.javagda40.pcconfigurator.database;

import com.sda.javagda40.pcconfigurator.model.Parts;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class PartsDao {
    public boolean existsPartsModel(String searchedLogin) {
        List<Parts> list = new ArrayList<>();

        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            // narzędzie do tworzenia zapytań i kreowania klauzuli 'where'
            CriteriaBuilder cb = session.getCriteriaBuilder();

            // obiekt reprezentujący zapytanie
            CriteriaQuery<Parts> criteriaQuery = cb.createQuery(Parts.class);

            // obiekt reprezentujący tabelę bazodanową.
            // do jakiej tabeli kierujemy nasze zapytanie?
            Root<Parts> rootTable = criteriaQuery.from(Parts.class);

            // wykonanie zapytania
            criteriaQuery
                    .select(rootTable) // select * from rootTable
                    .where(
                            cb.equal(rootTable.get("login"), searchedLogin )
                    );
//            criteriaQuery
//                    .select(rootTable)
//                    .where(
//                            cb.and(
//                                    cb.equal(rootTable.get("firstName"), searchedFirstName ),
//                                    cb.equal(rootTable.get("lastName"), searchedLastName )
//                            )
//                    ).orderBy(cb.asc(rootTable.get("firstName")));

            // specification
            list.addAll(session.createQuery(criteriaQuery).list());

            // poznanie uniwersalnego rozwiązania które działa z każdą bazą danych
            // używanie klas których będziecie używać na JPA (Spring)

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return !list.isEmpty();
    }
}
