package com.sda.grouproject.repository;

import com.sda.grouproject.model.Developer;
import com.sda.grouproject.model.Game;
import com.sda.grouproject.model.User;
import com.sda.grouproject.utils.SessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class GameRepository extends GenericRepository {

    private static GameRepository instance;

    private GameRepository()
    {

    }
    public static GameRepository getInstance()
    {
        if(instance==null) {
            instance= new GameRepository();
        }
        return instance;
    }

    public Game findById(Integer id)
    {
        Session session= SessionManager.getSessionFactory().openSession();
        Game game  = session.find(Game.class, id);
        session.close();
        return game;
    }


    public List<Game> findByGameName()
    {
        try {
            Session session = SessionManager.getSessionFactory().openSession();
            String findByGameNameQuerry = "from Game";
            Query<Game> gameQuery = session.createQuery(findByGameNameQuerry);
            //gameQuery.setParameter("gameName", gameName);
            List<Game> games = gameQuery.list();
            session.close();
            return games;
        }
        catch (NoResultException e)
        {
            System.out.println("Game you searched for is not here.");
            return null;
        }
        //todo make sure this works
    }

}
