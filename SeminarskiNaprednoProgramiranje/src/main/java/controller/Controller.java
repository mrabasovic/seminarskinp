package controller;

import java.util.ArrayList;

import db.DBBroker;
import domain.Hotel;
import domain.Klijent;
import domain.Termin;
import domain.TipPrevoza;
import domain.Vodic;


public class Controller {
	private static Controller instance;

    public Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

	public ArrayList<Hotel> vratiHotele() {
		return DBBroker.vratiHotele();
	}

	public ArrayList<Vodic> vratiVodice() {
		return DBBroker.vratiVodice();
	}

	public ArrayList<TipPrevoza> vratiPrevoz() {

		return DBBroker.vratiPrevoz();
	}

	public ArrayList<Termin> vratiTermine() {

		return DBBroker.vratiTermine();
	}

	public ArrayList<Klijent> vratiKlijente() {

		return DBBroker.vratiKlijente();
	}
}
