package com.sda.javagda40.pcconfigurator.database;

import com.sda.javagda40.pcconfigurator.model.Cpu;
import com.sda.javagda40.pcconfigurator.model.MoBo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CpuDao {
    public void saveMoboRelation(Cpu cpu, MoBo moBo){
        SessionFactory sessionFactory = HibernateUtil.getOurSessionFactory();
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            cpu = session.get(Cpu.class, cpu.getId());
            moBo = session.get(MoBo.class, moBo.getId());

            cpu.getMobos().add(moBo);
            session.saveOrUpdate(cpu);

            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
