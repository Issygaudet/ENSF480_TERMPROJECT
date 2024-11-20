package database;

import java.io.BufferedReader;
import java.io.IOException;

import Entity.*;

import java.io.FileInputStream;

public class readDatabase {
	ControlDB database_control;
	

	public ReadDB() {
		database_control = ControlDB.getobject();
	}

	public void loadDatabase() throws IOException{
		gettingMovies();
		gettingTheatres();
		gettingBankName();
		gettingAnnouncements();
		gettingBankInfo();
		gettingUsers();
		
	}
	public void gettingUsers() throws IOException{

		FileInputStream fstream = new FileInputStream("data/registeredUser.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String line;
		String[] argList = new String[10];

		while((line = br.readLine()) != null) {
			argList = line.split(";");
			if(argList[0].compareTo("") == 0){
				break;
			}

			Date foundDate = new Date(Integer.parseInt(argList[7]),Integer.parseInt(argList[8]),Integer.parseInt(argList[9]));
			database_control.addUser(new RegisteredUser(Integer.parseInt(argList[0]),argList[1],argList[2],argList[3],argList[4],argList[5],database_control.searchBankingInfo(Integer.parseInt(argList[6])),foundDate));
		}
		fstream.close();
	}

	public void gettingMovies() throws IOException {

		FileInputStream fstream = new FileInputStream("data/movie.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String line;
		String[] argList = new String[11];

		while((line = br.readLine()) != null) {
			argList = line.split(";");
			if(argList[0].compareTo("") == 0){
				break;
			}
			database_control.addMovie(new Movie(Integer.parseInt(argList[0]),argList[1],argList[2],argList[7],Double.parseDouble(argList[8]),argList[9]));
		}
		fstream.close();
	}

	public void gettingShowtimes() throws IOException{

		FileInputStream fstream = new FileInputStream("data/show.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String line;
		String[] argList = new String[8];

		while((line = br.readLine()) != null) {
			argList = line.split(";");
			if(argList[0].compareTo("") == 0){
				break;
			}

			Movie foundMovie = database_control.searchMovie(Integer.parseInt(argList[1]));
			ScreeningRoom foundAuditorium = database_control.searchAuditorium(Integer.parseInt(argList[2]));
			Date foundDate = new Date(Integer.parseInt(argList[3]),Integer.parseInt(argList[4]),Integer.parseInt(argList[5]));
			database_control.addShowtime(new Showtime(Integer.parseInt(argList[0]),foundMovie,foundAuditorium,
					foundDate,Integer.parseInt(argList[6]),Integer.parseInt(argList[7])));
		}
		fstream.close();
	}

	public void gettingTheatres() throws IOException{

		FileInputStream fstream = new FileInputStream("data/theatre.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String line;
		String[] argList = new String[4];

		while((line = br.readLine()) != null) {
			argList = line.split(";");
			if(argList[0].compareTo("") == 0){
				break;
			}
			database_control.addTheatre(new Theatre(Integer.parseInt(argList[0]), argList[1]));
		}
		fstream.close();
		gettingAuditoriums();
	}

	public void gettingBankInfo() throws IOException{
		FileInputStream fstream = new FileInputStream("Data/Bank.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String line;
		String[] argList = new String[8];

		while((line = br.readLine()) != null) {
			argList = line.split(";");
			if(argList[0].compareTo("") == 0){
				break;
			}
			UserBankInfo temp = new UserBankInfo(Integer.parseInt(argList[0]), argList[1], argList[2]);
			database_control.addBankingInfo(temp);
			database_control.getInst().account_addition(temp);
		}
		fstream.close();

	}

	public void gettingBankName() throws IOException{
		FileInputStream fstream = new FileInputStream("Data/BankName.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String line;
		String[] argList = new String[1];

		while((line = br.readLine()) != null) {
			argList = line.split(":");
			if(argList[0].compareTo("") == 0){
				break;
			}
			database_control.setInst(new Bank(argList[0]));
		}
		fstream.close();
	}
	public void gettingAuditoriums() throws IOException {

		FileInputStream fstream = new FileInputStream("Data/ScrRm.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String line;
		String[] argList = new String[4];

		while((line = br.readLine()) != null) {
			argList = line.split(";");
			if(argList[0].compareTo("") == 0){
				break;
			}
			database_control.addAuditorium(new ScreeningRoom(Integer.parseInt(argList[0]), Integer.parseInt(argList[1]), Integer.parseInt(argList[2]), database_control.searchTheatre(Integer.parseInt(argList[3]))));
		}
		fstream.close();
		gettingShowtimes();
	}

	public void gettingAnnouncements() throws IOException{

		FileInputStream fstream = new FileInputStream("Data/Anns.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String line;
		String[] argList = new String[8];

		while((line = br.readLine()) != null) {
			argList = line.split(";");
			if(argList[0].compareTo("") == 0){
				break;
			}
			Date foundDate1 = new Date(Integer.parseInt(argList[1]),Integer.parseInt(argList[2]),Integer.parseInt(argList[3]));
			Date foundDate2 = new Date(Integer.parseInt(argList[4]),Integer.parseInt(argList[5]),Integer.parseInt(argList[6]));
			Movie foundMovie = database_control.searchMovie(Integer.parseInt(argList[7]));
			Announcement temp = new Announcement(Integer.parseInt(argList[0]),foundDate1,foundDate2,foundMovie);
			database_control.addAnnouncement(temp);
			foundMovie.setMovieAnnouncement(temp);
		}
		fstream.close();
	}

	
}

