package database;
import java.util.ArrayList;
import Entity.*;
import src.Entity.UserRegistered;

public class controlDatabase {
    private static ControlDatabase object;

    private ArrayList<UserRegistered> list_of_users;
	private ArrayList<Announcement> list_of_announcements;
	private ArrayList<ScreeningRoom> list_of_auditoriums;
	private ArrayList<Payment> list_of_pay;
	private ArrayList<Receipt> list_of_receipts;
	private ArrayList<Movie> list_of_movies;
	private ArrayList<Showtime> list_of_showtimes;
	private ArrayList<Theatre> list_of_theatres;
	private ArrayList<UserBankInfo> list_of_banks;
	private ArrayList<Ticket> list_of_tickets;
	private BankInfo inst;

    private ControlDB() {
		setlist_of_movies(new ArrayList<Movie>());
		setlist_of_showtimes(new ArrayList<Showtime>());
		setlist_of_theatres(new ArrayList<Theatre>());
		setlist_of_tickets(new ArrayList<Ticket>());
		setlist_of_users(new ArrayList<UserRegistered>());
		setlist_of_announcements(new ArrayList<Announcement>());
		setlist_of_auditoriums(new ArrayList<ScreeningRoom>());
		setlist_of_banks(new ArrayList<UserBankInfo>());
		setlist_of_pay(new ArrayList<Payment>());
		setlist_of_receipts(new ArrayList<Receipt>());
		inst = new BankInfo("");
	}

    public static ControlDB getobject() {
		if (object == null) {
			object = new ControlDB();
		}
		return object;
	}

    public static void setobject(ControlDB object) {
		ControlDB.object = object;
	}

    //Setters & Getters
    public void setlist_of_movies(ArrayList<Movie> list_of_movies) {
        this.list_of_movies = list_of_movies;
    }
	
	public ArrayList<Movie> getlist_of_movies() {
        return list_of_movies;
    }

	public void setlist_of_showtimes(ArrayList<Showtime> list_of_showtimes) {
        this.list_of_showtimes = list_of_showtimes;
    }

	public ArrayList<Showtime> getlist_of_showtimes() {
        return list_of_showtimes;
    }

	public void setlist_of_theatres(ArrayList<Theatre> list_of_theatres) {
        this.list_of_theatres = list_of_theatres;
    }

	public ArrayList<Theatre> getlist_of_theatres() {
        return list_of_theatres;
    }

	public void setlist_of_tickets(ArrayList<Ticket> list_of_tickets) {
        this.list_of_tickets = list_of_tickets;
    }
	
	public ArrayList<Ticket> getlist_of_tickets() {
        return list_of_tickets;
    }

    public void setlist_of_users(ArrayList<UserRegistered> list_of_users) {
        this.list_of_users = list_of_users;
    }

    public ArrayList<UserRegistered> getlist_of_users() {
        return list_of_users;
    }

    public void setlist_of_announcements(ArrayList<Announcement> list_of_announcements) {
        this.list_of_announcements = list_of_announcements;
    }

    public ArrayList<Announcement> getlist_of_announcements() {
        return list_of_announcements;
    }

    public void setlist_of_auditoriums(ArrayList<ScreeningRoom> list_of_auditoriums) {
        this.list_of_auditoriums = list_of_auditoriums;
    }

    public ArrayList<ScreeningRoom> getlist_of_auditoriums() {
        return list_of_auditoriums;
    }

    public void setlist_of_banks(ArrayList<UserBankInfo> list_of_banks) {
        this.list_of_banks = list_of_banks;
    }
   
    public ArrayList<UserBankInfo> getlist_of_banks() {
        return list_of_banks;
    }
   
    public void setlist_of_pay(ArrayList<Payment> list_of_pay) {
        this.list_of_pay = list_of_pay;
    }

    public ArrayList<Payment> getlist_of_pay() {
        return list_of_pay;
    }

    public void setlist_of_receipts(ArrayList<Receipt> list_of_receipts) { this.list_of_receipts = list_of_receipts; }

    public ArrayList<Receipt> getlist_of_receipts() {
        return list_of_receipts;
    }

    public void setInst(BankInfo inst) {
        this.inst = inst;
    }

    public BankInfo getInst() {
        return inst;
    }

    public Movie findMovie(String title_of_movie) {
        if (title_of_movie == null) {
            return null;
        }
        for (int i = 0; i < list_of_movies.size(); i++) {
            if (list_of_movies.get(i).gettitle_of_movie().compareTo(title_of_movie) == 0) {
                return list_of_movies.get(i);
            }
        }
        return null;
    }


