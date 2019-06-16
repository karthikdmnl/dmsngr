package org.dmnl.dmsngr.repositories;

import java.util.Date;
import java.util.List;
import org.dmnl.dmsngr.configurations.HibernateUtil;
import org.dmnl.dmsngr.models.Message;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jvnet.hk2.annotations.Service;

@Service
public class MessageDaoImpl implements MessageDao {

    @Override
    public List<Message> findAll() {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            final Transaction t = session.beginTransaction();
            try {
                List<Message> messages = session.createQuery("from Message").list();
                t.commit();
                return messages;
            } catch (Exception e) {
                t.rollback();
                throw e;
            }
        } finally {
            session.close();
        }

    }

    @Override
    public void save(Message m) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            final Transaction t = session.beginTransaction();
            try {
                m.setCreated(new Date());
                session.save(m);
                t.commit();
            } catch (Exception e) {
                t.rollback();
                throw e;
            }
        } finally {
            session.close();
        }
    }

    @Override
    public Message findOne(Long id) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            final Transaction t = session.beginTransaction();
            try {
                Message message = (Message) session.get(Message.class, id);
                t.commit();
                return message;
            } catch (Exception e) {
                t.rollback();
                throw e;
            }
        } finally {
            session.close();
        }

    }

    @Override
    public void delete(Long id) {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            final Transaction t = session.beginTransaction();
            try {
                String hql = "delete from Message where messageId= :id";
                session.createQuery(hql).setLong("id", id).executeUpdate();
                t.commit();
            } catch (Exception e) {
                t.rollback();
                throw e;
            }
        } finally {
            session.close();
        }
    }

}
