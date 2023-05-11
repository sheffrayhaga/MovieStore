/*
 * If the user enter a password, user have access to add or remove movie titles, actors, year, or genre
 * movie added, display added successfully 
 * movie removed, display removed successfully
 * If search does not match movie titles, actors, year, or genre, display movie not found
 * If there is none to remove in the list, display no movies in the store
 *
 * @author Jovan Rayhag
 * @version 05/08/2023
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class ManagerMode
{
    public static final String ADD_REMVOVE = String.format("\n%s\n%s\n", "1. Add","2. Remove ");
    public static final String ADD = String.format("\n%s\n%s\n%s\n%s\n", "1. Add Title","2. Add Actor", "3. Add Year", "4. Add Genre");
    public static final String REMOVE = String.format("\n%s\n%s\n%s\n%s\n", "1. Remove Title","2. Remove Actor", "3. Remove Year", "4. Remove Genre");
    
    ArrayList<Movie> movies = new ArrayList<Movie>();

    public static final int PASSWORD = 123; // Manager Password

    public ManagerMode(Scanner stdIn, ArrayList<MovieStore.Movie> movies) 
    {
        boolean loggedIn = false;
        while (!loggedIn) 
        {
            System.out.printf("\n%s", "Enter 3 digits password:\n");
            int password = stdIn.nextInt();
    
            switch (password)
            {
                case PASSWORD:
                    System.out.printf("\n%s", "Login successful\n");
                    loggedIn = true;
                    break;
                default:
                    System.out.printf("\n%s","Invalid password\n");
                    
                boolean tryAgain = true;
                while (tryAgain) 
                {
                    System.out.printf("\n%s\n", "Do you want to try again? (y/n)");
                    String retry = stdIn.next();
                    if (retry.equalsIgnoreCase("n")) 
                    {
                        return;
                    } else if (retry.equalsIgnoreCase("y")) 
                    {
                        tryAgain = false;
                        break;
                    }
                }
            }
        }
        
        boolean option = false;
        while (!option) 
        {
            System.out.printf(ADD_REMVOVE);
            int addRemove = stdIn.nextInt();
    
            switch (addRemove)
            {
                case 1:
                    addMovie(stdIn, movies);
                    option = true;
                    break;
                case 2:
                    removeMovie(stdIn, movies);
                    option = true;
                    break;
                default:
                    System.out.printf("\n%s","Not In Option\n");
            }
        }
    }
    
    public void addMovie(Scanner stdIn, ArrayList<MovieStore.Movie> movies) 
    {
        System.out.printf("\n%s", "Enter Movie Title: ");
        String title = stdIn.next();
        
        System.out.printf("\n%s", "Enter Movie Actor: ");
        String actor = stdIn.next();
        
        System.out.printf("\n%s", "Enter Movie Year: ");
        int year = stdIn.nextInt();

        System.out.printf("\n%s", "Enter Movie Genre: ");
        String genre = stdIn.next();
    
        movies.add(new MovieStore.Movie(title, actor, year, genre));
        System.out.printf("\n%s", "Movie added successfully.\n\n");
    }
    
    public static void removeMovie(Scanner stdIn, ArrayList<MovieStore.Movie> movies) 
    {
        boolean choice = false;
        int remove;
        do 
        {
            System.out.printf(REMOVE);
            remove = stdIn.nextInt();
    
            if (remove < 1 || remove > 4) {
                System.out.printf("\n%s\n", "Not in Option");
            }
        } while (remove < 1 || remove > 4);
            
        switch (remove) 
        {
            case 1:
                System.out.printf("\n%s", "Enter Movie Title: ");
                String title = stdIn.next();
                for (MovieStore.Movie movie : movies) 
                {
                    if (movie.getTitle().equalsIgnoreCase(title)) 
                    {
                        movies.remove(movie);
                        System.out.printf("\n%s has been removed\n\n", title);
                        choice = true;
                    }
                }
                break;
            case 2:
                System.out.printf("\n%s", "Enter Movie Actor: ");
                String actor = stdIn.next();
                for (MovieStore.Movie movie : movies) 
                {
                    if (movie.getActor().equalsIgnoreCase(actor)) 
                    {
                        movies.remove(movie);   
                        System.out.printf("\n%s has been removed\n\n", actor);
                        choice = true;
                    }
                }
                break;
            case 3:
                System.out.printf("\n%s", "Enter Movie Year: ");
                int year = stdIn.nextInt();
                for (MovieStore.Movie movie : movies) 
                {
                    if (movie.getYear() == year) 
                    {
                        movies.remove(movie);
                        System.out.printf("\n%s has been removed\n\n", year);
                        choice = true;
                    }
                }
                break;
            case 4:
                System.out.printf("\n%s", "Enter Movie Genre: ");
                String genre = stdIn.next();
                for (MovieStore.Movie movie : movies) 
                {
                    if (movie.getGenre().equalsIgnoreCase(genre))
                    {
                        movies.remove(movie);
                        System.out.printf("\n%s has been removed\n\n", genre);
                        choice = true;
                    }
                }
                break;
            default:
                return;
        }
        
        if (!choice) 
        {
            System.out.printf("\n%s\n", "Movie not found\n");
        }
        
        if (movies.size() == 0) 
        {
            System.out.printf("%s\n", "No movies are stored.\n");
        }
    }
}