    public Theatre findTheatre(String name) {
        if (name == null) {
            return null;
        }
        for (int i = 0; i < list_of_theatres.size(); i++) {
            if (list_of_theatres.get(i).getname_of_theatre().compareTo(name) == 0) {
                return list_of_theatres.get(i);
            }
        }
        return null;
    }


    public ArrayList<Showtime> findAllShowtime(Movie searchMovie, Theatre searchTheatre) {
        if (searchTheatre == null || searchMovie == null) {
            return null;
        }
        ArrayList<Showtime> tempShowtime = new ArrayList<>();
        for (int i = 0; i < list_of_showtimes.size(); i++) {
            if (list_of_showtimes.get(i).getMovie() == searchMovie
                    && list_of_showtimes.get(i).getaudi().getTheatre() == searchTheatre) {
                tempShowtime.add(list_of_showtimes.get(i));
            }
        }
        return tempShowtime;
    }

    public Showtime findShowtime(Movie searchMovie, Theatre searchTheatre, Date date) {
        if (searchTheatre == null || searchMovie == null) {
            return null;
        }
        ArrayList<Showtime> tempShowtime = new ArrayList<>();
        for (int i = 0; i < list_of_showtimes.size(); i++) {
            if (list_of_showtimes.get(i).getMovie() == searchMovie
                    && list_of_showtimes.get(i).getaudi().getTheatre() == searchTheatre
                    && list_of_showtimes.get(i).getDate_of_show().toString().compareTo(date.toString()) == 0) {
                return list_of_showtimes.get(i);
            }
        }
        return null;
    }

    public ArrayList<Theatre> findTheatresPlayingMovie(Movie searchMovie) {
        if (searchMovie == null) {
            return null;
        }
        ArrayList<Theatre> searchList = new ArrayList<Theatre>();
        for (int i = 0; i < list_of_showtimes.size(); i++) {
            if (list_of_showtimes.get(i).getMovie() == searchMovie) {
                if (!searchList.contains(list_of_showtimes.get(i).getaudi().getTheatre())) {
                    searchList.add(list_of_showtimes.get(i).getaudi().getTheatre());
                }
            }
        }
        return searchList;
    }

    public Movie searchMovie(int id){
        for(int i = 0; i < getlist_of_movies().size(); i++){
            if(id == getlist_of_movies().get(i).getID_of_movie()){
                return getlist_of_movies().get(i);
            }
        }
        return null;
    }

    public Theatre searchTheatre(int id){
        for(int i = 0; i < getlist_of_theatres().size(); i++){
            if(id == getlist_of_theatres().get(i).getID_for_theatre()){
                return getlist_of_theatres().get(i);
            }
        }
        return null;
    }

    public ScreeningRoom searchAuditorium(int id){
        for(int i = 0; i < getlist_of_auditoriums().size(); i++){
            if(id == getlist_of_auditoriums().get(i).getAuditoriumID()){
                return getlist_of_auditoriums().get(i);
            }
        }
        return null;
    }

    public UserBankInfo searchBankingInfo(int id){
        for(int i = 0; i < getlist_of_banks().size(); i++){
            if(id == getlist_of_banks().get(i).getID_for_bank()){
                return getlist_of_banks().get(i);
            }
        }
        return null;
    }

    public Showtime searchShowtimeInfo(int id){
        for(int i = 0; i < getlist_of_showtimes().size(); i++){
            if(id == getlist_of_showtimes().get(i).getID_for_showtime()){
                return getlist_of_showtimes().get(i);
            }
        }
        return null;
    }

    //ADD FUNCTIONS
    public void addMovie(Movie movie) {
        list_of_movies.add(movie);
    }

    public void addShowtime(Showtime showtime) {
        list_of_showtimes.add(showtime);
    }

    public void addTheatre(Theatre theatre) {
        list_of_theatres.add(theatre);
    }

    public void addTicket(Ticket ticket) {
        list_of_tickets.add(ticket);
    }

    public void addUser(UserRegistered user) {
        list_of_users.add(user);
    }

    public void addAnnouncement(Announcement announcement) {
        list_of_announcements.add(announcement);
    }

    public void addAuditorium(ScreeningRoom auditorium) {
        list_of_auditoriums.add(auditorium);
    }

    public void addBankingInfo(UserBankInfo b) {
        this.list_of_banks.add(b);
    }

    public void addPayment(Payment p) {
        this.list_of_pay.add(p);
    }

    public void addReceipt(Receipt list_of_receipts) {
        this.list_of_receipts.add(list_of_receipts);
    }

    //REMOVE FUNCTIONS
    public void removeTicket(Ticket ticket) {
        list_of_tickets.remove(ticket);
    }

}
