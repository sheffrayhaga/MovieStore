/*
 * Give the user the ability to enter a password and allow the user to add or remove movie titles, actors, year, or genre
 * If the user does not enter a password, only allow the user to search or sort from the Movies Store list 
 * Option to display all movies
 * Option specific movie by movie title, actor or actress, year, or genre
 * Option  to sort the movie by title, actor, or actress from A to Z (Descending) or Z to A (Ascending)
 * Option to Exit the program
 * 
 * @author Jovan Rayhaga
 * @version 05/08/2023
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class MovieStore
{
    public static final String MAIN_MENU = String.format("%s\n%s\n%s\n%s\n%s\n%s\n", "Welcome to the Movie Store\n", "1. Manager Login", "2. Display All Movies", "3. Search Movies", "4. Sort Movies", "5. Exit");
    public static final String SEARCH_MOVIES = String.format("\n%s\n%s\n%s\n%s\n", "1. Search Title","2. Search Actor", "3. Search Year", "4. Search Genre");
    public static final String SORT_MOVIES = String.format("\n%s\n%s\n%s\n%s\n", "1. Sort Title","2. Sort Actor", "3. Sort Year", "4. Sort Genre");
    
    public static void main(String[] args) 
        {
        ArrayList<Movie> movies = new ArrayList<>();
        boolean mainMenu = true;
        
        while (mainMenu) 
        {
            Scanner stdIn = new Scanner(System.in);
            
            System.out.printf(MAIN_MENU);
            int option = stdIn.nextInt();
            
            switch (option) 
            {
                case 1:
                    ManagerMode manager = new ManagerMode(stdIn, movies);
                    break;
                case 2:
                    displayAllMovies(movies);
                    break;
                case 3:
                    searchMovie(stdIn, movies);
                    break;
                case 4:
                    sortMovie(stdIn, movies);
                    break;
                case 5:
                    mainMenu = false;
                    System.out.printf("\n%s", "Thank you");
                    break;       
                default:
                    System.out.printf("\n%s", "Not in option\n");
            }
        }
    }
    
    public static void displayAllMovies(ArrayList<Movie> movies) // Display All Movies method
    {
        if (movies.size() == 0) 
        {
            System.out.printf("\n%s", "No movies available\n");
        } else {
            for (Movie movie : movies) 
            {
                System.out.printf("\n%s (%d) - %s - %s\n\n", movie.getTitle(), movie.getYear(), movie.getActor(), movie.getGenre());
            }
        }
    }
    
    public static void searchMovie(Scanner stdIn, ArrayList<MovieStore.Movie> movies) //  Search Movies method
    {
        System.out.printf(SEARCH_MOVIES);
        int search = stdIn.nextInt();
        
        boolean searchMovie = false;
        switch (search) 
        {
            case 1:
                System.out.printf("\n%s", "Enter movie title:");
                String title = stdIn.next();
                for (Movie movie : movies) 
                {
                    if (movie.getTitle().equalsIgnoreCase(title))
                    {
                        System.out.printf("\n%s (%d) - %s - %s\n\n", movie.getTitle(), movie.getYear(), movie.getActor(), movie.getGenre());
                        searchMovie = true;
                    }
                }
                break;
            case 2:
                System.out.printf("\n%s", "Enter actor: ");
                String actor = stdIn.next();
                for (Movie movie : movies)
                {
                    if (movie.getActor().equalsIgnoreCase(actor)) 
                    {
                        System.out.printf("\n%s (%d) - %s - %s\n\n", movie.getTitle(), movie.getYear(), movie.getActor(), movie.getGenre());
                        searchMovie = true;
                    }
                }
                break;
            case 3:
                System.out.printf("\n%s", "Enter movie year:");
                int year = stdIn.nextInt();
                for (Movie movie : movies)
                {
                    if (movie.getYear() == year) 
                    {
                        System.out.printf("\n%s (%d) - %s - %s\n\n", movie.getTitle(), movie.getYear(), movie.getActor(), movie.getGenre());
                        searchMovie = true;
                    }
                }
                break;
            case 4:
                System.out.printf("\n%s", "Enter movie genre:");
                String genre = stdIn.next();
                for (Movie movie : movies) 
                {
                    if (movie.getGenre().equalsIgnoreCase(genre)) 
                    {
                        System.out.printf("\n%s (%d) - %s - %s\n\n", movie.getTitle(), movie.getYear(), movie.getActor(), movie.getGenre());
                        searchMovie = true;
                    }
                }
                break;
            default:
                System.out.printf("\n%s\n", "Not in Option\n");
        }
        
        if (!searchMovie) 
        {
            System.out.printf("\n%s\n", "Movie not found\n");
        }
    }
    
    public static void sortMovie(Scanner stdIn, ArrayList<MovieStore.Movie> movies) // Sort Movies method - Ascending or Descening
    {
        System.out.printf(SORT_MOVIES);
        int sort = stdIn.nextInt();

    
        System.out.printf("\n%s\n%s\n", "1. Ascending", "2. Descending");
        int order = stdIn.nextInt();
        
        if (order == 1) 
        {
            sortMovie(movies, sort, true);
            for (Movie movie : movies) 
            {
                System.out.printf("\n%s (%d) - %s - %s\n\n", movie.getTitle(), movie.getYear(), movie.getActor(), movie.getGenre());
            }   
        }
        if (order == 2) 
        {
            sortMovie(movies, sort, false); 
            for (Movie movie : movies) 
            {
                System.out.printf("\n%s (%d) - %s - %s\n\n", movie.getTitle(), movie.getYear(), movie.getActor(), movie.getGenre());
            }
        }
    }
    
    public static void sortMovie(ArrayList<MovieStore.Movie> movies, int sort, boolean ascending) // overloaded Sort Movie method
    {
        switch (sort) 
        {
            case 1:
                for (int i = 0; i < movies.size() - 1; i++) 
                {
                    for (int j = i + 1; j < movies.size(); j++) 
                    {
                        Movie m1 = movies.get(i);
                        Movie m2 = movies.get(j);
                        if (m1.getTitle().compareTo(m2.getTitle()) > 0 == ascending) 
                        {
                            movies.set(i, m2);
                            movies.set(j, m1);
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < movies.size() - 1; i++) 
                {
                    for (int j = i + 1; j < movies.size(); j++) 
                    {
                        Movie m1 = movies.get(i);
                        Movie m2 = movies.get(j);
                        if (m1.getActor().compareTo(m2.getActor()) > 0 == ascending) 
                        {
                            movies.set(i, m2);
                            movies.set(j, m1);
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < movies.size() - 1; i++) 
                {
                    for (int j = i + 1; j < movies.size(); j++) 
                    {
                        Movie m1 = movies.get(i);
                        Movie m2 = movies.get(j);
                        if ((m1.getYear() > m2.getYear()) == ascending) 
                        {
                            movies.set(i, m2);
                            movies.set(j, m1);
                        }
                    }
                }
                break;
            case 4:
                for (int i = 0; i < movies.size() - 1; i++) 
                {
                    for (int j = i + 1; j < movies.size(); j++) 
                    {
                        Movie m1 = movies.get(i);
                        Movie m2 = movies.get(j);
                        if (m1.getGenre().compareTo(m2.getGenre()) > 0 == ascending) 
                        {
                            movies.set(i, m2);
                            movies.set(j, m1);
                        }
                    }
                }
                break;
            default:
                System.out.printf("\n%s\n", "Not in Option\n");
                return;
        }
    }
    
    public static class Movie 
    {
        String title;
        String actor;
        int year;
        String genre;
        
        public Movie() // constructor
        {
            this.title = "";
            this.actor = "";
            this.year = 0 ;
            this.genre = "";
        }
        
        public Movie(String title, String actor, int year, String genre) //overloaded constructor
        {
            this.title = title;
            this.actor = actor;
            this.year = year;
            this.genre = genre;
        }
        
        public String getTitle() {
            return title;
        }
        
        public String getActor() 
        {
            return actor;
        }
        
        public int getYear() 
        {
            return year;
        }
        
        public String getGenre() 
        {
            return genre;
        }
        
        public void setTitle(String title) 
        {
            this.title = title;
        }
        
        public void setActor(String actor) 
        {
            this.actor = actor;
        }
        
        public void setYear(int year) 
        {
            this.year = year;
        }
        
        public void setGenre(String genre) 
        {
            this.genre = genre;
        }
    }
}