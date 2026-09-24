package com.iam;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateDemo
{
    public static void main(String[]a)
    {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
        Session session = factory.openSession();
        try {
            User user = new User("ceo","0101");
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();

            System.out.println("Generated ID : " + user.getId());
        }finally {
            session.close();
            factory.close();
        }
    }
}